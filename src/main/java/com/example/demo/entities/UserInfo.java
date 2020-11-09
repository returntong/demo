package com.example.demo.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    private String username;
    private String password;
    private UserDetails userDetails;
    private boolean enable;
}
