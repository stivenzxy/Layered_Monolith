package ConsoleViews.SistemaBiblioteca.Lectores;

import ConsoleViews.MenuAction;
import ConsoleViews.SistemaBiblioteca.Lectores.Actions.ListarLectoresAction;
import ConsoleViews.SistemaBiblioteca.Lectores.Actions.RegistrarLectorAction;
import Controllers.SistemaBiblioteca.LectorController;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VistaLectoresConsola {
    private final Scanner scanner;
    private final Map<Integer, MenuAction> actions = new HashMap<>();

    public VistaLectoresConsola(LectorController controller) {
        this.scanner = new Scanner(System.in);

        actions.put(1, new RegistrarLectorAction(scanner, controller));
        actions.put(2, new ListarLectoresAction(controller));
    }

    public void mostrarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n--- Gestión de Lectores ---");
            System.out.println("1. Registrar nuevo Lector");
            System.out.println("2. Listar todos los Lectores");
            System.out.println("0. Regresar al menú anterior");
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