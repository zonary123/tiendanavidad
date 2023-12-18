package org.tienda.Models;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;

import org.tienda.Utils.utilsTextField;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.Login;

/**
 * The type Model login.
 */
public class modelLogin {
  private utilsTextField textField;
  private Login login;

  /**
   * Instantiates a new Model login.
   */
  public modelLogin(Login login, utilsLenguaje lenguaje) {
    this.login = login;
    cambiarIdioma(lenguaje);
  }

  public void cambiarIdioma(utilsLenguaje lenguaje) {
    this.login.getJButtonPasswordOlvidada().setText(lenguaje.getMensaje().getString("login.button.forgotpassword"));
    this.login.getJButtonLogin().setText(lenguaje.getMensaje().getString("login.button.login"));
    this.login.getJButtonRegistrarse().setText(lenguaje.getMensaje().getString("login.button.register"));
    this.login.getJLabelLogin().setText(lenguaje.getMensaje().getString("login.h1"));
    this.login.getJLabelPassword().setText(lenguaje.getMensaje().getString("login.label.password"));
    this.login.getJLabelNoCuenta().setText(lenguaje.getMensaje().getString("login.label.notaccount"));
  }

  /**
   * Actualizar text field.
   *
   * @param textField   the text field
   * @param placeholder the placeholder
   * @param arc         the arc
   * @param icon        the icon
   * @param width       the width
   * @param height      the height
   */
  public void actualizarTextField(JTextField textField, String placeholder, int arc, String icon, int width, int height) {
    this.textField = new utilsTextField(textField);
    this.textField.setPlaceholder(placeholder);
    this.textField.setRounded(arc);
    this.textField.setLeadingIcon(new FlatSVGIcon(icon, width, height));
  }
}
