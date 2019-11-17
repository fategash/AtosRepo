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
		System.out.println("vamos a recorrer la BBDD");
		Iterator<Transaction> iterator=transactionDatabase.iterator();
//		for (Transaction t : transactionDatabase) {
//			if (t.getReference()==transaction.getReference()){
//	        	System.out.println("se mete en el if");
//	        	transactionDatabase.remove(i);
//	        	System.out.println("hace el remove");
//	        	transactionDatabase.add(transaction);
//	        	System.out.println("hace el add");
//	        }
//		}
		boolean found = false;
		while (found == false) {
			System.out.println("se mete en el while");
			Transaction element = null;
			if (iterator.hasNext())
				element=iterator.next();
			System.out.println("hace el iterator.next");
	        if (element.getReference()==transaction.getReference()){
	        	System.out.println("se mete en el if");
	        	transactionDatabase.remove(i);
	        	System.out.println("hace el remove");
	        	transactionDatabase.add(transaction);
	        	System.out.println("hace el add");
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
