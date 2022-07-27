package com.example.pos_proiectmongodb.controller;

import com.example.pos_proiectmongodb.models.Order;
import com.example.pos_proiectmongodb.services.interfaces.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookorder")
public class OrderController
{
    @Autowired
    private OrderServiceInterface orderServiceInterface;

    //private UUID id = UUID.randomUUID();
    private final String id="8";
    public String collection="client_"+id;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders()
    {
        try
        {
            return new ResponseEntity<>(orderServiceInterface.getAllOrder(collection), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order)
    {
        try
        {
            return new ResponseEntity<>(orderServiceInterface.createOrder(collection,order), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @GetMapping("/orders/{id}")
    public HttpEntity<Object> getOrder(@PathVariable("id") String id)
    {
        try
        {
            return new ResponseEntity<>(orderServiceInterface.getOrderById(collection,id), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order,@PathVariable("id") String id)
    {
        try
        {
            return new ResponseEntity<>(orderServiceInterface.updateOrderById(collection,order, id), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @DeleteMapping("/orders/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") String id) {
        try
        {
            orderServiceInterface.deleteOrder(collection,id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
