package com.monolitoPorCapas;

import ConsoleViews.SistemaBiblioteca.VistaBibliotecaConsola;
import ConsoleViews.SistemaClientes.VistaClientesConsola;
import ConsoleViews.SistemaDocumentos.VistaDocumentosConsola;
import ConsoleViews.VistaPrincipalConsola;
import DesktopViews.SistemaClientes.VistaPrincipalClientesEscritorio;
import DesktopViews.SistemaDocumentos.VistaPrincipalDocumentosEscritorio;
import DesktopViews.VistaPrincipalEscritorio;
import Factories.SistemaBibliotecaFactory;
import Factories.SistemaClientesFactory;
import Factories.SistemaDocumentosFactory;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando la interfaz gráfica de escritorio...");
        SwingUtilities.invokeLater(() -> {
            VistaPrincipalClientesEscritorio vClientes = SistemaClientesFactory.createClientesDesktopView();
            VistaPrincipalDocumentosEscritorio vDocumentos = SistemaDocumentosFactory.createDocumentosDesktopView();
            VistaPrincipalEscritorio mainFrame = new VistaPrincipalEscritorio(vClientes, vDocumentos);
            mainFrame.setVisible(true);
        });

        System.out.println("Iniciando la interfaz de consola...");
        VistaClientesConsola vClientes = SistemaClientesFactory.createClientesConsoleView();
        VistaDocumentosConsola vDocumentos = SistemaDocumentosFactory.createDocumentosConsoleView();
        VistaBibliotecaConsola vBiblioteca = SistemaBibliotecaFactory.createBibliotecaConsoleView();
        VistaPrincipalConsola vistaPrincipalConsola = new VistaPrincipalConsola(vClientes, vDocumentos, vBiblioteca);
        vistaPrincipalConsola.iniciar();

        System.out.println("La aplicación ha finalizado su ejecución en la consola.");
    }
}
