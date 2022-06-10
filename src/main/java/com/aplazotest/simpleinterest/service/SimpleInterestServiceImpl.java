package com.aplazotest.simpleinterest.service;

import com.aplazotest.simpleinterest.Constants;
import com.aplazotest.simpleinterest.error.InvalidArgumentException;
import com.aplazotest.simpleinterest.error.RequestValidation;
import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    /**
     * Calculates the payment for a simple interest request. Since I am not sure
     * about the business logic to implement this, I followed the logic below.
     * Every week, the payment will be composed of a fixed amount (fixedPay)
     * plus the interest on the remaining balance (principalRemaining). The
     * fixedPay is equals to the requested amount divided by the number of
     * terms. The principalRemaining for each week is equals to:<br/>
     * <ul>
     * <li>amount - (0 * amount / terms) in the first payment </li>
     * <li>amount - (1 * amount / terms) in the second payment </li>
     * <li>amount - (3 * amount / terms) in the third payment </li>
     * </ul>
     * and so on until the last payment. The rate on a weekly payment base is
     * the given year rate (from the request) divided by 365 (days in a year)
     * times 7 (days in a week). I used these links as references:<br/>
     * <ul>
     * <li>
     * <a href="https://www.bankrate.com/loans/personal-loans/how-to-calculate-loan-interest/">https://www.bankrate.com/loans/personal-loans/how-to-calculate-loan-interest/</a>
     * </li>
     * <li>
     * <a href="https://www.mainstreetcu.org/calculator/complex-loan">https://www.mainstreetcu.org/calculator/complex-loan</a>
     * </li>
     * </ul>
     *
     * @param simpleInterestRequest The object containing the simple interest
     * input data to calculate the payments.
     * @return List of payments calculated from the simple interest input data.
     */
    private List<Payment> calculatePayments(SimpleInterestRequest simpleInterestRequest) {
        List<Payment> payments = new ArrayList<>();
        double termPay;
        double principalRemaining = simpleInterestRequest.getAmount();
        final double fixedPay = principalRemaining / simpleInterestRequest.getTerms();
        final double weeklyRate = simpleInterestRequest.getRate() * 7 / 365 / 100;
        final LocalDate todayDate = LocalDate.now();

        for (int i = 1; i <= simpleInterestRequest.getTerms(); i++) {
            termPay = fixedPay + principalRemaining * weeklyRate;
            payments.add(new Payment(
                    i,
                    Double.parseDouble(Constants.DECIMAL_FORMAT.format(termPay)),
                    todayDate.plus(i, ChronoUnit.WEEKS)
            ));
            principalRemaining = fixedPay * (simpleInterestRequest.getTerms() - i);
        }

        return payments;

    }
}
