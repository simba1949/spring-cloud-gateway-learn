package vip.openpark.quick.start.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局过滤器
 * 接口耗时统计
 *
 * @author anthony
 * @version 2024/10/7
 * @since 2024/10/7 14:22
 */
@Slf4j
@Component
public class InterfaceTimeConsumingGlobalFilter implements GlobalFilter, Ordered {
	private static final String START_TIME = "startTime";

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
		return chain.filter(exchange)
			.then(Mono.fromRunnable(() -> {
				Long startTime = exchange.getAttribute(START_TIME);
				if (startTime != null) {
					ServerHttpRequest request = exchange.getRequest();
					String path = request.getURI().getPath();
					log.info("接口：路径{}，耗时：{}ms", path, System.currentTimeMillis() - startTime);
				}
			}));
	}

	/**
	 * 数值越小，优先级越高
	 *
	 * @return 优先级
	 */
	@Override
	public int getOrder() {
		return 0;
	}
}