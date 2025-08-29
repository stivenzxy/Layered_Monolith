package DesktopViews.SistemaDocumentos.Actions;

import Controllers.SistemaDocumentosController;
import DesktopViews.SistemaDocumentos.DocumentosDesktopView;
import Interfaces.Documento;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class CrearDocumentoSimpleAction extends AbstractAction {
    private final DocumentosDesktopView vista;
    private final SistemaDocumentosController controller;

    public CrearDocumentoSimpleAction(DocumentosDesktopView vista, SistemaDocumentosController controller) {
        super("Crear Documento Simple");
        putValue(SHORT_DESCRIPTION, "Crea un documento simple con formato y contenido");
        this.vista = vista;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vista.getContenido().trim().isEmpty()) {
            vista.mostrarError("El contenido del documento no puede estar vacío.");
            return;
        }

        try {
            Documento documento = controller.crearDocumentoSimple(
                vista.getFormato(), 
                vista.getContenido()
            );
            vista.mostrarDocumentoCreado(documento);
        } catch (Exception ex) {
            vista.mostrarError("Error al crear el documento: " + ex.getMessage());
        }
    }
}
