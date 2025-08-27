package Interfaces;

public interface DocumentoBuilder {
    DocumentoBuilder contenido(String contenido);
    DocumentoBuilder titulo(String titulo);
    DocumentoBuilder imagenUrl(String imagenUrl);
    DocumentoBuilder firma(String firma);
    Documento build();
}
