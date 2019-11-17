package com.example.actuatorservice2;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

	private static ArrayList<Transaction> transactionDatabase = new ArrayList<Transaction>();
	@Override
	public Transaction createTransaction(Transaction transaction) {
		transactionDatabase.add(transaction);
		return transaction;
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
	public ArrayList<Transaction> getTransactions() {
		return transactionDatabase;
	}

}
