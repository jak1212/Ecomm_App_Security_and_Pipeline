package TestConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class H2QueryRunner implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {



        // Run query and print result
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM users");

        for (Map<String, Object> row : rows) {
            System.out.println("Row: " + row);
        }
    }
}
