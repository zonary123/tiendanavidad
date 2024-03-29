/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package org.tienda.views;

import lombok.Getter;
import lombok.Setter;
import org.tienda.controller.cForgotPasswordPassword;
import org.tienda.model.Usuarios;

import javax.swing.*;
import java.io.IOException;

/**
 * The type Forgot password password.
 *
 * @author Carlos Varas Alonso
 */
@Getter @Setter
public class ForgotPasswordPassword extends JFrame {
  private cForgotPasswordPassword controller;
  private Usuarios usuario;

  /**
   * Creates new form ForgotPasswordPassword
   *
   * @param u the u
   */
  public ForgotPasswordPassword(Usuarios u) {
    initComponents();
    this.usuario = u;

    try {
      controller = new cForgotPasswordPassword(this);
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
    jLabelPassword = new javax.swing.JLabel();
    jPasswordFieldPassword = new javax.swing.JPasswordField();
    jPasswordFieldPassword1 = new javax.swing.JPasswordField();
    jLabelPasswordRepeat = new javax.swing.JLabel();

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
    jPanelForgot.add(jLabeltitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 95, 343, 75));
    jLabeltitulo.getAccessibleContext().setAccessibleName("asdasd");

    jButtonConfirmar.setBackground(new java.awt.Color(87, 93, 251));
    jButtonConfirmar.setFont(new java.awt.Font("Inter", 1, 16)); // NOI18N
    jButtonConfirmar.setForeground(new java.awt.Color(255, 255, 255));
    jButtonConfirmar.setText(bundle.getString("forgot.button.confirm")); // NOI18N
    jPanelForgot.add(jButtonConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 420, 343, 56));

    jLabelDescripcion.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    jLabelDescripcion.setText(bundle.getString("forgot.password.descripcion")); // NOI18N
    jPanelForgot.add(jLabelDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 189, -1, -1));
    jLabelDescripcion.getAccessibleContext().setAccessibleName(bundle.getString("forgot.email.descripcion")); // NOI18N

    jLabelPassword.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    jLabelPassword.setText(bundle.getString("forgot.password")); // NOI18N
    jPanelForgot.add(jLabelPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 220, -1, -1));

    jPasswordFieldPassword.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    jPanelForgot.add(jPasswordFieldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 250, 342, 55));

    jPasswordFieldPassword1.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    jPanelForgot.add(jPasswordFieldPassword1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 360, 342, 55));

    jLabelPasswordRepeat.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    jLabelPasswordRepeat.setText(bundle.getString("forgot.password")); // NOI18N
    jPanelForgot.add(jLabelPasswordRepeat, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 330, -1, -1));

    Container.add(jPanelForgot, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 269, 414, 560));

    getContentPane().add(Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1445, 1000));

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel Container;
  private javax.swing.JButton jButtonBack;
  private javax.swing.JButton jButtonClose;
  private javax.swing.JButton jButtonConfirmar;
  private javax.swing.JLabel jLabelDescripcion;
  private javax.swing.JLabel jLabelPassword;
  private javax.swing.JLabel jLabelPasswordRepeat;
  private javax.swing.JLabel jLabeltitulo;
  private javax.swing.JPanel jPanelForgot;
  private javax.swing.JPasswordField jPasswordFieldPassword;
  private javax.swing.JPasswordField jPasswordFieldPassword1;
  // End of variables declaration//GEN-END:variables

}
