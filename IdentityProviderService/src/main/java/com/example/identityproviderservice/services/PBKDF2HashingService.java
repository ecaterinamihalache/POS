package com.example.identityproviderservice.services;

import com.example.identityproviderservice.interfaces.HashingServiceInterface;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PBKDF2HashingService implements HashingServiceInterface
{
    private final Pbkdf2PasswordEncoder encoder;

    public PBKDF2HashingService() {
        this.encoder = new Pbkdf2PasswordEncoder();
    }

    @Override
    public String encode(String password) {
        return encoder.encode(password);
    }
}

