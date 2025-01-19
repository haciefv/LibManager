package model;

import enums.Exceptions;
import exceptions.GeneralExceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
//    Ali and MahammadAli

    private Long id;
    private String name;
    private String surName;
    private int age;
    private String gmail;
    private String phoneNumber;
    private Boolean isActive;
    private LocalDateTime registeredDate;
   // private Long bookId;


    //Constructor(empty)
    public User() {

    }

    //Constructor
    public User(Long id, String name, String surName,int age, String gmail, String number,Boolean isActive,LocalDateTime registeredDate) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.age=age;
        this.gmail = gmail;
        this.phoneNumber = number;

    }

    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age<0){
            throw new GeneralExceptions(Exceptions.INVALID_AGE);
        }else {
            this.age = age;
        }
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String number) {
        this.phoneNumber = number;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    //overrides


    @Override
    public String toString() {
        return "---------------\n" +
                "\nId: " + getId() +
                "\nName: " + getName() +
                "\nSurname: " + getSurName() +
                "\nage: " + getId() +
                "\nGmail: " + getGmail() +
                "\nPhone number: " + getPhoneNumber() +
                "\n---------------";
    }
}
