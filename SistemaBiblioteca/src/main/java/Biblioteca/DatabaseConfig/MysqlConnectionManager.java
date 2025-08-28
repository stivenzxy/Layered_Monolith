package Biblioteca.DatabaseConfig;

import Biblioteca.Utils.Configuracion;

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

    private static final String DRIVER = Configuracion.getPropiedad("DRIVER");
    private static final String URL = Configuracion.getPropiedad("URL");
    private static final String USUARIO = Configuracion.getPropiedad("USUARIO");
    private static final String PASSWORD = Configuracion.getPropiedad("PASSWORD");

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
        try (InputStream inputStream = MysqlConnectionManager.class.getClassLoader().getResourceAsStream("schema.sql")) {

            if (inputStream == null) {
                throw new RuntimeException("No se pudo encontrar el archivo schema.sql en los recursos.");
            }

            // 1. Lee el archivo completo a un solo String (esto está bien).
            String scriptSql = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            try (Connection conn = this.getConnection(); Statement stmt = conn.createStatement()) {

                // 2. Separa el script en sentencias individuales usando el punto y coma.
                String[] statements = scriptSql.split(";");

                // 3. Itera sobre cada sentencia y ejecútala.
                for (String statement : statements) {
                    // Ignora las líneas vacías que puedan resultar de la separación.
                    if (!statement.trim().isEmpty()) {
                        stmt.execute(statement);
                    }
                }
                System.out.println("Base de datos Mysql inicializada correctamente.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error crítico al inicializar la base de datos.", e);
        }
    }
}
