package fr.softview.springboot;

import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.softview.springboot.model.business.Operation;
import fr.softview.springboot.model.entity.AccountEntity;
import fr.softview.springboot.model.entity.OperationEntity;
import fr.softview.springboot.repository.AccountRepository;
import fr.softview.springboot.repository.OperationRepository;
import fr.softview.springboot.service.AccountService;
import junit.framework.Assert;
@RunWith(SpringRunner.class)
@DataJpaTest
public class MethodsReposityTest {
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private OperationRepository operationRepository;
	
	
	@Before
	public void setUp() throws Exception {
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void accountRepisotyTest() {
		
		AccountEntity a1=new AccountEntity();
		a1.setBalance(10);
		a1.setNumber("Test001");
		accountRepository.save(a1);
		AccountEntity a2=accountRepository.findByNumber("Test001");
		//Assert.assertEquals(a2, a1);
		assertEquals(a1.getNumber(),a2.getNumber());
		
		}
	@Test
	public void operationRepisotyTest() {
		OperationEntity op1 = new OperationEntity();
		op1.setId(1);
		op1.setAccount("Test001");
		op1.setAmount(100);
		op1.setType("DEPOSIT");
		op1.setBalance(100);
		op1.setDate("09/01/2018");	
		operationRepository.save(op1);
		List<OperationEntity>lop=operationRepository.findByAccount("Test001");
		assertTrue(lop.size()>0);
		//assertTrue(lop.contains(op1));
	}


}
