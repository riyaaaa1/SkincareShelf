package skincare;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)

abstract class DBConnectionPool {
    @BeforeAll
    static void connectDBConnectionPool() {
        System.out.println("<= DB Connection is Established. =>");
    }
    @AfterAll
    static void closeDBConnectionPool() {
        System.out.println("<= DB Connection is Closed. =>");
    }
}
