package loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.client.RestTemplate;

import loan.repository.CreditScoreProxy;

@SpringBootApplication 
@EnableFeignClients(clients = {CreditScoreProxy.class})
@EnableDiscoveryClient   //enabling connection with eureka server
public class LoanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanServiceApplication.class, args); 
		
		
	}
	@Bean
	public RestTemplate getRestTemplate() {   //1   RestTemplate is the class that we use to send the Request form loan-service
		//to credit-score to get the creditscore from it.
		return new RestTemplate();
	}

}
