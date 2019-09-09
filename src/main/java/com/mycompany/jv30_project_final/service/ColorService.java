/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.service;

import com.mycompany.jv30_project_final.entities.ColorEntity;
import com.mycompany.jv30_project_final.repositories.ColorRepository;
import com.mycompany.jv30_project_final.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shuichi0906
 */
@Service
public class ColorService {
 
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ColorRepository colorRepository;
    
    public ColorEntity findColorById(int colorId){
        ColorEntity color = colorRepository.findOne(colorId);
        if(color != null ){
            color.setProduct(productRepository.findByColor(color));
        }
        return color;
    }
}
