package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Interfaces.controllers;
import org.tienda.Model.Usuarios;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Utils.utilsTextField;
import org.tienda.Views.ForgotPasswordPassword;
import org.tienda.Views.Login;

import javax.swing.*;
import java.io.IOException;

/**
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordPassword implements controllers {
  private ForgotPasswordPassword vista;
  private static utilsLenguaje lenguaje;

  static {
    try {
      lenguaje = new utilsLenguaje();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static utilsTextField TextField = new utilsTextField();

  /**
   * Constructor de la clase
   *
   * @param vista Vista de la clase
   * @throws IOException Error de lectura de archivo
   */
  public cForgotPasswordPassword(ForgotPasswordPassword vista) throws IOException {
    this.vista = vista;
    initEvents();
    actualizarEstilos();
    actualizarLenguaje();
  }

  /**
   * Actualiza el lenguaje de la vista
   */
  @Override public void actualizarLenguaje() {
    vista.getJLabeltitulo().setText((lenguaje.getMensaje().getString("forgot.h1")));
    vista.getJLabelDescripcion().setText(lenguaje.getMensaje().getString("forgot.email.descripcion"));
    vista.getJLabelTFEmail().setText((lenguaje.getMensaje().getString("forgot.email")));
    vista.getJPasswordFieldPassword().setText("");
    vista.getJButtonConfirmar().setText((lenguaje.getMensaje().getString("forgot.button.confirm")));
  }

  /**
   * Actualiza los estilos de la vista
   */
  @Override public void actualizarEstilos() {
    TextField.actualizarTextField(vista.getJPasswordFieldPassword(), "******", 16, "img/svg/Email.svg", 22, 24, "#575DFB");
    vista.getJButtonConfirmar().putClientProperty("FlatLaf.style", "arc:" + 16);
  }

  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
    vista.getJButtonClose().addActionListener(e -> vista.dispose());
    vista.getJButtonBack().addActionListener(
      e -> {
        vista.dispose();
        new Login(null).setVisible(true);
      });
    vista.getJButtonConfirmar().addActionListener(
      e -> {
        if (vista.getJPasswordFieldPassword().getPassword().length > 5) {
          vista.getUsuario().setPassword(String.valueOf(vista.getJPasswordFieldPassword().getPassword()));
          if (!Usuarios.updatePassword(vista.getUsuario()))
            JOptionPane.showMessageDialog(null, "Error al actualizar la contrasenya", "Error", JOptionPane.INFORMATION_MESSAGE);
          vista.dispose();
          new Login(null).setVisible(true);
        } else {
          if (vista.getJPasswordFieldPassword().getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "La contrasenya no puede estar vacia", "Error", JOptionPane.INFORMATION_MESSAGE);
          } else {
            JOptionPane.showMessageDialog(null, "La contrasenya debe tener mas de 5 caracteres", "Error", JOptionPane.INFORMATION_MESSAGE);
          }
        }
      });
  }

}
