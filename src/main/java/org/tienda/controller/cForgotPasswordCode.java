package org.tienda.controller;

import org.tienda.model.Usuarios;
import org.tienda.utils.utilsTextField;
import org.tienda.views.ForgotPasswordCode;
import org.tienda.utils.utilsLenguaje;
import org.tienda.views.ForgotPasswordPassword;
import org.tienda.views.Login;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The type C forgot password code.
 *
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordCode {
  private static utilsLenguaje lenguaje;
  private static utilsTextField TextField = new utilsTextField();
  private ForgotPasswordCode vista;

  static {
    try {
      lenguaje = new utilsLenguaje();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private int intentos = 0;


  /**
   * Constructor de la clase
   *
   * @param vista Vista de la clase
   *
   * @throws IOException Error de lectura de archivo
   */
  public cForgotPasswordCode(ForgotPasswordCode vista) throws IOException {
    this.vista = vista;
    lenguaje = new utilsLenguaje();
    initEvents();
    actualizarEstilos();
    actualizarLenguaje();
  }

  /**
   * Inicializacion de eventos de la vista
   */
  private void initEvents() {
    vista.getRootPane().setDefaultButton(vista.getJButtonConfirmar());
    vista.getJTextFieldCodigo().requestFocus();
    vista.getJButtonConfirmar().addActionListener(e -> {
      if (Usuarios.checkCodigo(vista.getUsuario(), vista.getJTextFieldCodigo().getText())) {
        intentos = 0;
        vista.setVisible(false);
        vista.dispose();
        new ForgotPasswordPassword(vista.getUsuario()).setVisible(true);
        borrarCodigo();
      } else {
        intentos++;
        if (intentos > 3) {
          new Login(null).setVisible(true);
          vista.setVisible(false);
          vista.dispose();
        } else {
          JOptionPane.showMessageDialog(null, "Intentos: " + intentos + "/3", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

      }
    });
  }

  /**
   * Borra el codigo de la base de datos
   */
  public void borrarCodigo() {
    Usuarios user = new Usuarios();
    user.setCodigo(null);
    user.setEmail(vista.getUsuario().getEmail());
    Usuarios.updateCodigo(user);
  }

  /**
   * Actualiza el lenguaje de la vista
   */
  public void actualizarLenguaje() {
    vista.getJLabeltitulo().setText((lenguaje.getMensaje().getString("forgot.h1")));
    vista.getJLabelDescripcion().setText(lenguaje.getMensaje().getString("forgot.code.descripcion"));
    vista.getJLabelTFCodigo().setText((lenguaje.getMensaje().getString("forgot.code")));
    vista.getJTextFieldCodigo().setText(null);
    vista.getJButtonConfirmar().setText((lenguaje.getMensaje().getString("forgot.button.confirm")));

    vista.getJButtonClose().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getJButtonBack().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getJButtonConfirmar().setCursor(new Cursor(Cursor.HAND_CURSOR));
  }

  /**
   * Actualiza los estilos de la vista
   */
  public void actualizarEstilos() {
    TextField.actualizarTextField(vista.getJTextFieldCodigo(), lenguaje.getMensaje().getString("forgot.code.placeholder"), 16, "img/svg/Candado.svg", 22, 24, "#575DFB");
    vista.getJButtonConfirmar().putClientProperty("FlatLaf.style", "arc:" + 16);
    vista.getJPanelForgot().putClientProperty("FlatLaf.style", "arc:" + 16);
    vista.getJButtonClose().putClientProperty("FlatLaf.style", "arc:" + 999);

  }
}
