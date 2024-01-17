/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.tienda.views;

import lombok.Getter;
import lombok.Setter;
import org.tienda.controller.cCarrito;
import org.tienda.model.Usuarios;

/**
 * @author Carlos Varas Alonso
 */

@Getter
@Setter
public class Carrito extends javax.swing.JFrame {
  private Usuarios usuario;

  /**
   * Creates new form Carrito
   */
  public Carrito(Usuarios usuario) {
    this.usuario = usuario;
    initComponents();
    cCarrito cCarrito = new cCarrito(this);
  }


  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    Container = new javax.swing.JPanel();
    informacion = new javax.swing.JPanel();
    total = new javax.swing.JLabel();
    comprar = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    Container.setBackground(new java.awt.Color(255, 255, 255));
    Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    informacion.setBackground(new java.awt.Color(87, 93, 251));
    informacion.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    informacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    total.setFont(new java.awt.Font("Inter ExtraBold", 1, 32)); // NOI18N
    total.setForeground(new java.awt.Color(255, 255, 255));
    total.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    total.setText("Total:");
    total.setToolTipText("");
    informacion.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 36, -1, 24));

    comprar.setBackground(new java.awt.Color(15, 109, 142));
    comprar.setFont(new java.awt.Font("Inter ExtraBold", 0, 32)); // NOI18N
    comprar.setForeground(new java.awt.Color(255, 255, 255));
    comprar.setText("Comprar");
    informacion.add(comprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 15, 355, 65));

    Container.add(informacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 72, 1392, 96));

    getContentPane().add(Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1445, 1000));

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel Container;
  private javax.swing.JButton comprar;
  private javax.swing.JPanel informacion;
  private javax.swing.JLabel total;
  // End of variables declaration//GEN-END:variables
}