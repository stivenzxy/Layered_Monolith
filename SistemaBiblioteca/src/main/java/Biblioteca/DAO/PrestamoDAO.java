package Biblioteca.DAO;

import Biblioteca.DatabaseConfig.MysqlConnectionManager;
import Biblioteca.Entities.Prestamo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    public String crearPrestamo(Prestamo prestamo) {
        String sqlUpdateLibro = "UPDATE libro SET disponible = false WHERE id = ? AND disponible = true";
        String sqlInsertPrestamo = "INSERT INTO prestamo (libro_id, lector_id, fecha_prestamo) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            conn = MysqlConnectionManager.obtenerInstancia().getConnection();

            conn.setAutoCommit(false);

            try (PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdateLibro)) {
                pstmtUpdate.setLong(1, prestamo.getLibroId());
                int filasAfectadas = pstmtUpdate.executeUpdate();

                if (filasAfectadas == 0) {
                    throw new SQLException("El libro no está disponible para préstamo o no existe.");
                }
            }

            try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsertPrestamo, Statement.RETURN_GENERATED_KEYS)) {
                pstmtInsert.setLong(1, prestamo.getLibroId());
                pstmtInsert.setLong(2, prestamo.getLectorId());
                pstmtInsert.setDate(3, java.sql.Date.valueOf(prestamo.getFechaPrestamo()));

                pstmtInsert.executeUpdate();

                try (ResultSet generatedKeys = pstmtInsert.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        prestamo.setId(generatedKeys.getLong(1));
                    }
                }
            }

            conn.commit();
            return "Préstamo registrado exitosamente con ID: " + prestamo.getId();

        } catch (SQLException e) {
            System.err.println("Error al crear el préstamo: " + e.getMessage());
            try {
                conn.rollback();
                System.err.println("Transacción revertida.");
            } catch (SQLException ex) {
                System.err.println("Error al revertir la transacción: " + ex.getMessage());
            }
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public boolean devolverPrestamo(Long prestamoId, LocalDate fechaDevolucion) {
        String sqlUpdatePrestamo = "UPDATE prestamo SET fecha_devolucion = ? WHERE id = ?";
        String sqlUpdateLibro = "UPDATE libro SET disponible = true WHERE id = (SELECT libro_id FROM prestamo WHERE id = ?)";
        Connection conn = null;

        try {
            conn = MysqlConnectionManager.obtenerInstancia().getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtPrestamo = conn.prepareStatement(sqlUpdatePrestamo)) {
                pstmtPrestamo.setDate(1, java.sql.Date.valueOf(fechaDevolucion));
                pstmtPrestamo.setLong(2, prestamoId);
                int filasAfectadas = pstmtPrestamo.executeUpdate();
                if (filasAfectadas == 0) {
                    throw new SQLException("No se encontró el préstamo con ID: " + prestamoId);
                }
            }

            try (PreparedStatement pstmtLibro = conn.prepareStatement(sqlUpdateLibro)) {
                pstmtLibro.setLong(1, prestamoId);
                pstmtLibro.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al devolver el préstamo: " + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al revertir la transacción: " + ex.getMessage());
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        String sql = "SELECT id, libro_id, lector_id, fecha_prestamo, fecha_devolucion FROM prestamo ORDER BY fecha_prestamo DESC";
        List<Prestamo> prestamos = new ArrayList<>();

        try (Connection conn = MysqlConnectionManager.obtenerInstancia().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setId(rs.getLong("id"));
                prestamo.setLibroId(rs.getLong("libro_id"));
                prestamo.setLectorId(rs.getLong("lector_id"));

                prestamo.setFechaPrestamo(rs.getDate("fecha_prestamo").toLocalDate());

                Date fechaDevolucionSql = rs.getDate("fecha_devolucion");
                if (fechaDevolucionSql != null) {
                    prestamo.setFechaDevolucion(fechaDevolucionSql.toLocalDate());
                } else {
                    prestamo.setFechaDevolucion(null);
                }

                prestamos.add(prestamo);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los préstamos: " + e.getMessage());
        }

        return prestamos;
    }
}