package com.app.app.model;
import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;


@Entity
@Table(name="products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name",nullable=true)
    private String name;
    @Column(name="brands",nullable=true)
    private String brand;
    @Column(name="categories",nullable=true)
    private String category;
    @Column(name="price",nullable=true)
    private BigDecimal price;
    @Column(name="qty",nullable=true)
    private  Integer qty;

    private String imageFile;

    


    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getBrand(){
        return brand;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setQty(Integer qty){
        this.qty = qty;
    }

    public Integer getQty(){
        return qty;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }





   


    


}
