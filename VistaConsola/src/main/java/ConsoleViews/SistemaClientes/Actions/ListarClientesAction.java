package ConsoleViews.SistemaClientes.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaClientesController;
import DTO.Cliente.ClienteDTO;

import java.util.List;

public class ListarClientesAction implements MenuAction {
    private final SistemaClientesController controlador;

    public ListarClientesAction(SistemaClientesController controlador) {
        this.controlador = controlador;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Listado de Todos los Clientes ---");

        List<ClienteDTO> clientes = controlador.obtenerListadoClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados en el sistema.");
        } else {
            System.out.println("ID | Nro. Documento | Nombres y Apellidos | Email");
            System.out.println("----------------------------------------------------------");
            for (ClienteDTO cliente : clientes) {
                System.out.printf("%-2d | %-16s | %-25s | %s%n",
                        cliente.getId(),
                        cliente.getNumeroDocumento(),
                        cliente.getNombres() + " " + cliente.getApellidos(),
                        cliente.getEmail());
            }
        }
    }
}