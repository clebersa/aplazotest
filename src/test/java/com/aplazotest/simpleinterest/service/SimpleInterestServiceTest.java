package com.aplazotest.simpleinterest.service;

import com.aplazotest.simpleinterest.Constants;
import com.aplazotest.simpleinterest.config.Config;
import com.aplazotest.simpleinterest.error.InvalidArgumentException;
import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author cleber
 */
public class SimpleInterestServiceTest {

    private final SimpleInterestService simpleInterestService;

    public SimpleInterestServiceTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        simpleInterestService = context.getBean(SimpleInterestService.class);
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    private List<Payment> mapToPaymentsList(double[] paymentsValues) {
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
            payments.add(payment);
        }
        return payments;
    }

    @Test
    public void testCreatePayments_whenRequestHaveNoBody() {
        System.out.println("testCreatePayments_whenRequestHaveNoBody");
        SimpleInterestRequest simpleInterestRequest = null;

        InvalidArgumentException thrown = assertThrows(InvalidArgumentException.class, () -> simpleInterestService.createPayments(simpleInterestRequest));
        assertEquals("The request is empty", thrown.getMessage());
    }

    @Test
    public void testCreatePayments_whenRequestOk() {
        System.out.println("testCreatePayments_whenTermsValid");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(100, 10, 10);
        double[] payments = new double[]{
            10.1917808219178,
            10.172602739726,
            10.1534246575342,
            10.1342465753425,
            10.1150684931507,
            10.0958904109589,
            10.0767123287671,
            10.0575342465753,
            10.0383561643836,
            10.0191780821918
        };
        List<Payment> expResult = mapToPaymentsList(payments);

        List<Payment> result = assertDoesNotThrow(() -> simpleInterestService.createPayments(simpleInterestRequest));

        assertEquals(expResult, result);
    }

    @Test
    public void testCreatePayments_whenTermsTooSmall() {
        System.out.println("testCreatePayments_whenTermsTooSmall");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(100, 3, 10);

        InvalidArgumentException thrown = assertThrows(InvalidArgumentException.class, () -> simpleInterestService.createPayments(simpleInterestRequest));
        assertEquals("Terms must be at least 4", thrown.getMessage());
    }

    @Test
    public void testCreatePayments_whenTermsTooBig() {
        System.out.println("testCreatePayments_whenTermsTooBig");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(100, 53, 10);

        InvalidArgumentException thrown = assertThrows(InvalidArgumentException.class, () -> simpleInterestService.createPayments(simpleInterestRequest));
        assertEquals("Terms must be up to 52", thrown.getMessage());
    }

    @Test
    public void testCreatePayments_whenTermsLimitSmall() {
        System.out.println("testCreatePayments_whenTermsLimitSmall");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(100, 4, 10);
        double[] payments = new double[]{
            25.1917808219178,
            25.1438356164384,
            25.0958904109589,
            25.0479452054795
        };
        List<Payment> expResult = mapToPaymentsList(payments);

        List<Payment> result = assertDoesNotThrow(() -> simpleInterestService.createPayments(simpleInterestRequest));

        assertEquals(expResult, result);
    }

    @Test
    public void testCreatePayments_whenTermsLimitBig() {
        System.out.println("testCreatePayments_whenTermsLimitBig");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(100, 52, 10);
        double[] payments = new double[]{
            2.11485774499473,
            2.11116965226554,
            2.10748155953635,
            2.10379346680717,
            2.10010537407798,
            2.09641728134879,
            2.0927291886196,
            2.08904109589041,
            2.08535300316122,
            2.08166491043203,
            2.07797681770284,
            2.07428872497366,
            2.07060063224447,
            2.06691253951528,
            2.06322444678609,
            2.0595363540569,
            2.05584826132771,
            2.05216016859852,
            2.04847207586934,
            2.04478398314015,
            2.04109589041096,
            2.03740779768177,
            2.03371970495258,
            2.03003161222339,
            2.0263435194942,
            2.02265542676502,
            2.01896733403583,
            2.01527924130664,
            2.01159114857745,
            2.00790305584826,
            2.00421496311907,
            2.00052687038988,
            1.9968387776607,
            1.99315068493151,
            1.98946259220232,
            1.98577449947313,
            1.98208640674394,
            1.97839831401475,
            1.97471022128556,
            1.97102212855638,
            1.96733403582719,
            1.963645943098,
            1.95995785036881,
            1.95626975763962,
            1.95258166491043,
            1.94889357218124,
            1.94520547945205,
            1.94151738672287,
            1.93782929399368,
            1.93414120126449,
            1.9304531085353,
            1.92676501580611
        };
        List<Payment> expResult = mapToPaymentsList(payments);

        List<Payment> result = assertDoesNotThrow(() -> simpleInterestService.createPayments(simpleInterestRequest));

        assertEquals(expResult, result);
    }

    @Test
    public void testCreatePayments_whenRateTooSmall() {
        System.out.println("testCreatePayments_whenTermsTooSmall");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(100, 4, 1);

        InvalidArgumentException thrown = assertThrows(InvalidArgumentException.class, () -> simpleInterestService.createPayments(simpleInterestRequest));
        assertEquals("Rate must be more than 1%", thrown.getMessage());
    }

    @Test
    public void testCreatePayments_whenRateTooBig() {
        System.out.println("testCreatePayments_whenTermsTooBig");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(100, 4, 100);

        InvalidArgumentException thrown = assertThrows(InvalidArgumentException.class, () -> simpleInterestService.createPayments(simpleInterestRequest));
        assertEquals("Rate must be less than 100%", thrown.getMessage());
    }

    @Test
    public void testCreatePayments_whenRateLimitSmall() {
        System.out.println("testCreatePayments_whenTermsLimitSmall");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(100, 4, 1.1);
        double[] payments = new double[]{
            25.021095890411,
            25.0158219178082,
            25.0105479452055,
            25.0052739726027
        };
        List<Payment> expResult = mapToPaymentsList(payments);

        List<Payment> result = assertDoesNotThrow(() -> simpleInterestService.createPayments(simpleInterestRequest));

        assertEquals(expResult, result);
    }

    @Test
    public void testCreatePayments_whenRateLimitBig() {
        System.out.println("testCreatePayments_whenTermsLimitBig");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(100, 4, 99.9);
        double[] payments = new double[]{
            26.9158904109589,
            26.4369178082192,
            25.9579452054794,
            25.4789726027397
        };
        List<Payment> expResult = mapToPaymentsList(payments);

        List<Payment> result = assertDoesNotThrow(() -> simpleInterestService.createPayments(simpleInterestRequest));

        assertEquals(expResult, result);
    }

    @Test
    public void testCreatePayments_whenAmountTooSmall() {
        System.out.println("testCreatePayments_whenTermsTooSmall");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(1, 4, 10);

        InvalidArgumentException thrown = assertThrows(InvalidArgumentException.class, () -> simpleInterestService.createPayments(simpleInterestRequest));
        assertEquals("Amount must be more than $1.00", thrown.getMessage());
    }

    @Test
    public void testCreatePayments_whenAmountTooBig() {
        System.out.println("testCreatePayments_whenTermsTooBig");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(999999, 4, 10);

        InvalidArgumentException thrown = assertThrows(InvalidArgumentException.class, () -> simpleInterestService.createPayments(simpleInterestRequest));
        assertEquals("Amount must be less than $999,999.00", thrown.getMessage());
    }

    @Test
    public void testCreatePayments_whenAmountLimitSmall() {
        System.out.println("testCreatePayments_whenTermsLimitSmall");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(1.01, 4, 10);
        double[] payments = new double[]{
            0.25443698630137,
            0.253952739726027,
            0.253468493150685,
            0.252984246575342
        };
        List<Payment> expResult = mapToPaymentsList(payments);

        List<Payment> result = assertDoesNotThrow(() -> simpleInterestService.createPayments(simpleInterestRequest));

        assertEquals(expResult, result);
    }

    @Test
    public void testCreatePayments_whenAmountLimitBig() {
        System.out.println("testCreatePayments_whenTermsLimitBig");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(999998.01, 4, 10);
        double[] payments = new double[]{
            251917.30690274,
            251437.855802055,
            250958.40470137,
            250478.953600685
        };
        List<Payment> expResult = mapToPaymentsList(payments);

        List<Payment> result = assertDoesNotThrow(() -> simpleInterestService.createPayments(simpleInterestRequest));

        assertEquals(expResult, result);
    }

}
