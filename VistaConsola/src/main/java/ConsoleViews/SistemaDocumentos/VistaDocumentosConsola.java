package ConsoleViews.SistemaDocumentos;

import ConsoleViews.SistemaDocumentos.Actions.*;
import ConsoleViews.MenuAction;
import Controllers.SistemaDocumentosController;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VistaDocumentosConsola {
    private final Scanner scanner;
    private final Map<Integer, MenuAction> actions = new HashMap<>();

    public VistaDocumentosConsola(SistemaDocumentosController controller) {
        this.scanner = new Scanner(System.in);

        actions.put(1, new CrearDocumentoSimpleAction(scanner, controller));
        actions.put(2, new CrearDocumentoExtendidoAction(scanner, controller));
    }

    public void mostrarMenuPrincipal() {
        int opcion = -1;
        do {
            System.out.println("\n--- Sistema para Gestión de Documentos ---");
            System.out.println("1. Crear Documento Simple");
            System.out.println("2. Crear Documento Extendido (con título, contenido, imagen y firma)");
            System.out.println("0. Salir");
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
