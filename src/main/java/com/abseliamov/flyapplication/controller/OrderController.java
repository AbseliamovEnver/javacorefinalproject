package com.abseliamov.flyapplication.controller;

import com.abseliamov.flyapplication.entity.Route;
import com.abseliamov.flyapplication.entity.Ticket;
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

    public void addOrder(Ticket ticket, Route route) {
        Order order = Order.newOrderBuilder()
                .setId(0)
                .setRouteId(route.getId())
                .setTicket(ticket)
                .setDepartureCity(route.getDepartureCity())
                .setArrivalCity(route.getArrivalCity())
                .setDepartureTime(route.getDepartureTime())
                .setArrivalTime(route.getArrivalTime())
                .build();
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

    public void deleteOrder(long orderId) {
        orderService.delete(orderService.getById(orderId));
    }

    public void orderConfirm(long routeId, int placeNumber) {
        orderService.orderConfirm(routeId, placeNumber);
    }

    public boolean getAllOrderingTicket() {
        return orderService.getAllOrderingTicket();
    }

    public long getRouteIdByOrderId(long orderId) {
        return orderService.getRouteIDByOrderId(orderId);
    }

    public Ticket getTicketByOrderId(long orderId) {
        return orderService.getTicketByOrderId(orderId);
    }
}
