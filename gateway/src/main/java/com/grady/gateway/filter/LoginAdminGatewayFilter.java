package com.grady.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @Author Grady
 * @Date 2020/7/29 0:26
 * @Version 1.0
 */
@Component
public class LoginAdminGatewayFilter implements GatewayFilter, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(LoginAdminGatewayFilter.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        //请求地址中不包含admin，不是控台请求，不需要拦截
        if (!path.contains("/admin/")){
            return chain.filter(exchange);
        }

        if (path.contains("/system/admin/user/login") || path.contains("/system/admin/user/logout") || path.contains("/system/admin/kaptcha")){
            LOG.info("不需要控台验证:{}",path);
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("token");
        LOG.info("后台登录验证开始,token:{}",token);
        if (token ==null || token.isEmpty()){
            LOG.info("没有token，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        Object o = redisTemplate.opsForValue().get("token");
        LOG.info("redis中的token:{}", o);
        if (o == null){
            LOG.warn("token无效，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }else {
            LOG.info("已登录{}",o);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder()
    {
        return 1;
    }
}
