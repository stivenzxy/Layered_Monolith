package DesktopViews.SistemaDocumentos.Actions;

import DesktopViews.SistemaDocumentos.DocumentosDesktopView;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class LimpiarFormularioDocumentoAction extends AbstractAction {
    private final DocumentosDesktopView vista;

    public LimpiarFormularioDocumentoAction(DocumentosDesktopView vista) {
        super("Limpiar Formulario");
        putValue(SHORT_DESCRIPTION, "Limpia todos los campos del formulario");
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vista.limpiarFormulario();
    }
}
