package com.example.springboot.controller;

import com.example.springboot.converter.RoleConverter;
import com.example.springboot.dto.RoleDTO;
import com.example.springboot.model.RoleEntity;
import com.example.springboot.model.UserEntity;
import com.example.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    // ===============
    //    get EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/{id}")
    @ResponseBody
    public RoleDTO getUser(@PathVariable Long id){
        RoleConverter mapper = new RoleConverter();
        Optional<RoleEntity> roleEntity = roleService.getById(id);
        if( roleEntity.isPresent() )  return mapper.toDTO(roleEntity.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/all")
    @ResponseBody
    public List<RoleDTO> getAll(){
        RoleConverter mapper = new RoleConverter();
        List<RoleEntity> roles = roleService.getAll();

        // convert all roles Entities to DTOs
        List<RoleDTO> roleDTOs = roles.stream().map(mapper::toDTO).collect(Collectors.toList());
        return roleDTOs;
    }

    @GetMapping("")
    @ResponseBody
    public List<RoleDTO> getAllRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // request to the database using pagination
        Pageable pageable = PageRequest.of(page, size);
        Page<RoleEntity> roleEntityPage = roleService.findAllPageable(pageable);
        // convert the resulting list to dto
        RoleConverter mapper = new RoleConverter();
        List<RoleDTO> roleDTOs = roleEntityPage
                .getContent()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return roleDTOs;
    }

    // ===============
    //   post EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("/create")
    @ResponseBody
    public RoleEntity create(@RequestBody RoleDTO roleDTO) {
        RoleConverter roleConverter = new RoleConverter();
        // users for this role
        Set<UserEntity> users = new HashSet<>();

        RoleEntity roleEntity = roleConverter.toEntity(roleDTO,users);
        return roleService.save( roleEntity );
    }

    // ================
    //   delete EPs  //
    // ================

    @PreAuthorize("hasAnyRole('admin')")
    @DeleteMapping("/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id){
        roleService.delete(id);
        return "ok";
    }

}
