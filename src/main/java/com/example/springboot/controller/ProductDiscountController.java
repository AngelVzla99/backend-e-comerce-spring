package com.example.springboot.controller;

import com.example.springboot.converter.ProductDiscountConverter;
import com.example.springboot.dto.ProductDiscountDTO;
import com.example.springboot.model.ProductDiscountEntity;
import com.example.springboot.model.ProductEntity;
import com.example.springboot.service.ProductDiscountService;
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
@RequestMapping("/api/product-discount")
public class ProductDiscountController {
    @Autowired
    ProductDiscountService productDiscountService;
    @Autowired
    ProductDiscountConverter productDiscountConverter;

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/{id}")
    @ResponseBody
    public ProductDiscountDTO get(@PathVariable Long id) {
        return productDiscountService.findById(id);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping()
    @ResponseBody
    public Page<ProductDiscountDTO> getAllRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        // request to the database using pagination
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return productDiscountService.findAllPageable(pageable);
    }

    // ===============
    //   post EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping()
    @ResponseBody
    public ProductDiscountDTO create(@RequestBody ProductDiscountDTO productDiscountDTO) {
        return productDiscountService.save(productDiscountDTO);
    }

    // ================
    //   delete EPs  //
    // ================

    @PreAuthorize("hasAnyRole('admin')")
    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        productDiscountService.delete(id);
    }
}
