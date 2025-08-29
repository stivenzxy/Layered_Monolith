package Facade;

import Fabrica.ProveedorFabricaDocumento;
import Fabrica.ProveedorBuilderDocumento;
import Interfaces.Documento;
import Interfaces.FabricaDocumento;
import Interfaces.FabricaBuilder;
import Interfaces.DocumentoBuilder;

public class GestorDocumentos {
    public Documento crearDocumento(String formato, String contenido) {
        FabricaDocumento fabrica = ProveedorFabricaDocumento.obtenerFabrica(formato);
        Documento documento = fabrica.crear();
        documento.setContenido(contenido);
        return documento;
    }

    public Documento crearDocumentoExtendido(String tipoBuilder, String titulo, String contenido, String imagenUrl, String firma) {
        FabricaBuilder fabricaBuilder = ProveedorBuilderDocumento.obtenerBuilderFactory(tipoBuilder);
        DocumentoBuilder builder = fabricaBuilder.crearBuilder();
        return builder
                .titulo(titulo)
                .contenido(contenido)
                .imagenUrl(imagenUrl)
                .firma(firma)
                .build();
    }
}