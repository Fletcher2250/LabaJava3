package ru.surname.roads;

import java.util.List;

public class BidirectionalCity3_3 extends City {
    public BidirectionalCity3_3() {
        super();
    }

    public BidirectionalCity3_3(String name) {
        super(name);
    }

    public BidirectionalCity3_3(String name, List<Route> routes) {
        super(name, routes);
    }

    @Override
    public boolean addRoute(City destination, double cost) {
        for (Route route : this.getRoutes()) {
            if (route.getDestination().getName().equals(destination.getName())) {
                return false;
            }
        }

        for (Route route : destination.getRoutes()) {
            if (route.getDestination().getName().equals(this.getName())) {
                this.getRoutes().add(new Route(destination, cost));
                return true;
            }
        }

        this.getRoutes().add(new Route(destination, cost));

        destination.getRoutes().add(new Route(this, cost));

        return true;
    }

    @Override
    public boolean removeRoute(String destinationName) {
        City destCity = null;
        for (Route route : this.getRoutes()) {
            if (route.getDestination().getName().equals(destinationName)) {
                destCity = route.getDestination();
                break;
            }
        }

        if (destCity != null) {
            boolean removed1 = this.getRoutes().removeIf(r -> r.getDestination().getName().equals(destinationName));
            boolean removed2 = destCity.getRoutes().removeIf(r -> r.getDestination().getName().equals(this.getName()));
            return removed1 || removed2;
        }
        return false;
    }

    public boolean addOneWayRoute(City destination, double cost) {
        return super.addRoute(destination, cost);
    }
}
