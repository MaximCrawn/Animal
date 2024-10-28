import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Registry {
    private List<Animal> animals;

    public Registry() {
        this.animals = new ArrayList<>();
    }

    // Метод для добавления нового животного
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // Метод для получения всех животных
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals); // Возвращаем копию списка животных
    }

    // Метод для получения животных, отсортированных по дате рождения
    public List<Animal> getAnimalsSortedByBirthDate() {
        return animals.stream()
                      .sorted(Comparator.comparing(Animal::getBirthDate))
                      .collect(Collectors.toList());
    }

    // Метод для подсчета количества животных по типу
    public long countAnimalsByType(Class<? extends Animal> type) {
        return animals.stream()
                      .filter(type::isInstance)
                      .count();
    }

    // Метод для получения списка команд конкретного животного
    public List<String> getCommandsByAnimalName(String name) {
        return animals.stream()
                      .filter(animal -> animal.getName().equalsIgnoreCase(name))
                      .flatMap(animal -> animal.getCommands().stream())
                      .collect(Collectors.toList());
    }

    // Метод для получения животного по имени
    public Animal getAnimalByName(String name) {
        return animals.stream()
                      .filter(animal -> animal.getName().equalsIgnoreCase(name))
                      .findFirst()
                      .orElse(null);
    }
}
