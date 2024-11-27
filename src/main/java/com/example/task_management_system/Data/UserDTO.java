package com.example.task_management_system.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String password;
}
