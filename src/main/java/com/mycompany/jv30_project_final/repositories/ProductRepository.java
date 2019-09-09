/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.repositories;

import com.mycompany.jv30_project_final.entities.BrandEntity;
import com.mycompany.jv30_project_final.entities.CategoryEntity;
import com.mycompany.jv30_project_final.entities.ColorEntity;
import com.mycompany.jv30_project_final.entities.ProductEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shuichi0906
 */
@Repository
public interface ProductRepository extends
        CrudRepository<ProductEntity, Integer> {

    @Query(value = "Select * From product p "
            + "inner join category c on p.category_id = c.id "
            + "where p.name Like ?1 or c.name Like ?2 "
            + "order by p.id Asc", nativeQuery = true)
    public List<ProductEntity>
            findByNameContaining(String name1, String name2);

    @Query(value = "Select * From product p "
            + "order by p.create_at Desc "
            + "limit 4", nativeQuery = true)
     List<ProductEntity> getLatestProduct();
    
    @Query(value = "Select * From product p "
            + "order by p.price Asc "
            + "limit 8",nativeQuery = true)
     List<ProductEntity> getCheapestProduct();

     Page<ProductEntity> findByCategory(Pageable pageable, CategoryEntity category);

     List<ProductEntity> findByCategory(CategoryEntity category);

     List<ProductEntity> findByBrand(BrandEntity brand);

     Page<ProductEntity> findByBrand(Pageable pageable, BrandEntity brand);
    
     Page<ProductEntity> findByColor(Pageable pageable, ColorEntity color);
    
     List<ProductEntity> findByColor(ColorEntity color);

     Page<ProductEntity> findAll(Pageable pageable);

    @Query(value = "SELECT product FROM ProductEntity product "
            + "LEFT JOIN FETCH product.promotion promition "
            + "WHERE promition.startDate <= ?1 AND promition.endDate >= ?1 "
            + "AND product.id = ?2")
    ProductEntity findProductByPromotion(Date orderDate, int proId);
    
}
