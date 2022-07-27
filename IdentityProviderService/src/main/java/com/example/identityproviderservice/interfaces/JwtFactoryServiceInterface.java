package com.example.identityproviderservice.interfaces;

import java.util.Map;

public interface JwtFactoryServiceInterface<T>
{
    String generateToken(Map<String, Object> claims, T templateKey);
}
