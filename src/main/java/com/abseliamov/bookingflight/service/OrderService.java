package com.abseliamov.bookingflight.service;

import com.abseliamov.bookingflight.dao.OrderDAOImpl;
import com.abseliamov.bookingflight.dao.RouteDAOImpl;
import com.abseliamov.bookingflight.dao.TicketDAOImpl;
import com.abseliamov.bookingflight.entity.Order;
import com.abseliamov.bookingflight.entity.Ticket;
import com.abseliamov.bookingflight.entity.route.Route;
import com.abseliamov.bookingflight.utils.CurrentUser;

public class OrderService {
    private OrderDAOImpl orderDao;
    private RouteDAOImpl routeDAO;
    private TicketDAOImpl ticketDAO;
    private CurrentUser currentUser;

    public OrderService(OrderDAOImpl orderDao, CurrentUser currentUser) {
        this.orderDao = orderDao;
        this.currentUser = currentUser;
    }

    public void createOrder(long routeId, long ticketId) {
        Route route = routeDAO.getById(routeId);
        Ticket ticket = ticketDAO.getById(ticketId);
//        orderDao.create(new Order(, routeId, ticketId));
    }
}
