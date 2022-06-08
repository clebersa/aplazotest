package com.aplazotest.simpleinterest.controller;

import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import com.aplazotest.simpleinterest.service.SimpleInterestService;
import com.aplazotest.simpleinterest.config.Config;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cleber
 */
@RestController
@Component
@RequiredArgsConstructor
public class SimpleInterestController {
    
    private final SimpleInterestService simpleInterestService;

    @RequestMapping("/")
    public Collection<Payment> newPaymentCalculation(@RequestBody SimpleInterestRequest simpleInterestRequest) {
        return simpleInterestService.createPayments(simpleInterestRequest);
    }
}
