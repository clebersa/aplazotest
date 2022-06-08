package com.aplazotest.simpleinterest.service;

import com.aplazotest.simpleinterest.error.InvalidArgumentException;
import com.aplazotest.simpleinterest.error.RequestValidation;
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
    public List<Payment> createPayments(SimpleInterestRequest simpleInterestRequest) throws Exception {
        log.debug("Calculating payments from service...");

        RequestValidation requestValidation = validadeSimpleInterestRequest(simpleInterestRequest);
        if (!requestValidation.isValid()) {
            throw new InvalidArgumentException(requestValidation.getErrorMessage());
        }

        List<Payment> payments = calculatePayments(simpleInterestRequest);
        log.info("Number of payments calculated: " + payments.size());

        //TODO: Store payments
        return payments;
    }

    private RequestValidation validadeSimpleInterestRequest(SimpleInterestRequest simpleInterestRequest) {
        //Validate request
        if (simpleInterestRequest == null) {
            return RequestValidation.invalid("The request is empty");
        }

        //Validate terms
        if (simpleInterestRequest.getTerms() < 4) {
            return RequestValidation.invalid("Terms must be at least 4");
        } else if (simpleInterestRequest.getTerms() > 52) {
            return RequestValidation.invalid("Terms must be up to 52");
        }

        //Validate rate
        if (simpleInterestRequest.getRate() <= 1) {
            return RequestValidation.invalid("Rate must be more than 1%");
        } else if (simpleInterestRequest.getRate() >= 100) {
            return RequestValidation.invalid("Rate must be less than 100%");
        }

        //Validate amount
        if (simpleInterestRequest.getAmount() <= 1) {
            return RequestValidation.invalid("Amount must be more than $1.00");
        } else if (simpleInterestRequest.getAmount() >= 999999) {
            return RequestValidation.invalid("Amount must be less than $999,999.00");
        }

        return RequestValidation.valid();
    }

    private List<Payment> calculatePayments(SimpleInterestRequest simpleInterestRequest) {
        List<Payment> payments = new ArrayList<>();

        if (simpleInterestRequest == null) {
            return payments;
        }

        //TODO: Calculate the payments
        payments = Arrays.asList(
                new Payment(simpleInterestRequest.getTerms(), simpleInterestRequest.getAmount(), new Date()),
                new Payment(20, simpleInterestRequest.getRate(), new Date()),
                new Payment(10, 789.12, new Date())
        );

        return payments;

    }
}
