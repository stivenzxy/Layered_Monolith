package ConsoleViews.SistemaBiblioteca.Libros.Action;

import ConsoleViews.MenuAction;
import Controllers.SistemaBiblioteca.LibroController;
import DTO.Biblioteca.LibroDTO;

import java.util.Scanner;

public class RegistrarLibroAction implements MenuAction {
    private final Scanner scanner;
    private final LibroController controller;

    public RegistrarLibroAction(Scanner scanner, LibroController controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Registrar Nuevo Libro ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine().trim();
        System.out.print("Autor: ");
        String autor = scanner.nextLine().trim();

        if (titulo.isEmpty() || autor.isEmpty()) {
            System.out.println("Error: El título y el autor no pueden estar vacíos.");
            return;
        }

        LibroDTO nuevoLibro = new LibroDTO();
        nuevoLibro.setTitulo(titulo);
        nuevoLibro.setAutor(autor);
        nuevoLibro.setDisponible(true);

        try {
            String libroCreado = controller.agregarLibro(nuevoLibro);
            System.out.println("\nEl libro " + libroCreado + " ha sido agregado exitosamente a la biblioteca.");
        } catch (Exception e) {
            System.out.println("\nERROR AL REGISTRAR: " + e.getMessage());
        }
    }
}
