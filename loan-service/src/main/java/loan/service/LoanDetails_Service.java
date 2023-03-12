package loan.service;

import java.util.List;
import java.util.Optional;

import loan.entity.LoanDetails;

public interface LoanDetails_Service {
	
	int applyLoan(LoanDetails details);
	
	List<LoanDetails> viewAppliedLoans();
	
	LoanDetails getDetailsByLoanId(int loanid);
	
	void verifyLoanApplication(int loanid);

}
