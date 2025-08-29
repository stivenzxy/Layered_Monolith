package DesktopViews.SistemaClientes;

import Controllers.SistemaClientesController;
import DTO.Cliente.ClienteDTO;
import DesktopViews.SistemaClientes.Actions.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VistaPrincipalClientesEscritorio extends JPanel implements ClientesDesktopView{
    private final SistemaClientesController controller;

    private JTable tablaClientes;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtDocumento, txtNombres, txtApellidos, txtEmail;

    private Action guardarClienteAction;
    private Action buscarClienteAction;
    private Action eliminarClienteAction;

    private Action limpiarTablaAction;
    private Action refrescarTablaAction;

    public VistaPrincipalClientesEscritorio(SistemaClientesController controller) {
        this.controller = controller;
        crearActions();
        initComponents();
        initListeners();
        refrescarTabla(controller.obtenerListadoClientes());
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        txtId = new JTextField();
        txtId.setEditable(false);
        txtDocumento = new JTextField(20);
        txtNombres = new JTextField(20);
        txtApellidos = new JTextField(20);
        txtEmail = new JTextField(20);

        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Documento (*):"));
        panelFormulario.add(txtDocumento);
        panelFormulario.add(new JLabel("Nombres:"));
        panelFormulario.add(txtNombres);
        panelFormulario.add(new JLabel("Apellidos:"));
        panelFormulario.add(txtApellidos);
        panelFormulario.add(new JLabel("Email:"));
        panelFormulario.add(txtEmail);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(new JButton(guardarClienteAction));
        panelBotones.add(new JButton(buscarClienteAction));
        panelBotones.add(new JButton(eliminarClienteAction));
        panelBotones.add(new JButton(limpiarTablaAction));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);

        String[] columnas = {"ID", "Documento", "Nombres", "Apellidos", "Email"};
        tableModel = new DefaultTableModel(columnas, 0);
        tablaClientes = new JTable(tableModel);
        add(new JScrollPane(tablaClientes), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.add(new JButton(refrescarTablaAction));
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void initListeners() {
        tablaClientes.getSelectionModel().addListSelectionListener(e -> {
            boolean filaSeleccionada = tablaClientes.getSelectedRow() != -1;
            eliminarClienteAction.setEnabled(filaSeleccionada);

            if (!e.getValueIsAdjusting() && filaSeleccionada) {
                llenarFormularioDesdeTabla();
            }
        });
    }

    private void crearActions() {
        guardarClienteAction = new GuardarClienteAction(this, controller);
        buscarClienteAction = new BuscarClienteAction(this, controller);
        eliminarClienteAction = new EliminarClienteAction(this, controller);

        limpiarTablaAction = new LimpiarFormularioAction(this);
        refrescarTablaAction = new RefrescarListadoClientesAction(this, controller);
    }

    @Override
    public Long getSelectedId() {
        String idStr = txtId.getText();
        return idStr.isEmpty() ? null : Long.parseLong(idStr);
    }

    @Override
    public String getDocumento() { return txtDocumento.getText(); }

    @Override
    public String getNombres() { return txtNombres.getText(); }

    @Override
    public String getApellidos() { return txtApellidos.getText(); }

    @Override
    public String getEmail() { return txtEmail.getText(); }

    @Override
    public void refrescarTabla(List<ClienteDTO> clientes) {
        tableModel.setRowCount(0);
        for (ClienteDTO cliente : clientes) {
            tableModel.addRow(new Object[]{
                    cliente.getId(), cliente.getNumeroDocumento(), cliente.getNombres(),
                    cliente.getApellidos(), cliente.getEmail()
            });
        }
    }

    @Override
    public void limpiarFormulario() {
        txtId.setText("");
        txtDocumento.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        tablaClientes.clearSelection();
        guardarClienteAction.putValue(Action.NAME, "Guardar");
    }

    @Override
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void llenarFormularioDesdeTabla() {
        int filaSeleccionada = tablaClientes.getSelectedRow();
        txtId.setText(tableModel.getValueAt(filaSeleccionada, 0).toString());
        txtDocumento.setText(tableModel.getValueAt(filaSeleccionada, 1).toString());
        txtNombres.setText(tableModel.getValueAt(filaSeleccionada, 2).toString());
        txtApellidos.setText(tableModel.getValueAt(filaSeleccionada, 3).toString());
        txtEmail.setText(tableModel.getValueAt(filaSeleccionada, 4).toString());
        guardarClienteAction.putValue(Action.NAME, "Actualizar");
    }
}