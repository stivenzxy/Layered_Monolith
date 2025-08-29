package ConsoleViews.SistemaBiblioteca.Libros;

import ConsoleViews.MenuAction;
import ConsoleViews.SistemaBiblioteca.Libros.Action.ListarLibrosAction;
import ConsoleViews.SistemaBiblioteca.Libros.Action.RegistrarLibroAction;
import Controllers.SistemaBiblioteca.LibroController;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VistaLibrosConsola {
    private final Scanner scanner;
    private final Map<Integer, MenuAction> actions = new HashMap<>();

    public VistaLibrosConsola(LibroController controller) {
        this.scanner = new Scanner(System.in);

        actions.put(1, new RegistrarLibroAction(scanner, controller));
        actions.put(2, new ListarLibrosAction(controller));
    }

    public void mostrarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n--- Gestión de Libros ---");
            System.out.println("1. Registrar nuevo Libro");
            System.out.println("2. Listar todos los Libros");
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