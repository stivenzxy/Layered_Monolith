package Fachada.Interfaces;

import Interfaces.Documento;

public interface SistemaDocumentosFacade {
    Documento crearDocumentoSimple(String formato, String contenido);
    Documento crearDocumentoExtendido(String tipoBuilder, String titulo, String contenido, String imagenUrl, String firma);
}


