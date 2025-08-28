package com.monolitoPorCapas;

import ConsoleViews.SistemaClientes.VistaClientesConsola;
import DesktopViews.SistemaClientes.VistaPrincipalClientesEscritorio;
import Factories.SistemaClientesFactory;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando la interfaz gráfica de escritorio...");
        SwingUtilities.invokeLater(() -> {
            VistaPrincipalClientesEscritorio v = SistemaClientesFactory.createClientesDesktopView();
            VistaPrincipalEscritorio mainFrame = new VistaPrincipalEscritorio(v);
            mainFrame.setVisible(true);
        });

        System.out.println("Iniciando la interfaz de consola...");
        VistaClientesConsola v = SistemaClientesFactory.createClientesConsoleView();
        VistaPrincipalConsola vistaPrincipalConsola = new VistaPrincipalConsola(v);
        vistaPrincipalConsola.iniciar();

        System.out.println("La aplicación ha finalizado su ejecución en la consola.");
    }
}
