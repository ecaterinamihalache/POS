package com.example.mysqlservice.repositories.interfaces;

import com.example.mysqlservice.models.Author;
import java.sql.SQLException;
import java.util.List;

public interface IAuthorRepository
{
    void connect() throws SQLException;
    void disconnect() throws SQLException;
    List<Author> listAllAuthors() throws SQLException;
    Author getAuthor(Integer ID) throws SQLException;
    Author getAuthorByNume(String nume) throws SQLException;
    Author getAuthorByPrenume(String prenume) throws SQLException;
    Author getAuthorByNumeAndPrenume(String nume,String prenume) throws SQLException;
    Author createAuthor(Author author) throws SQLException;
    Author updateAuthor(Author author, Integer ID) throws SQLException;
    void deleteAuthor(Integer ID) throws SQLException;
}
