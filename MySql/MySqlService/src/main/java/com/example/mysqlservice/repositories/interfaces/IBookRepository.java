package com.example.mysqlservice.repositories.interfaces;

import com.example.mysqlservice.models.Author;
import com.example.mysqlservice.models.Book;
import com.example.mysqlservice.models.BookPartial;

import java.sql.SQLException;
import java.util.List;

public interface IBookRepository
{
    void connect() throws SQLException;
    void disconnect() throws SQLException;
    List<Book> listAllBooks() throws SQLException;
    Book getBook(String ISBN) throws SQLException;
    Book createBook(Book book) throws SQLException;
    Book updateBook(Book book, String ISBN) throws SQLException;
    void deleteBook(String ISBN) throws SQLException;
    List<Author> getAllAuthorsForABook(String ISBN) throws SQLException;
    Book getBookByTitle(String title) throws SQLException;
    List<Book> getBookByGenre(String genre) throws SQLException;
    List<Book> getBookByYear(Integer year) throws SQLException;
    List<Book> getBookByGenreAndYear(String genre,Integer year) throws SQLException;
    Integer getStocForABookFromISBN(String ISBN) throws SQLException;
    BookPartial getBookPartial(String ISBN) throws SQLException;
    void updateStocForBook(String ISBN, Integer nr) throws SQLException;
}
