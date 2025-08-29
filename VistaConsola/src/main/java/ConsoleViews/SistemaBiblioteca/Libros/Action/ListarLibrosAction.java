package ConsoleViews.SistemaBiblioteca.Libros.Action;

import ConsoleViews.MenuAction;
import Controllers.SistemaBiblioteca.LibroController;
import DTO.Biblioteca.LibroDTO;

import java.util.List;

public class ListarLibrosAction implements MenuAction {
    private final LibroController controller;

    public ListarLibrosAction(LibroController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Listado de Libros Registrados ---");
        try {
            List<LibroDTO> libros = controller.obtenerLibrosRegistrados();
            if (libros.isEmpty()) {
                System.out.println("No hay libros registrados.");
            } else {
                libros.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el listado: " + e.getMessage());
        }
    }
}
