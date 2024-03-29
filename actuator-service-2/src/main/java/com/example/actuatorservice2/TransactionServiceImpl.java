package com.example.actuatorservice2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

	private static ArrayList<Transaction> transactionDatabase = new ArrayList<Transaction>();
	
	
	@Override
	public Transaction createTransaction(Transaction transaction) {
		if(!balanceIsBelowZero(transaction.getAmount(),transaction.getFee(),transaction.getAccount_iban())) {
			transactionDatabase.add(transaction);
			return transaction;
		}
		else {
			transaction.setDescription("ERROR: this transaction would leave balance below zero");
			return transaction;
		}
			
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		int i = 0;
		Iterator<Transaction> iterator=transactionDatabase.iterator();
		boolean found = false;
		while (found == false) {
			Transaction element = null;
			if (iterator.hasNext())
				element=iterator.next();
	        if (element.getReference()==transaction.getReference()){
	        	transactionDatabase.remove(i);
	        	transactionDatabase.add(transaction);
	        	found=true;
	        }
		    i++; 
		}
		return transaction;
	}

	@Override
	public ArrayList<Transaction> getTransactions(String account) {
		ArrayList<Transaction> resultList = new ArrayList<Transaction>();
		Iterator<Transaction> iterator=transactionDatabase.iterator();
		while (iterator.hasNext()) {
			Transaction element = null;
			if (iterator.hasNext())
				element=iterator.next();
	        if (element.getAccount_iban().equalsIgnoreCase(account)){
	        	resultList.add(element);
	        } 
		}
		return resultList;
	}
	
	public boolean balanceIsBelowZero (BigDecimal amount, BigDecimal fee, String account_iban) {
		/*
		 * Here we assume we use another service like balanceService
		 * which access the balance and assures if balance + amount + fee is below 
		 * zero or not
		 */
		return false;
	}

}
