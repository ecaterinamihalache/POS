package com.example.pos_proiectmongodb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Book  implements Serializable
{
    private String ISBN;
    private String title;
    private Integer price;
    private Integer quantity;

    public Book(@JsonProperty("ISBN") String ISBN,
                @JsonProperty("title") String title,
                @JsonProperty("price") Integer price,
                @JsonProperty("quantity") Integer quantity)
    {
        this.ISBN = ISBN;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
