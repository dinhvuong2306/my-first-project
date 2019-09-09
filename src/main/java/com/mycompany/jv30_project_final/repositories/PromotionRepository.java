/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.repositories;

import com.mycompany.jv30_project_final.entities.ProductEntity;
import com.mycompany.jv30_project_final.entities.PromotionEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shuichi0906
 */
@Repository
public interface PromotionRepository extends 
        CrudRepository<PromotionEntity, Integer>{
    
  
    public List<PromotionEntity> findByProduct(ProductEntity product);
    
//    @Query(value = "Select * From promotion p "
//            + "where ?1 Between p.start_date And p.end_date",
//            nativeQuery = true)
//    public PromotionEntity findByOrderDate(Date orderDate);
    
    
    
}
