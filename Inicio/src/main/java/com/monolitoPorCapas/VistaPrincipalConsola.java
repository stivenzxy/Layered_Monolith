package com.monolitoPorCapas;

import ConsoleViews.SistemaClientes.VistaClientesConsola;
import ConsoleViews.SistemaDocumentos.VistaDocumentosConsola;

import java.util.Scanner;

public class VistaPrincipalConsola {
    private final VistaClientesConsola vistaClientes;
    private final VistaDocumentosConsola vistaDocumentos;
    
    public VistaPrincipalConsola(VistaClientesConsola vistaClientes, VistaDocumentosConsola vistaDocumentos) {
        this.vistaClientes = vistaClientes;
        this.vistaDocumentos = vistaDocumentos;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===== MENÚ PRINCIPAL (CONSOLA) =====");
            System.out.println("1. Sistema de Clientes");
            System.out.println("2. Gestor de Documentos");
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
                        // Llama al menú del módulo de documentos
                        vistaDocumentos.mostrarMenuPrincipal();
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
