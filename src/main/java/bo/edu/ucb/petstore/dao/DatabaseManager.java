package bo.edu.ucb.petstore.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:h2:mem:petstore;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void initializeDatabase() {
        try {
            Class.forName("org.h2.Driver");
            try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
                // Crear tabla de mascotas
                stmt.execute("CREATE TABLE IF NOT EXISTS pet (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(255)," +
                        "species VARCHAR(255)," +
                        "age INT," +
                        "price DOUBLE," +
                        "available BOOLEAN)");

                // Crear tabla de transacciones
                stmt.execute("CREATE TABLE IF NOT EXISTS transaction (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "pet_id INT," +
                        "purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                        "FOREIGN KEY (pet_id) REFERENCES pet(id))");

                // Poblar con datos iniciales
                stmt.execute("INSERT INTO pet (name, species, age, price, available) VALUES " +
                        "('Max', 'Dog', 3, 150.00, true)," +
                        "('Lucy', 'Cat', 2, 100.00, true)," +
                        "('Charlie', 'Dog', 5, 200.00, true)," +
                        "('Bella', 'Cat', 1, 120.00, true)," +
                        "('Rocky', 'Dog', 4, 180.00, true)," +
                        "('Molly', 'Cat', 3, 130.00, true)," +
                        "('Buddy', 'Dog', 2, 160.00, true)," +
                        "('Daisy', 'Cat', 4, 110.00, true)," +
                        "('Jack', 'Dog', 6, 220.00, true)," +
                        "('Chloe', 'Cat', 2, 140.00, true)," +
                        "('Toby', 'Dog', 1, 140.00, true)," +
                        "('Sophie', 'Cat', 5, 100.00, true)," +
                        "('Cody', 'Dog', 3, 170.00, true)," +
                        "('Zoe', 'Cat', 2, 125.00, true)," +
                        "('Buster', 'Dog', 7, 250.00, true)," +
                        "('Gracie', 'Cat', 1, 135.00, true)," +
                        "('Duke', 'Dog', 4, 190.00, true)," +
                        "('Mia', 'Cat', 3, 115.00, true)," +
                        "('Riley', 'Dog', 2, 155.00, true)," +
                        "('Lola', 'Cat', 4, 105.00, true)");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
