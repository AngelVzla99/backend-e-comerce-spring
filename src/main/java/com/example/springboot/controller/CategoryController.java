package com.example.springboot.controller;

import com.example.springboot.converter.CategoryConverter;
import com.example.springboot.dto.CategoryDTO;
import com.example.springboot.model.CategoryEntity;
import com.example.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryConverter categoryConverter;

    // ===============
    //    get EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping
    @ResponseBody
    public Page<CategoryDTO> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        // request to the database using pagination
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<CategoryEntity> entitiesPage = categoryService.findAllPageable(pageable);
        if( !entitiesPage.hasContent() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // convert the resulting list to dto
        Page<CategoryDTO> DTOs = entitiesPage.map(categoryConverter::entityToDTO);
        return DTOs;
    }

    // ===============
    //   post EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping
    @ResponseBody
    public CategoryDTO save(@RequestBody CategoryDTO categoryDTO) {
        CategoryEntity entity = categoryConverter.dtoToEntity(categoryDTO);
        categoryService.save(entity);
        return categoryConverter.entityToDTO(entity);
    }

    // =================
    //   delete EPs   //
    // =================

    @PreAuthorize("hasAnyRole('admin')")
    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        // find in the database
        if( categoryService.findById(id).isEmpty() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no category with id "+id);
        // delete from the db
        categoryService.delete(id);
    }
}
