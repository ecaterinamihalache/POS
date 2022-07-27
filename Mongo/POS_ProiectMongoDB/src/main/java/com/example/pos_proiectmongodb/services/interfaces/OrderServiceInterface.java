package com.example.pos_proiectmongodb.services.interfaces;

import com.example.pos_proiectmongodb.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderServiceInterface
{
    List<Order> getAllOrder(String collection);
    Optional<Order> getOrderById(String collection,String id);
    Order createOrder(String collection,Order order);
    Order updateOrderById(String collection,Order order, String id);
    void deleteOrder(String collection,String id);
}
