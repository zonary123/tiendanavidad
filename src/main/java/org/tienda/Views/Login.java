/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.tienda.Views;

import com.formdev.flatlaf.FlatIntelliJLaf;

import java.awt.*;
import java.io.IOException;
import javax.persistence.NoResultException;
import javax.swing.*;
import lombok.Getter;
import org.tienda.Controller.controllerLogin;
import org.tienda.Models.modelLogin;
import org.tienda.Utils.utilsLenguaje;

/**
 * The type Login.
 *
 * @author carlos
 */
@Getter
public class Login extends javax.swing.JFrame {

  private final controllerLogin cLogin;
  private final modelLogin mLogin;
  private utilsLenguaje lenguaje;

  /**
   * Creates new form Login
   */
  public Login(String user) {
    FlatIntelliJLaf.setup();
    FlatIntelliJLaf.setPreferredFontFamily("Roboto");
    initComponents();
    this.jTextFieldUsername.requestFocus();
    try {
      this.lenguaje = new utilsLenguaje();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
    this.cLogin = new controllerLogin(this, lenguaje);
    this.mLogin = new modelLogin(this, lenguaje);
    try {
      this.cLogin.initEvents();
    } catch (NoResultException e) {
      JOptionPane.showMessageDialog(this, this.lenguaje.getMensaje().getString("login.noexiste.account"), this.lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.INFORMATION_MESSAGE);
    }

    initTextFields();
    mLogin.actualizarBoton(getJButtonLogin(), 16);
    mLogin.actualizarBoton(getJButtonClose(), 999);
    getJPanelLogin().putClientProperty("FlatLaf.style", "arc:" + 8);
    getJTextFieldUsername().setText(user);
  }

  public void initTextFields() {
    mLogin.actualizarTextField(getJTextFieldUsername(), this.lenguaje.getMensaje().getString("login.username.placeholder"), 16, "img/svg/Person.svg", 22, 24, "#575DFB");
    mLogin.actualizarTextField(getJPasswordFieldPassword(), this.lenguaje.getMensaje().getString("login.password.placeholder"), 16, "img/svg/Candado.svg", 16, 19, "#575DFB");
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanelContainer = new javax.swing.JPanel();
    jPanelLogin = new javax.swing.JPanel();
    jLabelLogin = new javax.swing.JLabel();
    jButtonLogin = new javax.swing.JButton();
    jSeparator1 = new javax.swing.JSeparator();
    jLabelNoCuenta = new javax.swing.JLabel();
    jButtonPasswordOlvidada = new javax.swing.JButton();
    jTextFieldUsername = new javax.swing.JTextField();
    jLabelUsername = new javax.swing.JLabel();
    jLabelPassword = new javax.swing.JLabel();
    jButtonRegistrarse = new javax.swing.JButton();
    jButtonClose = new javax.swing.JButton();
    jButtonBack = new javax.swing.JButton();
    jPasswordFieldPassword = new javax.swing.JPasswordField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new java.awt.Color(196, 196, 196));
    setUndecorated(true);
    setResizable(false);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jPanelContainer.setBackground(new java.awt.Color(196, 196, 196));
    jPanelContainer.setPreferredSize(new java.awt.Dimension(1445, 1000));
    jPanelContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jPanelLogin.setBackground(new java.awt.Color(255, 255, 255));
    jPanelLogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabelLogin.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
    jLabelLogin.setForeground(new java.awt.Color(87, 93, 251));
    jLabelLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("lang/es_ES"); // NOI18N
    jLabelLogin.setText(bundle.getString("login.h1")); // NOI18N
    jLabelLogin.setAlignmentX(0.5F);
    jLabelLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jPanelLogin.add(jLabelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

    jButtonLogin.setBackground(new java.awt.Color(87, 93, 251));
    jButtonLogin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    jButtonLogin.setForeground(new java.awt.Color(255, 255, 255));
    jButtonLogin.setText("Login");
    jPanelLogin.add(jButtonLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 436, 343, 56));

    jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
    jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
    jPanelLogin.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 521, 343, 2));

    jLabelNoCuenta.setText(bundle.getString("login.label.notaccount")); // NOI18N
    jPanelLogin.add(jLabelNoCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 550, 140, -1));

    jButtonPasswordOlvidada.setForeground(new java.awt.Color(87, 93, 251));
    jButtonPasswordOlvidada.setText(bundle.getString("login.button.forgotpassword")); // NOI18N
    jButtonPasswordOlvidada.setAlignmentY(0.0F);
    jButtonPasswordOlvidada.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(87, 93, 251)));
    jButtonPasswordOlvidada.setContentAreaFilled(false);
    jButtonPasswordOlvidada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jButtonPasswordOlvidada.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
    jButtonPasswordOlvidada.setMargin(new java.awt.Insets(2, 14, 5, 14));
    jButtonPasswordOlvidada.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonPasswordOlvidadaActionPerformed(evt);
      }
    });
    jPanelLogin.add(jButtonPasswordOlvidada, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 384, -1, -1));
    jPanelLogin.add(jTextFieldUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 208, 343, 56));

    jLabelUsername.setText(bundle.getString("login.label.username")); // NOI18N
    jPanelLogin.add(jLabelUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 178, 340, 24));

    jLabelPassword.setText(bundle.getString("login.label.password")); // NOI18N
    jPanelLogin.add(jLabelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 292, 340, 24));

    jButtonRegistrarse.setForeground(new java.awt.Color(87, 93, 251));
    jButtonRegistrarse.setText(bundle.getString("login.button.register")); // NOI18N
    jButtonRegistrarse.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(87, 93, 251)));
    jButtonRegistrarse.setContentAreaFilled(false);
    jButtonRegistrarse.setMargin(new java.awt.Insets(2, 14, 5, 14));
    jButtonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonRegistrarseActionPerformed(evt);
      }
    });
    jPanelLogin.add(jButtonRegistrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 550, -1, -1));

    jButtonClose.setBackground(new java.awt.Color(255, 86, 86));
    jButtonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/svg/x.png"))); // NOI18N
    jButtonClose.setAlignmentX(0.5F);
    jButtonClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    jButtonClose.setIconTextGap(0);
    jButtonClose.setMargin(new java.awt.Insets(0, 0, 0, 0));
    jButtonClose.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonCloseActionPerformed(evt);
      }
    });
    jPanelLogin.add(jButtonClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 5, 20, 20));

    jButtonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/svg/Group 23.png"))); // NOI18N
    jButtonBack.setBorder(null);
    jButtonBack.setContentAreaFilled(false);
    jPanelLogin.add(jButtonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 38, 38, 38));
    jPanelLogin.add(jPasswordFieldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 322, 343, 55));

    jPanelContainer.add(jPanelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 200, 400, 600));

    getContentPane().add(jPanelContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1445, 1000));

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void jButtonPasswordOlvidadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPasswordOlvidadaActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonPasswordOlvidadaActionPerformed

  private void jButtonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarseActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonRegistrarseActionPerformed

  private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_jButtonCloseActionPerformed

  /**
   * Main.
   *
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Login(null).setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonBack;
  private javax.swing.JButton jButtonClose;
  private javax.swing.JButton jButtonLogin;
  private javax.swing.JButton jButtonPasswordOlvidada;
  private javax.swing.JButton jButtonRegistrarse;
  private javax.swing.JLabel jLabelLogin;
  private javax.swing.JLabel jLabelNoCuenta;
  private javax.swing.JLabel jLabelPassword;
  private javax.swing.JLabel jLabelUsername;
  private javax.swing.JPanel jPanelContainer;
  private javax.swing.JPanel jPanelLogin;
  private javax.swing.JPasswordField jPasswordFieldPassword;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JTextField jTextFieldUsername;
  // End of variables declaration//GEN-END:variables
}
