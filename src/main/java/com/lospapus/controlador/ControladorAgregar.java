/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lospapus.controlador;

import com.lospapus.modelo.Bebida;
import com.lospapus.modelo.Comida;
import com.lospapus.vistas.JFrameInventario;
import com.lospapus.vistas.JPanelAgregar;
import com.lospapus.vistas.JPanelModificar;
import com.lospapus.vistas.JPanelTabla;

/**
 *
 * @author nahvv
 */
public class ControladorAgregar {

    private JPanelAgregar agr;
    private Bebida beb;
    private Comida com;

    public ControladorAgregar(JPanelAgregar agr) {
        this.agr = agr;
        this.beb = new Bebida();
        this.com = new Comida();
    }

    public void agregarProductos() {
        int categoriaProducto = agr.getTipoProducto();
        String nombreProducto = agr.getNombreProducto();
        double precioProducto = agr.getPrecioProducto();
        int cantidadProducto = agr.getCantidadProducto();

        if (categoriaProducto == 1) {
            beb.agregarProducto(nombreProducto, precioProducto, cantidadProducto, categoriaProducto);
        }
        else if (categoriaProducto == 2){
            com.agregarProducto(nombreProducto, precioProducto, cantidadProducto, categoriaProducto);
        }
        else{
            System.out.println("error");
        }
    }
}
