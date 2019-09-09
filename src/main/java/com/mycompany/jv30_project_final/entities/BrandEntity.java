/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author shuichi0906
 */
@Entity
@Table(name = "brand")
public class BrandEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "brand",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<ProductEntity> product;

    public BrandEntity() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ProductEntity> getProduct() {
        return product;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProduct(List<ProductEntity> product) {
        this.product = product;
    }
    
    
}
