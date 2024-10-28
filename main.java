import java.util.Scanner;

public class Main {
    private static final Registry registry = new Registry();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Counter counter = new Counter()) {
            while (true) {
                System.out.println("1. Завести новое животное");
                System.out.println("2. Вывести список команд животного");
                System.out.println("3. Обучить животное новой команде");
                System.out.println("4. Вывести список животных по дате рождения");
                System.out.println("5. Показать количество животных");
                System.out.println("6. Выход");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addNewAnimal(scanner, counter);
                    case 2 -> listAnimalCommands();
                    case 3 -> trainAnimal(scanner);
                    case 4 -> listAnimalsByBirthDate();
                    case 5 -> showAnimalCount(scanner);
                    case 6 -> System.exit(0);
                    default -> System.out.println("Неверный выбор, попробуйте снова.");
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static void addNewAnimal(Scanner scanner, Counter counter) {
        // Логика добавления нового животного
    }

    private static void listAnimalCommands() {
        // Логика вывода списка команд
    }

    private static void trainAnimal(Scanner scanner) {
        // Логика добавления новых команд
    }

    private static void listAnimalsByBirthDate() {
        // Логика вывода животных по дате рождения
    }

    private static void showAnimalCount(Scanner scanner) {
        System.out.println("Выберите вариант для подсчёта:");
        System.out.println("1. Всего животных");
        System.out.println("2. Домашние животные (Pets)");
        System.out.println("3. Вьючные животные (PackAnimals)");

        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); 

        int count = 0;

        switch (categoryChoice) {
            case 1 -> {
                count = registry.getAnimals().size();
                System.out.println("Всего животных: " + count);
            }
            case 2 -> {
                System.out.println("Выберите тип домашнего животного:");
                System.out.println("1. Собака (Dog)");
                System.out.println("2. Кошка (Cat)");
                System.out.println("3. Хомяк (Hamster)");

                int petChoice = scanner.nextInt();
                scanner.nextLine(); 

                count = switch (petChoice) {
                    case 1 -> (int) registry.getAnimals().stream().filter(Dog.class::isInstance).count();
                    case 2 -> (int) registry.getAnimals().stream().filter(Cat.class::isInstance).count();
                    case 3 -> (int) registry.getAnimals().stream().filter(Hamster.class::isInstance).count();
                    default -> 0;
                };

                System.out.println("Количество выбранного типа домашних животных: " + count);
            }
            case 3 -> {
                System.out.println("Выберите тип вьючного животного:");
                System.out.println("1. Лошадь (Horse)");
                System.out.println("2. Верблюд (Camel)");
                System.out.println("3. Осел (Donkey)");

                int packAnimalChoice = scanner.nextInt();
                scanner.nextLine(); 

                count = switch (packAnimalChoice) {
                    case 1 -> (int) registry.getAnimals().stream().filter(Horse.class::isInstance).count();
                    case 2 -> (int) registry.getAnimals().stream().filter(Camel.class::isInstance).count();
                    case 3 -> (int) registry.getAnimals().stream().filter(Donkey.class::isInstance).count();
                    default -> 0;
                };

                System.out.println("Количество выбранного типа вьючных животных: " + count);
            }
            default -> System.out.println("Неверный выбор.");
        }
    }
}
