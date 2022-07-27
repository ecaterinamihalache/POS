package com.example.mysqlservice.controllers;

import com.example.mysqlservice.services.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.mysqlservice.models.Book;
import com.example.mysqlservice.models.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/bookcollection")
public class BookController
{
    @Autowired
    private IBookService bookService;

    @GetMapping("/books")
    public ResponseEntity getAllBooks(@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer items_per_page,@RequestParam(required = false) String title,@RequestParam(required = false) String genre, @RequestParam(required = false) Integer year,@RequestParam(required = false) String match)
    {
        List<Book> books = new ArrayList<>();
        try
        {
            if(page != null)
            {
                if(items_per_page!=null)
                {
                    books=bookService.getBooksPerPageWithItems(page, items_per_page);
                    books.forEach(book -> book.add(linkTo(methodOn(BookController.class).getAllBooks(page, items_per_page, title, genre, year, match)).withSelfRel()));
                    return new ResponseEntity(books, HttpStatus.OK);
                }
                books=bookService.getBooksPerPage(page);
                books.forEach(book -> book.add(linkTo(methodOn(BookController.class).getAllBooks(page, items_per_page, title, genre, year, match)).withSelfRel()));
                return new ResponseEntity(books, HttpStatus.OK);
            }

            if(title != null)
            {
                if(Objects.equals(match, "exact"))//exacta
                {
                    return new ResponseEntity<>(bookService.getBookByTitle(title).add(linkTo(methodOn(BookController.class).getAllBooks(page, items_per_page, title, genre, year, match)).withSelfRel()), HttpStatus.OK);
                }
                //partiala
                return new ResponseEntity<>(bookService.getBookByTitlePartial(title).add(linkTo(methodOn(BookController.class).getAllBooks(page, items_per_page, title, genre, year, match)).withSelfRel()), HttpStatus.OK);
            }

            if(genre!=null)
            {
                if(year!=null)
                {
                    books=bookService.getBookByGenreAndYear(genre, year);
                    books.forEach(book -> book.add(linkTo(methodOn(BookController.class).getAllBooks(page, items_per_page, title, genre, year, match)).withSelfRel()));
                    return new ResponseEntity(books, HttpStatus.OK);
                }
                books=bookService.getBookByGenre(genre);
                books.forEach(book -> book.add(linkTo(methodOn(BookController.class).getAllBooks(page, items_per_page, title, genre, year, match)).withSelfRel()));
                return new ResponseEntity(books, HttpStatus.OK);
            }
            if(year!=null)
            {
                books=bookService.getBookByYear(year);
                books.forEach(book -> book.add(linkTo(methodOn(BookController.class).getAllBooks(page, items_per_page, title, genre, year, match)).withSelfRel()));
                return new ResponseEntity(books, HttpStatus.OK);
            }
            books=bookService.getAllBooks();
            books.forEach(book -> book.add(linkTo(methodOn(BookController.class).getAllBooks(page, items_per_page, title, genre, year, match)).withSelfRel()));
            return new ResponseEntity(books, HttpStatus.OK);
        }
        catch (Exception e)
        {
            books=bookService.getAllBooks();
            books.forEach(book -> book.add(linkTo(methodOn(BookController.class).getAllBooks(page, items_per_page, title, genre, year, match)).withSelfRel()));
            return new ResponseEntity(books, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/books/{ISBN}")
    public ResponseEntity getBook(@PathVariable("ISBN") String ISBN,@RequestParam(required = false) boolean verbose)
    {
        try
        {
            if(!verbose)
            {
                return new ResponseEntity<>(bookService.getBookPartial(ISBN).add(linkTo(methodOn(BookController.class).getBook(ISBN, verbose)).withSelfRel()), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(bookService.getBook(ISBN).add(linkTo(methodOn(BookController.class).getBook(ISBN, verbose)).withSelfRel()), HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(bookService.getBook(ISBN).add(linkTo(methodOn(BookController.class).getBook(ISBN, verbose)).withSelfRel()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books/{ISBN}/stoc")
    public ResponseEntity getStocForABook(@PathVariable("ISBN") String ISBN, @RequestParam Integer nr)
    {
        try
        {
            boolean validareStoc = bookService.getStocForABookFromISBN(ISBN, nr);
            if(validareStoc)
            {
                return new ResponseEntity<>("Carti suficente in stoc! Comanda a fost plasata!", HttpStatus.CREATED);
            }
            else
            {
                return new ResponseEntity<>("Carti insuficente in stoc! Comanda nu a fost plasata!", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Probleme la server!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{ISBN}/authors")
    public ResponseEntity getAuthorsByBook(@PathVariable("ISBN") String ISBN)
    {
        List<Author> authors=new ArrayList<>();
        try
        {
            authors=bookService.getAuthorsByBook(ISBN);
            authors.forEach(author -> author.add(linkTo(methodOn(BookController.class).getAuthorsByBook(ISBN)).withSelfRel()));
            return new ResponseEntity(authors, HttpStatus.OK);
        }
        catch (Exception e)
        {
            authors=bookService.getAuthorsByBook(ISBN);
            authors.forEach(author -> author.add(linkTo(methodOn(BookController.class).getAuthorsByBook(ISBN)).withSelfRel()));
            return new ResponseEntity(authors, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book book)
    {
        try
        {
            return new ResponseEntity<>(bookService.createBook(book).add(linkTo(methodOn(BookController.class).createBook(book)).withSelfRel()), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(bookService.createBook(book).add(linkTo(methodOn(BookController.class).createBook(book)).withSelfRel()), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/books/{ISBN}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("ISBN") String ISBN)
    {
        try
        {
            return new ResponseEntity<>(bookService.updateBook(book, ISBN).add(linkTo(methodOn(BookController.class).updateBook(book,ISBN)).withSelfRel()), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(bookService.updateBook(book,ISBN).add(linkTo(methodOn(BookController.class).updateBook(book,ISBN)).withSelfRel()), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/books/{ISBN}")
    public ResponseEntity deleteBook(@PathVariable("ISBN") String ISBN)
    {
        try
        {
            bookService.deleteBook(ISBN);
            return new ResponseEntity<>(linkTo(methodOn(BookController.class).deleteBook(ISBN)).withSelfRel(),HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(linkTo(methodOn(BookController.class).deleteBook(ISBN)).withSelfRel(),HttpStatus.NOT_FOUND);
        }
    }
}

