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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Controller for simple interest operations.
 *
 * @author cleber
 */
@RestController
@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleInterestController {

    private final SimpleInterestService simpleInterestService;

    /**
     * Calculates the payments for a simple interest request.
     *
     * @param simpleInterestRequest
     * @return A list of payments.
     */
    @PostMapping("/calculate-simple-interest-payments")
    public Collection<Payment> newPaymentCalculation(@RequestBody SimpleInterestRequest simpleInterestRequest) {
        try {
            return simpleInterestService.createPayments(simpleInterestRequest);
        } catch (Exception e) {
            log.error("Unable to process request: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
