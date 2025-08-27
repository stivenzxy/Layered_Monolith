package App2ComponenteHTML;

import Interfaces.Documento;
import Interfaces.DocumentoBuilder;

public class DocumentoHTMLBuilder implements DocumentoBuilder {
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
        DocumentoHTML documento = new DocumentoHTML();
        
        StringBuilder sb = new StringBuilder();
        if (titulo != null) sb.append("<h1>").append(titulo).append("</h1>");
        if (contenido != null) sb.append("<p>").append(contenido).append("</p>");
        if (imagen != null) sb.append("<img src=\"").append(imagen).append("\" alt=\"Imagen\" />");
        if (firma != null) sb.append("<footer>").append(firma).append("</footer>");
        
        documento.setContenido(sb.toString());
        return documento;
    }
}
