package com.app.app.controller;

import org.springframework.boot.autoconfigure.ssl.SslProperties.Bundles.Watch.File;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
// import java.io.File;
import java.util.List;
import java.util.Optional;

import java.util.UUID;
import com.app.app.model.ProdcutValidation;
import com.app.app.model.Products;
import com.app.app.services.ProductRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductRepository repo;

    public ProductsController(ProductRepository repo) {
    this.repo = repo;
    }

    @GetMapping("/create")
    public String create(Model model){
        ProdcutValidation validator = new ProdcutValidation();
        model.addAttribute("validator",validator); // Creating new instance of Product model to send to form
        return "create";
    }


   @PostMapping("/create")
   public String store(@Valid @ModelAttribute("validator") ProdcutValidation validator,BindingResult result, HttpSession session, MultipartFile image) {
    if (result.hasErrors()) {
        return "create"; // Return to the form view with errors
    }

    // Validation for uploaded image (optional)
    if (image.isEmpty()) {
        result.addError(new FieldError("validator", "imageFile", "Please select an image to upload"));
        return "create"; // Return to the form view with errors
    }

        // Logic to save the product should be added here
        Products product = new Products();
        product.setName(validator.getName());
        product.setCategory(validator.getCategory());
        product.setBrand(validator.getBrand());
        product.setPrice(validator.getPrice());
        product.setQty(validator.getQty());

    // Image upload logic
        try {
            // Configure upload directory (outside of the application's root directory for security)
            String uploadDir = "public/images/"; // Replace with your actual directory path
           
        
            String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

            Path uploadPath = Paths.get(uploadDir,filename);

           

            // Create upload directory if it doesn't exist
            Files.createDirectories(uploadPath.getParent()); // Creates parent directories if needed

            // Save uploaded image
            image.transferTo(uploadPath);

            // Set image path on product (consider a separate field or adjust based on your model)
            product.setImageFile(filename); // Or adjust based on your image path structure

        } catch (IOException e) {
            // Handle upload exception appropriately, e.g., log the error and return a user-friendly message
            result.addError(new FieldError("validator", "imageFile", "Error uploading image: " + e.getMessage()));
            return "create"; // Return to the form view with errors
        }

        session.setAttribute("success", "Product added successfully.");
        repo.save(product);
        return "redirect:/products";
    }
   

    @GetMapping
    public String getProducts(Model model,HttpSession session){
        List<Products> products = repo.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("products", products);

        String message = (String) session.getAttribute("success");
        if(message != null){
            session.removeAttribute("success");
            model.addAttribute("success",message);
        }
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        Products product = repo.findById(id).get();
        model.addAttribute("product", product);
        return "edit";
    }

    @PostMapping("/update/{id}") // Ensure this mapping is correct
    public String update(@PathVariable Long id, @Valid @ModelAttribute("validator") ProdcutValidation validator,BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
           return "edit"; // Return to edit view if there are validation errors
        }

        Products product = repo.findById(id).get();

        // Update product details
        product.setName(validator.getName());
        product.setCategory(validator.getCategory());
        product.setBrand(validator.getBrand());
        product.setPrice(validator.getPrice());
        product.setQty(validator.getQty());

        repo.save(product); // Save the updated product

        session.setAttribute("success", "Product updated successfully."); // Set message in session
        return "redirect:/products"; // Redirect to the product listing page
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Products product = repo.findById(id).get();
        repo.delete(product);
        return "redirect:/products";
    }







    

}