package ConsoleViews.SistemaClientes.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaClientesController;
import DTO.Cliente.ClienteDTO;

import java.util.Scanner;

public class RegistrarClienteAction implements MenuAction {
    private final Scanner scanner;
    private final SistemaClientesController controlador;

    public RegistrarClienteAction(Scanner scanner, SistemaClientesController controlador) {
        this.scanner = scanner;
        this.controlador = controlador;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Registrar Nuevo Cliente ---");
        System.out.println("(Puede dejar campos en blanco presionando Enter, excepto el documento)");

        String numeroDocumento;
        do {
            System.out.print("Número de Documento (*obligatorio*): ");
            numeroDocumento = scanner.nextLine().trim();
            if (numeroDocumento.isEmpty()) {
                System.out.println("Error: El número de documento no puede estar vacío.");
            }
        } while (numeroDocumento.isEmpty());

        System.out.print("Nombres: ");
        String nombres = scanner.nextLine().trim();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        ClienteDTO nuevoClienteDto = new ClienteDTO();
        nuevoClienteDto.setNumeroDocumento(numeroDocumento);
        nuevoClienteDto.setNombres(nombres);
        nuevoClienteDto.setApellidos(apellidos);
        nuevoClienteDto.setEmail(email);

        try {
            String nuevoCliente = controlador.guardarCliente(nuevoClienteDto);
            System.out.println("\nCliente " + nuevoCliente + " registrado exitosamente!.");
        } catch (Exception e) {
            System.out.println("\nERROR AL REGISTRAR: " + e.getMessage());
        }
    }
}