package com.abseliamov.flyapplication.controller;

import com.abseliamov.flyapplication.utils.CurrentUser;
import com.abseliamov.flyapplication.entity.Order;
import com.abseliamov.flyapplication.service.OrderService;

import java.util.List;

public class OrderController {
    private OrderService orderService;
    private CurrentUser currentUser;

    public OrderController(OrderService orderService, CurrentUser currentUser) {
        this.orderService = orderService;
        this.currentUser = currentUser;
    }

    public void addOrder(Order order) {
        orderService.add(order);
    }

    public Order getOrderById(long id) {
        return orderService.getById(id);
    }

    public List<Order> getAllOrder() {
        return orderService.getAll();
    }

    public void updateOrder(Order order) {
        orderService.update(order);
    }

    public void deleteOrder(Order order) {
        orderService.delete(order);
    }

    public void orderConfirm(long routeId, int placeNumber) {
        orderService.orderConfirm(routeId, placeNumber);
    }
}
