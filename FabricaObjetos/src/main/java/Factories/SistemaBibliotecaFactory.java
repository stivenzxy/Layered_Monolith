package Factories;

import Biblioteca.Interfaces.LectorRepository;
import Biblioteca.Interfaces.LibroRepository;
import Biblioteca.Interfaces.PrestamoRepository;
import Biblioteca.Interfaces.Services.LectorService;
import Biblioteca.Interfaces.Services.LibroService;
import Biblioteca.Interfaces.Services.PrestamoService;
import Biblioteca.Repositories.LectorRepositoryImpl;
import Biblioteca.Repositories.LibroRepositoryImpl;
import Biblioteca.Repositories.PrestamoRepositoryImpl;
import Biblioteca.Services.LectorServiceImpl;
import Biblioteca.Services.LibroServiceImpl;
import Biblioteca.Services.PrestamoServiceImpl;
import ConsoleViews.SistemaBiblioteca.Lectores.VistaLectoresConsola;
import ConsoleViews.SistemaBiblioteca.Libros.VistaLibrosConsola;
import ConsoleViews.SistemaBiblioteca.Prestamos.VistaPrestamosConsola;
import ConsoleViews.SistemaBiblioteca.VistaBibliotecaConsola;
import Controllers.SistemaBiblioteca.LectorController;
import Controllers.SistemaBiblioteca.LibroController;
import Controllers.SistemaBiblioteca.PrestamoController;


public class SistemaBibliotecaFactory {
    public static LectorRepository createLectorRepository() {
        return new LectorRepositoryImpl();
    }

    public static LibroRepository createLibroRepository() {
        return new LibroRepositoryImpl();
    }

    public static PrestamoRepository createPrestamoRepository() {
        return new PrestamoRepositoryImpl();
    }

    public static LectorService createLectorService() {
        return new LectorServiceImpl(createLectorRepository());
    }

    public static LibroService createLibroService() {
        return new LibroServiceImpl(createLibroRepository());
    }

    public static PrestamoService createPrestamoService() {
        return new PrestamoServiceImpl(createPrestamoRepository(), createLibroRepository(), createLectorRepository());
    }

    public static LectorController createLectorController() {
        return new LectorController(createLectorService());
    }

    public static LibroController createLibroController() {
        return new LibroController(createLibroService());
    }

    public static PrestamoController createPrestamoController() {
        return new PrestamoController(createPrestamoService());
    }

    public static VistaLectoresConsola createLectoresConsoleView() {
        return new VistaLectoresConsola(createLectorController());
    }

    public static VistaLibrosConsola createLibrosConsoleView() {
        return new VistaLibrosConsola(createLibroController());
    }

    public static VistaPrestamosConsola createPrestamosConsoleView() {
        return new VistaPrestamosConsola(createPrestamoController(), createLectorController(), createLibroController());
    }

    public static VistaBibliotecaConsola createBibliotecaConsoleView() {
        return new VistaBibliotecaConsola(createLectoresConsoleView(), createLibrosConsoleView(), createPrestamosConsoleView());
    }

}
