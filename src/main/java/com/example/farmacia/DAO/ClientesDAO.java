package com.example.farmacia.DAO;

import com.example.farmacia.Clases.Clientes;
import com.example.farmacia.util.Conectar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientesDAO {
    public static int insertarCliente(Clientes clientes) {
        int columnasAfectadas = 0;
        String sql="INSERT INTO Clientes (email,contrasena) VALUES (?,?) ";
        try (Connection connection = Conectar.conectar()) {
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             statement.setString(1,clientes.getEmail());
             statement.setString(2,clientes.getContrasena());
             columnasAfectadas= statement.executeUpdate();
             ResultSet rs = statement.getGeneratedKeys();
             while (rs.next()) {
                int idGenrado = rs.getInt(1);
                    clientes.setId_cliente(idGenrado);
             }

        }catch (Exception e){
            System.out.println(e);
        }
        return columnasAfectadas;
    }
    public static boolean buscarCliente(String email) {
        String sql="SELECT * FROM Clientes";
        boolean esta=false;
        try (Connection connection = Conectar.conectar()){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String email2 = resultSet.getString("email");
                if (email.equals(email2)) {
                    esta=true;
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return esta;
    }
    public static boolean buscarContrasena(String contrasena) {
        String sql="SELECT * FROM Clientes";
        boolean esta=false;
        try {
            Connection connection = Conectar.conectar();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String contrasena2 = resultSet.getString("contrasena");
                if (contrasena.equals(contrasena2)) {
                    esta=true;
                }
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return esta;
    }
}
