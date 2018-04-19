package database;

import model.Book;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BookDAO implements DAO<Book> {

    Connection connection = DatabaseManager.getInstance().getConnection();

    public BookDAO() {
    }

    public Book get(int id) {

        String query = "SELECT * FROM BOOKS WHERE id="+ id;
        Book book = new Book();

        if (executeQueryFromString(query,book)) {
            return book;
        }
        else {
            return null;
        }
    }

    public boolean add(Book book) {

        String query =  "INSERT INTO BOOKS(" +
                "title," +
                "author," +
                "isbn," +
                "publication_year,"+
                "is_rented," +
                "date_rented," +
                "members_id_book_is_rented_to) " +
                "VALUES(?,?,?,?,?,?,?)";

        return executeQueryFromString(query,book);
    }

    public boolean update(Book book) {
        String query =  "UPDATE BOOKS SET " +
                "title = ?," +
                "author = ?," +
                "isbn = ?," +
                "publication_year = ?,"+
                "is_rented = ?," +
                "date_rented = ?," +
                "members_id_book_is_rented_to = ? " +
                "WHERE id = " + book.getId();

        return executeQueryFromString(query,book);
    }

    public boolean delete(Book book) {
        String query = "DELETE FROM BOOKS WHERE id = " + book.getId();

        return executeQueryFromString(query,book);
    }

    public Map getAll() {
        Map books = new HashMap();
        String query = "SELECT * FROM BOOKS";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Book book = new Book();

                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setIsbn(rs.getString("isbn"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setRented(rs.getBoolean("is_rented"));
                book.setDateRented(rs.getDate("date_rented"));
                book.setMemebersIdBookIsRentedTo(rs.getInt("members_id_book_is_rented_to"));

                books.put(book.getId(),book);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return books;
    }

    private boolean executeQueryFromString(String query, Book book) {

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            if (query.startsWith("INSERT") || query.startsWith("UPDATE")) {

                statement.setString(1, book.getTitle());
                statement.setString(2, book.getAuthor());
                statement.setString(3, book.getIsbn());
                statement.setInt(4, book.getPublicationYear());
                statement.setBoolean(5, book.isRented());
                statement.setDate(6, book.getDateRented());
                statement.setInt(7, book.getMemebersIdBookIsRentedTo());

                int rows = statement.executeUpdate();

                return !(rows == 0);
            }
            else if (query.startsWith("SELECT")) {

                ResultSet rs = statement.executeQuery();

                if (!rs.next()) {
                    return false;
                } else {
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setPublicationYear(rs.getInt("publication_year"));
                    book.setRented(rs.getBoolean("is_rented"));
                    book.setDateRented(rs.getDate("date_rented"));
                    book.setMemebersIdBookIsRentedTo(rs.getInt("members_id_book_is_rented_to"));
                    return true;
                }
            }
            else if (query.startsWith("DELETE")) {
                int rows = statement.executeUpdate(query);

                return !(rows == 0);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

}
