package com.example.springboot.dto;

import com.example.springboot.model.ProductEntity;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProductDTO {
    private Long id;
    private Long discountId;
    @NotNull(message = "name is required")
    private String name;
    @DecimalMin(value = "0", message = "price must be greater than or equal to 0")
    private Long price;
    @DecimalMin(value = "0", message = "taxPercentage must be greater than or equal to 0")
    @DecimalMax(value = "100", message = "taxPercentage must be less than or equal to 100")
    private Integer taxPercentage;
    @NotNull(message = "quantity is required")
    @DecimalMin(value = "0", message = "quantity must be 0")
    @DecimalMax(value = "0", message = "quantity must be 0")
    private Long quantity = 0L;
    @NotNull(message = "description is required")
    private String description;
    private String photoUrlSmall;
    private String photoUrlMedium;
    private String photoUrlBig;
    private String amount;
    private BigDecimal weight;
    private BigDecimal height;
    @NotNull(message = "categories is required")
    @Size(min = 1, message = "at least one category is required")
    private List<Long> categories;

    public ProductDTO() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDTO that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDiscountId(), that.getDiscountId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getTaxPercentage(), that.getTaxPercentage()) && Objects.equals(getQuantity(), that.getQuantity()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getPhotoUrlSmall(), that.getPhotoUrlSmall()) && Objects.equals(getPhotoUrlMedium(), that.getPhotoUrlMedium()) && Objects.equals(getPhotoUrlBig(), that.getPhotoUrlBig()) && Objects.equals(getAmount(), that.getAmount()) && Objects.equals(getWeight(), that.getWeight()) && Objects.equals(getHeight(), that.getHeight()) && Objects.equals(getCategories(), that.getCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDiscountId(), getName(), getPrice(), getTaxPercentage(), getQuantity(), getDescription(), getPhotoUrlSmall(), getPhotoUrlMedium(), getPhotoUrlBig(), getAmount(), getWeight(), getHeight(), getCategories());
    }

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