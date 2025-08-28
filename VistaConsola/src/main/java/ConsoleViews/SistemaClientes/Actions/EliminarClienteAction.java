package ConsoleViews.SistemaClientes.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaClientesController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EliminarClienteAction implements MenuAction {
    private final Scanner scanner;
    private final SistemaClientesController controlador;

    public EliminarClienteAction(Scanner scanner, SistemaClientesController controlador) {
        this.scanner = scanner;
        this.controlador = controlador;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Eliminar Cliente ---");
        System.out.print("Ingrese el ID del cliente que desea eliminar: ");

        try {
            long id = scanner.nextLong();
            scanner.nextLine();

            System.out.print("¿Está seguro de que desea eliminar al cliente con ID " + id + "? (s/n): ");
            String deleteConfirmation = scanner.nextLine().trim().toLowerCase();

            if (!deleteConfirmation.equals("s")) {
                System.out.println("Operación de eliminación cancelada.");
                return;
            }

            boolean eliminado = controlador.eliminarCliente(id);

            if (eliminado) {
                System.out.println("\nEl cliente con ID " + id + " ha sido eliminado correctamente!.");
            } else {
                System.out.println("\nError: No se pudo eliminar el cliente. Es posible que el ID no exista.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: El ID ingresado no es un número válido.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado durante la eliminación: " + e.getMessage());
        }
    }
}
