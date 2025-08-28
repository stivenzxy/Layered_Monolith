package DesktopViews.SistemaClientes.Actions;

import Controllers.SistemaClientesController;
import DesktopViews.SistemaClientes.ClientesDesktopView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EliminarClienteAction extends AbstractAction {

    private final ClientesDesktopView vista;
    private final SistemaClientesController controller;

    public EliminarClienteAction(ClientesDesktopView vista, SistemaClientesController controller) {
        super("Eliminar");
        putValue(Action.SHORT_DESCRIPTION, "Elimina el cliente actualmente seleccionado en la tabla");
        this.vista = vista;
        this.controller = controller;

        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Long id = vista.getSelectedId();

        if (id == null) {
            vista.mostrarError("No se ha seleccionado ningún cliente de la tabla.");
            return;
        }

        int deleteConfirmation = JOptionPane.showConfirmDialog(
                (java.awt.Component) vista,
                "¿Está seguro de que desea eliminar al cliente con ID " + id + "?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (deleteConfirmation != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            boolean eliminado = controller.eliminarCliente(id);

            if (eliminado) {
                vista.mostrarMensaje("Cliente eliminado exitosamente.");
                vista.refrescarTabla(controller.obtenerListadoClientes());
                vista.limpiarFormulario();
            } else {
                vista.mostrarError("No se pudo eliminar el cliente. Es posible que el ID no exista.");
            }
        } catch (Exception ex) {
            vista.mostrarError("Ocurrió un error inesperado al eliminar: " + ex.getMessage());
        }
    }
}
