package com.example.gatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {

    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

            // PRE Filter
            GatewayFilter filter = new OrderedGatewayFilter((exchange,chain) -> {
            ServerHttpRequest request=exchange.getRequest();
            ServerHttpResponse response=exchange.getResponse();

            log.info("Logging Filter baseMessage : {}", config.getBaseMessage());
            if (config.isPreLogger()){
                log.info("Logging PRE Start: request id -> {}", request.getId());

            }
            // Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(()-> {

                if (config.isPreLogger()) {
                    log.info("Logging POST Filter End: response code -> {}", response.getStatusCode());
                }

            }));
            }, Ordered.HIGHEST_PRECEDENCE); //실행 순서를 가장 먼저 실행 되도록 설정
        return filter;
    }



    @Data
    public static class Config {
        //Put the Config
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;

    }




}
