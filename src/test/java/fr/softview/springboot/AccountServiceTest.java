/**
 * 
 */
package fr.softview.springboot;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import fr.softview.springboot.model.business.Operation;
import fr.softview.springboot.model.dto.OperationsDto;
import fr.softview.springboot.repository.AccountRepository;
import fr.softview.springboot.service.AccountServiceImpl;

/**
 * @author Bessem
 *
 */
@RunWith(SpringRunner.class)
public class AccountServiceTest {
	
	
		@Mock
		private AccountRepository accountRepository;
		/*
		@Mock
	    private OperationRepository operationRepository;
		*/
		
		@MockBean
		private AccountServiceImpl accountService;
		
		private Operation operationDeposit ;
		private Operation operationWithdraw;
		private OperationsDto operationHistory ;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		operationDeposit = new Operation();
        operationDeposit.setId(1);
        operationDeposit.setAccountNumber("ABF23");
        operationDeposit.setAmount(10);
        operationDeposit.setType("DEPOSIT");
        operationDeposit.setBalance(10);
        operationDeposit.setDate("10/01/2018");
        
        operationWithdraw = new Operation();
        operationWithdraw.setId(2);
        operationWithdraw.setAccountNumber("ABF23");
        operationWithdraw.setAmount(10);
        operationWithdraw.setType("WITHDRAW");
        operationWithdraw.setBalance(20);
        operationWithdraw.setDate("10/01/2018");
        
        operationHistory=new OperationsDto();
        	
        	List<Operation> listop=new ArrayList<>();
        	listop.add(operationDeposit);
        	listop.add(operationWithdraw);
        	operationHistory.setOperations(listop);
			
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}

	/**
	 * Test method for {@link fr.softview.springboot.service.AccountServiceImpl#deposit(java.lang.String, int)}.
	 */
	@Test
	public void testDeposit() {
			
		Mockito.when(accountService.deposit("ABF23",10)).thenReturn(operationDeposit);
		Assert.assertEquals(operationDeposit,accountService.deposit("ABF23",10));
		
			
				
		
	}

	/**
	 * Test method for {@link fr.softview.springboot.service.AccountServiceImpl#withdraw(java.lang.String, int)}.
	 */
	@Test
	public void testWithdraw() {
		
		Mockito.when(accountService.withdraw("ABF23",10)).thenReturn(operationWithdraw);
		Assert.assertEquals(operationWithdraw,accountService.withdraw("ABF23",10));
		
	}

	/**
	 * Test method for {@link fr.softview.springboot.service.AccountServiceImpl#history(java.lang.String)}.
	 */
	@Test
	public void testHistory() {
		
		Mockito.when(accountService.history("ABF23")).thenReturn(operationHistory);
		Assert.assertEquals(operationHistory,accountService.history("ABF23"));
		 
	}
	
	

}
