package Biblioteca.DAO;

import Biblioteca.DatabaseConfig.MysqlConnectionManager;
import Biblioteca.Entities.Lector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LectorDAO {
    public String agregarLector(Lector lector) {
        String sql = "INSERT INTO lector (nombre, correo) VALUES (?, ?)";

        try (Connection conn = MysqlConnectionManager.obtenerInstancia().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, lector.getNombre());
            pstmt.setString(2, lector.getEmail());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("La creación del lector falló, no se afectaron filas.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lector.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Fallo al crear el lector, no se pudo obtener el ID.");
                }
            }
            return lector.getNombre();

        } catch (SQLException e) {
            System.err.println("Error al agregar el lector: " + e.getMessage());
            return null;
        }
    }

    public List<Lector> obtenerLectoresRegistrados() {
        String sql = "SELECT id, nombre, correo FROM lector";
        List<Lector> lectores = new ArrayList<>();

        try (Connection conn = MysqlConnectionManager.obtenerInstancia().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Lector lector = new Lector();
                lector.setId(rs.getLong("id"));
                lector.setNombre(rs.getString("nombre"));
                lector.setEmail(rs.getString("correo"));
                lectores.add(lector);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los lectores: " + e.getMessage());
        }
        return lectores;
    }
}