package edu.miu.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    public static final String report_url = "http://localhost:8082";
    public static final String auth_url = "http://localhost:8080/";

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("report_route", r -> r
                        .path("/api/report/**")
                        .filters(f -> f
                                .addRequestHeader("src", "wallet")
                                .rewritePath("/api/report/(?<remaining>.*)",
                                        "/api/reports/${remaining}"))
                        .uri(report_url))
                .route("existing_route", r -> r
                        .path("/**")
                        .filters(f -> f
                                .addRequestHeader("src", "wallet"))
                        .uri(auth_url))
                .build();
    }
}
