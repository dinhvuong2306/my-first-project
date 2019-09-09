/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.repositories;

import com.mycompany.jv30_project_final.entities.OrderEntity;
import com.mycompany.jv30_project_final.entities.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shuichi0906
 */
@Repository
public interface OrderRepository extends 
        CrudRepository<OrderEntity, Integer>{

    @Query(value = "Select * From orders o "
            + "inner join shipping s on o.shipping_id = s.id "
            + "where s.email = ?1", nativeQuery = true)
    public List<OrderEntity> findByAccount(String email);
    
    
    
}
