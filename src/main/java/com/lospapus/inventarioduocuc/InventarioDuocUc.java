/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.lospapus.inventarioduocuc;

import com.formdev.flatlaf.FlatDarkLaf;
import com.lospapus.modelo.Bebida;
import com.lospapus.modelo.Comida;
import com.lospapus.modelo.Producto;
import com.lospapus.vistas.JFrameLogin;

/**
 *
 * @author nahvv
 */
public class InventarioDuocUc {

    public static void main(String[] args) {
        
        FlatDarkLaf.setup();
        JFrameLogin login = new JFrameLogin();
        login.setVisible(true);
        
    }
}
