package com.abseliamov.bookingflight.controller;

import com.abseliamov.bookingflight.service.OrderService;
import com.abseliamov.bookingflight.utils.CurrentUser;

public class OrderController {
    private OrderService orderService;
    private CurrentUser currentUser;

    public OrderController(OrderService orderService, CurrentUser currentUser) {
        this.orderService = orderService;
        this.currentUser = currentUser;
    }

    public void createOrder(long routeId, long ticketId) {
        orderService.createOrder(routeId, ticketId);
    }
}
