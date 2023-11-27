/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lospapus.modelo;

/**
 *
 * @author nahvv
 */
public class Comida extends Producto {
    
    public Comida() {
        super(0, "", 0.0, 0);
    }


    
    @Override
    public void agregarProducto(String nombre, double precio, int cantidad, int opcion){
        super.agregarProducto(nombre, precio, cantidad, opcion);
    }
    
}
