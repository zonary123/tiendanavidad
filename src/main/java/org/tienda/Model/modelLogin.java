package org.tienda.Model;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Utils.utilsTextField;
import org.tienda.Views.Login;

import javax.swing.*;

/**
 * @author Carlos Varas Alonso
 */
public class modelLogin {
  private utilsTextField textField;
  private Login login;
  private utilsLenguaje lenguaje;

  /**
   * Instantiates a new Model login.
   */
  public modelLogin(Login login, utilsLenguaje lenguaje) {
    this.login = login;
    this.lenguaje = lenguaje;
    cambiarIdioma();
  }

  public void cambiarIdioma() {
    this.login.getJButtonPasswordOlvidada().setText(this.lenguaje.getMensaje().getString("login.button.forgotpassword"));
    this.login.getJButtonLogin().setText(this.lenguaje.getMensaje().getString("login.button.login"));
    this.login.getJButtonRegistrarse().setText(this.lenguaje.getMensaje().getString("login.button.register"));
    this.login.getJLabelLogin().setText(this.lenguaje.getMensaje().getString("login.h1"));
    this.login.getJLabelPassword().setText(this.lenguaje.getMensaje().getString("login.label.password"));
    this.login.getJLabelNoCuenta().setText(this.lenguaje.getMensaje().getString("login.label.notaccount"));
  }


  public void actualizarBoton(JButton boton, int arc) {
    boton.putClientProperty("FlatLaf.style", "arc:" + arc);
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
  public void actualizarTextField(JTextField textField, String placeholder, int arc, String icon, int width, int height, String color) {
    this.textField = new utilsTextField(textField);
    this.textField.setPlaceholder(placeholder);
    this.textField.setRounded(arc);
    this.textField.setLeadingIcon(new FlatSVGIcon(icon, width, height));
    this.textField.setOutLineColor(color);
    this.textField.setOutLineWidth(1);
    this.textField.setMargin(0, 14, 0, 0);
  }

}
