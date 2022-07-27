package com.example.mysqlservice.services;

import com.example.mysqlservice.models.Author;
import com.example.mysqlservice.models.Book;
import com.example.mysqlservice.models.BookPartial;
import com.example.mysqlservice.repositories.interfaces.IBookRepository;
import com.example.mysqlservice.services.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookService implements IBookService
{
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public List<Book> getAllBooks()
    {
        try
        {
            return bookRepository.listAllBooks();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Book getBook(String ISBN)
    {
        try
        {
            return bookRepository.getBook(ISBN);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Book createBook(Book book)
    {
        try
        {
            return bookRepository.createBook(book);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Book updateBook(Book book, String ISBN)
    {
        try
        {
            return bookRepository.updateBook(book, ISBN);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public void deleteBook(String ISBN)
    {
        try
        {
            bookRepository.deleteBook(ISBN);
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public List<Author> getAuthorsByBook(String ISBN)
    {
        try
        {
            return bookRepository.getAllAuthorsForABook(ISBN);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<Book> getBooksPerPage(Integer page)
    {
        try
        {
            List<Book> listBooks=bookRepository.listAllBooks();
            List<Book> books=new ArrayList<Book>();
            Integer items=3;
            if(page*items > listBooks.size())
            {
                for(int i=(page*items)-items;i<listBooks.size();i++)
                {
                    books.add(listBooks.get(i));
                }
            }
            else
            {
                for(int i=(page*items)-items;i<page*items;i++)
                {
                    books.add(listBooks.get(i));
                }
            }
            return books;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<Book> getBooksPerPageWithItems(Integer page, Integer items) {
        try
        {
            try
            {
                List<Book> listBooks=bookRepository.listAllBooks();
                List<Book> books=new ArrayList<Book>();
                if(page*items > listBooks.size())
                {
                    for(int i=(page*items)-items;i<listBooks.size();i++)
                    {
                        books.add(listBooks.get(i));
                    }
                }
                else
                {
                    for(int i=(page*items)-items;i<page*items;i++)
                    {
                        books.add(listBooks.get(i));
                    }
                }
                return books;
            }
            catch (Exception e)
            {
                return null;
            }
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Book getBookByTitle(String title)
    {
        try
        {
            return bookRepository.getBookByTitle(title);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Book getBookByTitlePartial(String title) {
        try
        {
            String titlu = title.substring(0, 1).toUpperCase() + title.substring(1);
            return bookRepository.getBookByTitle(titlu);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<Book> getBookByGenre(String genre) {
        try
        {
            return bookRepository.getBookByGenre(genre);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<Book> getBookByYear(Integer year) {
        try
        {
            return bookRepository.getBookByYear(year);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public List<Book> getBookByGenreAndYear(String genre, Integer year) {
        try
        {
            return bookRepository.getBookByGenreAndYear(genre, year);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public BookPartial getBookPartial(String ISBN) {
        try
        {
            return bookRepository.getBookPartial(ISBN);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public boolean getStocForABookFromISBN(String ISBN, Integer nr)
    {
        try
        {
            Integer nrStoc =  bookRepository.getStocForABookFromISBN(ISBN);
            if(nrStoc >= nr)
            {
                Integer updateStoc = nrStoc-nr;
                bookRepository.updateStocForBook(ISBN, updateStoc);
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
