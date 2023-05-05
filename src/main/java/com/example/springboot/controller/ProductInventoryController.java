package com.example.springboot.controller;

import com.example.springboot.converter.ProductInventoryConverter;
import com.example.springboot.dto.ProductInventoryDTO;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.model.ProductInventoryEntity;
import com.example.springboot.service.ProductInventoryService;
import com.example.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products-inventory")
public class ProductInventoryController {
    @Autowired
    ProductInventoryService productInventoryService;
    @Autowired
    ProductInventoryConverter productInventoryConverter;
    @Autowired
    ProductService productService;

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/{id}")
    @ResponseBody
    public ProductInventoryDTO get(@PathVariable Long id) {
        return productInventoryService.findById(id);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping()
    @ResponseBody
    public Page<ProductInventoryDTO> getAllRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        // request to the database using pagination
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return productInventoryService.findAllPageable(pageable);
    }

    // ===============
    //   post EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("")
    @ResponseBody
    public ProductInventoryDTO create(@RequestBody ProductInventoryDTO productInventoryDTO) {
        // save new inventory of a product in the db
        return productInventoryService.save(productInventoryDTO);
    }

    // ================
    //   delete EPs  //
    // ================

    @PreAuthorize("hasAnyRole('admin')")
    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        productInventoryService.delete(id);
    }

}
