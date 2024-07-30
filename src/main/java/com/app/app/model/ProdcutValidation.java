package com.app.app.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class ProdcutValidation {
    
     @NotEmpty(message = "Name is required")  
    private String name;  
    
    @NotEmpty(message = "Category is required")  
    private String category;  

    @NotEmpty(message = "Brand is required")  
    private String brand;  

    @DecimalMin(value = "1", message = "Price must be greater than or equal to 0.0")  
    private BigDecimal price;  

    @Min(value = 1, message = "Quantity must be greater than or equal to 0")  
    private Integer qty;  


    private String imageFile;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    


}
