package com.aplazotest.simpleinterest.service;

import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cleber
 */
@Slf4j
public class SimpleInterestServiceImpl implements SimpleInterestService {

    @Override
    public List<Payment> createPayments(SimpleInterestRequest simpleInterestRequest) {
        log.debug("Calculating payments from service...");
        List<Payment> payments = calculatePayments(simpleInterestRequest);
        log.info("Number of payments calculated: " + payments.size());

        //TODO: Store payments
        return payments;
    }

    private List<Payment> calculatePayments(SimpleInterestRequest simpleInterestRequest) {
        List<Payment> payments = new ArrayList<>();

        //TODO: Calculate the payments
        payments = Arrays.asList(
                new Payment(simpleInterestRequest.getTerms(), simpleInterestRequest.getAmount(), new Date()),
                new Payment(20, simpleInterestRequest.getRate(), new Date()),
                new Payment(10, 789.12, new Date())
        );

        return payments;

    }
}
