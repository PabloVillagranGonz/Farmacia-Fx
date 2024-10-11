package com.example.farmacia.Controller;

import com.example.farmacia.Clases.Clientes;
import com.example.farmacia.DAO.ClientesDAO;
import com.example.farmacia.Main;
import com.example.farmacia.util.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;


public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegistro;

    @FXML
    private PasswordField idContrasena;

    @FXML
    private TextField idEmail;

    @FXML
    void onLoginClick(ActionEvent event) {
        String correo = idEmail.getText();
        String contrasena = idContrasena.getText();

        if (correo.isEmpty() || contrasena.isEmpty()){
            Alerta.alertaError("Introduce email y contraseña");
        } else {
            String contrasenaEncriptada = DigestUtils.sha256Hex(contrasena);
            Clientes cliente = new Clientes(correo, contrasenaEncriptada);

            if (ClientesDAO.buscarCliente(correo)) {
                if (ClientesDAO.buscarContrasena(contrasenaEncriptada)){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pagina.fxml"));
                    Parent root = fxmlLoader.load();
                    PedidoController controller = fxmlLoader.getController();

                    Scene scene = new Scene(root);
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.setScene(scene);
                } catch (IOException e) {
                    Alerta.alertaError(e.getMessage());
                }
            } else {
                Alerta.alertaError("Contraseña incorrecta");
            }
        } else {
                Alerta.alertaError("No existe el cliente. REGISTRATE");
            }
        }
    }

    @FXML
    void onRegistroClick(ActionEvent event) {
        String email=idEmail.getText();
        String contrasena = idContrasena.getText();
        if (email.isEmpty() || contrasena.isEmpty()) {
            Alerta.alertaError("Introduce un email y una contraseña para tu nuevo usuario");
        } else {
            String contrasenaEncriptada = DigestUtils.sha256Hex(contrasena);
            Clientes cliente = new Clientes(email, contrasenaEncriptada);

            if (ClientesDAO.buscarCliente(email)) {
                Alerta.alertaError("Ya existe este cliente, Inicia sesion");

            } else {
                if (ClientesDAO.insertarCliente(cliente) == 1) {
                    Alerta.alertaInfo("Cliente creado, Inicia sesion");
                }
            }
        }
    }

}
