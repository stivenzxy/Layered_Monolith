package Fachada;

import Fachada.Interfaces.SistemaDocumentosFacade;
import Facade.GestorDocumentos;
import Interfaces.Documento;

public class FachadaDocumentos implements SistemaDocumentosFacade {

    private final GestorDocumentos gestor;

    public FachadaDocumentos(GestorDocumentos gestor) {
        this.gestor = gestor;
    }

    @Override
    public Documento crearDocumentoSimple(String formato, String contenido) {
        return gestor.crearDocumento(formato, contenido);
    }

    @Override
    public Documento crearDocumentoExtendido(String tipoBuilder, String titulo, String contenido, String imagenUrl, String firma) {
        return gestor.crearDocumentoExtendido(tipoBuilder, titulo, contenido, imagenUrl, firma);
    }
}


