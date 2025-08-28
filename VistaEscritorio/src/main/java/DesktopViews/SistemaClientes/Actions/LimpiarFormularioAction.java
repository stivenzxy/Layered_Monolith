package DesktopViews.SistemaClientes.Actions;

import DesktopViews.SistemaClientes.ClientesDesktopView;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

public class LimpiarFormularioAction extends AbstractAction {

    private final ClientesDesktopView vista;

    public LimpiarFormularioAction(ClientesDesktopView vista) {
        super("Limpiar");
        putValue(SHORT_DESCRIPTION, "Limpia todos los campos del formulario");
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vista.limpiarFormulario();
    }
}
