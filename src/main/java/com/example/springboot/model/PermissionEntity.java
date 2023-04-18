package com.example.springboot.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="permissions")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "section_name")
    private String sectionName;
    @Column(name = "`create`")
    private Boolean create;
    @Column
    private Boolean update;
    @Column
    private Boolean delete;
    @Column
    private Boolean read;
    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    private Set<RoleEntity> roles = new HashSet<>();

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "id=" + id +
                ", sectionName='" + sectionName + '\'' +
                ", create=" + create +
                ", update=" + update +
                ", delete=" + delete +
                ", read=" + read +
                ", roles=" + roles +
                '}';
    }

    // constructors

    public PermissionEntity(){}

    public PermissionEntity(String sectionName, Boolean create, Boolean update, Boolean delete, Boolean read, Set<RoleEntity> roles) {
        this.sectionName = sectionName;
        this.create = create;
        this.update = update;
        this.delete = delete;
        this.read = read;
        this.roles = roles;
    }

    public PermissionEntity(Long id, String sectionName, Boolean create, Boolean update, Boolean delete, Boolean read) {
        this.id = id;
        this.sectionName = sectionName;
        this.create = create;
        this.update = update;
        this.delete = delete;
        this.read = read;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Boolean getCreate() {
        return create;
    }

    public void setCreate(Boolean create) {
        this.create = create;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
