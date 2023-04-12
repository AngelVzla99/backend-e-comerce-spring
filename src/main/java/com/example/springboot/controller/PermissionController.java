package com.example.springboot.controller;

import com.example.springboot.model.PermissionEntity;
import com.example.springboot.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping
    @ResponseBody
    public List<PermissionEntity> permissions(){
        return permissionService.getPermissions();
    }
}
