package model.Dto;

import enums.Role;

import java.time.LocalDate;

public class UpdateUserDto {

    private String name;
    private String surname;
    private String username;
    private String password;
    private LocalDate birthDate;
    private String gmail;
    private String phoneNumber;
    private Boolean isActive;
    private Boolean isBlocked;
    private Role role; // (ADMIN, USER)

}
