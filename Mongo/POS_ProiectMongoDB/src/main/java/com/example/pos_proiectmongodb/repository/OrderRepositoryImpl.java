package com.example.pos_proiectmongodb.repository;

import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepositoryCustom
{
    private static String collectionName = "client";

    @Override
    public String getCollectionName() {
        return collectionName;
    }

    @Override
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
