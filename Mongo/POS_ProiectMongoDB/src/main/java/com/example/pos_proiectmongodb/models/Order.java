package com.example.pos_proiectmongodb.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(collection = "#{@orderRepository.getCollectionName()}")
public class Order implements Serializable
{
    @Id
    private String id;
    private Date date;
    private List<Book> items;
    private String status;

    public Order(@JsonProperty("id") String id,@JsonProperty("date") Date date,@JsonProperty("items") List<Book> items,@JsonProperty("status") String status) {
        this.id = id;
        this.date = date;
        this.items = items;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public List<Book> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setItems(List<Book> items) {
        this.items = items;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
