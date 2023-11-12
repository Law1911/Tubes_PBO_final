package com.cbank.Model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String senderName;
    private String norek;
    private String recipientName;
    private double amount;
    private String type;
    private String status;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction() {
    }

    public Transaction(String senderName, String norek, String recipientName, double amount, String type, String status,
            LocalDateTime date) {
        this.senderName = senderName;
        this.norek = norek;
        this.recipientName = recipientName;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getNorek() {
        return this.norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    public String getRecipientName() {
        return this.recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", senderName='" + getSenderName() + "'" +
            ", norek='" + getNorek() + "'" +
            ", recipientName='" + getRecipientName() + "'" +
            ", amount='" + getAmount() + "'" +
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            ", date='" + getDate() + "'" +
            ", account='" + getAccount() + "'" +
            "}";
    }


}
