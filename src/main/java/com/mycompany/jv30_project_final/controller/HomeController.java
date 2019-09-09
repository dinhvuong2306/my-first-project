/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.controller;

import com.mycompany.jv30_project_final.entities.AccountEntity;
import com.mycompany.jv30_project_final.service.ProductService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String viewHome(Model model) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        model.addAttribute("cheap", productService.getCheapestProduct());
        model.addAttribute("products", productService.getLatestProduct());
        return "home";
    }

    @RequestMapping("/login")
    public String viewLogin(Model model,
            @RequestParam(value = "error", required = false) boolean isError) {
        if (isError) {
            model.addAttribute("message", "login fail.");
        }
        return "login";
    }

    @RequestMapping("/registration")
    public String viewRegistration(Model model) {
        return "registration";
    }
}
