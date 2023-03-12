package loan.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import loan.dto.CreditScore;
import loan.entity.LoanDetails;
import loan.exception.ApplicationNotFoundException;
import loan.exception.PanCardNotAvailableException;
import loan.exception.ServerNotFoundException;
import loan.repository.CreditScoreProxy;
import loan.repository.LoanDetailsRepo;

@Service
public class LoanDetails_ServiceImpl implements LoanDetails_Service { 
	
	private int loanId = 1001;
	@Autowired 
	LoanDetailsRepo repo; 
	
	@Autowired     //establish a connection b/w two of them microservice.
	RestTemplate restTemplate;
	
	@Autowired 
	CreditScoreProxy  proxy;
	
	@Autowired 
	CreditScoreServiceR4J r4j;

	@Override
	public int applyLoan(LoanDetails details) {
		// TODO Auto-generated method stub
		Optional<Integer> id = repo.getMaxLoanId();
		if(id.isEmpty())
			details.setLoanId(loanId);
		else {
			loanId = id.get().intValue();
			loanId++;
			details.setLoanId(loanId);
		}
		details.setDateApplied(new Date());
		repo.save(details);
		return details.getLoanId();
	}

	@Override
	public List<LoanDetails> viewAppliedLoans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoanDetails getDetailsByLoanId(int loanid) {
		// TODO Auto-generated method stub
		return repo.getLoanDetailsById(loanid);
	}

	@Override
	public void verifyLoanApplication(int loanid) {
		//call creditscore -service to get the CreditScore object by passing pancard no as
		//paremeter using RestTemplate 
		
		/*LoanDetails loan = repo.getLoanDetailsById(loanid);
		if(loan == null) {
			throw new  ApplicationNotFoundException();
		}
		else
		
		{
		String pan = loan.getPancard();
		String url = "http://localhost:8182/credit/creditscore/score/"+pan;
		CreditScore score = restTemplate.getForObject(url, CreditScore.class);
		System.out.println(score);
		loan.setCreditScore(score.getCreditScore());
		if(score.getCreditScore() >= 700)
			loan.setLoanStatus("Approved");
		else {
			loan.setLoanStatus("Rejected");
			loan.setComments("Less Credit Score");
		}
		repo.save(loan);   
			}//save method cannot be used with Optional<>
		*/
		
		//By using feign client.Only 2 lines will change from above commented code.
		/*LoanDetails loan = repo.getLoanDetailsById(loanid);
		
		if(loan == null) {
			throw new  ApplicationNotFoundException();
		}
		
		String pan = loan.getPancard();
		CreditScore score = proxy.getScoreByPancard(pan);
		System.out.println(score);
		
		loan.setCreditScore(score.getCreditScore());
		if(score.getCreditScore() >= 700)
			loan.setLoanStatus("Approved");
		else {
			loan.setLoanStatus("Rejected");
			loan.setComments("Less Credit Score");
		}
		repo.save(loan);  
		
		
	} 
	*/
		LoanDetails loan = repo.getLoanDetailsById(loanid);
		if(loan == null) {
			throw new  ApplicationNotFoundException();
		}
	
		String pan = loan.getPancard();
		CreditScore score = (CreditScore) r4j.getCreditScoreFromProxy(pan);
		System.out.println(score);
		//now if the server is down we have to throw the exception that the server is down instead of showing that 
		//it is verified.
		if(score==null)
		{
			throw new PanCardNotAvailableException(); //if this is not done null object is returned 
		}
		else if(score.getCreditScore() == 0) {
			loan.setLoanStatus("");
			loan.setComments("");
			throw new ServerNotFoundException();
		}
		else {
			
		
			loan.setCreditScore(score.getCreditScore());
			if(score.getCreditScore() >= 700)
				loan.setLoanStatus("Approved");
			else {
				loan.setLoanStatus("Rejected");
				loan.setComments("Less Credit Score");
			}
			repo.save(loan);  
		}
}
	
	

}
