package com.example.mysqlservice.services.interfaces;

import com.example.mysqlservice.models.Author;
import com.example.mysqlservice.models.Book;
import com.example.mysqlservice.models.BookPartial;

import java.util.List;

public interface IBookService
{
    List<Book> getAllBooks();
    Book getBook(String ISBN);
    Book createBook(Book book);
    Book updateBook(Book book, String ISBN);
    void deleteBook(String ISBN);
    List<Author> getAuthorsByBook(String ISBN);
    List<Book> getBooksPerPage(Integer page);
    List<Book> getBooksPerPageWithItems(Integer page, Integer items);
    Book getBookByTitle(String title);
    Book getBookByTitlePartial(String title);
    List<Book> getBookByGenre(String genre);
    List<Book> getBookByYear(Integer year);
    List<Book> getBookByGenreAndYear(String genre,Integer year);
    BookPartial getBookPartial(String ISBN);
    boolean getStocForABookFromISBN(String ISBN, Integer nr);
}
