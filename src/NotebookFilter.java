import java.util.*;

// Класс Notebook представляет ноутбук с различными характеристиками
class Notebook {
    private String name; // Название ноутбука
    private int ram; // Объем оперативной памяти в ГБ
    private int storage; // Объем жесткого диска в ГБ
    private String operatingSystem; // Операционная система
    private String color; // Цвет ноутбука

    // Конструктор для инициализации характеристик ноутбука
    public Notebook(String name, int ram, int storage, String operatingSystem, String color) {
        this.name = name;
        this.ram = ram;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    // Методы для получения характеристик
    public String getName() {
        return name;
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

    // Переопределение метода toString для удобного отображения информации о ноутбуке
    @Override
    public String toString() {
        return "Notebook{" +
                "name='" + name + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

// Класс NotebookFilter отвечает за фильтрацию списка ноутбуков по заданным критериям
public class NotebookFilter {
    private List<Notebook> notebooks; // Список всех доступных ноутбуков

    // Конструктор принимает список ноутбуков
    public NotebookFilter(List<Notebook> notebooks) {
        this.notebooks = notebooks;
    }

    // Метод для фильтрации ноутбуков по введенным пользователем критериям
    public void filterNotebooks() {
        Scanner scanner = new Scanner(System.in); // Объект для считывания ввода пользователя
        Map<String, Integer> filters = new HashMap<>(); // Хранение критериев фильтрации

        // Предложение пользователю выбрать критерии фильтрации
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        boolean isFiltering = true; // Флаг для продолжения ввода критериев
        while (isFiltering) { // Пока пользователь хочет вводить критерии
            int criterion = scanner.nextInt(); // Получаем критерий от пользователя
            switch (criterion) {
                case 1:
                    System.out.print("Введите минимальное значение ОЗУ (GB): ");
                    int minRam = scanner.nextInt(); // Считываем минимальное значение ОЗУ
                    filters.put("ram", minRam); // Добавляем критерий в карту
                    break;
                case 2:
                    System.out.print("Введите минимальный объем ЖД (GB): ");
                    int minStorage = scanner.nextInt(); // Считываем минимальный объем ЖД
                    filters.put("storage", minStorage); // Добавляем критерий в карту
                    break;
                case 3:
                    System.out.print("Введите операционную систему: ");
                    String os = scanner.next(); // Считываем операционную систему
                    filters.put("os", Integer.valueOf(os)); // Добавляем критерий в карту
                    break;
                case 4:
                    System.out.print("Введите цвет: ");
                    String color = scanner.next(); // Считываем цвет
                    filters.put("color", Integer.valueOf(color)); // Добавляем критерий в карту
                    break;
                default:
                    System.out.println("Некорректный критерий. Попробуйте еще раз."); // Если введен неверный критерий
            }
            // Запрашиваем у пользователя, хочет ли он добавить еще один критерий
            System.out.print("Хотите добавить еще один критерий? (да/нет): ");
            String response = scanner.next();
            if (response.equalsIgnoreCase("нет")) { // Если пользователь ответил "нет", выходим из цикла
                isFiltering = false;
            }
        }

        // Фильтруем ноутбуки на основе введенных критериев
        List<Notebook> filteredNotebooks = filter(filters);
        System.out.println("Ноутбуки по заданным критериям:");
        for (Notebook notebook : filteredNotebooks) { // Выводим отфильтрованные ноутбуки
            System.out.println(notebook);
        }
    }

    // Метод фильтрации возвращает список ноутбуков, соответствующих заданным критериям
    private List<Notebook> filter(Map<String, Integer> filters) {
        List<Notebook> result = new ArrayList<>(); // Список для хранения отфильтрованных ноутбуков

        // Проходим по всем ноутбукам в исходном списке
        for (Notebook notebook : notebooks) {
            boolean matches = true; // Флаг для проверки соответствия критериям

            // Проверяем соответствие критериям
            if (filters.containsKey("ram") && notebook.getRam() < filters.get("ram")) {
                matches = false; // Ноутбук не соответствует по ОЗУ
            }

            if (filters.containsKey("storage") && notebook.getStorage() < filters.get("storage")) {
                matches = false; // Ноутбук не соответствует по объему жесткого диска
            }

            if (filters.containsKey("os") && !notebook.getOperatingSystem().equalsIgnoreCase(filters.get("os").toString())) {
                matches = false; // Ноутбук не соответствует по операционной системе
            }

            if (filters.containsKey("color") && !notebook.getColor().equalsIgnoreCase(filters.get("color").toString())) {
                matches = false; // Ноутбук не соответствует по цвету
            }

            // Если ноутбук соответствует всем критериям, добавляем его в результат
            if (matches) {
                result.add(notebook);
            }
        }

        return result; // Возвращаем отфильтрованный список
    }

    // Главный метод, где программа начинается
    public static void main(String[] args) {
        // Создаем список ноутбуков с их характеристиками
        List<Notebook> notebooks = new ArrayList<>();
        notebooks.add(new Notebook("Acer", 8, 512, "Windows", "Silver"));
        notebooks.add(new Notebook("Lenovo", 16, 1024, "Linux", "Black"));
        notebooks.add(new Notebook("HP", 4, 256, "Windows", "White"));

        // Создаем объект фильтрации и запускаем процесс фильтрации
        NotebookFilter notebookFilter = new NotebookFilter(notebooks);
        notebookFilter.filterNotebooks();
    }
}