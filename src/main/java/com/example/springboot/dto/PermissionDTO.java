package com.example.springboot.dto;

public class PermissionDTO {
    private Long id;
    private String sectionName;
    private Boolean create;
    private Boolean update;
    private Boolean delete;
    private Boolean read;

    public PermissionDTO(Long id, String sectionName, Boolean create, Boolean update, Boolean delete, Boolean read) {
        this.id = id;
        this.sectionName = sectionName;
        this.create = create;
        this.update = update;
        this.delete = delete;
        this.read = read;
    }

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
}
