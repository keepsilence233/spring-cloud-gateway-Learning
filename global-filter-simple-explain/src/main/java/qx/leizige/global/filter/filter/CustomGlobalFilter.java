package qx.leizige.global.filter.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 当请求与路由匹配时，过滤 Web 处理程序会将 的所有实例GlobalFilter和所有特定于路由的实例添加GatewayFilter到过滤器链中。
 * 这个组合的过滤器链是按org.springframework.core.Ordered接口排序的，你可以通过实现getOrder()方法来设置。
 */
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
