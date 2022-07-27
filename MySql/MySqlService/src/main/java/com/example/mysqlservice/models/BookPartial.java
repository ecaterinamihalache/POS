package com.example.mysqlservice.models;

import org.springframework.hateoas.RepresentationModel;

public class BookPartial extends RepresentationModel<BookPartial>
{
    private String ISBN;
    private String Titlu;
    private Integer An_publicare;

    public BookPartial(String ISBN, String titlu,Integer an_publicare)
    {
        this.ISBN = ISBN;
        Titlu = titlu;
        An_publicare = an_publicare;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitlu() {
        return Titlu;
    }

    public Integer getAn_publicare() {return An_publicare;}

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitlu(String titlu) { Titlu = titlu; }

    public void setAn_publicare(Integer an_publicare) {
        An_publicare = an_publicare;
    }

}
