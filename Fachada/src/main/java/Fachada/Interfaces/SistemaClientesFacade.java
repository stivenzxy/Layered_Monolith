package Fachada.Interfaces;

import DTO.ClienteDTO;

import java.util.List;

public interface SistemaClientesFacade {
    List<ClienteDTO> obtenerListadoClientes();
    ClienteDTO buscarClientePorId(Long id);
    void guardarCliente(ClienteDTO cliente);
    String actualizarCliente(ClienteDTO cliente);
    boolean eliminarCliente(Long id);
}
