package com.example.mysqlservice.controllers;

import com.example.mysqlservice.models.Author;
import com.example.mysqlservice.services.interfaces.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/bookcollection")
public class AuthorController
{
    @Autowired
    private IAuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity getAllAuthors(@RequestParam(required = false) String nume, @RequestParam(required = false) String prenume, @RequestParam(required = false) String match)
    {
        List<Author> authors=new ArrayList<>();
        try
        {
            if(nume!=null)
            {
                if(Objects.equals(match, "exact"))
                {
                    return new ResponseEntity<>(authorService.getAuthorByName(nume).add(linkTo(methodOn(AuthorController.class).getAllAuthors(nume, prenume, match)).withSelfRel()), HttpStatus.OK);
                }
                return new ResponseEntity<>(authorService.getAuthorByNamePartial(nume).add(linkTo(methodOn(AuthorController.class).getAllAuthors(nume, prenume, match)).withSelfRel()), HttpStatus.OK);
            }

            if(prenume!=null)
            {
                if(Objects.equals(match, "exact"))
                {
                    return new ResponseEntity<>(authorService.getAuthorByPrenume(prenume).add(linkTo(methodOn(AuthorController.class).getAllAuthors(nume, prenume, match)).withSelfRel()), HttpStatus.OK);
                }
                return new ResponseEntity<>(authorService.getAuthorByPrenumePartial(prenume).add(linkTo(methodOn(AuthorController.class).getAllAuthors(nume, prenume, match)).withSelfRel()), HttpStatus.OK);
            }

            if(prenume!=null && nume!=null)
            {
                if(Objects.equals(match, "exact"))
                {
                    return new ResponseEntity<>(authorService.getAuthorByNameAndPrenume(nume, prenume).add(linkTo(methodOn(AuthorController.class).getAllAuthors(nume, prenume, match)).withSelfRel()), HttpStatus.OK);
                }
                return new ResponseEntity<>(authorService.getAuthorByNameAndPrenumePartial(nume, prenume).add(linkTo(methodOn(AuthorController.class).getAllAuthors(nume, prenume, match)).withSelfRel()), HttpStatus.OK);
            }

            authors=authorService.getAllAuthors();
            authors.forEach(author -> author.add(linkTo(methodOn(AuthorController.class).getAllAuthors(nume, prenume, match)).withSelfRel()));
            return new ResponseEntity(authors, HttpStatus.OK);
        }
        catch (Exception e)
        {
            authors=authorService.getAllAuthors();
            authors.forEach(author -> author.add(linkTo(methodOn(AuthorController.class).getAllAuthors(nume, prenume, match)).withSelfRel()));
            return new ResponseEntity(authors, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/authors/{ID}")
    public ResponseEntity<Author> getAuthor(@PathVariable("ID") Integer ID)
    {
        try
        {
            return new ResponseEntity<>(authorService.getAuthor(ID).add(linkTo(methodOn(AuthorController.class).getAuthor(ID)).withSelfRel()), HttpStatus.OK);

        }
        catch (Exception e)
        {
            return new ResponseEntity<>(authorService.getAuthor(ID).add(linkTo(methodOn(AuthorController.class).getAuthor(ID)).withSelfRel()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/authors")
    public ResponseEntity createAuthor(@RequestBody Author author)
    {
        try
        {
            return new ResponseEntity<>(authorService.createAuthor(author).add(linkTo(methodOn(AuthorController.class).createAuthor(author)).withSelfRel()), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(authorService.createAuthor(author).add(linkTo(methodOn(AuthorController.class).createAuthor(author)).withSelfRel()), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/authors/{ID}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author,@PathVariable("ID") Integer ID)
    {
        try
        {
            return new ResponseEntity<>(authorService.updateAuthor(author, ID).add(linkTo(methodOn(AuthorController.class).updateAuthor(author, ID)).withSelfRel()), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(authorService.updateAuthor(author, ID).add(linkTo(methodOn(AuthorController.class).updateAuthor(author, ID)).withSelfRel()), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/authors/{ID}")
    public ResponseEntity deleteAuthor(@PathVariable("ID") Integer ID)
    {
        try
        {
            authorService.deleteAuthor(ID);
            return new ResponseEntity<>(linkTo(methodOn(AuthorController.class).deleteAuthor(ID)).withSelfRel(), HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(linkTo(methodOn(AuthorController.class).deleteAuthor(ID)).withSelfRel(), HttpStatus.NOT_FOUND);
        }
    }
}
