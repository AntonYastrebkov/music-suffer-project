package com.music.suffer.gateway.config;

import com.music.suffer.gateway.filter.OAuthSecurityFilter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "api")
@Setter
public class RouteConfig {
    private final OAuthSecurityFilter securityFilter;
    private List<String> allowedOrigins;
    private String userHost;
    private String adminHost;
    private String libraryHost;
    private String streamingHost;
    private String imageHost;

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                String origin = ctx.getRequest().getHeaders().get("Origin").get(0);
                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();
                headers.set("Access-Control-Allow-Origin", origin);
                headers.add("Access-Control-Allow-Methods", "*");
                headers.add("Access-Control-Max-Age", "3600");
                headers.add("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client");
                headers.add("Access-Control-Expose-Headers", "*");
                headers.add("Access-Control-Allow-Credentials", "true");
                if (request.getMethod() == HttpMethod.OPTIONS && allowedOrigins.contains(origin)) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-api", r -> r.path("/api/user/**")
                    .filters(f -> f.rewritePath("/api/user/(?<segment>.*)", "/${segment}"))
                    .uri(userHost))
                .route("library-service", r -> r.path("/api/library/**")
                        .filters(f -> f.rewritePath("/api/library/(?<segment>.*)", "/${segment}"))
                        .uri(libraryHost))
//                        .filter(securityFilter))
                .route("admin-service", r -> r.path("/api/admin/**")
                        .filters(f -> f.rewritePath("/api/admin/(?<segment>.*)", "/${segment}"))
                        .uri(adminHost))
//                        .filter(securityFilter))
                .route("streaming-service", r -> r.path("/api/streaming/**")
                        .filters(f -> f.rewritePath("/api/streaming/(?<segment>.*)", "/${segment}"))
                        .uri(streamingHost))
//                        .filter(securityFilter))
                .route("image-service", r -> r.path("/img/**")
                        .uri(imageHost))
                .build();
    }
}
