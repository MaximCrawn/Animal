import java.time.LocalDate;
import java.util.List;

public class Cat extends Pets {
    public Cat(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate);
        this.commands.addAll(commands);
    }
}
