package com.example.pos_proiectmongodb.services;

import com.example.pos_proiectmongodb.models.Order;
import com.example.pos_proiectmongodb.repository.OrderRepository;
import com.example.pos_proiectmongodb.services.interfaces.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceInterface
{
    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrder(String collection)
    {
        orderRepository.setCollectionName(collection);
        try
        {
            return orderRepository.findAll();
        }
        catch (Exception e)
        {
            return null;
        }

    }

    public Order createOrder(String collection,Order order)
    {
        orderRepository.setCollectionName(collection);
        try
        {
            return orderRepository.save(order);

        } catch (Exception e)
        {
            return null;
        }
    }

    public Optional<Order> getOrderById(String collection, String id)
    {
        orderRepository.setCollectionName(collection);
        try
        {
            return orderRepository.findById(id);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public Order updateOrderById(String collection,Order order, String id)
    {
        orderRepository.setCollectionName(collection);

        if (orderRepository.findById(id).isPresent())
        {
            return orderRepository.save(new Order(order.getId(),order.getDate(), order.getItems(), order.getStatus()));
        }
        else
        {
            return null;
        }
    }

    public void deleteOrder(String collection,String id)
    {
        orderRepository.setCollectionName(collection);
        orderRepository.deleteById(id);
    }
}
