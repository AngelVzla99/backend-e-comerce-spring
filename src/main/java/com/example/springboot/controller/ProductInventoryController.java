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
        Optional<ProductInventoryEntity> product = productInventoryService.findById(id);
        if (product.isPresent()) return productInventoryConverter.convertEntityToDto(product.get());
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/all")
    @ResponseBody
    public List<ProductInventoryDTO> getAll() {
        List<ProductInventoryEntity> entities = productInventoryService.getAll();
        // convert all Entities to DTOs
        return entities.stream().map(productInventoryConverter::convertEntityToDto).collect(Collectors.toList());
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
        Page<ProductInventoryEntity> responsePage = productInventoryService.findAllPageable(pageable);
        if (!responsePage.hasContent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        // convert the resulting list to dto
        return responsePage.map(productInventoryConverter::convertEntityToDto);
    }

    // ===============
    //   post EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("")
    @ResponseBody
    public ProductInventoryDTO create(@RequestBody ProductInventoryDTO productInventoryDTO) {
        // save new inventory of a product in the db
        ProductInventoryEntity productInv = productInventoryConverter.convertDtoToEntity(productInventoryDTO);
        ProductInventoryEntity newProductInventory = productInventoryService.save(productInv);
        // update the amount
        ProductEntity product = newProductInventory.getProduct();
        product.setQuantity( product.getQuantity() + newProductInventory.getQuantity() );
        productService.save(product);
        return productInventoryConverter.convertEntityToDto(newProductInventory);
    }

    // ================
    //   delete EPs  //
    // ================

    @PreAuthorize("hasAnyRole('admin')")
    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        if( productInventoryService.findById(id).isEmpty() )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This product inventory doesn't exist");
        productInventoryService.delete(id);
    }

}
