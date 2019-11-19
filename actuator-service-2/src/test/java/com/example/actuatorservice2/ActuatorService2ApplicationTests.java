package com.example.actuatorservice2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActuatorService2ApplicationTests {

	@Autowired
	TransactionService transactionService;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void createTransactionTest(){
		
		Transaction transaction = new Transaction(123456, "1234567890", new Date(), new BigDecimal(1), new BigDecimal(1), "CUCUMBER TEST TRANSACTION");
		
		
		transactionService.createTransaction(transaction);
		
		ArrayList<Transaction> listOfTransactions;
		listOfTransactions = transactionService.getTransactions(transaction.getAccount_iban());
			Iterator<Transaction> iterator=listOfTransactions.iterator();
			boolean found=false;
			while (found == false) {
				Transaction element = null;
				if (iterator.hasNext())
					element=iterator.next();
		        if (element.getReference()==transaction.getReference()){
		        	Assert.assertEquals(element.getAccount_iban(),transaction.getAccount_iban());
		        	Assert.assertEquals(element.getDescription(),transaction.getDescription());
		        	Assert.assertEquals(element.getAmount(),transaction.getAmount());
		        	found=true;
		        } 
			}
			
		}
	}

