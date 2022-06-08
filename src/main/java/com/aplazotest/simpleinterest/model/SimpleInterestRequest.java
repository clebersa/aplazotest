package com.aplazotest.simpleinterest.model;

/**
 *
 * @author cleber
 */
public class SimpleInterestRequest {

    private double amount;
    private int terms;
    private double rate;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTerms() {
        return terms;
    }

    public void setTerms(int terms) {
        this.terms = terms;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}
