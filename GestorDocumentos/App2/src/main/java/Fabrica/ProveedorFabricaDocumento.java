package Fabrica;

import App2ComponenteHTML.DocumentoHTMLFactory;
import App2ComponentePDF.DocumentoPdfFactory;
import App2ComponenteTextoPlano.DocumentoTextoPlanoFactory;
import Interfaces.FabricaDocumento;

public class ProveedorFabricaDocumento {
    public static FabricaDocumento obtenerFabrica(String tipoDocumento) {
        return switch (tipoDocumento) {
            case "pdf" -> DocumentoPdfFactory.getInstance();
            case "texto" -> DocumentoTextoPlanoFactory.getInstance();
            case "html" -> DocumentoHTMLFactory.getInstance();
            default -> throw new IllegalArgumentException("Tipo de documento " + tipoDocumento + " no soportado.");
        };
    }
}
