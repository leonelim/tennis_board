package Utils;

import exceptions.DatabaseCouldNotBeAccessed;
import org.apache.commons.dbcp.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            statement.execute("CREATE TABLE Players (ID INTEGER PRIMARY KEY AUTO_INCREMENT, Name VARCHAR)");
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
