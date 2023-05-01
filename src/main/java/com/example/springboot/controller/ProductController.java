package com.example.springboot.controller;

import com.example.springboot.converter.ProductConverter;
import com.example.springboot.dto.ProductDTO;
import com.example.springboot.model.ProductEntity;
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
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/{id}")
    @ResponseBody
    public ProductDTO get(@PathVariable Long id) {
        ProductConverter mapper = new ProductConverter();
        Optional<ProductEntity> product = productService.findById(id);
        if (product.isPresent()) return mapper.toDTO(product.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/all")
    @ResponseBody
    public List<ProductDTO> getAll() {
        ProductConverter mapper = new ProductConverter();
        List<ProductEntity> entities = productService.getAll();
        // convert all Entities to DTOs
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping()
    @ResponseBody
    public Page<ProductDTO> getAllRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        // request to the database using pagination
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<ProductEntity> responsePage = productService.findAllPageable(pageable);
        if (!responsePage.hasContent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // convert the resulting list to dto
        ProductConverter mapper = new ProductConverter();
        return responsePage.map(mapper::toDTO);
    }

    // ===============
    //   post EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("/create")
    @ResponseBody
    public ProductEntity create(@RequestBody ProductDTO productDTO) {
        ProductConverter roleConverter = new ProductConverter();
        ProductEntity product = roleConverter.toEntity(productDTO,null);
        return productService.save(product);
    }

    // ================
    //   delete EPs  //
    // ================

    @PreAuthorize("hasAnyRole('admin')")
    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        if( productService.findById(id).isEmpty() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This discount doesn't exist");
        productService.delete(id);
    }
}
