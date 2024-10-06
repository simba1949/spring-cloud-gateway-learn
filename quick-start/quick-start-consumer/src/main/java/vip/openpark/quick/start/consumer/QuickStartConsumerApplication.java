package vip.openpark.quick.start.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author anthony
 * @version 2024/10/6
 * @since 2024/10/6 14:02
 */
@EnableFeignClients(basePackages = "vip.openpark.gateway.api")
@EnableDiscoveryClient
@SpringBootApplication
public class QuickStartConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuickStartConsumerApplication.class, args);
	}
}