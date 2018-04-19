package model;

import java.sql.Date;

public class Member {

    private int id;
    private String name;
    private String idDocNumber;
    private Date membershipExpirationDate;

    public Member() {
    }

    public Member(int id) {
        this.id = id;
    }

    public Member(String name, String idDocNumber) {
        this.name = name;
        this.idDocNumber = idDocNumber;
    }

    public Member(int id, String name, String idDocNumber, Date membershipExpirationDate) {
        this.id = id;
        this.name = name;
        this.idDocNumber = idDocNumber;
        this.membershipExpirationDate = membershipExpirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdDocNumber() {
        return idDocNumber;
    }

    public void setIdDocNumber(String idDocNumber) {
        this.idDocNumber = idDocNumber;
    }

    public Date getMembershipExpirationDate() {
        return membershipExpirationDate;
    }

    public void setMembershipExpirationDate(Date membershipExpirationDate) {
        this.membershipExpirationDate = membershipExpirationDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idDocNumber='" + idDocNumber + '\'' +
                ", membershipExpirationDate=" + membershipExpirationDate +
                '}';
    }
}
