package com.cbank.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cbank.Model.Recipient;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Integer> {
    List<Recipient> findByAccount_id(int accountId);

    @Modifying
    @Query("DELETE FROM Recipient r WHERE r.id=:id")
    void deleteById(@Param("id") Integer id);
}
