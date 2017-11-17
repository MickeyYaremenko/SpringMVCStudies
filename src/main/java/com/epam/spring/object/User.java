package com.epam.spring.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Size(min = 5, message = "{name.size.error}")
    private String name;
    @Size(min = 4, max = 12, message = "{pass.size.error}")
    private String password;
    private Boolean admin;

    public User (String name){
        this.name = name;
    }
}

