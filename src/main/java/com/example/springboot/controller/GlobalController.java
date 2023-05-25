package com.example.springboot.controller;

import com.example.springboot.dto.HomeDTO;
import com.example.springboot.dto.UserDTO;
import com.example.springboot.pojo.LoginRequest;
import com.example.springboot.security.TokenUtils;
import com.example.springboot.security.UserDetailServiceImpl;
import com.example.springboot.service.CategoryService;
import com.example.springboot.service.ProductService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping()
public class GlobalController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;

    @GetMapping("/health")
    public ResponseEntity<Object> healthCheck() {
        // add custom logic to check the health of your application
        return new ResponseEntity<>("Application is running", HttpStatus.OK);
    }

    // ===============
    //    get EPs   //
    // ===============

    @GetMapping("api/home")
    @ResponseBody
    public HomeDTO getHomePage() {
        HomeDTO body = new HomeDTO();
        body.setMostBought(productService.findFirstProducts(10));
        body.setBestDeals(productService.findFirstProducts(10));
        body.setCategories(categoryService.findFirstCategories(5));
        // banners
        List<String> banners = new ArrayList<>();
        banners.add("https://static.vecteezy.com/system/resources/previews/000/701/690/original/abstract-polygonal-banner-background-vector.jpg");
        banners.add("https://tinkercademy.com/wp-content/uploads/2017/04/Generic-Banner-07-Web-App-Developer-1080x338.png");
        body.setBannerUrls(banners);
        return body;
    }

    @RequestMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.getUserToken( loginRequest.getEmail(), loginRequest.getPassword() );
    }
}
