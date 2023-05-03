package com.example.springboot.dto;

import com.example.springboot.model.ProductEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductDTO {
    private Long id;
    private Long discountId;
    private String name;
    private Long price;
    private Integer taxPercentage;
    private Long quantity;
    private String description;
    private String photoUrlSmall;
    private String photoUrlMedium;
    private String photoUrlBig;
    private String amount;
    private BigDecimal weight;
    private BigDecimal height;
    private List<Long> categories;

    public ProductDTO() {}

    // GETTERS AND SETTERS

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(Integer taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrlSmall() {
        return photoUrlSmall;
    }

    public void setPhotoUrlSmall(String photoUrlSmall) {
        this.photoUrlSmall = photoUrlSmall;
    }

    public String getPhotoUrlMedium() {
        return photoUrlMedium;
    }

    public void setPhotoUrlMedium(String photoUrlMedium) {
        this.photoUrlMedium = photoUrlMedium;
    }

    public String getPhotoUrlBig() {
        return photoUrlBig;
    }

    public void setPhotoUrlBig(String photoUrlBig) {
        this.photoUrlBig = photoUrlBig;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    // toString() method

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", discountId=" + discountId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", taxPercentage=" + taxPercentage +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", photoUrlSmall='" + photoUrlSmall + '\'' +
                ", photoUrlMedium='" + photoUrlMedium + '\'' +
                ", photoUrlBig='" + photoUrlBig + '\'' +
                ", amount='" + amount + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}