package App2ComponentePDF;

import App2ComponentePDFAdaptado.ComponentePdf;
import Interfaces.Documento;

public class DocumentoPdf implements Documento {
    private final ComponentePdf herramientaPdf;

    public DocumentoPdf() {
        this.herramientaPdf = ComponentePdf.getInstance();
    }

    @Override
    public void setContenido(String contenido) {
        herramientaPdf.pdfFijaContenido(contenido);
    }

    @Override
    public void dibujar() {
        herramientaPdf.pdfPrepararVisualizacion();
        herramientaPdf.pdfRefrescar();
        herramientaPdf.pdfFinalizarVisualizacion();
    }

    @Override
    public void imprimir() {
        herramientaPdf.pdfEnviarImpresora();
    }
}
