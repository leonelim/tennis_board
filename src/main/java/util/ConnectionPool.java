package util;
/*
import exceptions.DatabaseCouldNotBeAccessed;
import org.apache.commons.dbcp.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPool {
    private final static BasicDataSource dataSource = new BasicDataSource();
    static {
        dataSource.setUrl(PropertiesLoader.get("db.url"));
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE Players (ID INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20) UNIQUE NOT NULL)");
            statement.execute("CREATE TABLE Matches (ID INT PRIMARY KEY AUTO_INCREMENT, Player1 INT NOT NULL, Player2 INT NOT NULL, Winner INT NOT NULL, UNIQUE (Player1, Player2), FOREIGN KEY (Player1) REFERENCES Players(ID) ON DELETE CASCADE, FOREIGN KEY (Player2) REFERENCES Players(ID) ON DELETE CASCADE)");
        } catch (SQLException e) {
            throw new DatabaseCouldNotBeAccessed("The database could not resolve the request");
        }
    }
    public static Connection get() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DatabaseCouldNotBeAccessed("The database could not resolve the request");
        }
    }
}
*/