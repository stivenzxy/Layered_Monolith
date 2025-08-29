package ConsoleViews.SistemaBiblioteca.Prestamos.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaBiblioteca.PrestamoController;
import DTO.Biblioteca.PrestamoDTO;

import java.util.List;

public class ListarPrestamosAction implements MenuAction {
    private final PrestamoController controller;

    public ListarPrestamosAction(PrestamoController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Historial de Préstamos ---");
        try {
            List<PrestamoDTO> prestamos = controller.obtenerPrestamosRegistrados();
            if (prestamos.isEmpty()) {
                System.out.println("No hay préstamos registrados.");
            } else {
                prestamos.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el listado: " + e.getMessage());
        }
    }
}