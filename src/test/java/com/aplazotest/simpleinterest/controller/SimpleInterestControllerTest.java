package com.aplazotest.simpleinterest.controller;

import com.aplazotest.simpleinterest.TestsUtil;
import com.aplazotest.simpleinterest.error.InvalidArgumentException;
import com.aplazotest.simpleinterest.model.Payment;
import com.aplazotest.simpleinterest.model.SimpleInterestRequest;
import com.aplazotest.simpleinterest.service.SimpleInterestService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author cleber
 */
@WebMvcTest(SimpleInterestController.class)
@Slf4j
@AutoConfigureMockMvc
public class SimpleInterestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SimpleInterestService simpleInterestService;

    public SimpleInterestControllerTest() {
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

    /**
     * Test of newPaymentCalculation method, of class SimpleInterestController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testNewPaymentCalculation_noPayload() throws Exception {
        System.out.println("newPaymentCalculation");

        mvc.perform(post("/calculate-simple-interest-payments")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isBadRequest()
        );

    }

    /**
     * Test of newPaymentCalculation method, of class SimpleInterestController.
     */
    @Test
    public void testNewPaymentCalculation_invalidRequest() throws Exception {
        System.out.println("newPaymentCalculation_ok");
        String requestBody = "{\"amount\":0,\"terms\":0,\"rate\":0}";
        SimpleInterestRequest request = new SimpleInterestRequest(0, 0, 0);
        request.setId(1);

        double[] payments = new double[]{
            25.021095890411,
            25.0158219178082,
            25.0105479452055,
            25.0052739726027
        };
        List<Payment> expResult = TestsUtil.mapToPaymentsList(payments, request);

        SimpleInterestRequest requestWithoutId = new SimpleInterestRequest(0, 0, 0);
        //Don't care about the exception message at this point. To test if the exception is thrown.
        Mockito.when(simpleInterestService.createPayments(requestWithoutId)).thenThrow(new InvalidArgumentException(""));

        mvc.perform(post("/calculate-simple-interest-payments").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpectAll(
                        status().isBadRequest()
                );

    }

    /**
     * Test of newPaymentCalculation method, of class SimpleInterestController.
     */
    @Test
    public void testNewPaymentCalculation_ok() throws Exception {
        System.out.println("newPaymentCalculation_ok");
        String requestBody = "{\"amount\":100,\"terms\":4,\"rate\":1.1}";
        SimpleInterestRequest request = new SimpleInterestRequest(100, 4, 1.1);
        request.setId(1);

        double[] payments = new double[]{
            25.021095890411,
            25.0158219178082,
            25.0105479452055,
            25.0052739726027
        };
        List<Payment> expResult = TestsUtil.mapToPaymentsList(payments, request);

        SimpleInterestRequest requestWithoutId = new SimpleInterestRequest(100, 4, 1.1);
        Mockito.when(simpleInterestService.createPayments(requestWithoutId)).thenReturn(expResult);

        mvc.perform(post("/calculate-simple-interest-payments").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$", Matchers.hasSize(expResult.size())),
                        jsonPath("$[0].payment_number", CoreMatchers.is(expResult.get(0).getPaymentNumber()))
                );

    }

}
