package com.example.springboot.controller;

import com.example.springboot.converter.ProductDiscountConverter;
import com.example.springboot.dto.ProductDiscountDTO;
import com.example.springboot.model.ProductDiscountEntity;
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

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/{id}")
    @ResponseBody
    public ProductDiscountDTO get(@PathVariable Long id) {
        ProductDiscountConverter mapper = new ProductDiscountConverter();
        Optional<ProductDiscountEntity> productDiscount = productDiscountService.findById(id);
        if (productDiscount.isPresent()) return mapper.toDTO(productDiscount.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/all")
    @ResponseBody
    public List<ProductDiscountDTO> getAll() {
        ProductDiscountConverter mapper = new ProductDiscountConverter();
        List<ProductDiscountEntity> entities = productDiscountService.getAll();
        // convert all Entities to DTOs
        return entities.stream().map(mapper::toDTO).collect(Collectors.toList());
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
        Page<ProductDiscountEntity> responsePage = productDiscountService.findAllPageable(pageable);
        if (!responsePage.hasContent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // convert the resulting list to dto
        ProductDiscountConverter mapper = new ProductDiscountConverter();
        return responsePage.map(mapper::toDTO);
    }

    // ===============
    //   post EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("/create")
    @ResponseBody
    public ProductDiscountEntity create(@RequestBody ProductDiscountDTO productDiscountDTO) {
        ProductDiscountConverter roleConverter = new ProductDiscountConverter();
        ProductDiscountEntity productDiscount = roleConverter.toEntity(productDiscountDTO);
        return productDiscountService.save(productDiscount);
    }

    // ================
    //   delete EPs  //
    // ================

    @PreAuthorize("hasAnyRole('admin')")
    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        if( productDiscountService.findById(id).isEmpty() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This discount doesn't exist");
        productDiscountService.delete(id);
    }
}
