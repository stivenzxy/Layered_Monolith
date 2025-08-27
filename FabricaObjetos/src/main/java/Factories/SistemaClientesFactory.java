package Factories;

import ConsoleViews.SistemaClientes.VistaClientesConsola;
import Controllers.SistemaClientesController;
import DesktopViews.SistemaClientes.VistaClientesEscritorio;
import Fachada.FachadaClientes;
import Fachada.Interfaces.SistemaClientesFacade;
import Interfaces.ClientesRepository;
import Repositories.ClientesRepositoryImpl;

public class SistemaClientesFactory {
    public static ClientesRepository createClientesRepository() {
        return new ClientesRepositoryImpl();
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

    public static VistaClientesEscritorio createClientesDesktopView() {
        return new VistaClientesEscritorio(createClienteController());
    }
}
