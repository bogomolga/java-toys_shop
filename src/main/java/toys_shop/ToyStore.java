package toys_shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class ToyStore {
    private List<Toy> toys = new ArrayList<>();
    private List<Toy> prizeToys = new ArrayList<>();
    private int remainingToys;
    private static ToyStore toyStore = new ToyStore();

    public void addToy(int id, String name, int quantity, int weight) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                toy.setQuantity(toy.getQuantity() + quantity);
                toy.setWeight(weight);
                return;
            }
        }
        toys.add(new Toy(id, name, quantity, weight));
    }

    public Toy getPrizeToy() {
        Toy prizeToy = null;
        int totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }
        if (totalWeight > 0) {
            int randomNumber = (int) (Math.random() * totalWeight) + 1;
            int currentWeight = 0;
            for (Toy toy : toys) {
                currentWeight += toy.getWeight();
                if (currentWeight >= randomNumber) {
                    prizeToy = toy;
                    break;
                }
            }
            if (prizeToy != null) {
                // Запись prizeToy в файл prizeToys.txt
                try {
                    FileWriter writer = new FileWriter("src/main/java/toys_shop/prizeToys.txt", true);
                    writer.write(prizeToy.getId() + ";" + prizeToy.getName() + ";" + prizeToy.getQuantity() + ";" + prizeToy.getWeight() + "\n");
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Ошибка: не удалось записать призовую игрушку в файл.");
                    e.printStackTrace();
                }

                prizeToys.add(prizeToy);
                prizeToy.setQuantity(prizeToy.getQuantity() - 1);
                toys.remove(prizeToy);
            }
        }
        return prizeToy;
    }
    public List<Toy> getToys() {
        return toys;
    }

    public boolean changeToyWeight(int id, int weight) {
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                toy.setWeight(weight);
                return true;
            }
        }
        return false;
    }

    public void removeToy(Toy prizeToy) {
        // Проверяем, что prizeToy не null и находится в списке игрушек
        if (prizeToy != null && toys.contains(prizeToy)) {
            toys.remove(prizeToy);
            remainingToys--; // Уменьшаем количество оставшихся игрушек
        }
    }
    public static void main(String[] args) throws FileNotFoundException {

    }
}
