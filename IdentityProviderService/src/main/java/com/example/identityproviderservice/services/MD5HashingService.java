package com.example.identityproviderservice.services;

import com.example.identityproviderservice.interfaces.HashingServiceInterface;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Primary
public class MD5HashingService implements HashingServiceInterface
{
    private final MessageDigest encoder;

    @SneakyThrows
    public MD5HashingService() throws NoSuchAlgorithmException
    {
        this.encoder = MessageDigest.getInstance("MD5");
    }

    @Override
    public String encode(String password)
    {
        byte[] digest = encoder.digest(password.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(digest);
    }
}
