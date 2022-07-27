package com.example.pos_proiectmongodb.repository;

import com.example.pos_proiectmongodb.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String>, OrderRepositoryCustom
{
}
