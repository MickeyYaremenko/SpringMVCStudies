package com.epam.spring.object;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class User {
    @Size(min = 5, message = "Name should contain at least 5 characters")
    private String name;
    @Size(min = 4, max = 12, message = "Password should contain at least 5 and maximum of 12 characters")
    private String password;
    private Boolean admin;

}

