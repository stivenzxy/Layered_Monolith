package App2ComponentePDF;

import Interfaces.Documento;
import Interfaces.DocumentoBuilder;

public class DocumentoPdfExtendidoBuilder implements DocumentoBuilder {
    private String titulo;
    private String contenido;
    private String imagen;
    private String firma;

    @Override
    public DocumentoBuilder titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    @Override
    public DocumentoBuilder contenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    @Override
    public DocumentoBuilder imagenUrl(String imagenUrl) {
        this.imagen = imagenUrl;
        return this;
    }

    @Override
    public DocumentoBuilder firma(String firma) {
        this.firma = firma;
        return this;
    }

    @Override
    public Documento build() {
        DocumentoPdf documento = new DocumentoPdf();

        StringBuilder sb = new StringBuilder();
        if (titulo != null) sb.append("TITULO: ").append(titulo).append("\n");
        if (contenido != null) sb.append("CONTENIDO: ").append(contenido).append("\n");
        if (imagen != null) sb.append("IMAGEN: ").append(imagen).append("\n");
        if (firma != null) sb.append("FIRMA: ").append(firma).append("\n");

        documento.setContenido(sb.toString());

        return documento;
    }
}
