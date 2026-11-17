package ru.surname.roads;

import java.util.ArrayList;
import java.util.List;

public class RoadNetwork {
    private List<City> cities;

    public RoadNetwork() {
        this.cities = new ArrayList<>();
    }

    public List<City> getCities() {
        return cities;
    }

    public boolean addCity(String name) {
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }
        cities.add(new City(name));
        return true;
    }

    public City findCity(String name) {
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(name)) {
                return city;
            }
        }
        return null;
    }

    public String addRoad(String from, String to, double cost) {
        City cityFrom = findCity(from);
        City cityTo = findCity(to);

        if (cityFrom == null || cityTo == null) {
            return "Ошибка: один или оба города не найдены.";
        }
        if (from.equalsIgnoreCase(to)) {
            return "Ошибка: нельзя построить дорогу из города в самого себя.";
        }

        boolean added1 = cityFrom.addRoute(cityTo, cost);
        boolean added2 = cityTo.addRoute(cityFrom, cost);

        if (!added1 || !added2) {
            return "Ошибка: прямая дорога между этими городами уже существует.";
        }
        return "Дорога успешно добавлена.";
    }

    public String removeRoad(String from, String to) {
        City cityFrom = findCity(from);
        City cityTo = findCity(to);

        if (cityFrom == null || cityTo == null) {
            return "Ошибка: один или оба города не найдены.";
        }

        boolean removed1 = cityFrom.removeRoute(to);
        boolean removed2 = cityTo.removeRoute(from);

        if (!removed1 && !removed2) {
            return "Ошибка: дорога между этими городами не найдена.";
        }
        return "Дорога успешно удалена.";
    }

    @Override
    public String toString() {
        if (cities.isEmpty()) {
            return "Сеть пуста.";
        }
        StringBuilder sb = new StringBuilder("Сеть дорог:\n");
        for (City city : cities) {
            sb.append("  ").append(city.toString()).append("\n");
        }
        return sb.toString();
    }
}
