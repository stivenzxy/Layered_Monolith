package Controllers;

import DTO.ClienteDTO;
import Fachada.Interfaces.SistemaClientesFacade;

import java.util.List;

public class SistemaClientesController {
    private final SistemaClientesFacade facade;

    public SistemaClientesController(SistemaClientesFacade facade) {
        this.facade = facade;
    }

    public String guardarCliente(ClienteDTO clienteDTO) {
        return facade.guardarCliente(clienteDTO);
    }

    public ClienteDTO buscarClientePorId(Long id) {
        return facade.buscarClientePorId(id);
    }

    public List<ClienteDTO> obtenerListadoClientes() {
        return facade.obtenerListadoClientes();
    }

    public boolean eliminarCliente(Long id) {
        return facade.eliminarCliente(id);
    }
}
