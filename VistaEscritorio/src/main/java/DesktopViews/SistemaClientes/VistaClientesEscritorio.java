package DesktopViews.SistemaClientes;

import Controllers.SistemaClientesController;
import DTO.ClienteDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VistaClientesEscritorio extends JPanel {
    private final SistemaClientesController controller;

    private JTable tablaClientes;
    private DefaultTableModel tableModel;
    private JTextField txtId, txtDocumento, txtNombres, txtApellidos, txtEmail;
    private JButton btnGuardar, btnEliminar, btnLimpiar;

    public VistaClientesEscritorio(SistemaClientesController controller) {
        this.controller = controller;
        initComponents();
        initListeners();
        refrescarTabla();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // --- Panel del Formulario (Norte) ---
        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        txtId = new JTextField();
        txtId.setEditable(false); // El ID no se edita manualmente
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

        // --- Panel de Botones (junto al formulario) ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar Seleccionado");
        btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        // --- Tabla de Clientes (Centro) ---
        String[] columnas = {"ID", "Documento", "Nombres", "Apellidos", "Email"};
        tableModel = new DefaultTableModel(columnas, 0);
        tablaClientes = new JTable(tableModel);

        add(new JScrollPane(tablaClientes), BorderLayout.CENTER);
    }

    private void initListeners() {
        // Acción para el botón Guardar
        btnGuardar.addActionListener(e -> guardarCliente());

        // Acción para el botón Limpiar
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        tablaClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaClientes.getSelectedRow() != -1) {
                llenarFormularioDesdeTabla();
            }
        });
    }

    private void guardarCliente() {
        if (txtDocumento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El número de documento es obligatorio.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ClienteDTO dto = new ClienteDTO();
        dto.setNumeroDocumento(txtDocumento.getText());
        dto.setNombres(txtNombres.getText());
        dto.setApellidos(txtApellidos.getText());
        dto.setEmail(txtEmail.getText());
        // Si el campo ID tiene un valor, es una actualización
        if (!txtId.getText().isEmpty()) {
            dto.setId(Long.parseLong(txtId.getText()));
        }

        try {
            controller.crearCliente(dto);
            refrescarTabla();
            limpiarFormulario();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarFormularioDesdeTabla() {
        int filaSeleccionada = tablaClientes.getSelectedRow();
        txtId.setText(tableModel.getValueAt(filaSeleccionada, 0).toString());
        txtDocumento.setText(tableModel.getValueAt(filaSeleccionada, 1).toString());
        txtNombres.setText(tableModel.getValueAt(filaSeleccionada, 2).toString());
        txtApellidos.setText(tableModel.getValueAt(filaSeleccionada, 3).toString());
        txtEmail.setText(tableModel.getValueAt(filaSeleccionada, 4).toString());
    }

    public void refrescarTabla() {
        // Limpia el modelo actual de la tabla
        tableModel.setRowCount(0);
        // Pide la lista actualizada de clientes al controlador
        List<ClienteDTO> clientes = controller.obtenerListadoClientes();
        // Agrega cada cliente como una nueva fila en la tabla
        for (ClienteDTO cliente : clientes) {
            tableModel.addRow(new Object[]{
                    cliente.getId(),
                    cliente.getNumeroDocumento(),
                    cliente.getNombres(),
                    cliente.getApellidos(),
                    cliente.getEmail()
            });
        }
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtDocumento.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEmail.setText("");
        tablaClientes.clearSelection();
    }
}
