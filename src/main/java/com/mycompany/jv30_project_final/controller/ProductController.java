/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.controller;

import com.mycompany.jv30_project_final.entities.AccountEntity;
import com.mycompany.jv30_project_final.entities.BrandEntity;
import com.mycompany.jv30_project_final.entities.CategoryEntity;
import com.mycompany.jv30_project_final.entities.ColorEntity;
import com.mycompany.jv30_project_final.entities.ProductEntity;
import com.mycompany.jv30_project_final.service.BrandService;
import com.mycompany.jv30_project_final.service.CategoryService;
import com.mycompany.jv30_project_final.service.ColorService;
import com.mycompany.jv30_project_final.service.ProductService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author shuichi0906
 */
@Controller
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private BrandService brandService;

    @RequestMapping("/category/{page}")
    public String viewCategoryPage(Model model,
            @PathVariable("page") int page) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        model.addAttribute("categories", categoryService.getCategory());
        model.addAttribute("products", productService.getProductByPage(page));
        model.addAttribute("brands", brandService.getBrand());
        model.addAttribute("colors", categoryService.getColor());
        model.addAttribute("totalPage", productService.totalProduct(page));
        model.addAttribute("categoryId", 0);
        return "category";

    }

    @RequestMapping("/category/{categoryId}/{page}")
    public String viewCategoryDetail(Model model,
            @PathVariable("categoryId") int categoryId,
            @PathVariable("page") int page) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        CategoryEntity category = categoryService.findCategoryById(categoryId);
        model.addAttribute("categories", categoryService.getCategory());
        model.addAttribute("products", productService.findProductByCategory(category, page));
        model.addAttribute("brands", brandService.getBrand());
        model.addAttribute("colors", categoryService.getColor());
        model.addAttribute("totalPage", productService.totalProductByCategory(category, page));
        model.addAttribute("categoryId", categoryId);
        return "category";
    }

    @RequestMapping("/color/{colorId}/{page}")
    public String viewCategoryByColor(Model model,
            @PathVariable("colorId") int colorId,
            @PathVariable("page") int page) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        ColorEntity color = colorService.findColorById(colorId);
        model.addAttribute("categories", categoryService.getCategory());
        model.addAttribute("products", productService.findProductByColor(page, color));
        model.addAttribute("brands", brandService.getBrand());
        model.addAttribute("colors", categoryService.getColor());
        model.addAttribute("totalPage", productService.totalProductByColor(page, color));
        return "category_by_color";
    }

    @RequestMapping("/brand/{brandId}/{page}")
    public String viewCategoryByBrand(Model model,
            @PathVariable("brandId") int brandId,
            @PathVariable("page") int page) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        BrandEntity brand = brandService.findBrandById(brandId);
        model.addAttribute("categories", categoryService.getCategory());
        model.addAttribute("products", productService.findProductByBrand(page, brand));
        model.addAttribute("brands", brandService.getBrand());
        model.addAttribute("colors", categoryService.getColor());
        model.addAttribute("totalPage", productService.totalProductByBrand(brand, page));
        return "category_by_brand";
    }

    @RequestMapping("/product-detail/{productId}")
    public String viewProductDetail(Model model,
            @PathVariable("productId") int productId) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        model.addAttribute("product", productService.findProductByPromotion(new Date(), productId));
        return "product-detail";
    }

    @RequestMapping("/search")
    public String searchProduct(Model model,
            @ModelAttribute("searchTxt") String searchTxt) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        List<ProductEntity> products = productService.searchProductByName( searchTxt);
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getCategory());
        model.addAttribute("brands", brandService.getBrand());
        model.addAttribute("colors", categoryService.getColor());
        model.addAttribute("totalPage", products.size());
        return "category";
    }

}
