package com.example.asg02.model;

import java.util.Date;

public class Payment {
    private double amount;
    private Date createOn;
    private String paymentStatus;
    private int transactionId;
    private int cardId;

    public Payment(double amount, Date createOn, String paymentStatus, int transactionId, int cardId) {
        this.amount = amount;
        this.createOn = createOn;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
        this.cardId = cardId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
