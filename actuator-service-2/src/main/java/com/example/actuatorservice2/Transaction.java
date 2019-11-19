package com.example.actuatorservice2;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Transaction {

	private long reference;
    private String account_iban;
    private Date date;
    private BigDecimal amount;
    private BigDecimal fee;
    private String description;
    
    private static AtomicLong counter = new AtomicLong();

    
    /*basic constructor*/
    public Transaction(long reference, 
    				String account_iban, 
    				Date date, 
    				BigDecimal amount, 
    				BigDecimal fee, 
    				String description) {
		this.date = new Date();
		this.reference = reference;
        this.account_iban = account_iban;
        this.amount = amount;
        this.fee = fee;
        this.description = description;
    }
    
    /*constructor with body parameter*/
    public Transaction (Map<String, String> body){
		
		if (body.containsKey("reference"))
			this.reference = Long.valueOf(body.get("reference"));
		else
			this.reference = counter.incrementAndGet();
		
		this.account_iban = body.get("account_iban");
		
		if (body.containsKey("date"))
			try {
				this.date=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(body.get("date"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		else
			this.date = new Date();
			
		String a = body.get("amount");
		this.amount = new BigDecimal(a);
		this.amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		if (body.containsKey("fee"))
			this.fee= new BigDecimal((body.get("fee")));
			else
			this.fee = new BigDecimal(1);
		this.fee.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		if (body.containsKey("description"))
			this.description= (body.get("description"));
			else
			this.description = "Created transaction";
    }

    /*GETTERS AND SETTERS*/
    public void setReference(long reference) {
		this.reference = reference;
	}

	public void setAccount_iban(String account_iban) {
		this.account_iban = account_iban;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public void setDescription(String description) {
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
