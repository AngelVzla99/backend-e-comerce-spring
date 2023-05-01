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
import org.springframework.data.domain.Sort;
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
    public RoleDTO get(@PathVariable Long id){
        RoleConverter mapper = new RoleConverter();
        Optional<RoleEntity> roleEntity = roleService.findById(id);
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

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("")
    @ResponseBody
    public Page<RoleDTO> getAllRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        // request to the database using pagination
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<RoleEntity> roleEntityPage = roleService.findAllPageable(pageable);
        if( !roleEntityPage.hasContent() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // convert the resulting list to dto
        RoleConverter mapper = new RoleConverter();
        Page<RoleDTO> roleDTOs = roleEntityPage.map(mapper::toDTO);
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
    public void delete(@PathVariable Long id){
        if(roleService.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no role with id "+id);
        roleService.delete(id);
    }

}
