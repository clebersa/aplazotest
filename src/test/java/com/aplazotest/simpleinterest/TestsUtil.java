package com.aplazotest.simpleinterest;

import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines helpful methods to be used when running the tests.
 *
 * @author cleber
 */
public class TestsUtil {

    /**
     * Maps an array of payments values to Payment objects.
     *
     * @param paymentsValues The values for all the payments.
     * @param request The request associated to the payments.
     * @return The list of payments.
     */
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
}
