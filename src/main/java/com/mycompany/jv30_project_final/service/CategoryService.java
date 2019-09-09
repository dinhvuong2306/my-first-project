/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.service;

import com.mycompany.jv30_project_final.entities.BrandEntity;
import com.mycompany.jv30_project_final.entities.CategoryEntity;
import com.mycompany.jv30_project_final.entities.ColorEntity;
import com.mycompany.jv30_project_final.repositories.BrandRepository;
import com.mycompany.jv30_project_final.repositories.CategoryRepository;
import com.mycompany.jv30_project_final.repositories.ColorRepository;
import com.mycompany.jv30_project_final.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shuichi0906
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ColorRepository colorRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<CategoryEntity> getCategory(){
        List<CategoryEntity> categories =(List<CategoryEntity>) categoryRepository.findAll();
        if(categories != null && categories.size()>0){
            for(CategoryEntity category : categories){
                category.setProduct(productRepository.findByCategory(category));
            }
        }
        return categories;
    }
    
    public CategoryEntity findCategoryById(int categoryId){
        CategoryEntity category = categoryRepository.findOne(categoryId);
        if(category != null){
            category.setProduct(productRepository.findByCategory(category));
        }
        return category;
    }
    
    public List<ColorEntity> getColor(){
        return (List<ColorEntity>) colorRepository.findAll();
    }
}
