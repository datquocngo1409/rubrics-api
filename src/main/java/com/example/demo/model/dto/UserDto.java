package com.example.demo.model.dto;

import com.example.demo.model.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String role;
    private String name;
    private Long age;
    private String address;
    private String phone;
    private String email;
    private String avatar;
    private String token;
    private String code;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.name = user.getName();
        this.age = user.getAge();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.avatar = user.getAvatar();
        this.token = user.getToken();
        this.code = user.getCode();
    }
}
