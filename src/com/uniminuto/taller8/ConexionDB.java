package com.uniminuto.taller8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/kd_electronics";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static void ejecutarMigracionesSchema() {
        String sql = "ALTER TABLE productos ADD COLUMN IF NOT EXISTS activo BOOLEAN NOT NULL DEFAULT TRUE";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.err.println("No se pudo asegurar la columna activo en productos: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos PostgreSQL.");
        } catch (SQLException e) {
            System.err.println("Error crítico: No se pudo conectar a la base de datos.");
            System.err.println("Detalles del error: " + e.getMessage());
            String msg = e.getMessage();
            if (msg != null && msg.contains("No suitable driver")) {
                System.err.println("Falta el driver JDBC: añada postgresql-*.jar al classpath (en Eclipse: clic derecho en el proyecto → Build Path → Configure → Libraries → Add JARs, carpeta lib).");
            }
        }
        return conexion;
    }

}
