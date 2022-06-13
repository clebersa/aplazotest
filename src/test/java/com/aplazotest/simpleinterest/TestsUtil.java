package com.aplazotest.simpleinterest;

import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cleber
 */
public class TestsUtil {

    public static List<Payment> mapToPaymentsList(double[] paymentsValues, SimpleInterestRequest request) {
        final LocalDate todayDate = LocalDate.now();
        List<Payment> payments = new ArrayList<>();
        Payment payment;
        for (int i = 1; i <= paymentsValues.length; i++) {
            payment = new Payment(
                    i,
                    Double.parseDouble(Constants.DECIMAL_FORMAT.format(paymentsValues[i - 1])),
                    todayDate.plus(i, ChronoUnit.WEEKS)
            );
            payment.setId(i);
            payment.setSimpleInterestRequest(request);
            payments.add(payment);
        }
        return payments;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
