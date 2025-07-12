package forms;

import database.ConexionDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrarProductosForm extends JFrame{
    private JPanel registrarPanel;
    private JButton limpiarButton;
    private JButton registrarButton;
    private JTextField categoriaField;
    private JTextField cantidadField;
    private JTextField precioField;
    private JTextField descripcionField;
    private JTextField nombreField;
    private JTextField codigoField;
    private JLabel usernameTxt;
    private JButton buscarProductoButton;

    public RegistrarProductosForm(String username) {
        setTitle("Registrar Producto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setContentPane(registrarPanel);
        setVisible(true);

        usernameTxt.setText(username);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigoField.getText();
                String nombre = nombreField.getText();
                String descripcion = descripcionField.getText();
                String precio = precioField.getText();
                String cantidad = cantidadField.getText();
                String categoria = categoriaField.getText();

                // Validación de los campos
                if (codigo.trim().isEmpty() || nombre.trim().isEmpty() || descripcion.trim().isEmpty() || categoria.trim().isEmpty() || precio.trim().isEmpty() || cantidad.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(registrarPanel, "Por favor, llene todos los campos.");
                    return;
                }

                // Valida si existe el producto
                if (validarExistencia(codigo)){
                    JOptionPane.showMessageDialog(registrarPanel,"El producto con código " + codigo+" ya existe.");
                    return;
                }

                registrarProducto();

            }
        });

        buscarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscarProductoForm(username); // Navegar a la ventana de búsqueda de productos
                dispose(); // Cerrar la ventana de registrar producto
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigoField.setText("");
                nombreField.setText("");
                descripcionField.setText("");
                precioField.setText("");
                cantidadField.setText("");
                categoriaField.setText("");
            }
        });
    }

    // Metodo para validar si existe el producto
    private boolean validarExistencia(String codigo){
        // Consulta SQL para verificar si existe un producto con ese código
        String query = "SELECT * FROM productos WHERE codigo_producto = ?";
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)){
            // Asocia los parámetros de la consulta con los valores ingresados
            stmt.setString(1, codigo);
            // Ejecuta la consulta
            ResultSet rs = stmt.executeQuery();
            // Si hay resultados, significa que el producto ya existe
            return rs.next();
        } catch (SQLException e) {
            // Si ocurre un error en la conexión o la consulta, imprime el error y devuelve false
            e.printStackTrace();
            return false;
        }
    }

    public void registrarProducto(){
        String codigo = codigoField.getText();
        String nombre = nombreField.getText();
        String descripcion = descripcionField.getText();
        double precio;
        int cantidad;
        String categoria = categoriaField.getText();

        // Validar precio (debe ser un número positivo)
        try {
            precio = Double.parseDouble(precioField.getText());
            if (precio <= 0) {
                JOptionPane.showMessageDialog(registrarPanel, "El precio debe ser mayor que 0.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(registrarPanel, "Ingrese un valor numérico válido para el precio.");
            return;
        }

        // Validar cantidad (debe ser un número entero positivo)
        try {
            cantidad = Integer.parseInt(cantidadField.getText());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(registrarPanel, "La cantidad debe ser mayor que 0.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(registrarPanel, "Ingrese un valor numérico válido para la cantidad.");
            return;
        }

        // Ejecutar el query para registrar el producto en la base de datos
        String query = "INSERT INTO PRODUCTO (codigo_producto, nombre, descripcion, precio, cantidad, categoria) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigo);
            stmt.setString(2, nombre);
            stmt.setString(3, descripcion);
            stmt.setDouble(4, precio);
            stmt.setInt(5, cantidad);
            stmt.setString(6, categoria);

            stmt.executeUpdate();  // Ejecutar la inserción en la base de datos

            JOptionPane.showMessageDialog(registrarPanel, "Producto registrado con éxito.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(registrarPanel, "Error al registrar el producto.");
        }
    }
}
