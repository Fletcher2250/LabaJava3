package ru.tarasov.main;

import ru.surname.birds.Bird4;
import ru.surname.birds.Cuckoo4;
import ru.surname.birds.Parrot4;
import ru.surname.birds.Sparrow4;
import ru.surname.geometry.Point3D3_5;
import ru.surname.geometry.Point3_5;
import ru.surname.roads.*;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №3 ===");

        boolean running = true;

        while (running) {
            System.out.println("\n------- ГЛАВНОЕ МЕНЮ -------");
            System.out.println("1. Задача 1.10 — Дороги");
            System.out.println("2. Задача 2.5 — Маршрут");
            System.out.println("3. Задача 3.3 - Двустороняя дорога");
            System.out.println("4. Задача 3.5 - Трехмерная точка");
            System.out.println("5. Задача 4.3 - Птицы");
            System.out.println("6. Задача 5.9 - Городизм");
            System.out.println("7. Задача 6.5 - Сравнение городов");
            System.out.println("8. Задача 7.2 - Главный метод");
            System.out.println("9. Задача 7.3 - Возведение в степень");
            System.out.println("10. Задача 8.4 - Клонирование точки");
            System.out.println("0. Выход из программы");
            System.out.print("Выберите задачу (0-8): ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
                continue;
            }

            switch (choice) {
                case 1:
                    task1Menu();
                    break;
                case 2:
                    task2Menu();
                    break;
                case 3:
                    task3Menu();
                    break;
                case 4:
                    task4Menu();
                    break;
                case 5:
                    task5Menu();
                    break;
                case 6:
                    task6Menu();
                    break;
                case 7:
                    task7Menu();
                    break;
                case 8:
                    task8Menu();
                    break;
                case 9:
                    task9Menu(args);
                    break;
                case 10:
                    task10Menu();
                    break;
                case 0:
                    running = false;
                    System.out.println("Завершение работы. До свидания!");
                    break;
                default:
                    System.out.println("Ошибка: нет задачи с таким номером.");
            }
        }
        scanner.close();
    }

    // Задача 1.10----------------------------------------------------------
    private static void task1Menu() {
        RoadNetwork network = new RoadNetwork();
        boolean inTask = true;

        while (inTask) {
            System.out.println("1. Добавить город");
            System.out.println("2. Добавить дорогу");
            System.out.println("3. Удалить дорогу");
            System.out.println("4. Показать все города и дороги");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите пункт (0-4): ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAddCity(network);
                    break;
                case 2:
                    handleAddRoad(network);
                    break;
                case 3:
                    handleRemoveRoad(network);
                    break;
                case 4:
                    System.out.println("\n" + network.toString());
                    break;
                case 0:
                    inTask = false;
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Ошибка: такого пункта нет.");
            }
        }
    }

    private static void handleAddCity(RoadNetwork network) {
        System.out.print("Введите название нового города: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Ошибка: название города не может быть пустым.");
            return;
        }

        if (network.addCity(name)) {
            System.out.println("Город '" + name + "' успешно добавлен.");
        } else {
            System.out.println("Ошибка: город с таким названием уже существует.");
        }
    }

    private static void handleAddRoad(RoadNetwork network) {
        if (network.getCities().size() < 2) {
            System.out.println("Ошибка: для добавления дороги нужно минимум 2 города.");
            return;
        }

        System.out.print("Введите название города-отправления: ");
        String from = scanner.nextLine().trim();
        System.out.print("Введите название города-назначения: ");
        String to = scanner.nextLine().trim();
        System.out.print("Введите стоимость/длину дороги (число > 0): ");
        String costStr = scanner.nextLine().trim();

        if (from.isEmpty() || to.isEmpty() || costStr.isEmpty()) {
            System.out.println("Ошибка: все поля должны быть заполнены.");
            return;
        }

        double cost;
        try {
            cost = Double.parseDouble(costStr);
            if (cost <= 0) {
                System.out.println("Ошибка: стоимость должна быть положительным числом.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректное число.");
            return;
        }

        System.out.println(network.addRoad(from, to, cost));
    }

    private static void handleRemoveRoad(RoadNetwork network) {
        if (network.getCities().isEmpty()) {
            System.out.println("Ошибка: в сети нет городов.");
            return;
        }

        System.out.print("Введите название города-отправления: ");
        String from = scanner.nextLine().trim();
        System.out.print("Введите название города-назначения: ");
        String to = scanner.nextLine().trim();

        if (from.isEmpty() || to.isEmpty()) {
            System.out.println("Ошибка: названия городов не могут быть пустыми.");
            return;
        }

        System.out.println(network.removeRoad(from, to));
    }

    // Задача 2.5 -------------------------------------------------------------------------
    private static void task2Menu() {
        RoadNetworkV2 network = new RoadNetworkV2();
        boolean inTask = true;

        System.out.println("\n--- Задача 2: Поиск маршрута между городами ---");

        while (inTask) {
            System.out.println("1. Добавить город");
            System.out.println("2. Добавить дорогу");
            System.out.println("3. Найти маршрут между городами");
            System.out.println("4. Показать все города и дороги");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите пункт (0-4): ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAddCityTask2(network);
                    break;
                case 2:
                    handleAddRoadTask2(network);
                    break;
                case 3:
                    handleFindPath(network);
                    break;
                case 4:
                    System.out.println("\n" + network.toString());
                    break;
                case 0:
                    inTask = false;
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Ошибка: такого пункта нет.");
            }
        }
    }

    private static void handleAddCityTask2(RoadNetworkV2 network) {
        System.out.print("Введите название нового города: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Ошибка: название города не может быть пустым.");
            return;
        }

        if (network.addCity(name)) {
            System.out.println("Город '" + name + "' успешно добавлен.");
        } else {
            System.out.println("Ошибка: город с таким названием уже существует.");
        }
    }

    private static void handleAddRoadTask2(RoadNetworkV2 network) {
        if (network.getCities().size() < 2) {
            System.out.println("Ошибка: для добавления дороги нужно минимум 2 города.");
            return;
        }

        System.out.print("Введите название города-отправления: ");
        String from = scanner.nextLine().trim();
        System.out.print("Введите название города-назначения: ");
        String to = scanner.nextLine().trim();
        System.out.print("Введите стоимость/длину дороги (число > 0): ");
        String costStr = scanner.nextLine().trim();

        if (from.isEmpty() || to.isEmpty() || costStr.isEmpty()) {
            System.out.println("Ошибка: все поля должны быть заполнены.");
            return;
        }

        double cost;
        try {
            cost = Double.parseDouble(costStr);
            if (cost <= 0) {
                System.out.println("Ошибка: стоимость должна быть положительным числом.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректное число.");
            return;
        }

        System.out.println(network.addRoad(from, to, cost));
    }

    private static void handleFindPath(RoadNetworkV2 network) {
        if (network.getCities().size() < 2) {
            System.out.println("Ошибка: для поиска маршрута нужно минимум 2 города.");
            return;
        }

        System.out.print("Введите название города начала: ");
        String startName = scanner.nextLine().trim();
        System.out.print("Введите название города конца: ");
        String endName = scanner.nextLine().trim();

        if (startName.isEmpty() || endName.isEmpty()) {
            System.out.println("Ошибка: названия городов не могут быть пустыми.");
            return;
        }

        CityV2 startCity = network.findCity(startName);
        CityV2 endCity = network.findCity(endName);

        if (startCity == null) {
            System.out.println("Ошибка: город начала '" + startName + "' не найден.");
            return;
        }
        if (endCity == null) {
            System.out.println("Ошибка: город конца '" + endName + "' не найден.");
            return;
        }

        try {
            Path path = new Path(startCity, endCity);
            CityV2[] route = path.findPath();

            if (route.length == 0) {
                System.out.println("Путь между городами не найден.");
            } else {
                System.out.println("\nМаршрут найден:");
                System.out.println(path.toString());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Задача 3.3 ------------------------------------------------------------------------------------------------------
    private static void task3Menu() {
        BidirectionalRoadNetwork3_3 network = new BidirectionalRoadNetwork3_3();
        boolean inTask = true;

        while (inTask) {
            System.out.println("1. Добавить город");
            System.out.println("2. Добавить дорогу (автоматически двустороннюю)");
            System.out.println("3. Удалить дорогу");
            System.out.println("4. Показать все города и дороги");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите пункт (0-4): ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAddCityTask3(network);
                    break;
                case 2:
                    handleAddRoadTask3(network);
                    break;
                case 3:
                    handleRemoveRoadTask3(network);
                    break;
                case 4:
                    System.out.println("\n" + network.toString());
                    break;
                case 0:
                    inTask = false;
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Ошибка: такого пункта нет.");
            }
        }
    }

    private static void handleAddCityTask3(BidirectionalRoadNetwork3_3 network) {
        System.out.print("Введите название нового города: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Ошибка: название города не может быть пустым.");
            return;
        }

        if (network.addCity(name)) {
            System.out.println("Город '" + name + "' успешно добавлен.");
        } else {
            System.out.println("Ошибка: город с таким названием уже существует.");
        }
    }

    private static void handleAddRoadTask3(BidirectionalRoadNetwork3_3 network) {
        if (network.getCities().size() < 2) {
            System.out.println("Ошибка: для добавления дороги нужно минимум 2 города.");
            return;
        }

        System.out.print("Введите название города-отправления: ");
        String from = scanner.nextLine().trim();
        System.out.print("Введите название города-назначения: ");
        String to = scanner.nextLine().trim();
        System.out.print("Введите стоимость/длину дороги (число > 0): ");
        String costStr = scanner.nextLine().trim();

        if (from.isEmpty() || to.isEmpty() || costStr.isEmpty()) {
            System.out.println("Ошибка: все поля должны быть заполнены.");
            return;
        }

        double cost;
        try {
            cost = Double.parseDouble(costStr);
            if (cost <= 0) {
                System.out.println("Ошибка: стоимость должна быть положительным числом.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите корректное число.");
            return;
        }

        System.out.println(network.addRoad(from, to, cost));
    }

    private static void handleRemoveRoadTask3(BidirectionalRoadNetwork3_3 network) {
        if (network.getCities().isEmpty()) {
            System.out.println("Ошибка: в сети нет городов.");
            return;
        }

        System.out.print("Введите название города-отправления: ");
        String from = scanner.nextLine().trim();
        System.out.print("Введите название города-назначения: ");
        String to = scanner.nextLine().trim();

        if (from.isEmpty() || to.isEmpty()) {
            System.out.println("Ошибка: названия городов не могут быть пустыми.");
            return;
        }

        System.out.println(network.removeRoad(from, to));
    }

    // Задача 3.5 ------------------------------------------------------------------------------------------------------
    private static void task4Menu() {
        boolean inTask = true;

        while (inTask) {
            System.out.println("1. Создать трехмерную точку");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите пункт (0-1): ");

            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                inTask = false;
                System.out.println("Возврат в главное меню...");
                continue;
            }
            if (!input.equals("1")) {
                System.out.println("Ошибка: такого пункта нет.");
                continue;
            }

            System.out.print("Введите координату X (число): ");
            double x = readDouble();
            if (Double.isNaN(x)) continue;

            System.out.print("Введите координату Y (число): ");
            double y = readDouble();
            if (Double.isNaN(y)) continue;

            System.out.print("Введите координату Z (число): ");
            double z = readDouble();
            if (Double.isNaN(z)) continue;

            Point3D3_5 point = new Point3D3_5(x, y, z);
            System.out.println("\nТочка успешно создана: " + point.toString());
        }
    }

    private static double readDouble() {
        String input = scanner.nextLine().trim();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println(" Ошибка: введено не число. Попробуйте снова.");
            return Double.NaN;
        }
    }

    // Задача 4.3 ------------------------------------------------------------------------------------------------------
    private static void task5Menu() {
        boolean inTask = true;

        while (inTask) {
            System.out.println("1. Заставить петь Воробья");
            System.out.println("2. Заставить петь Кукушку");
            System.out.println("3. Заставить петь Попугая");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите пункт (0-3): ");

            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                inTask = false;
                System.out.println("Возврат в главное меню...");
                continue;
            }

            switch (input) {
                case "1":
                    Bird4 sparrow = new Sparrow4();
                    System.out.println(sparrow.getName() + " поет: " + sparrow.sing());
                    break;
                case "2":
                    Bird4 cuckoo = new Cuckoo4();
                    System.out.println(cuckoo.getName() + " поет: " + cuckoo.sing());
                    break;
                case "3":
                    System.out.print("Введите текст для попугая: ");
                    String text = scanner.nextLine().trim();
                    try {
                        Bird4 parrot = new Parrot4(text);
                        System.out.println(parrot.getName() + " поет: " + parrot.sing());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Ошибка: такого пункта нет.");
            }
        }
    }

    // Задача 5.9 ------------------------------------------------------------------------------------------------------
    private static void task6Menu() {
        System.out.println("\n--- Задача 6: Граф городов ---");

        BidirectionalCity3_3 A = new BidirectionalCity3_3("A");
        BidirectionalCity3_3 B = new BidirectionalCity3_3("B");
        BidirectionalCity3_3 D = new BidirectionalCity3_3("D");
        City C = new City("C");
        BidirectionalCity3_3 E = new BidirectionalCity3_3("E");

        A.addRoute(B, 10);
        B.addOneWayRoute(C, 10);
        A.addOneWayRoute(C, 10);
        E.addRoute(C, 10);
        E.addRoute(D, 10);
        D.addRoute(B, 10);

        System.out.println("Граф построен:");
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
        System.out.println(D);
        System.out.println(E);

        System.out.println("\nНажмите Enter для возврата в главное меню...");
        scanner.nextLine();
    }

    // Задача 6.5 ------------------------------------------------------------------------------------------------------
    private static void task7Menu() {
        boolean inTask = true;
        while (inTask) {
            System.out.println("1. Сравнить два обычных города");
            System.out.println("2. Сравнить обычный город и двусторонний");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите пункт (0-2): ");

            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                inTask = false;
                System.out.println("Возврат в главное меню...");
                continue;
            }

            if (input.equals("1") || input.equals("2")) {
                System.out.println("\n--- Создание первого города ---");
                City city1 = createCityManually();

                System.out.println("\n--- Создание второго города ---");
                City city2;
                if (input.equals("2")) {
                    city2 = createBidirectionalCityManually();
                } else {
                    city2 = createCityManually();
                }

                boolean areEqual = city1.equals(city2);
                System.out.println("\nРезультат сравнения: " + (areEqual ? "Города ОДИНАКОВЫЕ" : "Города РАЗНЫЕ"));
            } else {
                System.out.println("Ошибка: такого пункта нет.");
            }
        }
    }

    private static City createCityManually() {
        System.out.print("Введите название города: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Город";

        City city = new City(name);
        addRoutesManually(city);
        return city;
    }

    private static City createBidirectionalCityManually() {
        System.out.print("Введите название двустороннего города: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Двусторонний город";

        City city = new BidirectionalCity3_3(name);
        addRoutesManually(city);
        return city;
    }

    private static void addRoutesManually(City city) {
        System.out.print("Сколько путей добавить в этот город? (число >= 0): ");
        int count = 0;
        try {
            count = Integer.parseInt(scanner.nextLine().trim());
            if (count < 0) count = 0;
        } catch (NumberFormatException e) {
            System.out.println("Введено не число. Будет 0 путей.");
        }

        for (int i = 0; i < count; i++) {
            System.out.println("Путь " + (i + 1) + ":");
            System.out.print("  Название города-назначения: ");
            String destName = scanner.nextLine().trim();
            if (destName.isEmpty()) destName = "Пункт" + i;

            System.out.print("  Стоимость пути (число): ");
            double cost = 0;
            try {
                cost = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Введено не число. Стоимость будет 0.");
            }

            City dummyDest = new City(destName);
            city.addRoute(dummyDest, cost);
        }
    }

    // Задача 7.2---------------------------------------------------------------------------------------------
    private static void task8Menu() {
        int[] myArray = {5, -2, 9, 0, 3};

        System.out.println("Максимум: " + Block2_5.findMax(myArray));
        System.out.println("Минимум: " + Block2_5.findMin(myArray));
        System.out.println("Сумма: " + Block2_5.sum(myArray));
        System.out.println("Среднее: " + Block2_5.average(myArray));
        System.out.println("Разворот: " + Arrays.toString(Block2_5.reverse(myArray)));
        System.out.println("Сортировка: " + Arrays.toString(Block2_5.bubbleSort(myArray)));
    }

    // Задача 7.3 ------------------------------------------------------------------------------------------------------
    public static double calculatePower(String xStr, String yStr) {
        int x = parseInt(xStr);
        int y = parseInt(yStr);
        return pow(x, y);
    }

    private static void task9Menu(String[] args) {
        boolean inTask = true;

        while (inTask) {
            System.out.println("1. Возвести в степень (вручную)");
            System.out.println("2. Использовать аргументы командной строки");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите пункт (0-2): ");

            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                inTask = false;
                System.out.println("Возврат в главное меню...");
                continue;
            }

            String xStr = null;
            String yStr = null;

            if (input.equals("1")) {
                System.out.print("Введите X (целое число): ");
                xStr = scanner.nextLine().trim();
                System.out.print("Введите Y (целое число): ");
                yStr = scanner.nextLine().trim();
            } else if (input.equals("2")) {
                if (args.length >= 2) {
                    xStr = args[0];
                    yStr = args[1];
                    System.out.println("Используются аргументы: X=" + xStr + ", Y=" + yStr);
                } else {
                    System.out.println("Ошибка: аргументы командной строки не переданы при запуске.");
                    System.out.println("Настройте Program Arguments в конфигурации запуска IDE.");
                    continue;
                }
            } else {
                System.out.println("Ошибка: такого пункта нет.");
                continue;
            }

            try {
                double result = calculatePower(xStr, yStr);
                System.out.println("\nРезультат: " + xStr + " ^ " + yStr + " = " + result);
            } catch (NumberFormatException e) {
                System.out.println("\nОшибка: введены некорректные числа. Используйте целые числа.");
            }
        }
    }

    // Задача 9.4 ------------------------------------------------------------------------------------------------------
    private static void task10Menu() {
        boolean inTask = true;
        System.out.println("\n--- Задача 10: Клонирование точки ---");

        while (inTask) {
            System.out.println("\n--- Меню задачи 10 ---");
            System.out.println("1. Клонировать обычную точку (Point)");
            System.out.println("2. Клонировать трехмерную точку (Point3D)");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Выберите пункт (0-2): ");

            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                inTask = false;
                System.out.println("Возврат в главное меню...");
                continue;
            }

            if (input.equals("1")) {
                System.out.print("Введите X: ");
                double x = readDouble();
                if (Double.isNaN(x)) continue;
                System.out.print("Введите Y: ");
                double y = readDouble();
                if (Double.isNaN(y)) continue;

                Point3_5 original = new Point3_5(x, y);
                Point3_5 clone = original.clone();

                System.out.println("\nОригинал: " + original);
                System.out.println("Клон: " + clone);
                System.out.println("Ссылки совпадают (один объект)? " + (original == clone));

            } else if (input.equals("2")) {
                System.out.print("Введите X: ");
                double x = readDouble();
                if (Double.isNaN(x)) continue;
                System.out.print("Введите Y: ");
                double y = readDouble();
                if (Double.isNaN(y)) continue;
                System.out.print("Введите Z: ");
                double z = readDouble();
                if (Double.isNaN(z)) continue;

                Point3D3_5 original = new Point3D3_5(x, y, z);
                Point3D3_5 clone = original.clone();

                System.out.println("\nОригинал: " + original);
                System.out.println("Клон: " + clone);
                System.out.println("Ссылки совпадают (один объект)? " + (original == clone));

            } else {
                System.out.println("Ошибка: такого пункта нет.");
            }
        }
    }
}
