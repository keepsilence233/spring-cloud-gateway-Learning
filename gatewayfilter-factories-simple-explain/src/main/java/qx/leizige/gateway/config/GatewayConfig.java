package qx.leizige.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import qx.leizige.gateway.dto.Hello;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Objects;

/**
 * @author leizige
 * 2022/01/24
 */
//@Configuration
public class GatewayConfig {

    String message = "hello leizige!";
    String uri = "http://localhost:7000";

    /**
     * 使用 ModifyRequestBody 过滤器过滤器在网关向下游发送请求正文之前对其进行修改。
     */
    @Bean
    public RouteLocator modifyRequestBodyRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("rewrite_request_obj", r -> r.path("/verification/modifyRequestBody")
                        .filters(f -> f.modifyRequestBody(String.class, Hello.class, MediaType.APPLICATION_JSON_VALUE,
                                (exchange, s) -> {
                                    Hello hello = new Hello(message.toUpperCase(Locale.ROOT));
                                    return Mono.just(hello);
                                }))
                        .uri(uri)).build();
    }

    /**
     * 使用 ModifyResponseBody 过滤器在将响应正文发送回客户端之前对其进行修改。
     */
    @Bean
    public RouteLocator modifyResponseBodyRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("rewrite_response_obj", r -> r.path("/verification/modifyResponseBody")
                        .filters(f -> f.modifyResponseBody(String.class, Hello.class, (exchange, s) -> {
                            if (Objects.nonNull(s)) {
                                Hello hello = new Hello(s.toUpperCase(Locale.ROOT));
                                return Mono.just(hello);
                            }
                            return Mono.empty();
                        }))
                        .uri(uri)).build();
    }


}
