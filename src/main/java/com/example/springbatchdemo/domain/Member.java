package com.example.springbatchdemo.domain;


public class Member {

    public Member(String id, String toLowerCase, String toUpperCase) {
        this.id=id;
        this.firstName=toLowerCase;
        this.lastName=toUpperCase;
    }

    public Member(String id, String fullName) {
        this.id=id;
        this.fullName=fullName;
    }

    public Member(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    String id;
    String firstName;
    String lastName;



    String fullName;

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    String membershipDate;

    @Override
    public String toString() {
        return "Employee [id=" + getId() + ", fullName=" + getFullName() + ", membershipDate=" + getMembershipDate()+ "]";
    }




}
