package com.dev.mcc_tools.controllers;

import com.dev.mcc_tools.domain.User;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
//@NoArgsConstructor
public class RegistrationRequest {
    // user
    public String email;

    public String password;

    public String verifyPassword;

    public String role;

    // profile
    public String firstName;

    public String middleInitial = "";

    public String lastName;

    // phone number
    public String phoneNumber;

    public String phoneType;


    // address
    public String address1;

    public String address2;

    public String city;

    public String state;

    public String zipCode;


}
