package com.example.actuatorservice2;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {

	private final long reference;
    private final String account_iban;
    private final Date date;
    private final BigDecimal amount;
    private final BigDecimal fee;
    private final String description;

    public Transaction(long reference, 
    				String account_iban, 
    				Date date, 
    				BigDecimal amount, 
    				BigDecimal fee, 
    				String description) {
        this.reference = reference;
        this.account_iban = account_iban;
        this.date = date;
        this.amount = amount;
        this.fee = fee;
        this.description = description;
    }

    public long getReference() {
        return reference;
    }

    public String getAccount_iban() {
        return account_iban;
    }

	public Date getDate() {
		return date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public String getDescription() {
		return description;
	}
    
    
	
}
