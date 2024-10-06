package vip.openpark.gateway.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author anthony
 * @version 2024/10/6
 * @since 2024/10/6 13:25
 */
@FeignClient(name = "quick-start-gateway")
public interface IHelloService {
	/**
	 * hello
	 *
	 * @param name String
	 * @return String
	 */
	@GetMapping("/provider/hello")
	String hello(@RequestParam("name") String name);
}