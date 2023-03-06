package com.example.springboot.service.impl;

import com.example.springboot.model.Category;
import com.example.springboot.repository.ICategoryRepository;
import com.example.springboot.service.ICoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICoreService<Category> {

    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public Page<Category> findAll(String name, Pageable pageable) {
        return iCategoryRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Category findOne(Long id) {
        return iCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Category category) {
        iCategoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        iCategoryRepository.deleteById(id);
    }
}
