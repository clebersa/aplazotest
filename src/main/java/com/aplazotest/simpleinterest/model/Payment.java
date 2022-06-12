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
import lombok.Data;
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
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @JsonProperty("payment_number")
    private int paymentNumber;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("payment_date")
    private LocalDate paymentDate;
    @ManyToOne
    @JoinColumn(name = "SIMPLE_REQUEST_ID", referencedColumnName = "ID")
    @JsonIgnore
    private SimpleInterestRequest simpleInterestRequest;

    public Payment(int paymentNumber, double amount, LocalDate paymentDate) {
        this.paymentNumber = paymentNumber;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

}
