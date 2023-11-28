/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.lospapus.vistas;

import com.lospapus.basededatos.conexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nchig
 */
public class JPanelTabla extends javax.swing.JPanel {

    private int idBorrar;

    /**
     * Creates new form JPanelAgregar
     */
    public JPanelTabla() {
        initComponents();
        initStyles();
        mostrar(tabla, "");
        configurarEventos();
    }
private void initStyles(){
       buscar.putClientProperty("JTextField.placeholderText", "Ingrese a buscar por nombre,id,precio o cantidad");
        
    }
    private void configurarEventos() {
        // Asociar evento al JTextField para la búsqueda
        buscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrar(buscar.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrar(buscar.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrar(buscar.getText());
            }
        });

        // Asociar evento al botón de refrescar
        refrescarBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrar(tabla, ""); // Mostrar todos los registros al hacer clic en refrescar
            }
        });
    }

    public void filtrar(String texto) {
        mostrar(tabla, texto);
    }

    String tabla = "producto";
    DefaultTableModel model = new DefaultTableModel();
    
    public void sortearTipo(int tipo) {
    String sql = "SELECT * FROM " + tabla + " WHERE id_tipoproducto = ?";
    conexionBD con = new conexionBD();
    
    try (Connection conexion = con.obtenerConexion();
         PreparedStatement statement = conexion.prepareStatement(sql)) {

        // Establecer el tipo como parámetro
        statement.setInt(1, tipo);

        // Ejecutar la consulta
        ResultSet resultSet = statement.executeQuery();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Tipo");

        // Llenar el modelo con los resultados de la consulta
        while (resultSet.next()) {
            Object[] row = new Object[5];
            row[0] = resultSet.getInt("idproducto");
            row[1] = resultSet.getString("nombreProducto");
            row[2] = resultSet.getInt("precioProducto");
            row[3] = resultSet.getInt("cantidadProducto");

            int tipoProducto = resultSet.getInt("id_tipoproducto");
            row[4] = traducirTipoProducto(tipoProducto);

            model.addRow(row);
        }

        // Configurar el modelo en la tabla
        visor.setModel(model);

    } catch (SQLException e) {
        System.err.println("Error al filtrar por tipo: " + e.getMessage());
    }
}

private String traducirTipoProducto(int tipoProducto) {
    // Traduce el tipo de producto a "Bebida" o "Comida"
    return (tipoProducto == 1) ? "Bebida" : ((tipoProducto == 2) ? "Comida" : String.valueOf(tipoProducto));
}

    public void mostrar(String tabla, String filtro) {
        String sql = "SELECT * FROM " + tabla +
                     " WHERE idProducto LIKE ? OR nombreProducto LIKE ? OR precioProducto LIKE ? OR cantidadProducto LIKE ?";

        Statement st;
        conexionBD con = new conexionBD();
        Connection conexion = con.obtenerConexion();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Tipo");
        visor.setModel(model);

        String[] datos = new String[5];
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            // Establecer el filtro para las columnas idProducto, nombreProducto, precioProducto y cantidadProducto
            preparedStatement.setString(1, "%" + filtro + "%");
            preparedStatement.setString(2, "%" + filtro + "%");
            preparedStatement.setString(3, "%" + filtro + "%");
            preparedStatement.setString(4, "%" + filtro + "%");

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                String tipoProducto = rs.getString(5);
                if ("1".equals(tipoProducto)) {
                    datos[4] = "Bebida";
                } else if ("2".equals(tipoProducto)) {
                    datos[4] = "Comida";
                } else {
                    // Manejar otros casos si es necesario
                    datos[4] = tipoProducto;
                }
                model.addRow(datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR" + e.toString());
            System.out.println(sql); // Imprimir la consulta SQL en caso de error
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        botonEliminar = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        refrescarBoton = new javax.swing.JButton();
        BotonComida = new javax.swing.JCheckBox();
        botonBebida = new javax.swing.JCheckBox();

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(560, 500));

        visor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(visor);

        botonEliminar.setText("Eliminar");
        botonEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEliminarMouseClicked(evt);
            }
        });
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        refrescarBoton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gira-a-la-derecha (1).png"))); // NOI18N
        refrescarBoton.setText("O");
        refrescarBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refrescarBotonMouseClicked(evt);
            }
        });
        refrescarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refrescarBotonActionPerformed(evt);
            }
        });

        BotonComida.setForeground(new java.awt.Color(255, 255, 255));
        BotonComida.setText("Comida");
        BotonComida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonComidaActionPerformed(evt);
            }
        });

        botonBebida.setForeground(new java.awt.Color(255, 255, 255));
        botonBebida.setText("Bebida");
        botonBebida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBebidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(botonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                        .addGap(212, 212, 212))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(refrescarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BotonComida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonBebida)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonComida)
                    .addComponent(botonBebida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refrescarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addGap(33, 33, 33)
                .addComponent(botonEliminar)
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void refrescarBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refrescarBotonMouseClicked
        // TODO add your handling code here:
        mostrar(tabla, "");
    }//GEN-LAST:event_refrescarBotonMouseClicked

    private void botonEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEliminarMouseClicked
        // TODO add your handling code here:asdasdasdsad
        int fila = visor.getSelectedRow();
        if (fila >= 0) { // Verificar si se seleccionó una fila
            DefaultTableModel model = (DefaultTableModel) visor.getModel();
            idBorrar = Integer.parseInt(model.getValueAt(fila, 0).toString());
            model.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        conexionBD eliminarProducto = new conexionBD();
        try {
            eliminarProducto.eliminarProducto(idBorrar);
        } catch (SQLException ex) {
            Logger.getLogger(JPanelTabla.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_botonEliminarMouseClicked

    private void refrescarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refrescarBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refrescarBotonActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void BotonComidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonComidaActionPerformed
        // TODO add your handling code here:
        this.sortearTipo(2);
    }//GEN-LAST:event_BotonComidaActionPerformed

    private void botonBebidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBebidaActionPerformed
        // TODO add your handling code here:
        this.sortearTipo(1);
    }//GEN-LAST:event_botonBebidaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox BotonComida;
    private javax.swing.JCheckBox botonBebida;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JTextField buscar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton refrescarBoton;
    private javax.swing.JTable visor;
    // End of variables declaration//GEN-END:variables
}
