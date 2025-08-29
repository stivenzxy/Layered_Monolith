package ConsoleViews.SistemaBiblioteca;

import ConsoleViews.SistemaBiblioteca.Lectores.VistaLectoresConsola;
import ConsoleViews.SistemaBiblioteca.Libros.VistaLibrosConsola;
import ConsoleViews.SistemaBiblioteca.Prestamos.VistaPrestamosConsola;

import java.util.Scanner;

public class VistaBibliotecaConsola {

    private final VistaLectoresConsola vistaLectores;
    private final VistaLibrosConsola vistaLibros;
    private final VistaPrestamosConsola vistaPrestamos;

    public VistaBibliotecaConsola(VistaLectoresConsola vistaLectores,
                                  VistaLibrosConsola vistaLibros,
                                  VistaPrestamosConsola vistaPrestamos) {
        this.vistaLectores = vistaLectores;
        this.vistaLibros = vistaLibros;
        this.vistaPrestamos = vistaPrestamos;
    }

    public void mostrarMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===== MENÚ PRINCIPAL - BIBLIOTECA (CONSOLA) =====");
            System.out.println("1. Gestionar Lectores");
            System.out.println("2. Gestionar Libros");
            System.out.println("3. Gestionar Préstamos");
            System.out.println("0. Volver al Inicio");
            System.out.print("Seleccione un módulo: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        vistaLectores.mostrarMenu();
                        break;
                    case 2:
                        vistaLibros.mostrarMenu();
                        break;
                    case 3:
                        vistaPrestamos.mostrarMenu();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();
            }

        } while (opcion != 0);
    }
}