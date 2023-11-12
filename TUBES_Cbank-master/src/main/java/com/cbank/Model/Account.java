package com.cbank.Model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Account implements Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String norek;
    private double balance;

    private String firstName;
    private String lastName;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "account")
    private List<Transaction> transactions;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "account")
    private List<Recipient> recipients;

    @Transient
    private static final String BANK_CODE = "CBANK";

    @Transient
    private static final int ACCOUNT_NUMBER_LENGTH = 10;

    public Account() {
    }

    public Account(String firstName, String lastName) {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder(BANK_CODE);

        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH - BANK_CODE.length(); i++) {
            int digit = random.nextInt(10);
            accountNumber.append(digit);
        }

        this.firstName = firstName;
        this.lastName = lastName;

        norek = accountNumber.toString();
        balance = 0;
    }

    public Account(double balance, String firstName, String lastName) {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder(BANK_CODE);

        for (int i = 0; i < ACCOUNT_NUMBER_LENGTH - BANK_CODE.length(); i++) {
            int digit = random.nextInt(10);
            accountNumber.append(digit);
        }

        this.firstName = firstName;
        this.lastName = lastName;
        norek = accountNumber.toString();
        this.balance = balance;
    }

    public List<Recipient> getRecipients() {
        return this.recipients;
    }

    public void setRecipients(Recipient recipients) {
        this.recipients.add(recipients);
    }

    public void resetRecipients() {
        this.recipients = new ArrayList<Recipient>();
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNorek() {
        return this.norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Account transfer(double amount, Account accountRecipient) {
        if (balance >= amount) {
            balance -= amount;
            accountRecipient.deposit(amount);
            Transaction transaction = new Transaction(this.getFirstName() + " " + this.getLastName(), this.getNorek(),
                    accountRecipient.getFirstName() + " " + accountRecipient.getLastName(), amount, "Transfer",
                    "Exspense", LocalDateTime.now());

            transaction.setAccount(this);
            this.setTransactions(transaction);

            transaction = new Transaction(this.getFirstName() + " " + this.getLastName(), this.getNorek(),
                    accountRecipient.getFirstName() + " " + accountRecipient.getLastName(), amount, "Transfer",
                    "Income", LocalDateTime.now());
            transaction.setAccount(accountRecipient);
            accountRecipient.setTransactions(transaction);
            return accountRecipient;
        } else {
            return null;
        }
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new RuntimeException("Insufficient funds");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void resetTransactions() {
        this.transactions = new ArrayList<>();
    }

}
