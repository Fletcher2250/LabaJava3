package ru.surname.roads;

import java.util.ArrayList;
import java.util.List;

public class RoadNetworkV2 {
    private List<CityV2> cities;  // список всех городов в сети

    public RoadNetworkV2() {
        this.cities = new ArrayList<>();
    }

    public List<CityV2> getCities() { return cities; }

    public boolean addCity(String name) {
        for (CityV2 city : cities) {
            if (city.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }
        cities.add(new CityV2(name));
        return true;
    }

    public CityV2 findCity(String name) {
        for (CityV2 city : cities) {
            if (city.getName().equalsIgnoreCase(name)) {
                return city;
            }
        }
        return null;
    }

    public String addRoad(String from, String to, double cost) {
        CityV2 cityFrom = findCity(from);
        CityV2 cityTo = findCity(to);

        if (cityFrom == null || cityTo == null) {
            return "Ошибка: один или оба города не найдены.";
        }
        if (from.equalsIgnoreCase(to)) {
            return "Ошибка: нельзя построить дорогу из города в самого себя.";
        }

        for (RouteV2 route : cityFrom.getRoutes().getRoutes()) {
            if (route.getDestination().getName().equalsIgnoreCase(to)) {
                return "Ошибка: прямая дорога между этими городами уже существует.";
            }
        }

        cityFrom.getRoutes().addRoute(new RouteV2(cityTo, cost));
        cityTo.getRoutes().addRoute(new RouteV2(cityFrom, cost));

        return "Дорога успешно добавлена.";
    }

    public String removeRoad(String from, String to) {
        CityV2 cityFrom = findCity(from);
        CityV2 cityTo = findCity(to);

        if (cityFrom == null || cityTo == null) {
            return "Ошибка: один или оба города не найдены.";
        }

        boolean removed1 = removeRouteFromCity(cityFrom, to);
        boolean removed2 = removeRouteFromCity(cityTo, from);

        if (!removed1 && !removed2) {
            return "Ошибка: дорога между этими городами не найдена.";
        }
        return "Дорога успешно удалена.";
    }

    private boolean removeRouteFromCity(CityV2 city, String destinationName) {
        List<RouteV2> routes = city.getRoutes().getRoutes();
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).getDestination().getName().equalsIgnoreCase(destinationName)) {
                routes.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (cities.isEmpty()) {
            return "Сеть пуста.";
        }
        StringBuilder sb = new StringBuilder("Сеть дорог:\n");
        for (CityV2 city : cities) {
            sb.append("  ").append(city.toString()).append("\n");
        }
        return sb.toString();
    }
}