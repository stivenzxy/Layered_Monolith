package com.monolitoPorCapas;

import ConsoleViews.SistemaClientes.VistaClientesConsola;
import Factories.SistemaClientesFactory;

public class Main {
    public static void main(String[] args) {
        VistaClientesConsola vistaClientesConsola = SistemaClientesFactory.createVistaConsola();
        vistaClientesConsola.mostrarMenuPrincipal();
    }
}
