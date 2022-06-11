package com.aplazotest.simpleinterest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author cleber
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
//@Table
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int paymentNumber;
    private double amount;
    private LocalDate paymentDate;
    @ManyToOne
    @JoinColumn(name = "SIMPLE_REQUEST_ID", referencedColumnName = "ID")
    private SimpleInterestRequest simpleInterestRequest;

    public Payment(int paymentNumber, double amount, LocalDate paymentDate) {
        this.paymentNumber = paymentNumber;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    @JsonIgnore
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @JsonIgnore
    public SimpleInterestRequest getSimpleInterestRequest() {
        return simpleInterestRequest;
    }

    public void setSimpleInterestRequest(SimpleInterestRequest simpleInterestRequest) {
        this.simpleInterestRequest = simpleInterestRequest;
    }

}
