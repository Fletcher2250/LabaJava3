package ru.surname.roads;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private CityV2 start;
    private CityV2 end;

    public Path(CityV2 start, CityV2 end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Начало и конец маршрута не могут быть null");
        }
        this.start = start;
        this.end = end;
    }

    public CityV2 getEnd() { return end; }

    public void setEnd(CityV2 end) {
        if (end == null) {
            throw new IllegalArgumentException("Конец маршрута не может быть null");
        }
        this.end = end;
    }

    public CityV2[] findPath() {
        if (start.equals(end)) {
            return new CityV2[]{start};
        }

        List<CityV2> queue = new ArrayList<>();
        List<CityV2> visited = new ArrayList<>();
        java.util.Map<CityV2, CityV2> parent = new java.util.HashMap<>();

        queue.add(start);
        visited.add(start);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            CityV2 current = queue.remove(0);

            for (RouteV2 route : current.getRoutes().getRoutes()) {
                CityV2 neighbor = route.getDestination();

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.add(neighbor);

                    if (neighbor.equals(end)) {
                        return buildPath(parent);
                    }
                }
            }
        }
        return new CityV2[0];
    }

    private CityV2[] buildPath(java.util.Map<CityV2, CityV2> parent) {
        List<CityV2> path = new ArrayList<>();
        CityV2 current = end;

        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }

        java.util.Collections.reverse(path);
        return path.toArray(new CityV2[0]);
    }

    @Override
    public String toString() {
        CityV2[] path = findPath();
        if (path.length == 0) {
            return "Путь не найден";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.length; i++) {
            sb.append(path[i].getName());
            if (i < path.length - 1) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }
}
