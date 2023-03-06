package com.example.springboot.service.impl;

import com.example.springboot.model.Product;
import com.example.springboot.repository.IProductRepository;
import com.example.springboot.service.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ICoreService<Product> {

    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public Page<Product> findAll(String name, Pageable pageable) {
        return iProductRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Product findOne(Long id) {
        return iProductRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        iProductRepository.deleteById(id);
    }
}
