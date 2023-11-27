/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lospapus.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hansi
 */
public class conexionBD {

    public static Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Connection obtenerConexion() {
        Connection connection = null;
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/productos_bdd",
                    "root", "admin12341");
            System.out.println("Conexión exitosa");
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return connection;
    }

    public boolean verificarConexion() {
        try (Connection connection = obtenerConexion()) {
            System.out.println("Conexión exitosa");
            return true;
        } catch (SQLException ex) {
            System.out.println("Error de conexión: " + ex.getMessage());
            return false;
        }
    }
    
    public void insertarProducto(String nombreProducto, double precioProducto, int cantidadProducto, int tipoProducto) {
        String url = "jdbc:mysql://localhost:3306/productos_bdd";
        String usuario = "root";
        String pass = "admin12341";

        try (Connection conexion = DriverManager.getConnection(url, usuario, pass)) {
            // Consulta SQL para la inserción
            String sql = "INSERT INTO producto (nombreProducto, precioProducto, cantidadProducto,id_tipoProducto) VALUES (?, ?, ?,?)";

            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                // Establecer los parámetros
                preparedStatement.setString(1, nombreProducto);
                preparedStatement.setDouble(2, precioProducto);
                preparedStatement.setInt(3, cantidadProducto);
                preparedStatement.setInt(4, tipoProducto);

                // Ejecutar la consulta
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Producto insertado correctamente");
                } else {
                    System.out.println("No se pudo insertar el producto");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al insertar el producto: " + ex.getMessage());
        }
    }

    public static void eliminarProducto(int idProducto) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/productos_bdd";
        String usuario = "root";
        String pass = "admin12341";
        try (Connection conexion = DriverManager.getConnection(url, usuario, pass)) {
            // Crear la sentencia SQL con un PreparedStatement
            String sql = "DELETE FROM producto WHERE idProducto = ?";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                preparedStatement.setInt(1, idProducto);

                // Ejecutar la actualización
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Producto eliminado exitosamente.");
                } else {
                    System.out.println("No se encontró el producto con el ID proporcionado.");
                }
            }
        }
    }
    
    public static void modificarProducto(int idmodificar, String nombreModificar,double preciodouble, int cantidadint,int tipomodificar) {
        // Parámetros de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/productos_bdd";
        String usuario = "root";
        String clave = "admin12341";

        // Consulta SQL para actualizar el nombre del producto
        String sql = "UPDATE producto SET nombreProducto = ?, precioProducto = ?, cantidadProducto = ?, id_tipoproducto = ? WHERE idproducto = ?";

        try (Connection conexion = DriverManager.getConnection(url, usuario, clave);
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            // Establecer los parámetros de la consulta
            statement.setString(1, nombreModificar);
        statement.setDouble(2, preciodouble);
        statement.setInt(3, cantidadint);
        statement.setInt(4, tipomodificar);
        statement.setInt(5, idmodificar);
            

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Producto modificado exitosamente.");
            } else {
                System.out.println("No se pudo modificar el producto. Verifica el ID.");
            }

        } catch (SQLException e) {
            System.err.println("Error al modificar el producto: " + e.getMessage());
        }
    }

}
