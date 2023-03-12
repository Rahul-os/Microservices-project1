package loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import loan.entity.LoanDetails;
import loan.service.LoanDetails_Service;

//@CrossOrigin("http://localhost:8181/")
@RestController
@RequestMapping("/loandetails")
public class LoanDetailsController { 
	
	@Autowired 
	LoanDetails_Service service;
	
	@PostMapping 
	public ResponseEntity<String> applyLoan(@RequestBody LoanDetails details){
		int id = service.applyLoan(details);
		return new ResponseEntity<String>("applied successfully loan id"+id,HttpStatus.OK);
	}
	
	@GetMapping("/{loanid}")
	public ResponseEntity<LoanDetails> getDetailsById(@PathVariable("loanid") int loanid){
		LoanDetails details = service.getDetailsByLoanId(loanid);
		return new ResponseEntity<LoanDetails>(details,HttpStatus.OK);
	}
	
	@GetMapping("/verify/{loanid}")
	public ResponseEntity<String> verifyLoan(@PathVariable("loanid") int loanid){
		service.verifyLoanApplication(loanid);
		return new ResponseEntity<String>("Verified",HttpStatus.OK);
	}
	

}
