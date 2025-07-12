package forms;

import database.ConexionDB;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm extends JFrame {
    private JPanel loginPanel;
    private JPasswordField passwordField;
    private JTextField usuarioTextField;
    private JButton ingresarButton;

    public LoginForm(){
        setTitle("Inicio de Sesión"); //titulo de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); //cierra el programa al cerrar la ventana
        setSize(350, 400); //dimensiones de la ventana
        setContentPane(loginPanel); //panel principal
        setLocationRelativeTo(null); // posiciona la ventana en el centro de la pantalla
        setVisible(true); //ventana visible


        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtiene el texto ingresado en los campos de usuario y contraseña
                String username = usuarioTextField.getText();
                String password = String.valueOf(passwordField.getPassword());

                // Verificar si los campos están vacíos, si los campos están vacíos muestra un mensaje y sale del metodo
                if (username.trim().isEmpty() || password.trim().isEmpty()){
                    JOptionPane.showMessageDialog(loginPanel,"Por favor, llene los campos.");
                    return;
                }

                // Validar las credenciales ingresadas
                if (validarLogin(username,password)){
                    JOptionPane.showMessageDialog(loginPanel,"Iniciando sesión...");
                    new RegistrarProductosForm(username);
                    dispose();
                }else{
                    // Si las credenciales no coinciden, se muestra un mensaje de error
                    JOptionPane.showMessageDialog(loginPanel,"Usuario o contraseña incorrectos.");
                }
            }
        });
    }

    // Metodo para validar el login en la base de datos
    private boolean validarLogin(String username, String password){
        // Consulta SQL para verificar si existe un usuario con ese nombre de usuario y contraseña
        String query = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
        try(Connection conn = ConexionDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)){
            // Asocia los parámetros de la consulta con los valores ingresados
            stmt.setString(1, username);
            stmt.setString(2, password);
            // Ejecuta la consulta
            ResultSet rs = stmt.executeQuery();
            // Si hay resultados, significa que el usuario y contraseña son válidos
            return rs.next();
        } catch (SQLException e) {
            // Si ocurre un error en la conexión o la consulta, imprime el error y devuelve false
            e.printStackTrace();
            return false;
        }
    }
}
