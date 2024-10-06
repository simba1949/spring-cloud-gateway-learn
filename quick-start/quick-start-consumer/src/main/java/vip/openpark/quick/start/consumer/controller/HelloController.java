package vip.openpark.quick.start.consumer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.openpark.gateway.api.IHelloService;

/**
 * @author anthony
 * @version 2024/10/6
 * @since 2024/10/6 14:03
 */
@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class HelloController {
	private final IHelloService helloService;

	@GetMapping("hello")
	public String hello(@RequestParam("name") String name) {
		return helloService.hello(name);
	}
}