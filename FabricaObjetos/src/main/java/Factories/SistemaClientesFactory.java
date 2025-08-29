package Factories;

import ConsoleViews.SistemaClientes.VistaClientesConsola;
import Controllers.SistemaClientesController;
import DesktopViews.SistemaClientes.VistaPrincipalClientesEscritorio;
import Fachada.FachadaClientes;
import Fachada.Interfaces.SistemaClientesFacade;
import Interfaces.ClienteRepository;
import Repositories.ClienteRepositoryImpl;

public class SistemaClientesFactory {
    public static ClienteRepository createClientesRepository() {
        return new ClienteRepositoryImpl();
    }

    public static SistemaClientesFacade createClienteFacade() {
        return new FachadaClientes(createClientesRepository());
    }

    public static SistemaClientesController createClienteController() {
        return new SistemaClientesController(createClienteFacade());
    }

    public static VistaClientesConsola createClientesConsoleView() {
        return new VistaClientesConsola(createClienteController());
    }

    public static VistaPrincipalClientesEscritorio createClientesDesktopView() {
        return new VistaPrincipalClientesEscritorio(createClienteController());
    }
}
