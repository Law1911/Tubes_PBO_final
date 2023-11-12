package com.cbank.Repository;

import com.cbank.Model.Account;
import com.cbank.Model.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // List<Transaction> findBySenderNameOrRecipientName(String senderName, String
    // recipientName);

    List<Transaction> findByAccount_id(int accountId);
}