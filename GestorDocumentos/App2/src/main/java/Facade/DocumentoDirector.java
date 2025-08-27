package Facade;

import Fabrica.ProveedorBuilderDocumento;
import Interfaces.Documento;
import Interfaces.DocumentoBuilder;
import Interfaces.FabricaBuilder;

public class DocumentoDirector {
    
    public Documento crearDocumentoSimple(String tipo, String contenido) {
        DocumentoBuilder builder = obtenerBuilder(tipo);
        return builder.contenido(contenido).build();
    }
    
    public Documento crearDocumentoCompleto(String tipo, String titulo, String contenido, String imagen, String firma) {
        DocumentoBuilder builder = obtenerBuilder(tipo);
        return builder
                .titulo(titulo)
                .contenido(contenido)
                .imagenUrl(imagen)
                .firma(firma)
                .build();
    }
    
    public Documento crearDocumentoReporte(String tipo, String titulo, String contenido) {
        DocumentoBuilder builder = obtenerBuilder(tipo);
        return builder
                .titulo(titulo)
                .contenido(contenido)
                .firma("Generado automáticamente")
                .build();
    }
    
    private DocumentoBuilder obtenerBuilder(String tipo) {
        FabricaBuilder fabricaBuilder = ProveedorBuilderDocumento.obtenerBuilderFactory(tipo);
        return fabricaBuilder.crearBuilder();
    }
}
