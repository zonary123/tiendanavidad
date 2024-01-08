/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package org.tienda.Views;

import lombok.Getter;
import lombok.Setter;
import org.tienda.Controller.cForgotPasswordCode;
import org.tienda.Model.modelForgotPasswordCode;
import org.tienda.Objects.Usuarios;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author carlos
 */
@Getter @Setter
public class ForgotPasswordCode extends JFrame {
  private cForgotPasswordCode controller = null;
  private modelForgotPasswordCode modelo = null;
  private Usuarios usuario;

  /**
   * Creates new form ForgotPasswordPassword
   */
  public ForgotPasswordCode(Usuarios u) {
    initComponents();
    this.usuario = u;
    try {
      controller = new cForgotPasswordCode(this);
      modelo = new modelForgotPasswordCode(this);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    Container = new javax.swing.JPanel();
    jPanelForgot = new javax.swing.JPanel();
    jButtonBack = new javax.swing.JButton();
    jButtonClose = new javax.swing.JButton();
    jLabeltitulo = new javax.swing.JLabel();
    jButtonConfirmar = new javax.swing.JButton();
    jLabelDescripcion = new javax.swing.JLabel();
    jLabelTFCodigo = new javax.swing.JLabel();
    jTextFieldCodigo = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setUndecorated(true);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    Container.setBackground(new java.awt.Color(196, 196, 196));
    Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jPanelForgot.setBackground(new java.awt.Color(255, 255, 255));
    jPanelForgot.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jButtonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/svg/Group 23.png"))); // NOI18N
    jButtonBack.setBorder(null);
    jButtonBack.setContentAreaFilled(false);
    jPanelForgot.add(jButtonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 57, 38, 38));

    jButtonClose.setBackground(new java.awt.Color(255, 86, 86));
    jButtonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/svg/x.png"))); // NOI18N
    jButtonClose.setAlignmentX(0.5F);
    jButtonClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButtonClose.setIconTextGap(0);
    jButtonClose.setMargin(new java.awt.Insets(0, 0, 0, 0));
    jPanelForgot.add(jButtonClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(387, 7, 20, 20));

    jLabeltitulo.setFont(new java.awt.Font("Inter", 1, 32)); // NOI18N
    jLabeltitulo.setForeground(new java.awt.Color(87, 93, 251));
    jLabeltitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("lang/es_ES"); // NOI18N
    jLabeltitulo.setText(bundle.getString("forgot.h1")); // NOI18N
    jLabeltitulo.setAlignmentX(0.5F);
    jLabeltitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jPanelForgot.add(jLabeltitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 125, 371, 75));
    jLabeltitulo.getAccessibleContext().setAccessibleName("asdasd");

    jButtonConfirmar.setBackground(new java.awt.Color(87, 93, 251));
    jButtonConfirmar.setFont(new java.awt.Font("Inter", 1, 16)); // NOI18N
    jButtonConfirmar.setForeground(new java.awt.Color(255, 255, 255));
    jButtonConfirmar.setText(bundle.getString("forgot.button.confirm")); // NOI18N
    jPanelForgot.add(jButtonConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 381, 343, 56));

    jLabelDescripcion.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    jLabelDescripcion.setText(bundle.getString("forgot.email.descripcion")); // NOI18N
    jPanelForgot.add(jLabelDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 219, -1, -1));
    jLabelDescripcion.getAccessibleContext().setAccessibleName(bundle.getString("forgot.email.descripcion")); // NOI18N

    jLabelTFCodigo.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    jLabelTFCodigo.setText("Email");
    jPanelForgot.add(jLabelTFCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 267, -1, -1));

    jTextFieldCodigo.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    jPanelForgot.add(jTextFieldCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 297, 343, 56));

    Container.add(jPanelForgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 269, 414, 462));

    getContentPane().add(Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1445, 1000));

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ForgotPasswordCode.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(ForgotPasswordCode.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(ForgotPasswordCode.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(ForgotPasswordCode.class.getName()).log(Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new ForgotPasswordCode(null).setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel Container;
  private javax.swing.JButton jButtonBack;
  private javax.swing.JButton jButtonClose;
  private javax.swing.JButton jButtonConfirmar;
  private javax.swing.JLabel jLabelDescripcion;
  private javax.swing.JLabel jLabelTFCodigo;
  private javax.swing.JLabel jLabeltitulo;
  private javax.swing.JPanel jPanelForgot;
  private javax.swing.JTextField jTextFieldCodigo;
  // End of variables declaration//GEN-END:variables

}