package vip.openpark.quick.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author anthony
 * @version 2024/10/6
 * @since 2024/10/6 13:14
 */
@EnableDiscoveryClient
@SpringBootApplication
public class QuickStartGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuickStartGatewayApplication.class, args);
	}
}