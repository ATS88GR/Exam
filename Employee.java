package ExamTaskV2;

import java.io.Serial;
import java.io.Serializable;
import java.util.GregorianCalendar;

public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1381274585789427876L;
    private String name;
    private String surname;
    private String dob;     //date of birthday
    private String gender;
    private String phoneNumber;
    private String jobTitle;
    private String depName;     //department name
    private String bossName;
    private String bossSurname;
    private GregorianCalendar empDate;      //employment date
    private int salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String  gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getBossSurname() {
        return bossSurname;
    }

    public void setBossSurname(String bossSurname) {
        this.bossSurname = bossSurname;
    }

    public GregorianCalendar getEmpDate() {
        return empDate;
    }

    public void setEmpDate(GregorianCalendar empDate) {
        this.empDate = empDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
