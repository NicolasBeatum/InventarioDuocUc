/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lospapus.controlador;

import com.lospapus.basededatos.conexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author hansi
 */
public class RellenarCombo {
    public void rellenarBox(String tabla, JComboBox combo,String valor){
        String sql="select * from "+tabla;
        Statement st;
        conexionBD conect=new conexionBD();
        Connection conexion=conect.obtenerConexion();
        try{
            st=conexion.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                combo.addItem(rs.getString(valor));
                
                
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERROR");
            
        }
        
    }
}
