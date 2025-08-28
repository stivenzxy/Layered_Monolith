package DAO;

import DatabaseConfig.H2ConnectionManager;
import Entities.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO {

    public String crear(Cliente cliente){
        String sql = "INSERT INTO clientes (numero_documento, nombres, apellidos, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = H2ConnectionManager.obtenerInstancia().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cliente.getNumeroDocumento());
            pstmt.setString(2, cliente.getNombres());
            pstmt.setString(3, cliente.getApellidos());
            pstmt.setString(4, cliente.getEmail());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("La creación del cliente falló, no se afectaron filas.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("La creación del cliente falló, no se pudo obtener el ID.");
                }
            }

            return cliente.getNombres();

        } catch (SQLException e) {
            System.err.println("Error al crear el cliente: " + e.getMessage());
            return null;
        }
    }

    public String actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET numero_documento = ?, nombres = ?, apellidos = ?, email = ? WHERE id = ?";

        try (Connection conn = H2ConnectionManager.obtenerInstancia().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNumeroDocumento());
            pstmt.setString(2, cliente.getNombres());
            pstmt.setString(3, cliente.getApellidos());
            pstmt.setString(4, cliente.getEmail());
            pstmt.setLong(5, cliente.getId());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("La actualización del cliente falló, no se encontró el registro con ID " + cliente.getId());
            }
            return cliente.getNombres();

        } catch (SQLException e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
            return null;
        }
    }

    public boolean eliminar(Long id) {

        if (obtenerPorId(id) == null) {
            System.out.println("El cliente con id " + id + " no existe en la base de datos.");
        }

        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conn = H2ConnectionManager.obtenerInstancia().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            int filasAfectadas = pstmt.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
            return false;
        }
    }

    public Cliente obtenerPorId(Long id) {
        String sql = "SELECT id, numero_documento, nombres, apellidos, email FROM clientes WHERE id = ?";

        try (Connection conn = H2ConnectionManager.obtenerInstancia().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Cliente.builder()
                            .id(rs.getLong("id"))
                            .numeroDocumento(rs.getString("numero_documento"))
                            .nombres(rs.getString("nombres"))
                            .apellidos(rs.getString("apellidos"))
                            .email(rs.getString("email"))
                            .build();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cliente por ID: " + e.getMessage());
        }

        return null;
    }

    public List<Cliente> obtenerTodos() {
        String sql = "SELECT id, numero_documento, nombres, apellidos, email FROM clientes";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = H2ConnectionManager.obtenerInstancia().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = Cliente.builder()
                        .id(rs.getLong("id"))
                        .numeroDocumento(rs.getString("numero_documento"))
                        .nombres(rs.getString("nombres"))
                        .apellidos(rs.getString("apellidos"))
                        .email(rs.getString("email"))
                        .build();

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los clientes: " + e.getMessage());
        }

        return clientes;
    }
}