package ConsoleViews.SistemaBiblioteca.Prestamos.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaBiblioteca.LectorController;
import Controllers.SistemaBiblioteca.LibroController;
import Controllers.SistemaBiblioteca.PrestamoController;
import DTO.Biblioteca.LectorDTO;
import DTO.Biblioteca.LibroDTO;
import DTO.Biblioteca.PrestamoDTO;

import java.util.List;
import java.util.Scanner;

public class CrearPrestamoAction implements MenuAction {
    private final Scanner scanner;
    private final PrestamoController prestamoController;
    private final LectorController lectorController;
    private final LibroController libroController;

    public CrearPrestamoAction(Scanner scanner, PrestamoController prestamoController, LectorController lectorController, LibroController libroController) {
        this.scanner = scanner;
        this.prestamoController = prestamoController;
        this.lectorController = lectorController;
        this.libroController = libroController;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Registrar Nuevo Préstamo ---");

        try {
            System.out.println("Libros disponibles:");
            List<LibroDTO> librosDisponibles = libroController.obtenerLibrosRegistrados().stream()
                    .filter(LibroDTO::isDisponible)
                    .toList();

            if (librosDisponibles.isEmpty()) {
                System.out.println("No hay libros disponibles para prestar.");
                return;
            }
            librosDisponibles.forEach(System.out::println);
            System.out.print("Ingrese el ID del libro a prestar: ");
            Long libroId = scanner.nextLong();

            System.out.println("\nLectores registrados:");
            List<LectorDTO> lectores = lectorController.obtenerLectoresRegistrados();
            if (lectores.isEmpty()) {
                System.out.println("No hay lectores registrados para realizar un préstamo.");
                return;
            }
            lectores.forEach(System.out::println);
            System.out.print("Ingrese el ID del lector: ");
            Long lectorId = scanner.nextLong();
            scanner.nextLine();

            PrestamoDTO nuevoPrestamo = new PrestamoDTO();
            nuevoPrestamo.setLibroId(libroId);
            nuevoPrestamo.setLectorId(lectorId);

            PrestamoDTO prestamoCreado = prestamoController.crearPrestamo(nuevoPrestamo);
            System.out.println("\n¡ÉXITO! Préstamo registrado con ID: " + prestamoCreado.getId());

        } catch (Exception e) {
            System.out.println("\nERROR AL REGISTRAR EL PRÉSTAMO: " + e.getMessage());
            scanner.nextLine();
        }
    }
}