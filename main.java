import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
                scanner.nextLine(); // Считывание новой строки

                switch (choice) {
                    case 1 -> addNewAnimal(scanner, counter);
                    case 2 -> listAnimalCommands();
                    case 3 -> trainAnimal(scanner);
                    case 4 -> listAnimalsByBirthDate();
                    case 5 -> System.out.println("Всего животных в реестре: " + counter.getCount());
                    case 6 -> System.exit(0);
                    default -> System.out.println("Неверный выбор, попробуйте снова.");
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static void addNewAnimal(Scanner scanner, Counter counter) {
        System.out.println("Выберите категорию животного:");
        System.out.println("1. Домашние животные (Pets)");
        System.out.println("2. Вьючные животные (PackAnimals)");

        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); // Считывание новой строки

        Animal animal = null;
        if (categoryChoice == 1) {
            System.out.println("Выберите тип домашнего животного:");
            System.out.println("1. Собака (Dog)");
            System.out.println("2. Кошка (Cat)");
            System.out.println("3. Хомяк (Hamster)");

            int petChoice = scanner.nextInt();
            scanner.nextLine(); // Считывание новой строки

            animal = createPet(scanner, petChoice);
        } else if (categoryChoice == 2) {
            System.out.println("Выберите тип вьючного животного:");
            System.out.println("1. Лошадь (Horse)");
            System.out.println("2. Верблюд (Camel)");
            System.out.println("3. Осел (Donkey)");

            int packAnimalChoice = scanner.nextInt();
            scanner.nextLine(); // Считывание новой строки

            animal = createPackAnimal(scanner, packAnimalChoice);
        }

        if (animal != null) {
            registry.addAnimal(animal);
            try {
                counter.add();
                System.out.println("Животное добавлено в реестр.");
            } catch (Exception e) {
                System.err.println("Ошибка при добавлении животного: " + e.getMessage());
            }
        } else {
            System.out.println("Некорректный выбор животного.");
        }
    }

    private static Animal createPet(Scanner scanner, int choice) {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату рождения животного (ГГГГ-ММ-ДД): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        List<String> commands = enterCommands(scanner);

        return switch (choice) {
            case 1 -> new Dog(name, birthDate, commands);
            case 2 -> new Cat(name, birthDate, commands);
            case 3 -> new Hamster(name, birthDate, commands);
            default -> null;
        };
    }

    private static Animal createPackAnimal(Scanner scanner, int choice) {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату рождения животного (ГГГГ-ММ-ДД): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        List<String> commands = enterCommands(scanner);

        return switch (choice) {
            case 1 -> new Horse(name, birthDate, commands);
            case 2 -> new Camel(name, birthDate, commands);
            case 3 -> new Donkey(name, birthDate, commands);
            default -> null;
        };
    }

    private static List<String> enterCommands(Scanner scanner) {
        System.out.print("Введите команды для животного через запятую: ");
        String commandsInput = scanner.nextLine();
        return Arrays.asList(commandsInput.split(",\\s*"));
    }

    private static void listAnimalCommands() {
        if (registry.getAnimals().isEmpty()) {
            System.out.println("Список животных пуст.");
            return;
        }
        
        System.out.print("Введите имя животного: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        
        registry.getAnimals().stream()
                .filter(animal -> animal.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresentOrElse(
                    animal -> {
                        System.out.println("Команды животного " + animal.getName() + ":");
                        animal.getCommands().forEach(System.out::println);
                    },
                    () -> System.out.println("Животное с именем " + name + " не найдено.")
                );
    }
    

    private static void trainAnimal(Scanner scanner) {
        System.out.print("Введите имя животного для обучения: ");
        String animalName = scanner.nextLine();
        System.out.print("Введите новую команду: ");
        String newCommand = scanner.nextLine();

        registry.getAnimals().stream()
                .filter(a -> a.getName().equalsIgnoreCase(animalName))
                .forEach(a -> {
                    a.addCommand(newCommand);
                    System.out.println("Животное обучено новой команде.");
                });
    }

    private static void listAnimalsByBirthDate() {
        registry.getAnimalsSortedByBirthDate()
                .forEach(animal -> System.out.println(animal.getName() + " - " + animal.getBirthDate()));
    }
}
