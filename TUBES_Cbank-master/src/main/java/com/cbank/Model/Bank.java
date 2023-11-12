package com.cbank.Model;

public abstract interface Bank {

    public abstract Account transfer(double amount, Account account);

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract double getBalance();
}
