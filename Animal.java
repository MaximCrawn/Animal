import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    private String name;
    private LocalDate birthDate;
    protected List<String> commands;

    public Animal(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getCommands() {
        return new ArrayList<>(commands);
    }

    public void addCommand(String command) {
        this.commands.add(command);
    }

    public void addCommands(List<String> newCommands) {
        this.commands.addAll(newCommands);
    }
}
