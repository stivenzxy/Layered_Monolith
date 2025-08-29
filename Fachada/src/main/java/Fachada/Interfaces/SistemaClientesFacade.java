package Fachada.Interfaces;

import DTO.Cliente.ClienteDTO;

import java.util.List;

public interface SistemaClientesFacade {
    List<ClienteDTO> obtenerListadoClientes();
    ClienteDTO buscarClientePorId(Long id);
    String guardarCliente(ClienteDTO cliente);
    boolean eliminarCliente(Long id);
}
