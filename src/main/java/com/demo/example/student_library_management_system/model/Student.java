package com.demo.example.student_library_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {

    //id,name,email,mobile,dept,sem,gender,address,dob
    @Id
    @Column(name="id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="mobile",nullable = false)
    private String mobile;

    @Column(name="dept",nullable = false)
    private String dept;

    @Column(name="sem",nullable = false)
    private String sem;

    @Column(name="gender",nullable = false)
    private String gender;

    @Column(name="address",nullable = false)
    private String address;

    @Column(name="dob",nullable = false)
    private String dob;

    @JsonManagedReference
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL) //one student will have one card
    private Card card;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
