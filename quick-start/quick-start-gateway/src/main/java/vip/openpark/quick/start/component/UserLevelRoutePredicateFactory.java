package vip.openpark.quick.start.component;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.NumberUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言工厂
 * 用户等级大于配置的等级，则允许访问服务
 *
 * @author anthony
 * @version 2024/10/7
 * @since 2024/10/7 8:26
 */
@Component
public class UserLevelRoutePredicateFactory extends AbstractRoutePredicateFactory<UserLevelRoutePredicateFactory.Config> {
	public static final String USER_LEVEL_KEY = "userLevel";

	/**
	 * 构造函数
	 */
	public UserLevelRoutePredicateFactory() {
		super(Config.class);
	}

	@Override
	public Predicate<ServerWebExchange> apply(Config config) {
		return new Predicate<ServerWebExchange>() {
			@Override
			public boolean test(ServerWebExchange serverWebExchange) {
				ServerHttpRequest request = serverWebExchange.getRequest();
				MultiValueMap<String, String> queryParams = request.getQueryParams();
				String userLevelStr = queryParams.getFirst(USER_LEVEL_KEY);
				if (null == userLevelStr || userLevelStr.isBlank()) {
					return false;
				}

				Integer userLevel = NumberUtils.parseNumber(userLevelStr, Integer.class);
				return userLevel >= config.getUserLevel();
			}
		};
	}

	/**
	 * 开启 Shortcut 配置模式
	 *
	 * @return List
	 */
	@Override
	public List<String> shortcutFieldOrder() {
		return Collections.singletonList(USER_LEVEL_KEY);
	}


	@Getter
	@Setter
	@Validated
	public static class Config {
		@NotNull
		private Integer userLevel;
	}
}