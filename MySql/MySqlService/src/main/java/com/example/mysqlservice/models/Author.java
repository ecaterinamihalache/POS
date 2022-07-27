package com.example.mysqlservice.models;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Table(name="author")
public class Author extends RepresentationModel<Author>
{
    private Integer ID;
    private String Prenume;
    private String Nume;

    public Author(Integer ID, String prenume, String nume)
    {
        this.ID = ID;
        Prenume = prenume;
        Nume = nume;
    }

    public Integer getID() {
        return ID;
    }

    public String getPrenume() {
        return Prenume;
    }

    public String getNume() {
        return Nume;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }
}
