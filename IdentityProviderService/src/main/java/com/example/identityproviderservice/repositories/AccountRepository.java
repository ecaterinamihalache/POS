package com.example.identityproviderservice.repositories;

import com.example.identityproviderservice.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>
{
    Optional<Account> findByEmailAndPassword(String email, String password);
}
