package vip.openpark.quick.start.component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author anthony
 * @version 2024/10/7
 * @since 2024/10/7 15:03
 */
@Slf4j
@Component
public class InterfaceEnableGatewayFilterFactory extends AbstractGatewayFilterFactory<InterfaceEnableGatewayFilterFactory.Config> {
	private static final String ENABLE_KEY = "enable";

	public InterfaceEnableGatewayFilterFactory() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return new GatewayFilter() {
			@Override
			public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
				Boolean enable = config.getEnable();
				if (enable) {
					return chain.filter(exchange);
				}

				ServerHttpResponse response = exchange.getResponse();
				response.setStatusCode(HttpStatus.BAD_REQUEST);
				return response.setComplete();
			}
		};
	}

	@Override
	public List<String> shortcutFieldOrder() {
		return List.of(ENABLE_KEY);
	}

	@Getter
	@Setter
	@Validated
	public static class Config {
		private Boolean enable; // 是否启用，true表示启用，非true表示禁用
	}
}