package com.aplazotest.simpleinterest.service;

import com.aplazotest.simpleinterest.config.Config;
import com.aplazotest.simpleinterest.error.InvalidArgumentException;
import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
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
        //TODO: Create result to compare
        List<Payment> expResult = null;

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
        //TODO: Create result to compare
        List<Payment> expResult = null;

        List<Payment> result = assertDoesNotThrow(() -> simpleInterestService.createPayments(simpleInterestRequest));

        assertEquals(expResult, result);
    }

    @Test
    public void testCreatePayments_whenTermsLimitBig() {
        System.out.println("testCreatePayments_whenTermsLimitBig");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(100, 52, 10);
        //TODO: Create result to compare
        List<Payment> expResult = null;

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
        //TODO: Create result to compare
        List<Payment> expResult = null;

        List<Payment> result = assertDoesNotThrow(() -> simpleInterestService.createPayments(simpleInterestRequest));

        assertEquals(expResult, result);
    }

    @Test
    public void testCreatePayments_whenRateLimitBig() {
        System.out.println("testCreatePayments_whenTermsLimitBig");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(100, 4, 99.9);
        //TODO: Create result to compare
        List<Payment> expResult = null;

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
        //TODO: Create result to compare
        List<Payment> expResult = null;

        List<Payment> result = assertDoesNotThrow(() -> simpleInterestService.createPayments(simpleInterestRequest));

        assertEquals(expResult, result);
    }

    @Test
    public void testCreatePayments_whenAmountLimitBig() {
        System.out.println("testCreatePayments_whenTermsLimitBig");
        SimpleInterestRequest simpleInterestRequest;
        simpleInterestRequest = new SimpleInterestRequest(999998.01, 4, 10);
        //TODO: Create result to compare
        List<Payment> expResult = null;

        List<Payment> result = assertDoesNotThrow(() -> simpleInterestService.createPayments(simpleInterestRequest));

        assertEquals(expResult, result);
    }

}
