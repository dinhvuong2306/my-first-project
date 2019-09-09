/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.service;

import com.mycompany.jv30_project_final.entities.ShippingEntity;
import com.mycompany.jv30_project_final.repositories.ShippingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shuichi0906
 */
@Service
public class ShippingService {
    
    @Autowired
    private ShippingRepository shippingRepository;

    public List<ShippingEntity> getShipping(){
        return (List<ShippingEntity>) shippingRepository.findAll();
    }
    
    public ShippingEntity saveShipping(ShippingEntity shipping){
        return shippingRepository.save(shipping);
    }
}
