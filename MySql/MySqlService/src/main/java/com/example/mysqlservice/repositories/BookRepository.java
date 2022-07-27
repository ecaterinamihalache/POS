package com.example.mysqlservice.repositories;

import com.example.mysqlservice.models.Author;
import com.example.mysqlservice.models.Book;
import com.example.mysqlservice.models.BookPartial;
import com.example.mysqlservice.repositories.interfaces.IBookRepository;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository implements IBookRepository
{
    final private String jdbcURL="jdbc:mariadb://localhost:33066/bookcollection";
    final private String jdbcUsername="db_manager";
    final private String jdbcPassword="1234";
    private Connection jdbcConnection;

    public void connect() throws SQLException
    {
        if (jdbcConnection == null || jdbcConnection.isClosed())
        {
            try
            {
                Class.forName("org.mariadb.jdbc.Driver");
            }
            catch (ClassNotFoundException e)
            {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    public void disconnect() throws SQLException
    {
        if (jdbcConnection != null && !jdbcConnection.isClosed())
        {
            jdbcConnection.close();
        }
    }

    public List<Book> listAllBooks() throws SQLException
    {
        List<Book> listBook=new ArrayList<>();

        String sql="SELECT * FROM book";

        connect();

        Statement st = jdbcConnection.createStatement();
        ResultSet resultSet = st.executeQuery(sql);

        while(resultSet.next())
        {
            String ISBN = resultSet.getString("ISBN");
            String Titlu = resultSet.getString("titlu");
            String Editura = resultSet.getString("editura");
            Integer An_publicare = resultSet.getInt("an_publicare");
            String Gen_literar = resultSet.getString("gen_literar");
            Integer Stoc = resultSet.getInt("stoc");

            Book book = new Book(ISBN, Titlu, Editura, An_publicare, Gen_literar, Stoc);
            listBook.add(book);
        }
        resultSet.close();
        st.close();
        disconnect();

        return listBook;
    }

    public Book getBook(String ISBN) throws SQLException
    {
        Book book = null;
        String sql = "SELECT * FROM book WHERE ISBN=?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, ISBN);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            String Titlu=resultSet.getString("titlu");
            String Editura=resultSet.getString("editura");
            Integer An_publicare=resultSet.getInt("an_publicare");
            String Gen_literar=resultSet.getString("gen_literar");
            Integer Stoc = resultSet.getInt("stoc");

            book = new Book(ISBN, Titlu, Editura, An_publicare, Gen_literar, Stoc);
        }
        resultSet.close();
        statement.close();
        return book;
    }

    @Override
    public Book createBook(Book book) throws SQLException
    {
        String sql = "INSERT INTO book (ISBN, titlu, editura, an_publicare, gen_literar, stoc) VALUES (?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getISBN());
        statement.setString(2, book.getTitlu());
        statement.setString(3, book.getEditura());
        statement.setInt(4, book.getAn_publicare());
        statement.setString(5, book.getGen_literar());
        statement.setInt(6, book.getStoc());

        statement.execute();

        statement.close();
        disconnect();
        return book;
    }

    @Override
    public Book updateBook(Book bookDetails, String ISBN) throws SQLException
    {
        String sql = "UPDATE book SET titlu=?, editura=?, an_publicare=?, gen_literar=?, stoc=? WHERE ISBN=?";
        connect();
        Book book = null;

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
       
        statement.setString(1, bookDetails.getTitlu());
        statement.setString(2, bookDetails.getEditura());
        statement.setInt(3, bookDetails.getAn_publicare());
        statement.setString(4, bookDetails.getGen_literar());
        statement.setInt(5, bookDetails.getStoc());
        statement.setString(6, ISBN);

        statement.execute();

        book.setISBN(bookDetails.getISBN());
        book.setTitlu(bookDetails.getTitlu());
        book.setEditura(bookDetails.getEditura());
        book.setAn_publicare(bookDetails.getAn_publicare());
        book.setGen_literar(bookDetails.getGen_literar());
        book.setStoc(bookDetails.getStoc());

        statement.close();
        disconnect();
        return book;
    }

    @Override
    public void deleteBook(String ISBN) throws SQLException
    {
        String sql = "DELETE FROM book where ISBN = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, ISBN);

        statement.executeUpdate();
        statement.close();
        disconnect();
    }

    @Override
    public List<Author> getAllAuthorsForABook(String ISBN) throws SQLException
    {
        List<Author> authors=new ArrayList<>();

        String sql="SELECT * from author where ID IN (Select ID_autor from book_author where ISBN= ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, ISBN);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next())
        {
            Integer ID = resultSet.getInt("ID");
            String Prenume = resultSet.getString("prenume");
            String Nume = resultSet.getString("nume");

            Author author = new Author(ID, Prenume, Nume);
            authors.add(author);
        }

        statement.close();
        disconnect();
        return authors;
    }

    @Override
    public Book getBookByTitle(String title) throws SQLException
    {
        Book book = null;
        String sql = "SELECT * FROM book WHERE titlu=?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, title);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            String ISBN=resultSet.getString("ISBN");
            String Titlu=resultSet.getString("titlu");
            String Editura=resultSet.getString("editura");
            Integer An_publicare=resultSet.getInt("an_publicare");
            String Gen_literar=resultSet.getString("gen_literar");
            Integer Stoc=resultSet.getInt("stoc");

            book = new Book(ISBN, Titlu, Editura, An_publicare, Gen_literar, Stoc);
        }
        resultSet.close();
        statement.close();
        return book;
    }

    @Override
    public List<Book> getBookByGenre(String genre) throws SQLException
    {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE gen_literar=?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, genre);

        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next())
        {
            String ISBN=resultSet.getString("ISBN");
            String Titlu=resultSet.getString("titlu");
            String Editura=resultSet.getString("editura");
            Integer An_publicare=resultSet.getInt("an_publicare");
            String Gen_literar=resultSet.getString("gen_literar");
            Integer Stoc=resultSet.getInt("stoc");

            Book book = new Book(ISBN, Titlu, Editura, An_publicare, Gen_literar, Stoc);
            books.add(book);
        }
        resultSet.close();
        statement.close();
        return books;
    }

    @Override
    public List<Book> getBookByYear(Integer year) throws SQLException
    {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE an_publicare=?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, year);

        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next())
        {
            String ISBN=resultSet.getString("ISBN");
            String Titlu=resultSet.getString("titlu");
            String Editura=resultSet.getString("editura");
            Integer An_publicare=resultSet.getInt("an_publicare");
            String Gen_literar=resultSet.getString("gen_literar");
            Integer Stoc=resultSet.getInt("stoc");

            Book book = new Book(ISBN, Titlu, Editura, An_publicare, Gen_literar, Stoc);
            books.add(book);
        }
        resultSet.close();
        statement.close();
        return books;
    }

    @Override
    public Integer getStocForABookFromISBN(String ISBN) throws SQLException
    {
        Integer stoc = null;
        String sql = "SELECT stoc FROM book WHERE ISBN=?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, ISBN);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            stoc = resultSet.getInt("stoc");
        }
        resultSet.close();
        statement.close();
        return stoc;
    }

    @Override
    public void updateStocForBook(String ISBN, Integer nr) throws SQLException
    {
        String sql = "UPDATE book SET stoc=? WHERE ISBN=?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setInt(1,nr);
        statement.setString(2, ISBN);
        statement.execute();

        statement.close();
        disconnect();
    }

    @Override
    public List<Book> getBookByGenreAndYear(String genre, Integer year) throws SQLException
    {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE an_publicare=? AND gen_literar=?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, year);
        statement.setString(2, genre);

        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next())
        {
            String ISBN=resultSet.getString("ISBN");
            String Titlu=resultSet.getString("titlu");;
            String Editura=resultSet.getString("editura");
            Integer An_publicare=resultSet.getInt("an_publicare");
            String Gen_literar=resultSet.getString("gen_literar");
            Integer Stoc=resultSet.getInt("stoc");

            Book book = new Book(ISBN, Titlu, Editura, An_publicare, Gen_literar, Stoc);
            books.add(book);
        }
        resultSet.close();
        statement.close();
        return books;
    }

    @Override
    public BookPartial getBookPartial(String ISBN) throws SQLException
    {
        BookPartial book = null;
        String sql = "SELECT ISBN, titlu, an_publicare FROM book WHERE ISBN=?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, ISBN);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next())
        {
            String Titlu=resultSet.getString("titlu");
            Integer An_publicare=resultSet.getInt("an_publicare");

            book = new BookPartial(ISBN, Titlu, An_publicare);
        }
        resultSet.close();
        statement.close();
        return book;
    }
}
