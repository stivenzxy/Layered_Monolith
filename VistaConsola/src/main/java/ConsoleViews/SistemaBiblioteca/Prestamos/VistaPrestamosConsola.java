package ConsoleViews.SistemaBiblioteca.Prestamos;

import ConsoleViews.MenuAction;
import ConsoleViews.SistemaBiblioteca.Prestamos.Actions.CrearPrestamoAction;
import ConsoleViews.SistemaBiblioteca.Prestamos.Actions.DevolverPrestamoAction;
import ConsoleViews.SistemaBiblioteca.Prestamos.Actions.ListarPrestamosAction;
import Controllers.SistemaBiblioteca.LectorController;
import Controllers.SistemaBiblioteca.LibroController;
import Controllers.SistemaBiblioteca.PrestamoController;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VistaPrestamosConsola {
    private final Scanner scanner;
    private final Map<Integer, MenuAction> actions = new HashMap<>();

    public VistaPrestamosConsola(PrestamoController prestamoController, LectorController lectorController, LibroController libroController) {
        this.scanner = new Scanner(System.in);

        actions.put(1, new CrearPrestamoAction(scanner, prestamoController, lectorController, libroController));
        actions.put(2, new ListarPrestamosAction(prestamoController));
        actions.put(3, new DevolverPrestamoAction(scanner, prestamoController));
    }

    public void mostrarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n--- Gestión de Préstamos ---");
            System.out.println("1. Registrar nuevo Préstamo");
            System.out.println("2. Listar todos los Préstamos");
            System.out.println("3. Registrar Devolución de Libro");
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