package ConsoleViews.SistemaBiblioteca.Lectores.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaBiblioteca.LectorController;
import DTO.Biblioteca.LectorDTO;

import java.util.List;

public class ListarLectoresAction implements MenuAction {
    private final LectorController controller;

    public ListarLectoresAction(LectorController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Listado de Lectores Registrados ---");
        try {
            List<LectorDTO> lectores = controller.obtenerLectoresRegistrados();
            if (lectores.isEmpty()) {
                System.out.println("No hay lectores registrados.");
            } else {
                lectores.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el listado: " + e.getMessage());
        }
    }
}
