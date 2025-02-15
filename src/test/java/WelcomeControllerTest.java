import org.junit.Test;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Collections;
import java.sql.DriverManager;

public class WelcomeControllerTest {
    @Test
    public void test() {
        String url = "jdbc:h2:mem:test";
        try {
            Connection connection = DriverManager.getConnection(url, "")
        }
    }
}