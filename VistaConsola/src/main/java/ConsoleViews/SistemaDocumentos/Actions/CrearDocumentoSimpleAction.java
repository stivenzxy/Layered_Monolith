package ConsoleViews.SistemaDocumentos.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaDocumentosController;
import Interfaces.Documento;

import java.util.Scanner;

public class CrearDocumentoSimpleAction implements MenuAction {
    private final Scanner scanner;
    private final SistemaDocumentosController controlador;

    public CrearDocumentoSimpleAction(Scanner scanner, SistemaDocumentosController controlador) {
        this.scanner = scanner;
        this.controlador = controlador;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Crear Documento Simple ---");
        
        System.out.println("Formatos disponibles: pdf, html, texto");
        System.out.print("Formato del documento: ");
        String formato = scanner.nextLine().trim().toLowerCase();
        
        if (!formato.matches("pdf|html|texto")) {
            System.out.println("Error: Formato no válido. Use: pdf, html o texto");
            return;
        }
        
        System.out.print("Contenido del documento: ");
        String contenido = scanner.nextLine().trim();
        
        if (contenido.isEmpty()) {
            System.out.println("Error: El contenido no puede estar vacío.");
            return;
        }

        try {
            Documento documento = controlador.crearDocumentoSimple(formato, contenido);
            System.out.println("\nDocumento " + formato.toUpperCase() + " creado exitosamente!");
            System.out.println("Contenido: " + contenido);
        } catch (Exception e) {
            System.out.println("\nERROR AL CREAR DOCUMENTO: " + e.getMessage());
        }
    }
}
