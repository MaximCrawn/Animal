import java.time.LocalDate;
import java.util.List;

public class Donkey extends PackAnimals {
    public Donkey(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate);
        this.commands.addAll(commands);
    }
}