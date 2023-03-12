package loan.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import loan.dto.CreditScore;
import loan.repository.CreditScoreProxy;

@Component
public class CreditScoreServiceR4J { 
	@Autowired 
	CreditScoreProxy proxy;
	
	//we will use CircuitBreaker 
	@CircuitBreaker(name = "creditscore-service" , fallbackMethod = "creditScoreServiceFallBack")
	public Object getCreditScoreFromProxy(String pancard)
	{
		System.out.println("Inside Delegate class FromProxy()");
		CreditScore score = proxy.getScoreByPancard(pancard);
		return score;
	}
	
	//The below method is the Fallbackmethod.It is used to show a message when the second service server is down 
	//so that we don't see the exception.
	public Object creditScoreServiceFallBack(String pancard , Exception ex) {
		System.out.println("credit score service is down!!!... fallback route enable.... "+ex.getMessage());
		System.out.println("CIRCUIT BREAKER ENABLED!!! No response form CreditScore service at this point of time."
				+ "Service will be back shortly...."+new Date());
		CreditScore score = new CreditScore();
		return score;
	}

}
