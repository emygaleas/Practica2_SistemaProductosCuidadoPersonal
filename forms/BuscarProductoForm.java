package forms;

import database.ConexionDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarProductoForm extends JFrame {
    private JLabel usernameTxt;
    private JTextField categoriaField;
    private JTextField cantidadField;
    private JTextField precioField;
    private JTextArea descripcionField;
    private JTextField nombreField;
    private JTextField codigoField;
    private JButton buscarButton;
    private JButton irARegistrarProductoButton;
    private JButton loginButton;
    private JPanel buscarPanel;
    private JButton limpiarButton;

    public BuscarProductoForm(String username){
        setTitle("Buscar Producto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400); // Tamaño de la ventana
        setContentPane(buscarPanel); // Panel principal
        setLocationRelativeTo(null); // Centrar la ventana

        setVisible(true); // Ventana visible

        // Mostrar el nombre de usuario en la etiqueta
        usernameTxt.setText(username);

        // Acción del botón de buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigoField.getText().trim();
                if (codigo.isEmpty()) {
                    JOptionPane.showMessageDialog(buscarPanel, "Por favor ingrese el código del producto.");
                    return;
                }

                buscarProducto(codigo);
            }
        });

        // Acción para ir a registrar un producto
        irARegistrarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrarProductosForm(username);
                dispose();
            }
        });

        // Acción para volver al login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(buscarPanel, "Cerrando sesión...");
                dispose();
                new LoginForm();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombreField.setText("");
                descripcionField.setText("");
                precioField.setText("");
                cantidadField.setText("");
                categoriaField.setText("");
            }
        });
    }

    // Metodo para buscar el producto por código
    public void buscarProducto(String codigo) {
        String query = "SELECT * FROM PRODUCTOS WHERE codigo_producto = ?";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Si el producto es encontrado, se muestran los datos
                nombreField.setText(rs.getString("nombre"));
                descripcionField.setText(rs.getString("descripcion"));
                descripcionField.setCaretPosition(0); // Permite que el scroll pane se vea desde la primera línea, no la última
                precioField.setText(String.valueOf(rs.getDouble("precio")));
                cantidadField.setText(String.valueOf(rs.getInt("cantidad")));
                categoriaField.setText(rs.getString("categoria"));
            } else {
                // Si no se encuentra el producto
                JOptionPane.showMessageDialog(buscarPanel, "Producto no encontrado.");
                nombreField.setText("");
                descripcionField.setText("");
                precioField.setText("");
                cantidadField.setText("");
                categoriaField.setText("");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(buscarPanel, "Error al buscar el producto.");
        }
    }
}
