package com.example.identityproviderservice.interfaces;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface JwtParserServiceInterface<T>
{
    Jws<Claims> validateSignatureAndGetTokenParser(String token, T templateKey);
    boolean isTokenValid(String token, T templateKey);
}
