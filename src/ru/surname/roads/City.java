package ru.surname.roads;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private List<Route> routes;

    public City() {
        this.name = "Неизвестный город";
        this.routes = new ArrayList<>();
    }

    public City(String name) {
        this.name = name;
        this.routes = new ArrayList<>();
    }

    public City(String name, List<Route> routes) {
        this.name = name;
        this.routes = routes != null ? routes : new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Route> getRoutes() { return routes; }
    public void setRoutes(List<Route> routes) { this.routes = routes; }

    public boolean addRoute(City destination, double cost) {
        for (Route route : routes) {
            if (route.getDestination().getName().equals(destination.getName())) {
                return false;
            }
        }
        routes.add(new Route(destination, cost));
        return true;
    }

    public boolean removeRoute(String destinationName) {
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).getDestination().getName().equals(destinationName)) {
                routes.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof City)) return false;
        City city = (City) o;
        if (routes.size() != city.routes.size()) return false;
        return routes.containsAll(city.routes);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name).append(": [");
        for (int i = 0; i < routes.size(); i++) {
            result.append(routes.get(i).toString());
            if (i < routes.size() - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}