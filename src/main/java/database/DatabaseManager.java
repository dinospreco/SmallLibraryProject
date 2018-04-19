package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseManager {

    private static DatabaseManager instance = null;
    private Connection connection = null;

    private DatabaseManager() {

    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }

        return instance;
    }

    private boolean openConnection() {

        final String USERNAME = "";
        final String PASSWORD = "";
        final String CONNECTION_STRING = "jdbc:h2:~/database";
        final String DRIVER = "org.h2.Driver";

        try {

            Class.forName(DRIVER);
            connection = DriverManager.getConnection(CONNECTION_STRING,USERNAME,PASSWORD);

            createTables();

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Connection getConnection() {
        if (connection == null) {
            if (openConnection()) {
                return connection;
            }
            else {
                return null;
            }
        }
        return connection;
    }

    void close() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() {
        try {
            Statement statement;
            statement = connection.createStatement();
            String booksQuery =  "CREATE TABLE IF NOT EXISTS  BOOKS(" +
                    "id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                    "title VARCHAR(255)," +
                    "author VARCHAR(255)," +
                    "isbn VARCHAR(255)," +
                    "publication_year INT," +
                    "is_rented BOOLEAN," +
                    "date_rented DATE," +
                    "members_id_book_is_rented_to INT)";
            statement.executeUpdate(booksQuery);

            statement = connection.createStatement();
            String membersQuery =  "CREATE TABLE IF NOT EXISTS  MEMBERS(" +
                    "id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "id_doc_number VARCHAR(225)," +
                    "membership_expiration_date DATE)";
            statement.executeUpdate(membersQuery);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
