/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.service;

import com.mycompany.jv30_project_final.entities.OrderDetailEntity;
import com.mycompany.jv30_project_final.entities.OrderEntity;
import com.mycompany.jv30_project_final.entities.ProductEntity;
import com.mycompany.jv30_project_final.repositories.OrderDetailRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shuichi0906
 */
@Service
public class OrderDetailService {
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetailEntity setOrderDetail(ProductEntity product, OrderEntity order){
        OrderDetailEntity orderDetail = new OrderDetailEntity();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(1);
        orderDetail.setPrice(product.getPrice());
        return orderDetail;
    }
    
    public OrderDetailEntity saveOrderDetail(OrderDetailEntity orderDetail){
        return orderDetailRepository.save(orderDetail);
    }

    
}
