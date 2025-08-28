package ConsoleViews.SistemaClientes;

import ConsoleViews.SistemaClientes.Actions.*;
import ConsoleViews.MenuAction;
import Controllers.SistemaClientesController;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VistaClientesConsola {
    private final Scanner scanner;
    private final Map<Integer, MenuAction> actions = new HashMap<>();

    public VistaClientesConsola(SistemaClientesController controller) {
        this.scanner = new Scanner(System.in);

        actions.put(1, new RegistrarClienteAction(scanner, controller));
        actions.put(2, new ListarClientesAction(controller));
        actions.put(3, new BuscarClienteAction(scanner, controller));
        actions.put(4, new ActualizarClienteAction(scanner, controller));
        actions.put(5, new EliminarClienteAction(scanner, controller));
    }

    public void mostrarMenuPrincipal() {
        int opcion = -1;
        do {
            System.out.println("\n--- Sistema para Gestión de Clientes ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Listar todos los Clientes");
            System.out.println("3. Buscar cliente por ID");
            System.out.println("4. Actualizar datos de Cliente");
            System.out.println("5. Eliminar Cliente");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion == 0) continue;

                MenuAction action = actions.get(opcion);

                if (action != null) {
                    action.execute();
                } else {
                    System.out.println("Opción no válida. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
                scanner.nextLine();
            }
        } while (opcion != 0);
    }
}