package DatabaseConfig;

import Utils.Configuracion;
import Utils.DatabaseException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class H2ConnectionManager {
    private static volatile H2ConnectionManager instancia;

    private static final String DRIVER = Configuracion.getPropiedad("DRIVER");
    private static final String URL = Configuracion.getPropiedad("URL");
    private static final String USUARIO = Configuracion.getPropiedad("USUARIO");
    private static final String PASSWORD = Configuracion.getPropiedad("PASSWORD");

    private H2ConnectionManager() {
        try {
            Class.forName(DRIVER);
            inicializarBaseDeDatos();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error: No se encontró el driver de H2.", e);
        }
    }

    public static H2ConnectionManager obtenerInstancia() {
        if (instancia == null) {
            synchronized (H2ConnectionManager.class) {
                if (instancia == null) {
                    instancia = new H2ConnectionManager();
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

    private void inicializarBaseDeDatos() {
        try (InputStream inputStream = H2ConnectionManager.class.getClassLoader().getResourceAsStream("schema.sql")) {

            if (inputStream == null) {
                throw new RuntimeException("No se pudo encontrar el archivo schema.sql en los recursos.");
            }

            String scriptSql = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            try (Connection conn = this.getConnection(); Statement stmt = conn.createStatement()) {
                stmt.execute(scriptSql);
                System.out.println("Base de datos H2 inicializada correctamente.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error crítico al inicializar la base de datos.", e);
        }
    }
}