package com.example.actuatorservice2;

import java.util.ArrayList;

public interface TransactionService {
	
	 public abstract Transaction createTransaction(Transaction transaction);
	 public abstract Transaction updateTransaction(Transaction transaction);
	 public abstract ArrayList<Transaction> getTransactions();
}
