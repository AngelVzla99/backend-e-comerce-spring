package com.example.springboot.User.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartialUpdateUserDto {
    private String password = null;
    private String resetPasswordToken = null;
}
