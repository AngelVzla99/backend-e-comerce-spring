package com.example.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name="permission")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "section_name")
    private String sectionName;
    @Column
    private Boolean create;
    @Column
    private Boolean update;
    @Column
    private Boolean delete;
    @Column
    private Boolean read;
}
