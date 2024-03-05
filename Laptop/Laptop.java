package Laptop;
import java.util.*;

public class Laptop {
    private String model;
    private int ram;
    private int storage;
    private String operatingSystem;
    private String color;

    public Laptop(String model, int ram, int storage, String operatingSystem, String color) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public static List<Laptop> filter(List<Laptop> laptops, Map<String, Object> criteria) {
        List<Laptop> result = new ArrayList<>();

        for (Laptop laptop : laptops) {
            boolean matches = true;
            for (Map.Entry<String, Object> entry : criteria.entrySet()) {
                String criterion = entry.getKey();
                Object value = entry.getValue();
                switch (criterion) {
                    case "RAM":
                        if (laptop.getRam() < (int) value) {
                            matches = false;
                        }
                        break;
                    case "Storage":
                        if (laptop.getStorage() < (int) value) {
                            matches = false;
                        }
                        break;
                    case "OperatingSystem":
                        if (!laptop.getOperatingSystem().equals(value)) {
                            matches = false;
                        }
                        break;
                    case "Color":
                        if (!laptop.getColor().equals(value)) {
                            matches = false;
                        }
                        break;
                    default:
                        // Handle other criteria if needed
                        break;
                }
            }
            if (matches) {
                result.add(laptop);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Laptop> laptops = new ArrayList<>();
        laptops.add(new Laptop("Asus", 8, 512, "Windows", "Black"));
        laptops.add(new Laptop("HP", 16, 256, "Linux", "Gray"));
        laptops.add(new Laptop("Dell", 16, 1024, "Windows", "Silver"));

        Scanner scanner = new Scanner(System.in);
        Map<String, Object> criteria = new HashMap<>();

        System.out.println("Введите критерии фильтрации:");

        System.out.print("Введите операционную систему: ");
        String os = scanner.nextLine();
        criteria.put("OperatingSystem", os);

        System.out.print("Введите минимальный объем ОЗУ: ");
        int ram = scanner.nextInt();
        criteria.put("RAM", ram);

        System.out.print("Введите минимальный объем жесткого диска: ");
        int storage = scanner.nextInt();
        criteria.put("Storage", storage);

        System.out.print("Введите цвет: ");
        scanner.nextLine(); // Consume newline
        String color = scanner.nextLine();
        criteria.put("Color", color);

        List<Laptop> result = Laptop.filter(laptops, criteria);

        if (result.isEmpty()) {
            System.out.println("Ноутбука, соответствующего заданным критериям, не найдено.");
        } else {
            System.out.print("Результаты поиска: ");
            for (Laptop laptop : result) {
                System.out.println(laptop.getModel());
            }
        }
    }
}
