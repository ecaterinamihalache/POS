package com.example.mysqlservice.models;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Table;

@Table(name="book")
public class Book extends RepresentationModel<Book>
{
    private String ISBN;
    private String Titlu;
    private String Editura;
    private Integer An_publicare;
    private String Gen_literar;
    private Integer Stoc;

    public Book(String ISBN, String titlu, String editura, Integer an_publicare, String gen_literar,Integer stoc)
    {
        this.ISBN = ISBN;
        Titlu = titlu;
        Editura = editura;
        An_publicare = an_publicare;
        Gen_literar = gen_literar;
        Stoc = stoc;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitlu() {
        return Titlu;
    }

    public String getEditura() {
        return Editura;
    }

    public Integer getAn_publicare() {return An_publicare;}

    public String getGen_literar() {
        return Gen_literar;
    }

    public Integer getStoc() { return Stoc; }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitlu(String titlu) { Titlu = titlu; }

    public void setEditura(String editura) {
        Editura = editura;
    }

    public void setAn_publicare(Integer an_publicare) {
        An_publicare = an_publicare;
    }

    public void setGen_literar(String gen_literar) {
        Gen_literar = gen_literar;
    }

    public void setStoc(Integer stoc) { Stoc = stoc; }
}

