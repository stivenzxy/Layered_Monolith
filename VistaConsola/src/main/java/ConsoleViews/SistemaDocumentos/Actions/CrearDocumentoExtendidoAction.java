package ConsoleViews.SistemaDocumentos.Actions;

import ConsoleViews.MenuAction;
import Controllers.SistemaDocumentosController;
import Interfaces.Documento;

import java.util.Scanner;

public class CrearDocumentoExtendidoAction implements MenuAction {
    private final Scanner scanner;
    private final SistemaDocumentosController controlador;

    public CrearDocumentoExtendidoAction(Scanner scanner, SistemaDocumentosController controlador) {
        this.scanner = scanner;
        this.controlador = controlador;
    }

    @Override
    public void execute() {
        System.out.println("\n--- Crear Documento Extendido ---");
        
        System.out.println("Formatos disponibles: pdf, html, texto");
        System.out.print("Formato del documento: ");
        String tipoBuilder = scanner.nextLine().trim().toLowerCase();
        
        if (!tipoBuilder.matches("pdf|html|texto")) {
            System.out.println("Error: Formato no válido. Use: pdf, html o texto");
            return;
        }
        
        System.out.print("Título del documento: ");
        String titulo = scanner.nextLine().trim();
        
        if (titulo.isEmpty()) {
            System.out.println("Error: El título no puede estar vacío.");
            return;
        }
        
        System.out.print("Contenido del documento: ");
        String contenido = scanner.nextLine().trim();
        
        if (contenido.isEmpty()) {
            System.out.println("Error: El contenido no puede estar vacío.");
            return;
        }
        
        System.out.print("URL de imagen (opcional): ");
        String imagenUrl = scanner.nextLine().trim();
        if (imagenUrl.isEmpty()) {
            imagenUrl = "sin-imagen";
        }
        
        System.out.print("Firma (opcional): ");
        String firma = scanner.nextLine().trim();
        if (firma.isEmpty()) {
            firma = "Firma del autor";
        }

        try {
            Documento documento = controlador.crearDocumentoExtendido(tipoBuilder, titulo, contenido, imagenUrl, firma);
            System.out.println("\nDocumento extendido " + tipoBuilder.toUpperCase() + " creado exitosamente!");
            System.out.println("Título: " + titulo);
            System.out.println("Contenido: " + contenido);
            System.out.println("Imagen: " + imagenUrl);
            System.out.println("Firma: " + firma);
        } catch (Exception e) {
            System.out.println("\nERROR AL CREAR DOCUMENTO: " + e.getMessage());
        }
    }
}
