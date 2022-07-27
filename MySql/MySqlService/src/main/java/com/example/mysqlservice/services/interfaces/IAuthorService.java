package com.example.mysqlservice.services.interfaces;

import com.example.mysqlservice.models.Author;

import java.util.List;

public interface IAuthorService
{
    List<Author> getAllAuthors();
    Author getAuthor(Integer ID);
    Author getAuthorByName(String nume);
    Author getAuthorByPrenume(String prenume);
    Author getAuthorByNameAndPrenume(String nume,String prenume);
    Author getAuthorByNamePartial(String nume);
    Author getAuthorByPrenumePartial(String prenume);
    Author getAuthorByNameAndPrenumePartial(String nume,String prenume);
    Author createAuthor(Author author);
    Author updateAuthor(Author author, Integer ID);
    void deleteAuthor(Integer ID);
}
