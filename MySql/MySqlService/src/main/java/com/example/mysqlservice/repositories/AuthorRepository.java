package com.example.mysqlservice.repositories;

import com.example.mysqlservice.models.Author;
import com.example.mysqlservice.repositories.interfaces.IAuthorRepository;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorRepository implements IAuthorRepository
{
    final private String jdbcURL="jdbc:mariadb://localhost:33066/bookcollection";
    final private String jdbcUsername="db_manager";
    final private String jdbcPassword="1234";
    private Connection jdbcConnection;

    @Override
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

    @Override
    public void disconnect() throws SQLException
    {
        if (jdbcConnection != null && !jdbcConnection.isClosed())
        {
            jdbcConnection.close();
        }
    }

    @Override
    public List<Author> listAllAuthors() throws SQLException
    {
        List<Author> listAuthor=new ArrayList<>();

        String sql="SELECT * FROM author";

        connect();

        Statement st = jdbcConnection.createStatement();
        ResultSet resultSet = st.executeQuery(sql);

        while (resultSet.next())
        {
            Integer ID=resultSet.getInt("ID");
            String prenume=resultSet.getString("prenume");
            String nume=resultSet.getString("nume");


            Author author = new Author(ID, prenume, nume);
            listAuthor.add(author);
        }
        resultSet.close();
        st.close();
        disconnect();

        return listAuthor;
    }

    @Override
    public Author getAuthor(Integer ID) throws SQLException
    {
        Author author = null;
        String sql = "SELECT * FROM author WHERE ID=?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, ID);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            String prenume=resultSet.getString("prenume");
            String nume=resultSet.getString("nume");

            author = new Author(ID, prenume, nume);
        }
        resultSet.close();
        statement.close();
        return author;
    }

    @Override
    public Author getAuthorByNume(String nume) throws SQLException {
        Author author = null;
        String sql = "SELECT * FROM author WHERE nume=?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, nume);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            Integer ID=resultSet.getInt("ID");
            String prenume=resultSet.getString("prenume");

            author = new Author(ID, prenume, nume);
        }
        resultSet.close();
        statement.close();
        return author;
    }

    @Override
    public Author getAuthorByPrenume(String prenume) throws SQLException {
        Author author = null;
        String sql = "SELECT * FROM author WHERE prenume=?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, prenume);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            Integer ID=resultSet.getInt("ID");
            String nume=resultSet.getString("nume");

            author = new Author(ID, prenume, nume);
        }
        resultSet.close();
        statement.close();
        return author;
    }

    @Override
    public Author getAuthorByNumeAndPrenume(String nume, String prenume) throws SQLException {
        Author author = null;
        String sql = "SELECT * FROM author WHERE prenume=? AND nume=?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, prenume);
        statement.setString(2, nume);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
        {
            Integer ID=resultSet.getInt("ID");

            author = new Author(ID, prenume, nume);
        }
        resultSet.close();
        statement.close();
        return author;
    }

    @Override
    public Author createAuthor(Author author) throws SQLException
    {
        String sql = "INSERT INTO author (ID, prenume, nume) VALUES (?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, author.getID());
        statement.setString(2, author.getPrenume());
        statement.setString(3, author.getNume());

        statement.execute();

        statement.close();
        disconnect();
        return author;
    }

    @Override
    public Author updateAuthor(Author authorDetails, Integer ID) throws SQLException
    {
        String sql = "UPDATE author SET prenume=?, nume=? WHERE ID=?";
        connect();
        Author author = null;

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);

        statement.setString(1, authorDetails.getPrenume());
        statement.setString(2, authorDetails.getNume());
        statement.setInt(3, authorDetails.getID());

        statement.execute();

        author.setID(authorDetails.getID());
        author.setNume(authorDetails.getNume());
        author.setPrenume(authorDetails.getPrenume());


        statement.close();
        disconnect();
        return author;
    }

    @Override
    public void deleteAuthor(Integer ID) throws SQLException
    {
        String sql = "DELETE FROM author where ID = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, ID);

        statement.executeUpdate();
        statement.close();
        disconnect();
    }
}
