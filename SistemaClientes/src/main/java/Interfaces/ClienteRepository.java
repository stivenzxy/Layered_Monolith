package Interfaces;

import Entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    String crear(Cliente cliente);
    Optional<Cliente> obtenerPorId(Long id);
    List<Cliente> obtenerTodos();
    String actualizar(Cliente cliente);
    boolean eliminar(Long id);
}
