/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv30_project_final.service;

import com.mycompany.jv30_project_final.entities.OrderDetailEntity;
import com.mycompany.jv30_project_final.entities.OrderEntity;
import com.mycompany.jv30_project_final.entities.ShippingEntity;
import com.mycompany.jv30_project_final.repositories.OrderDetailRepository;
import com.mycompany.jv30_project_final.repositories.OrderRepository;
import com.mycompany.jv30_project_final.repositories.ShippingRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author shuichi0906
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    private static final Logger LOG = Logger.getLogger(OrderService.class);

    public List<OrderEntity> getOrders() {
        return (List<OrderEntity>) orderRepository.findAll();
    }

    public OrderDetailEntity saveOrderDetail(OrderDetailEntity orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderEntity order) {
        order = orderRepository.save(order);
        if (order.getId() > 0) {
            for (OrderDetailEntity orderDetail : order.getOrderDetail()) {
                orderDetail.setOrder(order);
                orderDetailRepository.save(orderDetail);
            }
        }
    }

    public List<OrderEntity> findOrderByAccount(String email) {
        List<OrderEntity> orders = orderRepository.findByAccount(email);
        for (OrderEntity order : orders) {
            order.setShipping(shippingRepository.findByOrder(order));
            order.setOrderDetail(orderDetailRepository.findByOrder(order));
        }
        return orders;
    }

    public OrderEntity findOrderById(int orderId) {
        OrderEntity order = orderRepository.findOne(orderId);
        if (order != null) {
            order.setOrderDetail(orderDetailRepository.findByOrder(order));
            order.setShipping(shippingRepository.findByOrder(order));
        }
        return order;
    }

}
