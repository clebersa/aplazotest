package com.aplazotest.simpleinterest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * @author cleber
 */
@ToString
@EqualsAndHashCode
public class Payment {

    private int paymentNumber;
    private double amount;
    private LocalDate paymentDate;

    public Payment(int paymentNumber, double amount, LocalDate paymentDate) {
        this.paymentNumber = paymentNumber;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    @JsonProperty("payment_number")
    public int getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(int paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    @JsonProperty("amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @JsonProperty("payment_date")
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

}
