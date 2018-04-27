package controller;

import database.MemberDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.Member;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MemberCtrl {
    private static MemberDAO dao = new MemberDAO();

    public static void addMember(Member member) {
        dao.add(member);
    }

    public static Member getMemberByIdDocNumber(String idDocNumber) {
        return dao.get("id_doc_number = \'" + idDocNumber + "\'");
    }

    public static Member getMemberById(String id) {
        return dao.get("id = \'" + id + "\'");
    }


    public static boolean isMembershipExpied (Member member) {
        Calendar cal = Calendar.getInstance();
        if (member.getMembershipExpirationDate().after(cal.getTime())){
            return false;
        }
        else {
            return true;
        }
    }

    public static void extendMembership(Member member) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(member.getMembershipExpirationDate());
        cal.add(Calendar.YEAR,1);
        Date newDate = new Date(cal.getTime().getTime());
        member.setMembershipExpirationDate(newDate);
        dao.update(member);
    }

    public static ObservableList<Member> getAllMemberssForGui() {
        ObservableList<Member> members = FXCollections.observableArrayList();
        List<Member> memberList = new ArrayList<Member>(dao.getAll().values());

        for (Member member : memberList) {
            members.add(member);
        }

        return members;
    }
}
