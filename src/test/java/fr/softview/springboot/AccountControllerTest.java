package fr.softview.springboot;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.softview.springboot.controller.AccountController;
import fr.softview.springboot.exception.AccountException;
import fr.softview.springboot.exception.OperationException;
import fr.softview.springboot.model.business.Operation;
import fr.softview.springboot.model.dto.DepositDto;
import fr.softview.springboot.model.dto.OperationsDto;
import fr.softview.springboot.model.dto.WithdrawDto;
import fr.softview.springboot.service.AccountService;
import fr.softview.springboot.util.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sambakamara on 19/11/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();


    private String accountNumber = "ABF23";
    private String wrongAccountNumber = "AB11_NOT_EXIST";
    private int depositAmount = 50;
    private int withdrawAmount = 30;

    @Before
    public  void setUp() {


        Operation operationDeposit = new Operation();
        operationDeposit.setId(1);
        operationDeposit.setAccountNumber(accountNumber);
        operationDeposit.setAmount(depositAmount);
        operationDeposit.setType("DEPOSIT");
        operationDeposit.setBalance(depositAmount);
        operationDeposit.setDate("21/11/2017");

        Operation operationWithdraw = new Operation();
        operationWithdraw.setId(2);
        operationWithdraw.setAccountNumber(accountNumber);
        operationWithdraw.setAmount(withdrawAmount);
        operationWithdraw.setType("WITHDRAW");
        operationWithdraw.setBalance(20);
        operationWithdraw.setDate("21/11/2017");

        List<Operation> operations = new ArrayList<>();
        operations.add(operationDeposit);
        operations.add(operationWithdraw);

        OperationsDto operationsDto = new OperationsDto();
        operationsDto.setOperations(operations);

        //test that should be ok
        Mockito.when(accountService.deposit(accountNumber, depositAmount)).thenReturn(operationDeposit);
        Mockito.when(accountService.withdraw(accountNumber, withdrawAmount)).thenReturn(operationWithdraw);
        Mockito.when(accountService.history(accountNumber)).thenReturn(operationsDto);

        //test that should throw exception
        Mockito.when(accountService.deposit(accountNumber, -50)).thenThrow(new OperationException(Constants.ERROR_INVALID_OPERATION));
        Mockito.when(accountService.withdraw(accountNumber, -26)).thenThrow(new AccountException(Constants.ERROR_INVALID_OPERATION));

        Mockito.when(accountService.deposit(wrongAccountNumber, depositAmount)).thenThrow(new AccountException(Constants.ERROR_INVALID_ACCOUNT));
        Mockito.when(accountService.withdraw(wrongAccountNumber, depositAmount)).thenThrow(new AccountException(Constants.ERROR_INVALID_ACCOUNT));


    }

    @Test
    public void should_make_deposit() throws Exception{
        DepositDto depositDto = new DepositDto();
        depositDto.setAccountNumber(accountNumber);
        depositDto.setAmount(depositAmount);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/deposit", depositDto)
                                        .content(mapper.writeValueAsString(depositDto))
                                        .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Operation operation = mapper.readValue(result.getResponse().getContentAsString(), Operation.class);

        Assert.assertEquals(operation.getId(), 1);
        Assert.assertEquals(operation.getType(), "DEPOSIT");
        Assert.assertEquals(operation.getAmount(), 50);
        Assert.assertEquals(operation.getAccountNumber(), "ABF23");
        Assert.assertEquals(operation.getBalance(), 50);
        Assert.assertEquals(operation.getDate(), "21/11/2017");
    }

    @Test
    public void should_throw_operation_exception_on_make_deposit() throws Exception{
        DepositDto depositDto = new DepositDto();
        depositDto.setAccountNumber(accountNumber);
        depositDto.setAmount(-50);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/deposit", depositDto)
                .content(mapper.writeValueAsString(depositDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String  response = mapper.readValue(result.getResponse().getContentAsString(), String.class);

        Assert.assertEquals (HttpStatus.BAD_REQUEST.toString(), response);
    }

    @Test
    public void should_throw_account_exception_on_make_deposit() throws Exception{
        DepositDto depositDto = new DepositDto();
        depositDto.setAccountNumber(wrongAccountNumber);
        depositDto.setAmount(depositAmount);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/deposit", depositDto)
                .content(mapper.writeValueAsString(depositDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String  response = mapper.readValue(result.getResponse().getContentAsString(), String.class);

        Assert.assertEquals (HttpStatus.BAD_REQUEST.toString(), response);
    }


    @Test
    public void should_throw_operation_exception_on_make_withdraw() throws Exception{
        DepositDto depositDto = new DepositDto();
        depositDto.setAccountNumber(accountNumber);
        depositDto.setAmount(-26);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/withdraw", depositDto)
                .content(mapper.writeValueAsString(depositDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String  response = mapper.readValue(result.getResponse().getContentAsString(), String.class);

        Assert.assertEquals (HttpStatus.BAD_REQUEST.toString(), response);
    }

    @Test
    public void should_make_withdraw() throws Exception{

        WithdrawDto withdrawDto = new WithdrawDto();
        withdrawDto.setAccountNumber(accountNumber);
        withdrawDto.setAmount(withdrawAmount);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/withdraw", withdrawDto)
                                        .content(mapper.writeValueAsString(withdrawDto))
                                        .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Operation operation = mapper.readValue(result.getResponse().getContentAsString(), Operation.class);

        Assert.assertEquals(operation.getId(), 2);
        Assert.assertEquals(operation.getType(), "WITHDRAW");
        Assert.assertEquals(operation.getAmount(), 30);
        Assert.assertEquals(operation.getAccountNumber(), "ABF23");
        Assert.assertEquals(operation.getBalance(), 20);
        Assert.assertEquals(operation.getDate(), "21/11/2017");
    }

    @Test
    public void should_throw_account_exception_on_make_withdraw() throws Exception{
        DepositDto depositDto = new DepositDto();
        depositDto.setAccountNumber(wrongAccountNumber);
        depositDto.setAmount(depositAmount);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/withdraw", depositDto)
                .content(mapper.writeValueAsString(depositDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String  response = mapper.readValue(result.getResponse().getContentAsString(), String.class);

        Assert.assertEquals (HttpStatus.BAD_REQUEST.toString(), response);
    }

    @Test
    public void should_retrieve_operation_history() throws Exception{

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/history/ABF23");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        OperationsDto operationsDto = mapper.readValue(result.getResponse().getContentAsString(), OperationsDto.class);

        Operation operation = operationsDto.getOperations().get(0);

        Assert.assertEquals(operation.getId(), 1);
        Assert.assertEquals(operation.getType(), "DEPOSIT");
        Assert.assertEquals(operation.getAmount(), 50);
        Assert.assertEquals(operation.getAccountNumber(), "ABF23");
        Assert.assertEquals(operation.getBalance(), 50);
        Assert.assertEquals(operation.getDate(), "21/11/2017");

        operation = operationsDto.getOperations().get(1);

        Assert.assertEquals(operation.getId(), 2);
        Assert.assertEquals(operation.getType(), "WITHDRAW");
        Assert.assertEquals(operation.getAmount(), 30);
        Assert.assertEquals(operation.getAccountNumber(), "ABF23");
        Assert.assertEquals(operation.getBalance(), 20);
        Assert.assertEquals(operation.getDate(), "21/11/2017");
    }
}
