package com.cbank.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String norek;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Recipient() {
    }

    public Recipient(String norek, String firstName, String lastName, Account account) {
        this.norek = norek;
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }

    public Recipient id(int id) {
        setId(id);
        return this;
    }

    public Recipient norek(String norek) {
        setNorek(norek);
        return this;
    }

    public Recipient firstName(String firstName) {
        setFirstName(firstName);
        return this;
    }

    public Recipient lastName(String lastName) {
        setLastName(lastName);
        return this;
    }

    public Recipient account(Account account) {
        setAccount(account);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", norek='" + getNorek() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", account='" + getAccount() + "'" +
                "}";
    }

    public Integer getId() {
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

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
