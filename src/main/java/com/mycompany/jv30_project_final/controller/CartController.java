/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.controller;

import com.mycompany.jv30_project_final.entities.AccountEntity;
import com.mycompany.jv30_project_final.entities.OrderDetailEntity;
import com.mycompany.jv30_project_final.entities.OrderEntity;
import com.mycompany.jv30_project_final.entities.ProductEntity;
import com.mycompany.jv30_project_final.entities.ShippingEntity;
import com.mycompany.jv30_project_final.service.AccountService;
import com.mycompany.jv30_project_final.service.BrandService;
import com.mycompany.jv30_project_final.service.CategoryService;
import com.mycompany.jv30_project_final.service.OrderDetailService;
import com.mycompany.jv30_project_final.service.OrderService;
import com.mycompany.jv30_project_final.service.ProductService;
import com.mycompany.jv30_project_final.service.ShippingService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author shuichi0906
 */
@Controller
public class CartController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/view_shopping_cart")
    public String viewOrder(Model model, HttpSession session) {
        return "shopping-cart";
    }

    @RequestMapping(value = "/add-to-cart", method = RequestMethod.GET)
    public String orderProduct(Model model,
            @RequestParam("productId") Integer productId, HttpSession session) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        ProductEntity product = productService.findProductByPromotion(new Date(), productId);
        if (product != null) {
            int totalPrice = 0;
            OrderEntity order = (OrderEntity) session.getAttribute("order");
            if (order != null) {
                List<OrderDetailEntity> orderDetails = order.getOrderDetail();
                if (orderDetails != null && orderDetails.size() > 0) {
                    boolean productExist = false;
                    for (int i = 0; i < orderDetails.size(); i++) {

                        if (orderDetails.get(i).getProduct().getId() == productId) {
                            int temp = orderDetails.get(i).getQuantity();
                            orderDetails.get(i).setQuantity(temp + 1);
                            productExist = true;
                        }
                        totalPrice += (orderDetails.get(i).getQuantity()) * (orderDetails.get(i).getPrice());

                    }
                    if (productExist == false) {
                        OrderDetailEntity addOrderDetail = new OrderDetailEntity();
                        addOrderDetail.setPrice(product.getPrice() - product.getPromotion().get(0).getDiscount());
                        addOrderDetail.setQuantity(1);
                        addOrderDetail.setOrder(order);
                        addOrderDetail.setProduct(product);
                        orderDetails.add(addOrderDetail);
                        totalPrice += product.getPrice();
                    }
                    order.setTotalPrice(totalPrice);
                    order.setOrderDetail(orderDetails);
                }
                session.setAttribute("order", order);
            } else {
                order = new OrderEntity();
                order.setOrderDate(new Date());
                List<OrderDetailEntity> orderDetails = new ArrayList<>();
                OrderDetailEntity detail = new OrderDetailEntity();
                detail.setOrder(order);
                detail.setPrice(product.getPrice() - product.getPromotion().get(0).getDiscount());
                detail.setProduct(product);
                detail.setQuantity(1);

                orderDetails.add(detail);
                order.setOrderDetail(orderDetails);
                order.setTotalPrice(detail.getPrice());
                session.setAttribute("order", order);

            }
        }

        return "shopping-cart";
    }

    @RequestMapping(value = "update-quantity/{productId}", method = RequestMethod.POST)
    public String updateQuantity(Model model, HttpServletRequest request,
            HttpSession session, @PathVariable("productId") int productId,
            @ModelAttribute("quantity") int quantity) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        OrderEntity order = (OrderEntity) session.getAttribute("order");
        ProductEntity product = productService.findProductByPromotion(new Date(), productId);
        if (order != null) {
            int totalPrice = order.getTotalPrice();
            int oldQuantity;
            for (OrderDetailEntity orderDetail : order.getOrderDetail()) {
                if (orderDetail.getProduct().getId() == productId) {
                    oldQuantity = orderDetail.getQuantity();
                    orderDetail.setQuantity(quantity);
                    order.setTotalPrice(totalPrice + (orderDetail.getQuantity() * product.getPrice())
                            - (oldQuantity * product.getPrice()));
                    break;
                }
            }
            session.setAttribute("order", order);
            model.addAttribute("order", order);
        }
        return "shopping-cart";
    }

    @RequestMapping(value = "/delete/{productId}")
    public String deleteProduct(Model model,
            @PathVariable("productId") int productId, HttpSession session) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        OrderEntity order = (OrderEntity) session.getAttribute("order");
        ProductEntity product = productService.findProductByPromotion(new Date(), productId);
        if (order != null) {
            int totalPrice = order.getTotalPrice();
            for (OrderDetailEntity orderDetail : order.getOrderDetail()) {
                if (orderDetail.getProduct().getId() == productId) {
                    order.getOrderDetail().remove(orderDetail);
                    order.setTotalPrice(totalPrice - (product.getPrice() * orderDetail.getQuantity()));
                    break;
                }
            }
            session.setAttribute("order", order);
            model.addAttribute("order", order);
        }

        return "shopping-cart";
    }

    @RequestMapping("/continue")
    public String continueOrder(Model model, HttpSession session) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        OrderEntity order = (OrderEntity) session.getAttribute("order");
        model.addAttribute("products", productService.getLatestProduct());

        if (order == null) {
            return "home";
        }
        if (order.getOrderDetail().isEmpty()) {
            session.invalidate();
        }

        return "home";
    }

    @RequestMapping("/view-checkout")
    public String viewCheckout(Model model, HttpSession session
    ) {
        OrderEntity order = (OrderEntity) session.getAttribute("order");
        model.addAttribute("order", order);
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            String email = account.getEmail();
            AccountEntity acc = accountService.findAccountByEmail(email);
            ShippingEntity shipping = acc.getShipping().get(0);
            model.addAttribute("shipping", shipping);
            model.addAttribute("account", account);
        }
        return "checkout";
    }

    @RequestMapping(value = "/proceed", method = RequestMethod.POST)
    public String viewCheckout(Model model, HttpSession session,
            HttpServletRequest request,
            @ModelAttribute("shipping") ShippingEntity shipping,
            @ModelAttribute("note") String note) {
        Object principal
                = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AccountEntity) {
            AccountEntity account = (AccountEntity) principal;
            model.addAttribute("account", account);
        }
        OrderEntity order = (OrderEntity) session.getAttribute("order");
        String email = shipping.getEmail();
        AccountEntity acc = accountService.findAccountByEmail(email);
        if (acc == null) {
            shipping.setAccount(null);
        } else {
            shipping.setAccount(acc);
        }

        // save shipping
        ShippingEntity shipinfo = shippingService.saveShipping(shipping);
        //
        order.setNote(note);
        order.setShipping(shipinfo);
        order.setStatus("ORDERED");
        //save order
        orderService.saveOrder(order);

        //save orderDetail
        for (OrderDetailEntity orderDetailEntity : order.getOrderDetail()) {
            orderDetailEntity.setOrder(order);
            orderDetailService.saveOrderDetail(orderDetailEntity);
        }

//        session.removeAttribute("order");
        try {
            MimeMessage massage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(massage, true);
            helper.setFrom(" <gladiator0906@gmail.com>");
            helper.setTo(shipping.getEmail());
            helper.setReplyTo(" NuHaShop <gladiator0906@gmail.com>");
            helper.setSubject("Order of: " + shipping.getFullName());
            String url = "https://www.facebook.com/NuHachatluongtaothuonghieu/";

            String text = "<h3> Thank you for your shopping at NuHaShop</h3>"
                    + "<br> Your order_detail:"
                    + "<br> Total-price:" + order.getTotalPrice() + "VNĐ"
                    + "<br> Shipping-address:" + shipping.getAddress()
                    + "<br> Phone number:" + shipping.getPhoneNumber()
                    + "<br> Please click here to follow your order: " + url;

            helper.setText(text, true);
            massage.setContent(text, "text/html");
            mailSender.send(massage);
        } catch (Exception ex) {

        }

        model.addAttribute("order", order);
        session.removeAttribute("order");
        return "order-confirm";
    }

}
