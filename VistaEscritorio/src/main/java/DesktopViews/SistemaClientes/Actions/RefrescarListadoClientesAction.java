package DesktopViews.SistemaClientes.Actions;

import Controllers.SistemaClientesController;
import DTO.Cliente.ClienteDTO;
import DesktopViews.SistemaClientes.ClientesDesktopView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RefrescarListadoClientesAction extends AbstractAction {
    private final ClientesDesktopView vista;
    private final SistemaClientesController controller;

    public RefrescarListadoClientesAction(ClientesDesktopView vista, SistemaClientesController controller) {
        super("Refrescar Tabla");
        putValue(SHORT_DESCRIPTION, "Vuelve a cargar todos los clientes desde la base de datos");
        this.vista = vista;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            List<ClienteDTO> clientes = controller.obtenerListadoClientes();

            vista.refrescarTabla(clientes);
            vista.mostrarMensaje("Tabla de clientes actualizada.");
        } catch (Exception ex) {
            vista.mostrarError("No se pudo refrescar la tabla: " + ex.getMessage());
        }
    }
}