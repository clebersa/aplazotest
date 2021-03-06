package com.aplazotest.simpleinterest.service;

import com.aplazotest.simpleinterest.Constants;
import com.aplazotest.simpleinterest.error.InvalidArgumentException;
import com.aplazotest.simpleinterest.error.RequestValidation;
import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import com.aplazotest.simpleinterest.repository.PaymentRepository;
import com.aplazotest.simpleinterest.repository.SimpleInterestRequestRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * This class implements all the operations defined in the SimpleInterestService
 * interface.
 *
 * @author cleber
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class SimpleInterestServiceImpl implements SimpleInterestService {

    private final PaymentRepository paymentRepository;
    private final SimpleInterestRequestRepository simpleInterestRequestRepository;

    @Override
    @Transactional
    public List<Payment> createPayments(SimpleInterestRequest simpleInterestRequest) throws InvalidArgumentException {
        log.debug("Calculating payments from service...");

        RequestValidation requestValidation = validadeSimpleInterestRequest(simpleInterestRequest);
        if (!requestValidation.isValid()) {
            throw new InvalidArgumentException(requestValidation.getErrorMessage());
        }

        simpleInterestRequestRepository.save(simpleInterestRequest);

        List<Payment> payments = calculatePayments(simpleInterestRequest);
        log.info("Number of payments calculated: " + payments.size());

        paymentRepository.saveAll(payments);
        return payments;
    }

    /**
     * Validates a simple interest request. This method verifies if the request
     * attributes are within the accepted thresholds for the request.
     *
     * @param simpleInterestRequest
     * @return The result of the request validation.
     */
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
        Payment payment;
        for (int i = 1; i <= simpleInterestRequest.getTerms(); i++) {
            termPay = fixedPay + principalRemaining * weeklyRate;
            payment = new Payment(
                    i,
                    Double.parseDouble(Constants.DECIMAL_FORMAT.format(termPay)),
                    todayDate.plus(i, ChronoUnit.WEEKS)
            );
            payment.setSimpleInterestRequest(simpleInterestRequest);
            payments.add(payment);
            principalRemaining = fixedPay * (simpleInterestRequest.getTerms() - i);
        }

        return payments;

    }
}
