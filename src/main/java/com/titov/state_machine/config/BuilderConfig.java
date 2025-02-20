package com.titov.state_machine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.guard.Guard;

@Configuration
@Slf4j
public class BuilderConfig {

    @Bean
    public Guard<String, String> mediumGuard() {
        return ctx -> false;
    }

    @Bean
    public Guard<String, String> highGuard() {
        return ctx -> true;
    }

    @Bean
    public Guard<String, String> isCh1() {
        return ctx -> false;
    }

    @Bean
    public Guard<String, String> isCh2() {
        return ctx -> false;
    }

    @Bean
    public Guard<String, String> isCh3() {
        return ctx -> true;
    }

    @Bean
    public Guard<String, String> isCh4() {
        return ctx -> false;
    }
}
