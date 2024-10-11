package com.example.farmacia.Controller;

import com.example.farmacia.Clases.Producto;
import com.example.farmacia.util.Alerta;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TicketController {

    @FXML
    private Button btnFinalizar;

    @FXML
    private Button btnMostrar;

    @FXML
    private TextArea txtaTicket;

    ArrayList<Producto> listaProductos2;


    @FXML
    void onFinClick(ActionEvent event) {

        Alerta.alertaInfo("Pedido finalizado, gracias por realizar su pedido.");

        // Este metodo cierra la aplicacion completamente
        Platform.exit();
    }

    @FXML
    void onMostrarClick(ActionEvent event) {
        for (Producto productos : listaProductos2) {
            txtaTicket.setText("Producto: " + productos.getNombreProducto() +"  " + productos.getPrecioProducto() + "  €");
        }
    }

    void metod(ArrayList<Producto> productos2) {
        listaProductos2 = productos2;
        double total = 0;

        for (Producto producto : listaProductos2) {
            total = producto.getPrecioProducto();

            producto.setTotal(total);
        }
    }
}
