package com.abseliamov.flyapplication.service;

import com.abseliamov.flyapplication.utils.CurrentUser;
import com.abseliamov.flyapplication.dao.OrderDao;
import com.abseliamov.flyapplication.dao.TicketDao;
import com.abseliamov.flyapplication.entity.Order;
import com.abseliamov.flyapplication.entity.Ticket;

import java.util.List;

public class OrderService implements ServiceInterface<Order> {
    private OrderDao orderDao;
    private TicketDao ticketDao;
    private CurrentUser currentUser;

    public OrderService(OrderDao orderDao, TicketDao ticketDao, CurrentUser currentUser) {
        this.orderDao = orderDao;
        this.ticketDao = ticketDao;
        this.currentUser = currentUser;
    }

    @Override
    public void add(Order order) {
        orderDao.create(order);
    }

    @Override
    public Order getById(long id) {
        return orderDao.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public void delete(Order order) {
        orderDao.delete(order);
    }

    public void orderConfirm(long ticketId) {
        Ticket ticket = ticketDao.getAll().stream()
                .filter(ticketOrder -> ticketOrder.getId() == ticketId)
                .findFirst().get();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf(" %-5s%-15s%-15s%-18s%-1s\n", "ID", "DEPARTURE CITY", "ARRIVAL CITY", "DEPARTURE TIME", "ARRIVAL TIME");
        System.out.printf(" %-5d%-15s%-15s%-18s%-1s\n",
                ticket.getId(),
                ticket.getRoute().getDepartureCity().getName(),
                ticket.getRoute().getArrivalCity().getName(),
                ticket.getRoute().getDepartureTime(),
                ticket.getRoute().getArrivalTime());
        System.out.println("|----------|------------|-------------|---------------|------------|");
        System.out.printf(" %-15s%-1s\n", "FIRST NAME", "LAST NAME", "ARRIVAL CITY", "DEPARTURE TIME", "ARRIVAL TIME");
        System.out.println("|----------|------------|-------------|---------------|------------|");
        System.out.printf(" %-15s%-1s\n",
                currentUser.getUser().getFirstName(),
                currentUser.getUser().getLastName());
        System.out.println("|-----|-----|------------|-------------|---------------|------------|");
        System.out.printf(" %-5s%-15s%-15s%-18s%-1s\n", "PLACE", "LOCATION", "TYPE SEAT", "BAGGAGE", "PRICE");
        System.out.println("|-----|-----|------------|-------------|---------------|------------|");
        System.out.printf(" %-5d%-15s%-15s%-18s%.2f\n",
                ticket.getPlaceNumber(),
                ticket.getLocation(),
                ticket.getTypeSeat(),
                ticket.getBaggage(),
                ticket.getPrice());
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }
}
