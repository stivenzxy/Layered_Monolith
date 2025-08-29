package Biblioteca.DatabaseConfig;

import Biblioteca.Utils.MysqlConfiguration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class MysqlConnectionManager {
    private static volatile MysqlConnectionManager instancia;

    private static final String DRIVER = MysqlConfiguration.getPropiedad("DRIVER");
    private static final String URL = MysqlConfiguration.getPropiedad("URL");
    private static final String USUARIO = MysqlConfiguration.getPropiedad("USUARIO");
    private static final String PASSWORD = MysqlConfiguration.getPropiedad("PASSWORD");

    private MysqlConnectionManager() {
        try {
            Class.forName(DRIVER);
            inicializarBaseDeDatos();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error: No se encontró el driver de Mysql.", e);
        }
    }

    public static MysqlConnectionManager obtenerInstancia() {
        if (instancia == null) {
            synchronized (MysqlConnectionManager.class) {
                if (instancia == null) {
                    instancia = new MysqlConnectionManager();
                }
            }
        }
        return instancia;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException exception) {
            throw new DatabaseException("Error al obtener la conexión con la base de datos", 500);
        }
    }

    public void inicializarBaseDeDatos() {
        try (InputStream inputStream = MysqlConnectionManager.class.getClassLoader().getResourceAsStream("MysqlSchema.sql")) {

            if (inputStream == null) {
                throw new RuntimeException("No se pudo encontrar el archivo MysqlSchema.sql en los recursos.");
            }

            String scriptSql = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            try (Connection conn = this.getConnection(); Statement stmt = conn.createStatement()) {

                String[] statements = scriptSql.split(";");

                for (String statement : statements) {
                    if (!statement.trim().isEmpty()) {
                        stmt.execute(statement);
                    }
                }
                //System.out.println("Base de datos Mysql inicializada correctamente.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error crítico al inicializar la base de datos.", e);
        }
    }
}
