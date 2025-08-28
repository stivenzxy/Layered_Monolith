package DesktopViews.SistemaClientes;

import DTO.ClienteDTO;

import java.util.List;

public interface ClientesDesktopView {
    Long getSelectedId();
    String getDocumento();
    String getNombres();
    String getApellidos();
    String getEmail();

    void refrescarTabla(List<ClienteDTO> clientes);
    void limpiarFormulario();
    void mostrarError(String mensaje);
    void mostrarMensaje(String mensaje);
}
