package Facade;

import Fabrica.ProveedorFabricaDocumento;
import Fabrica.ProveedorBuilderDocumento;
import Interfaces.Documento;
import Interfaces.FabricaDocumento;
import Interfaces.FabricaBuilder;
import Interfaces.DocumentoBuilder;

public class GestorDocumentos {
    public void renderizarDocumento(String formato, String contenido) {
        if ("pdfExtendido".equalsIgnoreCase(formato)) {
            FabricaBuilder fabricaBuilder = ProveedorBuilderDocumento.obtenerBuilderFactory("pdf");
            DocumentoBuilder builder = fabricaBuilder.crearBuilder();
            Documento documento = builder
                    .titulo("Pdf con Builder")
                    .contenido(contenido)
                    .imagenUrl("imagen.png")
                    .firma("Firma del autor")
                    .build();

            documento.dibujar();
            documento.imprimir();
            System.out.println("--- Renderizado de PDF con Builder finalizado ---\n");
            return;
        }
        
        if ("htmlExtendido".equalsIgnoreCase(formato)) {
            FabricaBuilder fabricaBuilder = ProveedorBuilderDocumento.obtenerBuilderFactory("html");
            DocumentoBuilder builder = fabricaBuilder.crearBuilder();
            Documento documento = builder
                    .titulo("HTML con Builder")
                    .contenido(contenido)
                    .imagenUrl("imagen.png")
                    .firma("Firma del autor")
                    .build();

            documento.dibujar();
            documento.imprimir();
            System.out.println("--- Renderizado de HTML con Builder finalizado ---\n");
            return;
        }
        
        if ("textoExtendido".equalsIgnoreCase(formato)) {
            FabricaBuilder fabricaBuilder = ProveedorBuilderDocumento.obtenerBuilderFactory("texto");
            DocumentoBuilder builder = fabricaBuilder.crearBuilder();
            Documento documento = builder
                    .titulo("Texto Plano con Builder")
                    .contenido(contenido)
                    .imagenUrl("imagen.png")
                    .firma("Firma del autor")
                    .build();

            documento.dibujar();
            documento.imprimir();
            System.out.println("--- Renderizado de Texto Plano con Builder finalizado ---\n");
            return;
        }

        FabricaDocumento fabrica = ProveedorFabricaDocumento.obtenerFabrica(formato);
        Documento documento = fabrica.crear();
        documento.setContenido(contenido);

        documento.dibujar();
        documento.imprimir();
        System.out.println("--- Renderizado de " + formato + " finalizado ---\n");
    }
}