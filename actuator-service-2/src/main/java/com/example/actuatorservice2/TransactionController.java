package com.example.actuatorservice2;

import java.math.BigDecimal;
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
		//return transactionDatabase;
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
		
		//Iterator<Transaction> iterator=transactionDatabase.iterator();
		Long reference;
		if (body.containsKey("reference"))
			reference = Long.valueOf(body.get("reference"));
		else
			reference = counter.incrementAndGet();
		String account_iban = body.get("account_iban");
		String a = body.get("amount");
		BigDecimal amount = new BigDecimal(a);
		amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal fee = new BigDecimal(1);
		fee.setScale(2, BigDecimal.ROUND_HALF_UP);
		Transaction newTransaction = new Transaction(reference,
													account_iban,
													new Date(),
													amount,
													fee,
													"Created transaction");
		System.out.println("Veamos si la referencia es la misma");
		if (body.containsKey("reference")){
			System.out.println(body.get("reference"));
			//int i=0;
			//while (iterator.hasNext()) {
				//Transaction element=iterator.next();
		        //if (element.getReference()==newTransaction.getReference()){
		    return transactionService.updateTransaction(newTransaction);
		        	//transactionDatabase.set(i,newTransaction);
		        //}
			    //i++;   
			//}
		}
		else
			 return transactionService.createTransaction(newTransaction);
			//transactionDatabase.add(newTransaction);
		//return newTransaction;
	}
}
