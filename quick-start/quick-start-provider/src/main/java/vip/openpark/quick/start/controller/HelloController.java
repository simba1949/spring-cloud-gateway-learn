package vip.openpark.quick.start.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.openpark.gateway.api.IHelloService;

/**
 * @author anthony
 * @version 2024/10/6
 * @since 2024/10/6 13:58
 */
@Slf4j
@RestController
@RequestMapping
public class HelloController implements IHelloService {
	@Override
	@GetMapping("hello")
	public String hello(@RequestParam(name = "name", required = false) String name) {
		return "hello " + name;
	}
}