package com.app.app.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.app.model.Products;



public interface ProductRepository extends JpaRepository<Products,Long> {

}
