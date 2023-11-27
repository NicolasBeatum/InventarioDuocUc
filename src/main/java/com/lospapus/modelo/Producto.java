/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lospapus.modelo;

import com.lospapus.basededatos.conexionBD;
import javax.swing.JOptionPane;

/**
 *
 * @author nahvv
 */
public abstract class Producto {
    protected int categoriaProducto;
    protected String nombreProducto;
    protected double precioProducto;
    protected int cantidadProducto;

    public Producto(int categoriaProducto, String nombreProducto, double precioProducto, int cantidadProducto) {
        this.categoriaProducto = categoriaProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public void agregarProducto(String nombre, double precio, int cantidad, int opcion) {
        conexionBD insertarProducto = new conexionBD();
        insertarProducto.insertarProducto(nombre, precio, cantidad, opcion);
        JOptionPane.showMessageDialog(null, "AGREGADO!");

    }
    
}
