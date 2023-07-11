package me.janek.user.config;

import me.janek.user.application.error.FeignErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static feign.Logger.Level;

@Configuration
public class FeignClientConfig {

    @Bean
    public Level feignLoggerLevel() {
        return Level.FULL;
    }

    @Bean
    public FeignErrorDecoder getFeignErrorDecoder() {
        return new FeignErrorDecoder();
    }

}
