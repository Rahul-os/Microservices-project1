package loan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import loan.entity.LoanDetails;

public interface LoanDetailsRepo extends JpaRepository<LoanDetails, Integer> {

	@Query(value = "select max(loanId) from LoanDetails")
	Optional<Integer> getMaxLoanId();
	
	@Query(value = "select loan from LoanDetails loan where loan.loanId = ?1")
	LoanDetails getLoanDetailsById(int loanid);
	
}
