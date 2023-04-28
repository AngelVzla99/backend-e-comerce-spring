package com.example.springboot.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {
    private Long id;
    private String roleName;

    public RoleDTO(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
