package com.example.springboot.controller;

import com.example.springboot.dto.OrderDTO;
import com.example.springboot.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Status codes:
 * 0 => Created
 * 1 => The payments are checked
 * 2 => The order was packed
 * 3 => The rider is in coming
 * 4 => The order was delivered and have been completed
 * 5 => The order have been cancelled
 */

@RestController
@RequestMapping("/api/orders")
public class orderController {
    @Autowired
    OrderService orderService;

    // ===============
    //    get EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping("/{id}")
    @ResponseBody
    public OrderDTO get(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @GetMapping
    @ResponseBody
    public Page<OrderDTO> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        // request to the database using pagination
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return orderService.findAllPageable(pageable);
    }

    // ===============
    //   post EPs   //
    // ===============

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("/create")
    @ResponseBody
    public OrderDTO save( @Valid @RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/{orderId}/rider-coming")
    @ResponseBody
    public void paymentChecked(@PathVariable Long orderId){
        orderService.paymentChecked(orderId);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/{orderId}/order-coming")
    @ResponseBody
    public void riderComing(@PathVariable Long orderId){
        orderService.riderComing(orderId);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/{orderId}/order-completed")
    @ResponseBody
    public void orderCompleted(@PathVariable Long orderId){
        orderService.orderCompleted(orderId);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/{orderId}/order-cancelled")
    @ResponseBody
    public void orderCancelled(@PathVariable Long orderId){
        orderService.orderCancelled(orderId);
    }

}
