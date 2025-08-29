package ConsoleViews.SistemaBiblioteca.Prestamos.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaBiblioteca.PrestamoController;

import java.util.Scanner;

public class DevolverPrestamoAction implements MenuAction {
    private final Scanner scanner;
    private final PrestamoController controller;

    public DevolverPrestamoAction(Scanner scanner, PrestamoController controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Registrar Devolución ---");
        System.out.print("Ingrese el ID del préstamo a devolver: ");

        try {
            long prestamoId = scanner.nextLong();
            scanner.nextLine();

            boolean devuelto = controller.devolverPrestamo(prestamoId);

            if (devuelto) {
                System.out.println("\n¡ÉXITO! Devolución registrada correctamente.");
            } else {
                System.out.println("\nERROR: No se pudo registrar la devolución. Verifique el ID del préstamo.");
            }
        } catch (Exception e) {
            System.out.println("\nERROR: " + e.getMessage());
            scanner.nextLine();
        }
    }
}