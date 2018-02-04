/*
 * Member is class that represents library member
 *
 * @author Dino Spreco
 * @date 30.1.2018.
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Member {
    private int id;
    private String name;
    private String idDocNumber;
    private Date membershipExpirationDate;
    private int numberOfRentedBooks;
    private static int totalNumberOfMembers; //This is just for generating unique id


    //<editor-fold desc="Constructors">
    public Member(String name, String idDocNumber) {
        this.name = InputCheck.checkName(name);
        this.idDocNumber = InputCheck.checkIdDocNumber(idDocNumber);

        //Setting default values; id = totalNumberOfMembers + 1, membershipExpirationDate = current + 1 year.
        this.id = totalNumberOfMembers + 1;

        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.YEAR,1);
        this.membershipExpirationDate = currentDate.getTime();
        this.numberOfRentedBooks = 0;
        Member.totalNumberOfMembers++;
    }
    //</editor-fold>

    //<editor-fold desc="Getters and Setters>
    // - id, id is set by constructor
    public int getId() {
        return id;
    }

    // - name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = InputCheck.checkName(name);
    }

    // - Identification Document Number
    public String getIdDocNumber() {
        return idDocNumber;
    }
    public void setIdDocNumber(String idDocNumber) {
        this.idDocNumber = InputCheck.checkIdDocNumber(idDocNumber);
    }

    // - membershipExpirationDate
    public Date getMembershipExpirationDate() {
        return membershipExpirationDate;
    }
    // membershipExpirationDate cannot be set, it can only be extended by one 1 year or specified number of months
    // see extendMembership() and extendMembership(int numberOfMontsh) methods

    // - totalNumberOfMembers
    public static int getTotalNumberOfMembers() {
        return totalNumberOfMembers;
    }
    // totalNumberOfMembers cannot be set since its only for generating unique member id

    // - numberOfRentedBooks
    public int getNumberOfRentedBooks() {
        return numberOfRentedBooks;
    }
    public void increaseNumberOfRentedBooks() {
        this.numberOfRentedBooks++;
    }
    public void decreaseNumberOfRentedBooks() {
        this.numberOfRentedBooks--;
    }
    //</editor-fold>


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy.");
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idDocNumber='" + idDocNumber + '\'' +
                ", membershipExpirationDate=" + sdf.format(membershipExpirationDate) +
                '}';
    }

    //This method extends members membership by one year
    public void extendMembership() {
        //Using Calendar class to add 1 year to the date
        Calendar cal = Calendar.getInstance();
        cal.setTime(membershipExpirationDate);
        cal.add(Calendar.YEAR,1);
        this.membershipExpirationDate = cal.getTime();
    }

    //This method extends members membership by specified number of months
    public void extendMembership(int numberOfMonths) {
        //Using Calendar class to add 1 year to the date
        Calendar cal = Calendar.getInstance();
        cal.setTime(membershipExpirationDate);
        cal.add(Calendar.MONTH,numberOfMonths);
        this.membershipExpirationDate = cal.getTime();
    }

    //This method checks if membership is expired
    public boolean isMembershipExpired() {
        if(Calendar.getInstance().after(this.membershipExpirationDate)) {
            return true;
        }
        else {
            return false;
        }
    }
}
