package com.example.mysqlservice.services;

import com.example.mysqlservice.models.Author;
import com.example.mysqlservice.repositories.interfaces.IAuthorRepository;
import com.example.mysqlservice.services.interfaces.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements IAuthorService
{
    @Autowired
    private IAuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        try
        {
            return authorRepository.listAllAuthors();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Author getAuthor(Integer ID) {
        try
        {
            return authorRepository.getAuthor(ID);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Author getAuthorByName(String nume) {
        try
        {
            return authorRepository.getAuthorByNume(nume);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Author getAuthorByPrenume(String prenume) {
        try
        {
            return authorRepository.getAuthorByPrenume(prenume);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Author getAuthorByNameAndPrenume(String nume, String prenume) {
        try
        {
            return authorRepository.getAuthorByNumeAndPrenume(nume,prenume);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Author getAuthorByNamePartial(String nume) {
        try
        {
            String name = nume.substring(0, 1).toUpperCase() + nume.substring(1);
            return authorRepository.getAuthorByNume(name);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Author getAuthorByPrenumePartial(String prenume) {
        try
        {
            String pre = prenume.substring(0, 1).toUpperCase() + prenume.substring(1);
            return authorRepository.getAuthorByPrenume(pre);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Author getAuthorByNameAndPrenumePartial(String nume, String prenume) {
        try
        {
            String name = nume.substring(0, 1).toUpperCase() + nume.substring(1);
            String pre = prenume.substring(0, 1).toUpperCase() + prenume.substring(1);
            return authorRepository.getAuthorByNumeAndPrenume(nume,prenume);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Author createAuthor(Author author) {
        try
        {
            return authorRepository.createAuthor(author);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Author updateAuthor(Author author, Integer ID) {
        try
        {
            return authorRepository.updateAuthor(author, ID);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public void deleteAuthor(Integer ID) {
        try
        {
            authorRepository.deleteAuthor(ID);
        }
        catch (Exception e)
        {
        }
    }
}
