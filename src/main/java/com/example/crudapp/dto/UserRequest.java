package com.example.crudapp.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private int age;
}
