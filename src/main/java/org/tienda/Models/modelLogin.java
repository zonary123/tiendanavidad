package org.tienda.Models;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;

import org.tienda.Utils.utilsTextField;


/**
 * The type Model login.
 */
public class modelLogin {
  private utilsTextField textField;

  /**
   * Instantiates a new Model login.
   */
  public modelLogin() {

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
