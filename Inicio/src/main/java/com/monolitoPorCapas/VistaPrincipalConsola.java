package com.monolitoPorCapas;

import ConsoleViews.SistemaClientes.VistaClientesConsola;

import java.util.Scanner;

public class VistaPrincipalConsola {
    private final VistaClientesConsola vistaClientes;
    public VistaPrincipalConsola(VistaClientesConsola vistaClientes) {
        this.vistaClientes = vistaClientes;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===== MENÚ PRINCIPAL (CONSOLA) =====");
            System.out.println("1. Sistema de Clientes");
            System.out.println("2. Gestor de Documentos (No implementado)");
            System.out.println("0. Salir de la Consola");
            System.out.print("Seleccione un módulo: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        // Llama al menú del módulo de clientes
                        vistaClientes.mostrarMenuPrincipal();
                        break;
                    case 2:
                        System.out.println("Este módulo aún no está disponible.");
                        break;
                    case 0:
                        System.out.println("Cerrando la interfaz de consola...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine(); // Limpieza extra en caso de error
            }

        } while (opcion != 0);
    }
}
