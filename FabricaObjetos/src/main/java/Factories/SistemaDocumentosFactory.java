package Factories;

import Controllers.SistemaDocumentosController;
import Fachada.FachadaDocumentos;
import Fachada.Interfaces.SistemaDocumentosFacade;
import Facade.GestorDocumentos;
import ConsoleViews.SistemaDocumentos.VistaDocumentosConsola;
import DesktopViews.SistemaDocumentos.VistaPrincipalDocumentosEscritorio;

public class SistemaDocumentosFactory {
    
    public static GestorDocumentos createGestorDocumentos() {
        return new GestorDocumentos();
    }

    public static SistemaDocumentosFacade createDocumentosFacade() {
        return new FachadaDocumentos(createGestorDocumentos());
    }

    public static SistemaDocumentosController createDocumentosController() {
        return new SistemaDocumentosController(createDocumentosFacade());
    }
    
    public static VistaDocumentosConsola createDocumentosConsoleView() {
        return new VistaDocumentosConsola(createDocumentosController());
    }
    
    public static VistaPrincipalDocumentosEscritorio createDocumentosDesktopView() {
        return new VistaPrincipalDocumentosEscritorio(createDocumentosController());
    }
}
