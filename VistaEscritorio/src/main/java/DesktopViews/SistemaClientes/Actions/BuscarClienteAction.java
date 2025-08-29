package DesktopViews.SistemaClientes.Actions;

import Controllers.SistemaClientesController;
import DTO.Cliente.ClienteDTO;
import DesktopViews.SistemaClientes.ClientesDesktopView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BuscarClienteAction extends AbstractAction {
    private final ClientesDesktopView vista;
    private final SistemaClientesController controller;

    public BuscarClienteAction(ClientesDesktopView vista, SistemaClientesController controller) {
        super("Buscar");
        putValue(SHORT_DESCRIPTION, "Busca un cliente específico por su número de ID");
        this.vista = vista;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String idStr = JOptionPane.showInputDialog(
                (java.awt.Component) vista,
                "Ingrese el ID del cliente a buscar:",
                "Buscar Cliente",
                JOptionPane.QUESTION_MESSAGE
        );

        if (idStr == null || idStr.trim().isEmpty()) {
            return;
        }

        try {
            long id = Long.parseLong(idStr);

            ClienteDTO clienteEncontrado = controller.buscarClientePorId(id);

            if (clienteEncontrado != null) {
                vista.mostrarMensaje("Cliente encontrado:\n" + clienteEncontrado);
            } else {
                vista.mostrarError("No se encontró ningún cliente con el ID " + id + ".");
            }

        } catch (NumberFormatException ex) {
            vista.mostrarError("El ID ingresado no es un número válido.");
        } catch (Exception ex) {
            vista.mostrarError("Ocurrió un error inesperado durante la búsqueda: " + ex.getMessage());
        }
    }
}