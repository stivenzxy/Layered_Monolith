package Biblioteca.DAO;

import Biblioteca.DatabaseConfig.MysqlConnectionManager;
import Biblioteca.Entities.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    public String agregarLibro(Libro libro) {
        String sql = "INSERT INTO libro (titulo, autor, disponible) VALUES (?, ?, ?)";

        try (Connection conn = MysqlConnectionManager.obtenerInstancia().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setBoolean(3, libro.isDisponible());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("La creación del libro falló, no se afectaron filas.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    libro.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Fallo al crear el libro, no se pudo obtener el ID.");
                }
            }
            return "Libro agregado exitosamente: " + libro.getTitulo();

        } catch (SQLException e) {
            System.err.println("Error al agregar el libro: " + e.getMessage());
            return null;
        }
    }

    public List<Libro> obtenerLibrosRegistrados() {
        String sql = "SELECT id, titulo, autor, disponible FROM libro";
        List<Libro> libros = new ArrayList<>();

        try (Connection conn = MysqlConnectionManager.obtenerInstancia().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getLong("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setDisponible(rs.getBoolean("disponible"));
                libros.add(libro);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los libros: " + e.getMessage());
        }

        return libros;
    }
}
