package Controllers;

import Fachada.Interfaces.SistemaDocumentosFacade;
import Interfaces.Documento;

public class SistemaDocumentosController {
    private final SistemaDocumentosFacade facade;

    public SistemaDocumentosController(SistemaDocumentosFacade facade) {
        this.facade = facade;
    }

    public Documento crearDocumentoSimple(String formato, String contenido) {
        return facade.crearDocumentoSimple(formato, contenido);
    }

    public Documento crearDocumentoExtendido(String tipoBuilder, String titulo, String contenido, String imagenUrl, String firma) {
        return facade.crearDocumentoExtendido(tipoBuilder, titulo, contenido, imagenUrl, firma);
    }
}


