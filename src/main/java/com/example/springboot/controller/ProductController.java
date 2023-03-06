package com.example.springboot.controller;

import com.example.springboot.model.Category;
import com.example.springboot.model.Product;
import com.example.springboot.service.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ICoreService<Product> productICoreService;

    @Autowired
    private ICoreService<Category> categoryICoreService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAllProduct(@PageableDefault Pageable pageable,
                                                        @RequestParam("search")Optional<String> name) {
        Page<Product> products;
        if (name.isPresent()) {
            products = productICoreService.findAll(name.get(), pageable);
        } else {
            products = productICoreService.findAll("", pageable);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<Category>> findAllCategory(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(categoryICoreService.findAll("", pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(productICoreService.findOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Product product) {
        productICoreService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productICoreService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
