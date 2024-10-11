package com.example.farmacia.DAO;

import com.example.farmacia.Clases.Producto;
import com.example.farmacia.util.Alerta;
import com.example.farmacia.util.Conectar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductosDAO {

    public static double buscarPrecio(String nombreProducto){
        double precioProducto= 0;
        String sql = "SELECT * FROM productos";
        try (Connection connection = Conectar.conectar()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombreProducto2 = rs.getString("nombreProducto");
                if (nombreProducto.equals(nombreProducto2)) {
                    precioProducto = rs.getDouble("precioProducto");

                }
            }
        }catch (Exception e){
            Alerta.alertaError(e.getMessage());
        }
        return  precioProducto;
    }
}
