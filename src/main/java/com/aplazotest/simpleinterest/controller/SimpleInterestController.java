package com.aplazotest.simpleinterest.controller;

import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import com.aplazotest.simpleinterest.service.SimpleInterestService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author cleber
 */
@RestController
@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleInterestController {

    private final SimpleInterestService simpleInterestService;

    @PostMapping("/calculate-payment")
    public Collection<Payment> newPaymentCalculation(@RequestBody SimpleInterestRequest simpleInterestRequest) {
        try {
            return simpleInterestService.createPayments(simpleInterestRequest);
        } catch (Exception e) {
            log.error("Unable to process request: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
