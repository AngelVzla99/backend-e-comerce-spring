package com.example.springboot.dto;

import java.util.List;

public class HomeDTO {
    private List<ProductDTO> mostBought;
    private List<CategoryDTO> categories;
    private List<ProductDTO> bestDeals;
    private List<String> bannerUrls;

    public HomeDTO() {
    }

    public List<ProductDTO> getMostBought() {
        return mostBought;
    }

    public void setMostBought(List<ProductDTO> mostBought) {
        this.mostBought = mostBought;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<ProductDTO> getBestDeals() {
        return bestDeals;
    }

    public void setBestDeals(List<ProductDTO> bestDeals) {
        this.bestDeals = bestDeals;
    }

    public List<String> getBannerUrls() {
        return bannerUrls;
    }

    public void setBannerUrls(List<String> bannerUrls) {
        this.bannerUrls = bannerUrls;
    }
}
