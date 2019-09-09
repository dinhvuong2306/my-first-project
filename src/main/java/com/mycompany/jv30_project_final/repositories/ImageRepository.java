/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.repositories;

import com.mycompany.jv30_project_final.entities.ImageEntity;
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
public interface ImageRepository extends
        CrudRepository<ImageEntity, Integer> {

    @Query(value = "select * from image i "
            + "where i.product_id = ?1 "
            + "order by i.id Asc", nativeQuery = true)
    public List<ImageEntity> findByProductId(int productId);

    public List<ImageEntity> findByProduct(ProductEntity product);
}
