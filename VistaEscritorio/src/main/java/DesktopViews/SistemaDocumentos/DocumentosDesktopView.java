package DesktopViews.SistemaDocumentos;

import Interfaces.Documento;

public interface DocumentosDesktopView {
    String getFormato();
    String getContenido();
    String getTitulo();
    String getImagenUrl();
    String getFirma();
    
    void limpiarFormulario();
    void mostrarError(String mensaje);
    void mostrarMensaje(String mensaje);
    void mostrarDocumentoCreado(Documento documento);
}
