package Controllers;

import DTO.ClienteDTO;
import Fachada.Interfaces.SistemaClientesFacade;

import java.util.List;

public class SistemaClientesController {
    private final SistemaClientesFacade facade;

    public SistemaClientesController(SistemaClientesFacade facade) {
        this.facade = facade;
    }

    public void crearCliente(ClienteDTO clienteDTO) {
        facade.guardarCliente(clienteDTO);
    }

    public List<ClienteDTO> obtenerListadoClientes() {
        return facade.obtenerListadoClientes();
    }
}
