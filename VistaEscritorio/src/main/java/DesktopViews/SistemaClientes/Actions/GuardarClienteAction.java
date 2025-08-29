package DesktopViews.SistemaClientes.Actions;

import Controllers.SistemaClientesController;
import DTO.Cliente.ClienteDTO;
import DesktopViews.SistemaClientes.ClientesDesktopView;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class GuardarClienteAction extends AbstractAction {
    private final ClientesDesktopView vistaPrincipal;
    private final SistemaClientesController controller;

    public GuardarClienteAction(ClientesDesktopView vista, SistemaClientesController controller) {
        super("Guardar");
        putValue(SHORT_DESCRIPTION, "Guarda un cliente nuevo o actualiza uno existente");
        this.vistaPrincipal = vista;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vistaPrincipal.getDocumento().trim().isEmpty()) {
            vistaPrincipal.mostrarError("El número de documento es obligatorio.");
            return;
        }

        ClienteDTO dto = new ClienteDTO();
        dto.setNumeroDocumento(vistaPrincipal.getDocumento());
        dto.setNombres(vistaPrincipal.getNombres());
        dto.setApellidos(vistaPrincipal.getApellidos());
        dto.setEmail(vistaPrincipal.getEmail());

        Long id = vistaPrincipal.getSelectedId();
        if (id != null) {
            dto.setId(id);
        }

        try {
            controller.guardarCliente(dto);
            vistaPrincipal.mostrarMensaje("Cliente guardado exitosamente!");

            vistaPrincipal.refrescarTabla(controller.obtenerListadoClientes());
            vistaPrincipal.limpiarFormulario();
        } catch (Exception ex) {
            vistaPrincipal.mostrarError("Error al guardar el cliente: " + ex.getMessage());
        }
    }
}