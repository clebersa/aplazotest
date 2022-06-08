package com.aplazotest.simpleinterest.service;

import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import java.util.List;

/**
 *
 * @author cleber
 */
public interface SimpleInterestService {

    public List<Payment> createPayments(SimpleInterestRequest simpleInterestRequest);
}
