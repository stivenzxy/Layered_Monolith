package ConsoleViews.SistemaClientes.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaClientesController;
import DTO.ClienteDTO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BuscarClienteAction implements MenuAction {
    private final Scanner scanner;
    private final SistemaClientesController controlador;

    public BuscarClienteAction(Scanner scanner, SistemaClientesController controlador) {
        this.scanner = scanner;
        this.controlador = controlador;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Buscar Cliente por ID ---");
        System.out.print("Ingrese el ID del cliente que desea buscar: ");

        try {
            long id = scanner.nextLong();
            scanner.nextLine();

            ClienteDTO clienteEncontrado = controlador.buscarClientePorId(id);

            if (clienteEncontrado != null) {
                System.out.println("\n Cliente encontrado: ");
                System.out.println(clienteEncontrado);
            } else {
                System.out.println("\nNo se encontró ningún cliente con el ID " + id);
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: El ID ingresado no es un número válido.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado durante la búsqueda: " + e.getMessage());
        }
    }
}