package vip.openpark.quick.start.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.openpark.gateway.api.IHelloService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Enumeration;

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

	@GetMapping("quick-start-provider/hello4GatewayFilter")
	public String hello4GatewayFilter(HttpServletRequest request, HttpServletResponse response) {
		log.info("-------------------------------------------------------");
		// 获取所有 request header 名称
		Enumeration<String> headerNames4Request = request.getHeaderNames();
		while (headerNames4Request.hasMoreElements()) {
			String headerName = headerNames4Request.nextElement();
			log.info("request headerName:{},headerValue:{}", headerName, request.getHeader(headerName));
		}

		log.info("-------------------------------------------------------");
		// 获取所有 request parameter 名称
		Enumeration<String> parameterNames4Request = request.getParameterNames();
		while (parameterNames4Request.hasMoreElements()) {
			String parameterName = parameterNames4Request.nextElement();
			log.info("request parameterName:{},parameterValue:{}", parameterName, request.getParameter(parameterName));
		}

		log.info("-------------------------------------------------------");
		// 获取所有 response header 名称
		Collection<String> headerNames4Response = response.getHeaderNames();
		for (String headerName : headerNames4Response) {
			log.info("response headerName:{},headerValue:{}", headerName, response.getHeader(headerName));
		}

		response.setHeader("X-Response-date", LocalDateTime.now().toString());
		response.setHeader("X-Response-data", "hello4GatewayFilter");

		return "hello4GatewayFilter " + LocalDateTime.now();
	}

	@GetMapping("quick-start-provider/hello4GatewayFilterPath")
	public String hello4GatewayFilterPath(HttpServletRequest request, HttpServletResponse response) {
		return "hello4GatewayFilterPath " + LocalDateTime.now();
	}

	@GetMapping("quick-start-provider/hello4GatewayFilterRedirectTo")
	public String hello4GatewayFilterRedirectTo(HttpServletRequest request, HttpServletResponse response) {
		return "hello4GatewayFilterRedirectTo " + LocalDateTime.now();
	}

	@GetMapping("condition/hello4GatewayFilterFactory")
	public String hello4GatewayFilterFactory(HttpServletRequest request, HttpServletResponse response) {
		return "hello4GatewayFilterFactory " + LocalDateTime.now();
	}
}