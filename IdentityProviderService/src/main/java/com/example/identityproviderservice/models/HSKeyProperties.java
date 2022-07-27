package com.example.identityproviderservice.models;

import com.example.identityproviderservice.interfaces.FileIOManagerServiceInterface;
import com.example.identityproviderservice.services.FileIOManagerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

@Component
@ConfigurationProperties(prefix = "key.set")
public class HSKeyProperties
{
    @Value("${secret.key.file}")
    private String SECRET_KEY_FILE;

    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    @PostConstruct
    private void createRSAKey() throws Exception {
        FileIOManagerServiceInterface fileIOManagerService = new FileIOManagerService();

        // Reading secret key from file
        byte[] keyBytes = fileIOManagerService.readFile(SECRET_KEY_FILE);
        secretKey = new String(keyBytes, StandardCharsets.UTF_8);
    }
}

