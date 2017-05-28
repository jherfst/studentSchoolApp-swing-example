package sr.unasat.week6.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jherfst on 5/15/2017.
 */
public class DBConnection {

    private Connection connection = null;
    private final String USERNAME = "student";
    private final String PASSWORD = "student";
    private final String DB_URL = "jdbc:mysql://localhost/studenten";

    public DBConnection(){

        try {
            // Onderstaande zoekt de juiste class op uit de library en laad het in JVM
            Class.forName("com.mysql.jdbc.Driver");

            // De connectie wordt vervolgens gemaakt naar de database middels de juiste authenticatie
            connection =DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public Connection getConnection() {
        return connection;
    }

}
