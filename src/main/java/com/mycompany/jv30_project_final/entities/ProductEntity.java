/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author shuichi0906
 */
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column(length = 1000)
    private String description;

    private int price;

    @Column(name = "quantity_in_store")
    private int quantityInStory;

    private String size;

    @Column(length = 100)
    private String status;

    @Column(name = "create_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @OneToMany(mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<OrderDetailEntity> orderDetail;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<ImageEntity> image;

    @ManyToMany(cascade = {CascadeType.PERSIST,
        CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "pro_color_relationship",
            joinColumns = @JoinColumn(name = "pro_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "color_id",
                    referencedColumnName = "id"))
    private List<ColorEntity> color;

    @ManyToMany(cascade = {CascadeType.PERSIST,
        CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "product_promotion_relationship",
            joinColumns = @JoinColumn(name = "product_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "promotion_id",
                    referencedColumnName = "id"))
    private List<PromotionEntity> promotion;

    public String getStatus() {
        return status;
    }

    public List<OrderDetailEntity> getOrderDetail() {
        return orderDetail;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public List<ImageEntity> getImage() {
        return image;
    }

    public List<ColorEntity> getColor() {
        return color;
    }

    public List<PromotionEntity> getPromotion() {
        return promotion;
    }

    public int getPrice() {
        return price;
    }
    

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOrderDetail(List<OrderDetailEntity> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public void setImage(List<ImageEntity> image) {
        this.image = image;
    }

    public void setColor(List<ColorEntity> color) {
        this.color = color;
    }

    public void setPromotion(List<PromotionEntity> promotion) {
        this.promotion = promotion;
    }

    public ProductEntity() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantityInStory() {
        return quantityInStory;
    }

    public String getSize() {
        return size;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantityInStory(int quantityInStory) {
        this.quantityInStory = quantityInStory;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}
