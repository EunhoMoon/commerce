package me.janek.gateway.config;

import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpExchangeFactory {

    @Bean
    public InMemoryHttpExchangeRepository exchange() {
        return new InMemoryHttpExchangeRepository();
    }

}
