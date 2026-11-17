package ru.surname.roads;

public class Route {
    private City destination;
    private double cost;

    public Route() {
        this.destination = null;
        this.cost = 0.0;
    }

    public Route(City destination, double cost) {
        this.destination = destination;
        this.cost = cost;
    }

    public City getDestination() { return destination; }
    public void setDestination(City destination) { this.destination = destination; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Double.compare(route.cost, cost) == 0 &&
                destination.getName().equals(route.destination.getName());
    }

    @Override
    public String toString() {
        return destination.getName() + ":" + (int) cost;
    }
}