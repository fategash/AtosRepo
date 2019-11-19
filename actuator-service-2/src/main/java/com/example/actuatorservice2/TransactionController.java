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
	
	//private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	TransactionService transactionService;

	
	/*This request should be a PUT as the reference could be optional
	 * That means that the transaction on the payload could be one that already exists on the system
	 * */
	@RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public Transaction createTransaction(@RequestBody Map<String, String> body) {

		Transaction newTransaction = new Transaction(body);
		
			if (body.containsKey("reference")){
			    return transactionService.updateTransaction(newTransaction);
			}
			else
				 return transactionService.createTransaction(newTransaction);

	}
	
	@GetMapping("/search")
    @ResponseBody
    public ArrayList<Transaction> searchTransactions(@RequestParam (name="account", required=true) String account_iban) {
        return transactionService.getTransactions(account_iban);
    }
	
//	@GetMapping("/status")
//    @ResponseBody
//    public Transaction status() {
//        return new Transaction();
//    }
	
	

}
