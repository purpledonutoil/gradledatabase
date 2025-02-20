package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService{

    public static void main(String[] args) {
        Connection conn = Database.getInstance().getConnection();

        try {
            String initDB = Files.readString(Path.of("sql/init_db.sql"), StandardCharsets.UTF_8);
            try (Statement st = conn.createStatement()) {
                st.executeUpdate(initDB);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }
}

