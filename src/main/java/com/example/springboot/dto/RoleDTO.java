package com.example.springboot.dto;

import com.example.springboot.model.PermissionEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO {
    private Long id;
    private String roleName;
    private List<PermissionDTO> permissions = new ArrayList<>();

    public RoleDTO(Long id, String roleName, List<PermissionDTO> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.permissions = permissions;
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

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
}
