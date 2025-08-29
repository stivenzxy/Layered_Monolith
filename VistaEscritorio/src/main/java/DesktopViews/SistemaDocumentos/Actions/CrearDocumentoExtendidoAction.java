package DesktopViews.SistemaDocumentos.Actions;

import Controllers.SistemaDocumentosController;
import DesktopViews.SistemaDocumentos.DocumentosDesktopView;
import Interfaces.Documento;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class CrearDocumentoExtendidoAction extends AbstractAction {
    private final DocumentosDesktopView vista;
    private final SistemaDocumentosController controller;

    public CrearDocumentoExtendidoAction(DocumentosDesktopView vista, SistemaDocumentosController controller) {
        super("Crear Documento Extendido");
        putValue(SHORT_DESCRIPTION, "Crea un documento extendido con Builder (título, imagen, firma)");
        this.vista = vista;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vista.getTitulo().trim().isEmpty()) {
            vista.mostrarError("El título del documento no puede estar vacío.");
            return;
        }

        if (vista.getContenido().trim().isEmpty()) {
            vista.mostrarError("El contenido del documento no puede estar vacío.");
            return;
        }

        try {
            Documento documento = controller.crearDocumentoExtendido(
                vista.getFormato(),
                vista.getTitulo(),
                vista.getContenido(),
                vista.getImagenUrl().isEmpty() ? "sin-imagen" : vista.getImagenUrl(),
                vista.getFirma().isEmpty() ? "Firma del autor" : vista.getFirma()
            );
            vista.mostrarDocumentoCreado(documento);
        } catch (Exception ex) {
            vista.mostrarError("Error al crear el documento extendido: " + ex.getMessage());
        }
    }
}
