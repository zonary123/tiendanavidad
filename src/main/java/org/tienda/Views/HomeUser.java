/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.tienda.Views;

import lombok.Getter;
import lombok.Setter;
import org.tienda.Controller.controllerHome;
import org.tienda.Model.Usuarios;

/**
 * @author Carlos Varas Alonso
 */
@Getter
@Setter
public class HomeUser extends javax.swing.JFrame{

  /**
   * Creates new form HomeUser
   */
  public HomeUser(Usuarios user) {
    initComponents();
    controllerHome cHome = new controllerHome(this);
    jLabelUsername.setText(user.getUsername());
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    Container = new javax.swing.JPanel();
    header = new javax.swing.JPanel();
    User = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabelUsername = new javax.swing.JLabel();
    ScrollContainerProducts = new javax.swing.JScrollPane();
    ContainerProducts = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    Container.setBackground(new java.awt.Color(255, 255, 255));
    Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    header.setBackground(new java.awt.Color(231, 231, 231));
    header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    User.setBackground(new java.awt.Color(255, 255, 255));
    User.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel1.setText("img");
    User.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 5, 26, 26));

    jLabelUsername.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    jLabelUsername.setText("Username");
    User.add(jLabelUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

    header.add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 7, -1, 36));

    Container.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, 1410, 50));

    ScrollContainerProducts.setViewportView(ContainerProducts);

    Container.add(ScrollContainerProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 72, 1100, 916));

    getContentPane().add(Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1445, 1000));

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel Container;
  private javax.swing.JPanel ContainerProducts;
  private javax.swing.JScrollPane ScrollContainerProducts;
  private javax.swing.JPanel User;
  private javax.swing.JPanel header;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabelUsername;
  // End of variables declaration//GEN-END:variables
}
