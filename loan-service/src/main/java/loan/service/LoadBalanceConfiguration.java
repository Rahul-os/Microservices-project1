package loan.service;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Load balancing class
@Configuration
public class LoadBalanceConfiguration { 
	
	@Bean 
	ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
			ConfigurableApplicationContext context) {
		System.out.println("Configuring load balance to prefer same instance...");
		return ServiceInstanceListSupplier.builder() 
				.withBlockingDiscoveryClient()
				.withSameInstancePreference()
				.build(context);
	}

}
