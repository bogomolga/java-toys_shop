package toys_shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {
    private static ToyStore toyStore;

    public Ui(ToyStore toyStore) {
        this.toyStore = toyStore;
    }

    public static void start() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            printMenu();
            System.out.print("Введите номер действия: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    playGame();
                    break;
                case 2:
                    addNewToy("prizeToys.txt");
                    break;
                case 3:
                    changeToyWeight();
                    break;
                case 4:
                    showPrizeToys();
                    break;
                case 5:
                    showRemainingToys();
                    break;
                case 6:
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Ошибка: неправильный ввод.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1 - начать игру, получить призовую игрушку");
        System.out.println("2 - добавить новую игрушку");
        System.out.println("3 - изменить вес игрушки по id");
        System.out.println("4 - показать файл с призовыми игрушками");
        System.out.println("5 - показать список оставшихся игрушек");
        System.out.println("6 - выход из программы");
    }

    static void playGame() {
        //Toy prizeToy = toyStore.choosePrizeToy();
        Toy prizeToy = toyStore.getPrizeToy();

        if (prizeToy == null) {
            System.out.println("Ошибка: не удалось получить призовую игрушку.");
            return;
        }

        toyStore.removeToy(prizeToy);
        System.out.println("Призовая игрушка: \"" + prizeToy.getName() + "\" добавлена в файл.");
    }

    static void addNewToy(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите id игрушки: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        System.out.print("Введите название игрушки: ");
        String name = scanner.nextLine();
        System.out.print("Введите количество игрушек: ");
        int quantity = scanner.nextInt();
        System.out.print("Введите вес игрушки: ");
        int weight = scanner.nextInt();

        toyStore.addToy(id, name, quantity, weight);
        System.out.println("Игрушка добавлена в список.");
    }

    static void changeToyWeight() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите id игрушки: ");
        int id = scanner.nextInt();
        System.out.print("Введите новый вес игрушки: ");
        int weight = scanner.nextInt();

        if (toyStore.changeToyWeight(id, weight)) {
            System.out.println("Вес игрушки изменен.");
        } else {
            System.out.println("Ошибка: не удалось изменить вес игрушки.");
        }
    }

    static void showRemainingToys() {
        System.out.println("Список оставшихся игрушек:");
        for (Toy toy : toyStore.getToys()) {
            System.out.printf("ID: %d, Название: %s, Количество: %d, Вес: %d%%\n",
                    toy.getId(), toy.getName(), toy.getQuantity(), toy.getWeight());
        }
    }


/*    private void showPrizeToys2() {
        // Создаем объект JSON из файла
        JSONObject jsonObject = readJsonFromFile("prize_toys.json");

        // Если объект пустой, выводим сообщение
        if (jsonObject == null) {
            System.out.println("Призовых игрушек пока нет.");
            return;
        }

        // Получаем массив призовых игрушек из объекта JSON
        JSONArray prizeToysArray = jsonObject.getJSONArray("prizeToys");

        // Если массив пустой, выводим сообщение
        if (prizeToysArray.length() == 0) {
            System.out.println("Призовых игрушек пока нет.");
            return;
        }

        // Выводим список призовых игрушек
        System.out.println("Список призовых игрушек:");
        for (int i = 0; i < prizeToysArray.length(); i++) {
            JSONObject toyObject = prizeToysArray.getJSONObject(i);
            int id = toyObject.getInt("id");
            String name = toyObject.getString("name");
            String description = toyObject.getString("description");
            int weight = toyObject.getInt("weight");
            Toy toy = new Toy(id, name, description, weight);
            System.out.println(toy.toString());
        }
    }*/


}

