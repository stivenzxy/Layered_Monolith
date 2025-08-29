package ConsoleViews.SistemaBiblioteca.Lectores.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaBiblioteca.LectorController;
import DTO.Biblioteca.LectorDTO;

import java.util.Scanner;

public class RegistrarLectorAction implements MenuAction {
    private final Scanner scanner;
    private final LectorController controller;

    public RegistrarLectorAction(Scanner scanner, LectorController controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Registrar Nuevo Lector ---");
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Correo electrónico: ");
        String email = scanner.nextLine().trim();

        if (nombre.isEmpty() || email.isEmpty()) {
            System.out.println("Error: El nombre y el correo no pueden estar vacíos.");
            return;
        }

        LectorDTO nuevoLector = new LectorDTO();
        nuevoLector.setNombre(nombre);
        nuevoLector.setEmail(email);

        try {
            String lectorCreado = controller.agregarLector(nuevoLector);
            System.out.println("\nSe ha registrado el lector " + lectorCreado + " de forma exitosa!");
        } catch (Exception e) {
            System.out.println("\nERROR AL REGISTRAR: " + e.getMessage());
        }
    }
}
