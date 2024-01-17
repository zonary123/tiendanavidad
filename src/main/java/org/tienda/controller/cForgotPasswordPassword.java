package org.tienda.controller;

import org.tienda.model.Usuarios;
import org.tienda.utils.utilsLenguaje;
import org.tienda.utils.utilsTextField;
import org.tienda.views.ForgotPasswordPassword;
import org.tienda.views.Login;
import org.tienda.validator.validator;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The type C forgot password password.
 *
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordPassword {
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
   *
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
  public void actualizarLenguaje() {
    vista.getJLabeltitulo().setText((lenguaje.getMensaje().getString("forgot.h1")));
    vista.getJLabelDescripcion().setText(lenguaje.getMensaje().getString("forgot.email.descripcion"));
    vista.getJLabelTFEmail().setText((lenguaje.getMensaje().getString("forgot.email")));
    vista.getJPasswordFieldPassword().setText("");
    vista.getJButtonConfirmar().setText((lenguaje.getMensaje().getString("forgot.button.confirm")));
  }

  /**
   * Actualiza los estilos de la vista
   */
  public void actualizarEstilos() {
    TextField.actualizarTextField(vista.getJPasswordFieldPassword(), "******", 16, "img/svg/Email.svg", 22, 24, "#575DFB");
    vista.getJButtonConfirmar().putClientProperty("FlatLaf.style", "arc:" + 16);
    vista.getJPanelForgot().putClientProperty("FlatLaf.style", "arc:" + 16);
    vista.getJButtonClose().putClientProperty("FlatLaf.style", "arc:" + 999);

    vista.getJButtonClose().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getJButtonBack().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getJButtonConfirmar().setCursor(new Cursor(Cursor.HAND_CURSOR));
  }

  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
    vista.getJPasswordFieldPassword().requestFocus();
    vista.getJButtonClose().addActionListener(e -> vista.dispose());
    vista.getJButtonBack().addActionListener(
      e -> {
        vista.dispose();
        new Login(null).setVisible(true);
      });
    vista.getJButtonConfirmar().addActionListener(
      e -> {
        if (validator.isPassword(String.valueOf(vista.getJPasswordFieldPassword().getPassword()))) {
          vista.getUsuario().setPassword(String.valueOf(vista.getJPasswordFieldPassword().getPassword()));
          if (!Usuarios.updatePassword(vista.getUsuario()))
            JOptionPane.showMessageDialog(null, "Error al actualizar la contrasenya", "Error", JOptionPane.INFORMATION_MESSAGE);
          vista.dispose();
          new Login(null).setVisible(true);
        }
      });
  }

}
