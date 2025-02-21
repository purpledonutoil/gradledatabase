package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {

    public static void main(String[] args) {
        Connection conn = Database.getInstance().getConnection();

        try {
            String populateDB = Files.readString(Path.of("sql/populate_db.sql"), StandardCharsets.UTF_8);
            try (Statement st = conn.createStatement()) {
                st.executeUpdate(populateDB);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }
}

