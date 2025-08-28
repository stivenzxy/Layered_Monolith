package ConsoleViews.SistemaClientes.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaClientesController;
import DTO.ClienteDTO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ActualizarClienteAction implements MenuAction {
    private final Scanner scanner;
    private final SistemaClientesController controlador;

    public ActualizarClienteAction(Scanner scanner, SistemaClientesController controlador) {
        this.scanner = scanner;
        this.controlador = controlador;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Actualizar Cliente ---");

        long id;
        try {
            System.out.print("Ingrese el ID del cliente que desea actualizar: ");
            id = scanner.nextLong();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: El ID debe ser un número.");
            scanner.nextLine();
            return;
        }

        try {
            ClienteDTO clienteActual = controlador.buscarClientePorId(id);
            if (clienteActual == null) {
                System.out.println("Error: No se encontró ningún cliente con el ID " + id);
                return;
            }

            System.out.println("\nDatos actuales del cliente:");
            System.out.println(clienteActual);
            System.out.println("\nIngrese los nuevos datos a continuación. Presione Enter para mantener el valor actual.");


            System.out.print("Nuevo Número de Documento [" + clienteActual.getNumeroDocumento() + "]: ");
            String numeroDocumento = scanner.nextLine().trim();

            System.out.print("Nuevos Nombres [" + clienteActual.getNombres() + "]: ");
            String nombres = scanner.nextLine().trim();

            System.out.print("Nuevos Apellidos [" + clienteActual.getApellidos() + "]: ");
            String apellidos = scanner.nextLine().trim();

            System.out.print("Nuevo Email [" + clienteActual.getEmail() + "]: ");
            String email = scanner.nextLine().trim();

            ClienteDTO datosParaActualizar = new ClienteDTO();
            datosParaActualizar.setId(id);
            datosParaActualizar.setNumeroDocumento(numeroDocumento);
            datosParaActualizar.setNombres(nombres);
            datosParaActualizar.setApellidos(apellidos);
            datosParaActualizar.setEmail(email);

            String clienteActualizado = controlador.guardarCliente(datosParaActualizar);

            System.out.println("\n¡ÉXITO! El cliente " + clienteActualizado + " ha sido actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("\nERROR AL ACTUALIZAR LOS DATOS DEL CLIENTE: " + e.getMessage());
        }
    }
}
