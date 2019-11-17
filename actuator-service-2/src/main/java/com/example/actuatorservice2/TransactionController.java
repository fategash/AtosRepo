package com.example.actuatorservice2;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.actuatorservice2.Transaction;

@Controller
public class TransactionController {
	
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	TransactionService transactionService;

	@GetMapping("/search")
    @ResponseBody
    public ArrayList<Transaction> searchTransactions() {
        return transactionService.getTransactions();
    }
	
	@GetMapping("/status")
    @ResponseBody
    public Transaction status(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
        return new Transaction(counter.incrementAndGet(), "NUMEROIBANDECUENTA1111111",new Date(), new BigDecimal(3.18), new BigDecimal(1.18), "Status Transaction");
    }
	
	/*This request should be a PUT as the reference could be optional
	 * That means that the transaction on the payload could be one that already exists on the system
	 * */
	@RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public Transaction createTransaction(@RequestBody Map<String, String> body) {
		
		Long reference;
		Date date = null;
		
		if (body.containsKey("reference"))
			reference = Long.valueOf(body.get("reference"));
		else
			reference = counter.incrementAndGet();
		String account_iban = body.get("account_iban");
		if (body.containsKey("date"))
			try {
				date=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(body.get("date"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		else
			date = new Date();
		String a = body.get("amount");
		BigDecimal amount = new BigDecimal(a);
		amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal fee = new BigDecimal(1);
		fee.setScale(2, BigDecimal.ROUND_HALF_UP);
		Transaction newTransaction = new Transaction(reference,
													account_iban,
													date,
													amount,
													fee,
													"Created transaction");
		if(!balanceIsBelowZero(amount, fee, account_iban)) {
			if (body.containsKey("reference")){
			    return transactionService.updateTransaction(newTransaction);
			}
			else
				 return transactionService.createTransaction(newTransaction);
		}
		else
			return newTransaction = new Transaction(reference,
					account_iban,
					date,
					amount,
					fee,
					"Transaction failed: balance would be below zero");
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
