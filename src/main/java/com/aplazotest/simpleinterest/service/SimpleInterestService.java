package com.aplazotest.simpleinterest.service;

import com.aplazotest.simpleinterest.error.InvalidArgumentException;
import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import java.util.List;

/**
 * Interface defining all available operations for simple interest service.
 *
 * @author cleber
 */
public interface SimpleInterestService {

    /**
     * Creates and stores a list of payments based on a simple interest request.
     *
     * @param simpleInterestRequest
     * @return The list of calculated payments
     * @throws InvalidArgumentException If the request is invalid.
     */
    public List<Payment> createPayments(SimpleInterestRequest simpleInterestRequest) throws InvalidArgumentException;
}
