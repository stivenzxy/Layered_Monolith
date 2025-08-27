package Fabrica;

import App2ComponenteHTML.DocumentoHTMLBuilderFactory;
import App2ComponentePDF.DocumentoPdfBuilderFactory;
import App2ComponenteTextoPlano.DocumentoTextoPlanoBuilderFactory;
import Interfaces.FabricaBuilder;

public class ProveedorBuilderDocumento {
    public static FabricaBuilder obtenerBuilderFactory(String tipoDocumento) {
        return switch (tipoDocumento) {
            case "pdf" -> DocumentoPdfBuilderFactory.getInstance();
            case "texto" -> DocumentoTextoPlanoBuilderFactory.getInstance();
            case "html" -> DocumentoHTMLBuilderFactory.getInstance();
            default -> throw new IllegalArgumentException("No se ha encontrado builder para el tipo de documento " + tipoDocumento + ".");
        };
    }
}
