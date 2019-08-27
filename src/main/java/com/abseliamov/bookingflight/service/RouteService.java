package com.abseliamov.bookingflight.service;

import com.abseliamov.bookingflight.dao.CityDAOImpl;
import com.abseliamov.bookingflight.dao.RouteDAOImpl;
import com.abseliamov.bookingflight.dao.TicketDAOImpl;
import com.abseliamov.bookingflight.entity.Ticket;
import com.abseliamov.bookingflight.entity.TypeCabin;
import com.abseliamov.bookingflight.entity.route.Route;
import com.abseliamov.bookingflight.utils.CurrentUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RouteService {
    private RouteDAOImpl routeDAO;
    private CityDAOImpl cityDAOImpl;
    private CityService cityService;
    private TicketDAOImpl ticketDAO;
    private CurrentUser currentUser;
    private List<Ticket> cities;

    public RouteService(RouteDAOImpl routeDAO, CityService cityService, CurrentUser currentUser) {
        this.routeDAO = routeDAO;
        this.cityService = cityService;
        this.currentUser = currentUser;
        this.cityDAOImpl = cityDAOImpl;
    }

//    public Ticket getCity(int cityId) {
//        Ticket ticket = null;
////        cities = cityDAOImpl.getAll();
//        if (!cities.isEmpty()) {
//            for (Ticket ticketItem : cities) {
//                if (ticketItem.getId() == cityId) {
//                    ticket = ticketItem;
//                    break;
//                }
//            }
//        } else {
//            System.out.println("List of cities is empty.");
//        }
//        return ticket;
//    }

    public List<Route> getRouteByRequest(long departureCityId, long arrivalCityId, LocalDateTime timeDeparture,
                                         TypeCabin typeCabin, int numberPassengers) {
        List<Route> routes = routeDAO.getAll().stream()
                .filter(route -> route.getDepartureCityId() == departureCityId)
                .filter(route -> route.getArrivalCityId() == arrivalCityId)
                .filter(route -> route.getTimeDeparture().isAfter(timeDeparture))
                .collect(Collectors.toList());
        if (typeCabin == TypeCabin.ECONOMY) {
            routes = routes.stream()
                    .filter(route -> route.getBusinessClassSeat() >= numberPassengers)
                    .collect(Collectors.toList());
        } else if (typeCabin == TypeCabin.BUSINESS) {
            routes = routes.stream()
                    .filter(route -> route.getEconomyClassSeat() >= numberPassengers)
                    .collect(Collectors.toList());
        }
        if (!routes.isEmpty()) {
            printRoutes(routes);
        }
        return routes;
    }

    public boolean getAllDate() {
//        List<Route> routes = routeDAO.getAll();
//        if (!routes.isEmpty()) {
//            System.out.println("*********************************");
//            System.out.println("Indicate departure date.");
//            System.out.println("ID\tDATE");
//            System.out.println("*********************************");
//            for (Route route : routes) {
//                if (route.getTimeDeparture().isAfter(LocalDate.now())) {
//                    System.out.println(route.getId() + ".\t" + route.getTimeDeparture());
//                }
//            }
//            System.out.println("*********************************");
//        } else {
//            System.out.println("List routes is empty.");
//            return false;
//        }
        return true;
    }

    public boolean getAllRoutes() {
        return false;
    }

    private void printRoutes(List<Route> routes) {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-15s%-32s%-30s%-1s\n", " ", "CITY", "DATE", "CLASS PLACE");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-15s%-15s%-19s%-20s%-10s%-1s", "ID", "DEPARTURE", "ARRIVAL",
                "DEPARTURE", "ARRIVAL", "BUSINESS", "ECONOMY\n");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Route route : routes) {
            System.out.printf("%-5d%-15s%-15s%-19s%-23s%-10d%-1d\n",
                    route.getId(),
                    cityService.getCityById(route.getDepartureCityId()).getName(),
                    cityService.getCityById(route.getArrivalCityId()).getName(),
                    route.getTimeDeparture(),
                    route.getTimeArrival(),
                    route.getBusinessClassSeat(),
                    route.getEconomyClassSeat());
        }
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void printOrder(Route route) {
        System.out.println("|---------------|---------------|-------------------|-------------------|");
        System.out.printf("    %-17s%-15s%-20s%-1s", "DEPARTURE", "ARRIVAL", "DEPARTURE DATE", "ARRIVAL DATE\n");
        System.out.println("|---------------|---------------|-------------------|-------------------|");
        System.out.printf(" %-17s%-17s%-20s%-1s\n",
                cityService.getCityById(route.getDepartureCityId()).getName(),
                cityService.getCityById(route.getArrivalCityId()).getName(),
                route.getTimeDeparture(),
                route.getTimeArrival());
        System.out.println("|---------------|---------------|-------------------|-------------------|\n");
    }

    public void reduceSeat(long routeId, long ticketId) {
        Route route = routeDAO.getById(routeId);
        Ticket ticket = ticketDAO.getById(ticketId);
        if (route != null) {
            if (ticket.getTypeCabin() == TypeCabin.BUSINESS) {
                route.setBusinessClassSeat(route.getBusinessClassSeat() - 1);
            } else if (ticket.getTypeCabin() == TypeCabin.ECONOMY) {
                route.setEconomyClassSeat(route.getEconomyClassSeat() - 1);
            }
        }
        routeDAO.update(route);
        ticketDAO.update(ticket);
    }
}
