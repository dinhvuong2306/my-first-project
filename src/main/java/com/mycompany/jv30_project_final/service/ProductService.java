/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.service;

import com.mycompany.jv30_project_final.entities.BrandEntity;
import com.mycompany.jv30_project_final.entities.CategoryEntity;
import com.mycompany.jv30_project_final.entities.ColorEntity;
import com.mycompany.jv30_project_final.entities.ProductEntity;
import com.mycompany.jv30_project_final.entities.PromotionEntity;
import com.mycompany.jv30_project_final.repositories.CategoryRepository;
import com.mycompany.jv30_project_final.repositories.ImageRepository;
import com.mycompany.jv30_project_final.repositories.ProductRepository;
import com.mycompany.jv30_project_final.repositories.PromotionRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author shuichi0906
 */
@Service
public class ProductService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository imageRepository;


    public List<ProductEntity> getProductByPage(int page) {
        Page<ProductEntity> pagePro = productRepository.findAll(new PageRequest(page, 12));
        List<ProductEntity> products = pagePro.getContent();
        if (products != null && products.size() > 0) {
            for (ProductEntity product : products) {
                product.setImage(imageRepository.findByProduct(product));
                product.setPromotion(promotionRepository.findByProduct(product));
            }
        }
        return products;
    }

    public int totalProduct(int page) {
        Page<ProductEntity> pagePro = productRepository.findAll(new PageRequest(page, 12));
        return pagePro.getTotalPages();
    }

    public List<ProductEntity> findProductByColor(int page, ColorEntity color) {
        Page<ProductEntity> pagePro = productRepository.findByColor(new PageRequest(page, 12), color);
        List<ProductEntity> products = pagePro.getContent();
        if (products != null && products.size() > 0) {
            for (ProductEntity product : products) {
                product.setImage(imageRepository.findByProduct(product));
                product.setPromotion(promotionRepository.findByProduct(product));
            }
        }
        return products;
    }

    public int totalProductByColor(int page, ColorEntity color) {
        Page<ProductEntity> pagePro = productRepository.findByColor(new PageRequest(page, 12), color);
        return pagePro.getTotalPages();
    }

    public List<ProductEntity> findProductByCategory(CategoryEntity category, int page) {
        Page<ProductEntity> pagePro = productRepository.findByCategory(new PageRequest(page, 12), category);
        List<ProductEntity> products = pagePro.getContent();
        if (products != null && products.size() > 0) {
            for (ProductEntity product : products) {
                product.setImage(imageRepository.findByProduct(product));
                product.setPromotion(promotionRepository.findByProduct(product));
            }
        }
        return products;
    }

    public int totalProductByCategory(CategoryEntity category, int page) {
        Page<ProductEntity> pagePro = productRepository.findByCategory(new PageRequest(page, 12), category);
        return pagePro.getTotalPages();
    }

    public List<ProductEntity> findProductByBrand(int page, BrandEntity brand) {
        Page<ProductEntity> pagePro = productRepository.findByBrand(new PageRequest(page, 12), brand);
        List<ProductEntity> products = pagePro.getContent();
        if (products != null && products.size() > 0) {
            for (ProductEntity product : products) {
                product.setImage(imageRepository.findByProduct(product));
                product.setPromotion(promotionRepository.findByProduct(product));
            }
        }
        return products;
    }

    public int totalProductByBrand(BrandEntity brand, int page) {
        Page<ProductEntity> pagePro = productRepository.findByBrand(new PageRequest(page, 12), brand);
        return pagePro.getTotalPages();
    }

    public List<ProductEntity> searchProductByName(String searchTxt) {
        List<ProductEntity> products = productRepository.findByNameContaining("%" + searchTxt + "%",
                "%" + searchTxt + "%");
        if (products != null && products.size() > 0) {
            for (ProductEntity product : products) {
                product.setImage(imageRepository.findByProduct(product));
                product.setPromotion(promotionRepository.findByProduct(product));
            }
        }
        return products;
    }

    public ProductEntity findProductByPromotion(Date orderDate, int productId) {
        ProductEntity product = productRepository.findProductByPromotion(orderDate, productId);
        if (product != null) {
            product.setImage(imageRepository.findByProduct(product));
            product.setCategory(categoryRepository.findByProduct(product));
            List<PromotionEntity> promo = product.getPromotion();
            PromotionEntity promotion = promo.get(0);

            List<PromotionEntity> pro = new ArrayList<>();
            pro.add(promotion);
            product.setPromotion(pro);
        } else {
            product = productRepository.findOne(productId);
            PromotionEntity promotion = new PromotionEntity();
            promotion.setDiscount(0);
            List<PromotionEntity> pro = new ArrayList<>();
            pro.add(promotion);
            product.setPromotion(pro);
            product.setImage(imageRepository.findByProduct(product));
            product.setCategory(categoryRepository.findByProduct(product));

        }
        return product;
    }

    public List<ProductEntity> getLatestProduct() {
        List<ProductEntity> products = productRepository.getLatestProduct();
        if (products != null && products.size() > 0) {
            for (ProductEntity product : products) {
                product.setImage(imageRepository.findByProduct(product));
                product.setPromotion(promotionRepository.findByProduct(product));
            }
        }
        return products;
    }

    public List<ProductEntity> getCheapestProduct(){
        List<ProductEntity> products = productRepository.getCheapestProduct();
        if (products != null && products.size() > 0) {
            for (ProductEntity product : products) {
                product.setImage(imageRepository.findByProduct(product));
                product.setPromotion(promotionRepository.findByProduct(product));
            }
        }
        return products;
    }
}
