package App2ComponenteTextoPlano;

import Interfaces.Documento;

public class DocumentoTextoPlano implements Documento {
    private String contenido;

    @Override
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public void dibujar() {
        System.out.println("Dibujando contenido como TextoPlano: " + contenido);
    }

    @Override
    public void imprimir() {
        System.out.println("Imprimiendo Texto Plano: " + contenido);
    }
}
