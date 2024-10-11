package com.example.farmacia.Controller;

import com.example.farmacia.Clases.Producto;
import com.example.farmacia.DAO.ProductosDAO;
import com.example.farmacia.Main;
import com.example.farmacia.util.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PedidoController {

    @FXML
    private Button btnHacerPedido;

    @FXML
    private Button btnVolver;

    @FXML
    private ToggleGroup conjunto;

    @FXML
    private RadioButton tgAmoxicilina;

    @FXML
    private RadioButton tgIbuprofeno;

    @FXML
    private RadioButton tgParacetamol;

    @FXML
    private RadioButton tgSupositorios;


    @FXML
    void onHacerClickVolver(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void onHacerPedidoClick(ActionEvent event) {
        ArrayList<Producto> lista = new ArrayList<>();
        if (conjunto.getSelectedToggle()==null){
            Alerta.alertaError("Elige un producto");
        } else {
            String ibuprofeno = "ibuprofeno";
            String amoxicilina = "amoxicilina";
            String paracetamol = "paracetamol";
            String supositorios = "supositorios";

            String nombreProducto="";
            double precioProducto;
            Producto producto;

            try{
                if (tgIbuprofeno.isSelected()){
                    nombreProducto = ibuprofeno;
                    precioProducto = ProductosDAO.buscarPrecio(nombreProducto);
                } else if (tgAmoxicilina.isSelected()) {
                    nombreProducto = amoxicilina;
                    precioProducto = ProductosDAO.buscarPrecio(nombreProducto);
                } else if (tgParacetamol.isSelected()){
                    nombreProducto = paracetamol;
                    precioProducto = ProductosDAO.buscarPrecio(nombreProducto);
                } else {
                    nombreProducto = supositorios;
                    precioProducto = ProductosDAO.buscarPrecio(nombreProducto);
                }

                producto = new Producto(nombreProducto, precioProducto);
                lista.add(producto);

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource( "ticket.fxml"));
                Parent root = fxmlLoader.load();

                TicketController ticketController = fxmlLoader.getController();
                ticketController.metod(lista);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

}
