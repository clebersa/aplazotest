package com.aplazotest.simpleinterest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 *
 * @author cleber
 */
public class Payment {

    private int paymentNumber;
    private double amount;
    private Date paymentDate;

    public Payment(int paymentNumber, double amount, Date paymentDate) {
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
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

}
