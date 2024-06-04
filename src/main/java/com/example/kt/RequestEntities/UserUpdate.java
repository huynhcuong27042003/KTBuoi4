package com.example.kt.RequestEntities;

import com.example.kt.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdate {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private Role role ;
    private boolean isDeleted;
}
