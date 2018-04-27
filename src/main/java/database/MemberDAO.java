package database;

import model.Member;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MemberDAO implements DAO<Member> {

    Connection connection = DatabaseManager.getInstance().getConnection();

    public MemberDAO() {
    }

    public Member get(String sqlWhereValue) {
        String query = "SELECT * FROM MEMBERS WHERE " + sqlWhereValue;

        Member member = new Member();

        if (executeQueryFromString(query,member)) {
            return member;
        }
        else {
            return null;
        }
    }

    public boolean add(Member member) {
        String query = "INSERT INTO MEMBERS(" +
                "name," +
                "id_doc_number," +
                "membership_expiration_date) " +
                "VALUES(?,?,?)";

        return executeQueryFromString(query,member);
    }

    public boolean update(Member member) {
        String query = "UPDATE MEMBERS SET " +
                "name = ?," +
                "id_doc_number = ?," +
                "membership_expiration_date = ? " +
                "WHERE id = " + member.getId();

        return executeQueryFromString(query,member);
    }

    public boolean delete(Member member) {
        String query = "DELETE FROM MEMBERS WHERE id = " + member.getId();
        return executeQueryFromString(query,member);
    }

    public Map getAll() {
        Map members = new HashMap();
        String query = "SELECT * FROM MEMBERS";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Member member = new Member();

                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                member.setIdDocNumber(rs.getString("id_doc_number"));
                member.setMembershipExpirationDate(rs.getDate("membership_expiration_date"));

                members.put(member.getId(),member);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return members;
    }

    private boolean executeQueryFromString(String query, Member member) {

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            if (query.startsWith("INSERT") || query.startsWith("UPDATE")) {
                statement.setString(1, member.getName());
                statement.setString(2, member.getIdDocNumber());
                statement.setDate(3, member.getMembershipExpirationDate());

                int rows = statement.executeUpdate();

                return !(rows == 0);
            }
            else if (query.startsWith("SELECT")) {

                ResultSet rs = statement.executeQuery();

                if (!rs.next()) {
                    return false;
                } else {
                    member.setId(rs.getInt("id"));
                    member.setName(rs.getString("name"));
                    member.setIdDocNumber(rs.getString("id_doc_number"));
                    member.setMembershipExpirationDate(rs.getDate("membership_expiration_date"));

                    return true;
                }
            }
            else if (query.startsWith("DELETE")) {
                int rows = statement.executeUpdate(query);

                return !(rows == 0);
            }

        }

        catch (SQLException e) {
            //e.printStackTrace();
            return false;
        }
        catch (NullPointerException e) {
            //e.printStackTrace();
            return false;
        }

        return false;
    }

}
