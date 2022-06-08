package com.aplazotest.simpleinterest.config;

import com.aplazotest.simpleinterest.service.SimpleInterestService;
import com.aplazotest.simpleinterest.service.SimpleInterestServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author cleber
 */
@Configuration
@ComponentScan("com.aplazotest.simpleinterest")
public class Config {
    @Bean
    public SimpleInterestService simpleInterestService(){
        return new SimpleInterestServiceImpl();
    }
}
