package model;

import enums.Exceptions;
import enums.Role;
import exceptions.GeneralExceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {

//    Ali and MahammadAli

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private LocalDate birthDate;
    private String gmail;
    private String phoneNumber;
    private Boolean isActive;
    private Boolean isBlocked;
    private LocalDateTime registeredDate;
    private LocalDateTime lastLoginDate;
    private Role role; // (ADMIN, USER)

    //Constructors

    public User() {
    }

    public User(Long id, String name, String surname, String username, String password, LocalDate birthDate,
                String gmail, String phoneNumber, Boolean isActive, Boolean isBlocked, Role role) {

        if (id == null || id <= 0) {
            throw new GeneralExceptions(Exceptions.ID_CANNOT_BE_ZERO);
        }
        this.id = id;

        if (name == null || name.trim().isEmpty()) {
            throw new GeneralExceptions(Exceptions.INVALID_OPTION);
        }
        this.name = name;

        if (surname == null || surname.trim().isEmpty()) {
            throw new GeneralExceptions(Exceptions.INVALID_OPTION);
        }
        this.surname = surname;

        if (username == null || username.trim().isEmpty()) {
            throw new GeneralExceptions(Exceptions.INVALID_OPTION);
        }
        this.username = username;

        if (password == null || password.trim().length() < 6) {
            throw new GeneralExceptions(Exceptions.INVALID_OPTION);
        }
        this.password = password;

        if (birthDate == null || birthDate.isAfter(LocalDate.now())) {
            throw new GeneralExceptions(Exceptions.INVALID_DATA_FORMAT);
        }
        this.birthDate = birthDate;

        if (gmail == null || !gmail.contains("@") || !gmail.contains(".")) {
            throw new GeneralExceptions(Exceptions.INVALID_OPTION);
        }
        this.gmail = gmail;

        if (phoneNumber == null || phoneNumber.trim().length() < 7) {
            throw new GeneralExceptions(Exceptions.INVALID_OPTION);
        }
        this.phoneNumber = phoneNumber;

        if (isActive == null) {
            throw new GeneralExceptions(Exceptions.INVALID_OPTION);
        }
        this.isActive = isActive;

        if (isBlocked == null) {
            throw new GeneralExceptions(Exceptions.INVALID_OPTION);
        }
        this.isBlocked = isBlocked;

        if (role == null || (!role.equals(Role.ADMIN) && !role.equals(Role.USER))) {
            throw new GeneralExceptions(Exceptions.INVALID_OPTION);
        }
        this.role = role;

        this.registeredDate = LocalDateTime.now();
        this.lastLoginDate = null;
    }



    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return username + " " + surname;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGmail() {
        return gmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public Role getRole() {
        return role;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    //Overrides

    @Override
    public String toString() {
        return "---------------\n" +
                "\nName: " + getName() +
                "\nSurname: " + getSurname() +
                "\nUsername: " + getUsername() +
                "\nBirthDate: " + getBirthDate() +
                "\nGmail: " + getGmail() +
                "\nPhone number: " + getPhoneNumber() +
                "\nRole: " + getRole() +
                "\nRegistered Date: " + getRegisteredDate() +
                "\nLast login Date: " + getLastLoginDate() +
                "\n---------------";
    }

}
