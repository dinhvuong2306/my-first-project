/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.repositories;

import com.mycompany.jv30_project_final.entities.OrderDetailEntity;
import com.mycompany.jv30_project_final.entities.OrderEntity;
import com.mycompany.jv30_project_final.entities.ProductEntity;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shuichi0906
 */
@Repository
public interface OrderDetailRepository extends 
        CrudRepository<OrderDetailEntity, Integer>{
    
    public List<OrderDetailEntity> findByOrder(OrderEntity order);
    
    public List<OrderDetailEntity> findByProduct(ProductEntity product);

    @Query(value = "Select * From order_detail od "
            + "inner join orders o on od.order_id = o.id "
            + "where o.id = ?1", nativeQuery = true)
    public List<OrderDetailEntity> findByOrderId(int orderId);
}
