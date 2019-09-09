/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.repositories;

import com.mycompany.jv30_project_final.entities.AccountEntity;
import com.mycompany.jv30_project_final.entities.OrderEntity;
import com.mycompany.jv30_project_final.entities.ShippingEntity;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shuichi0906
 */
@Repository
public interface ShippingRepository extends 
        CrudRepository<ShippingEntity, Integer>{
    
    public List<ShippingEntity> findByAccount(AccountEntity account);

    public ShippingEntity findByOrder(OrderEntity order);
}
