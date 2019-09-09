/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.service;

import com.mycompany.jv30_project_final.entities.BrandEntity;
import com.mycompany.jv30_project_final.repositories.BrandRepository;
import com.mycompany.jv30_project_final.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shuichi0906
 */
@Service
public class BrandService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private BrandRepository brandRepository;

    public List<BrandEntity> getBrand() {
        return (List<BrandEntity>) brandRepository.findAll();
    }
    
    public BrandEntity findBrandById(int brandId){
        BrandEntity brand = brandRepository.findOne(brandId);
        if(brand != null){
            brand.setProduct(productRepository.findByBrand(brand));
        }
        return brand;
    }
}
