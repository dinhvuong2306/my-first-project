/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.controller;

import com.mycompany.jv30_project_final.entities.AccountEntity;
import com.mycompany.jv30_project_final.entities.OrderEntity;
import com.mycompany.jv30_project_final.service.AccountService;
import com.mycompany.jv30_project_final.service.OrderService;
import com.mycompany.jv30_project_final.service.ProductService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/"})
    public String viewHome(Model model) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountEntity account = (AccountEntity) principal;
        model.addAttribute("account", account);
        model.addAttribute("products", productService.getLatestProduct());
        return "home";
    }

    @RequestMapping("/view-list-order")
    public String viewListOrder(Model model) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountEntity account = (AccountEntity) principal;
        String email = account.getEmail();
        model.addAttribute("account", account);
        model.addAttribute("orders", orderService.findOrderByAccount(email));
        return "user/view-order";
    }

    @RequestMapping(value = "/view-detail/{orderId}")
    public String viewOrderDetail(Model model,
            @PathVariable("orderId") int orderId) {
        model.addAttribute("order", orderService.findOrderById(orderId));
        return "user/order-detail";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchByDate(Model model,
            @ModelAttribute("fromDate") String fromDate,
            @ModelAttribute("toDate") String toDate) throws ParseException {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountEntity account = (AccountEntity) principal;
        String email = account.getEmail();
        List<OrderEntity> orders = orderService.findOrderByAccount(email);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = format.parse(fromDate);
        Date dateTo = format.parse(toDate);
        List<OrderEntity> list = new ArrayList<>();
        for (OrderEntity order : orders) {
            Date orderDate = order.getOrderDate();
            if (orderDate.after(dateFrom) == true && orderDate.before(dateTo) == true) {
                list.add(order);
            }
        }
        model.addAttribute("account", account);
        model.addAttribute("orders", list);
        return "user/view-order";
    }
    
    @RequestMapping("/changeAccountInfo")
    public String viewChangePage(Model model,
            @RequestParam(value = "message", required = false) String message,
            @RequestParam(value = "status", required = false) String status){
        model.addAttribute("message", message);
        model.addAttribute("status", status);
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountEntity account = (AccountEntity) principal;
        model.addAttribute("account", account);
        return "user/change-account-info";
    }
    
    @RequestMapping(value = "/change-info", method = RequestMethod.POST)
    public String viewConfirm(Model model,
            @ModelAttribute("account") AccountEntity account){
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountEntity acc = (AccountEntity) principal;
        account.setAccountRoles(acc.getAccountRoles());
        account.setId(acc.getId());
        account = accountService.saveAccount(account);
        if (account.getId() > 0){
            model.addAttribute("account", account);
            return "redirect:/user/changeAccountInfo?message=change your account information is successful"
                    + "&status=success";
        } else {
            model.addAttribute("account", acc);
            return "redirect:/user/changeAccountInfo?message=change your account information is fail"
                    + "&status=fail";
        }
    }
}
