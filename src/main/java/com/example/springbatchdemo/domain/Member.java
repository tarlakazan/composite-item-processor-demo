package com.example.springbatchdemo.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String membershipDate;

    @Override
    public String toString() {
        return "Employee [id=" + getId() + ", fullName=" + getFullName() + ", membershipDate=" + getMembershipDate()+ "]";
    }




}
