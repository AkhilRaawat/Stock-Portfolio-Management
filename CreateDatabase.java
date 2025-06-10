import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabase {
    public static void main(String[] args) {
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            
            // Connect to PostgreSQL
            Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres", 
                "postgres", 
                "12345");
            
            Statement stmt = conn.createStatement();
            
            // Drop the database if it exists
            stmt.execute("DROP DATABASE IF EXISTS customer_test");
            
            // Create the database
            stmt.execute("CREATE DATABASE customer_test");
            
            System.out.println("Database 'customer_test' created successfully!");
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
