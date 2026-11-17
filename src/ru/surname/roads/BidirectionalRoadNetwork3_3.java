package ru.surname.roads;

import java.util.ArrayList;
import java.util.List;

public class BidirectionalRoadNetwork3_3 {
    private List<BidirectionalCity3_3> cities;

    public BidirectionalRoadNetwork3_3() {
        this.cities = new ArrayList<>();
    }

    public List<BidirectionalCity3_3> getCities() {
        return cities;
    }

    public boolean addCity(String name) {
        for (BidirectionalCity3_3 city : cities) {
            if (city.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }
        cities.add(new BidirectionalCity3_3(name));
        return true;
    }

    public BidirectionalCity3_3 findCity(String name) {
        for (BidirectionalCity3_3 city : cities) {
            if (city.getName().equalsIgnoreCase(name)) {
                return city;
            }
        }
        return null;
    }

    public String addRoad(String from, String to, double cost) {
        BidirectionalCity3_3 cityFrom = findCity(from);
        BidirectionalCity3_3 cityTo = findCity(to);

        if (cityFrom == null || cityTo == null) {
            return "Ошибка: один или оба города не найдены.";
        }
        if (from.equalsIgnoreCase(to)) {
            return "Ошибка: нельзя построить дорогу из города в самого себя.";
        }

        boolean added = cityFrom.addRoute(cityTo, cost);

        if (!added) {
            return "Ошибка: дорога между этими городами уже существует.";
        }
        return "Двусторонняя дорога успешно добавлена.";
    }

    public String removeRoad(String from, String to) {
        BidirectionalCity3_3 cityFrom = findCity(from);
        if (cityFrom == null) {
            return "Ошибка: город отправления не найден.";
        }

        boolean removed = cityFrom.removeRoute(to);
        if (!removed) {
            return "Ошибка: дорога между этими городами не найдена.";
        }
        return "Двусторонняя дорога успешно удалена.";
    }

    @Override
    public String toString() {
        if (cities.isEmpty()) {
            return "Сеть пуста.";
        }
        StringBuilder sb = new StringBuilder("Сеть двусторонних дорог:\n");
        for (BidirectionalCity3_3 city : cities) {
            sb.append("  ").append(city.toString()).append("\n");
        }
        return sb.toString();
    }
}