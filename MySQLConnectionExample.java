import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnectionExample {

    // URL для подключения к базе данных `humanfriends`
    private static final String URL = "jdbc:mysql://localhost:3306/humanfriends"; // замените humanfriends на имя вашей БД, если оно отличается
    private static final String USER = "root"; // ваш логин
    private static final String PASSWORD = "ProMax1945"; // ваш пароль

    public static void main(String[] args) {
        // Устанавливаем подключение
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Соединение с MySQL установлено!");

            // SQL-запрос для извлечения всех записей из таблицы pacanimals
            String sql = "SELECT * FROM packanimals";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                // Обрабатываем результаты запроса
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String type = resultSet.getString("type");
                    String birthDate = resultSet.getString("birthdate");
                    String commands = resultSet.getString("commands");

                    System.out.println("ID: " + id +
                            ", Name: " + name +
                            ", Type: " + type +
                            ", Birth Date: " + birthDate +
                            ", Commands: " + commands);
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка подключения или выполнения запроса: " + e.getMessage());
        }
    }
}

