package com.aplazotest.simpleinterest.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class SimpleInterestRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double amount;
    private int terms;
    private double rate;
    @OneToMany(mappedBy = "simpleInterestRequest")
    private List<Payment> payments;

    public SimpleInterestRequest(double amount, int terms, double rate) {
        this.amount = amount;
        this.terms = terms;
        this.rate = rate;
    }
}
