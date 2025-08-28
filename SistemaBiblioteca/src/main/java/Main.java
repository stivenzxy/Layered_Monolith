import Biblioteca.DatabaseConfig.MysqlConnectionManager;
import Biblioteca.Entities.Lector;
import Biblioteca.Entities.Prestamo;
import Biblioteca.Interfaces.LectorRepository;
import Biblioteca.Repositories.LectorRepositoryImpl;
import Biblioteca.Repositories.PrestamoRepositoryImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        MysqlConnectionManager.obtenerInstancia().inicializarBaseDeDatos();

        LectorRepositoryImpl lectorRepository = new LectorRepositoryImpl();

        System.out.println(lectorRepository.obtenerLectoresRegistrados());

        PrestamoRepositoryImpl prestamoRepository = new PrestamoRepositoryImpl();

        Prestamo prestamo = new Prestamo();
        prestamo.setLectorId(1L);
        prestamo.setLibroId(1L);
        LocalDate fechaPrestamo = LocalDate.now();
        prestamo.setFechaPrestamo(fechaPrestamo);

       // prestamoRepository.crearPrestamo(prestamo);

        System.out.println(prestamoRepository.obtenerPrestamosRegistrados());
    }
}
