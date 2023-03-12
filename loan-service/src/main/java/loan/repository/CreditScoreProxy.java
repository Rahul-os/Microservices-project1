package loan.repository;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import loan.dto.CreditScore;
import loan.service.LoadBalanceConfiguration;

//@FeignClient(name = "creditscore-service" , url = "http://localhost:8182/credit/creditscore") 
@FeignClient("creditscore-service")
@LoadBalancerClient(name = "creditscore-service" , configuration = LoadBalanceConfiguration.class)  //added while using loadbalancing

public interface CreditScoreProxy { 
	@GetMapping("/credit/creditscore/score/{pancard}")
	CreditScore getScoreByPancard(@PathVariable("pancard") String pancard);

}
