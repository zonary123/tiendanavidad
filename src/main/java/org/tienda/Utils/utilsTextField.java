package org.tienda.Utils;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;

/**
 * The type Utils text field.
 *
 * @author Carlos Varas Alonso
 */
public class utilsTextField {
  private JTextField textField;

  /**
   * Constructor vacio.
   */
  public utilsTextField() {
  }

  /**
   * Constructor con parametro.
   *
   * @param textField el text field
   */
  public utilsTextField(JTextField textField) {
    this.textField = textField;
  }

  /**
   * Pone placeholder.
   *
   * @param placeholder the placeholder
   */
  public void setPlaceholder(String placeholder) {
    textField.putClientProperty("JTextField.placeholderText", placeholder);
  }

  /**
   * Sets rounded.
   *
   * @param arc el radio de la esquina
   */
  public void setRounded(int arc) {
    textField.putClientProperty("FlatLaf.style", "arc:" + arc);
  }

  /**
   * Sets trailing icon.
   *
   * @param icon the icon
   */
  public void setTrailingIcon(FlatSVGIcon icon) {
    textField.putClientProperty("JTextField.trailingIcon", icon);
  }

  /**
   * Sets leading icon.
   *
   * @param icon the icon
   */
  public void setLeadingIcon(FlatSVGIcon icon) {
    textField.putClientProperty("JTextField.leadingIcon", icon);
  }

  /**
   * pone el color del borde.
   *
   * @param color el color
   */
  public void setOutLineColor(String color) {
    textField.putClientProperty("JComponent.outline", new Color[]{Color.decode(color), Color.decode(color)});
  }

  /**
   * pone el ancho del borde.
   *
   * @param width el ancho
   */
  public void setOutLineWidth(int width) {
    textField.putClientProperty("Component.innerOutlineWidth", width);
  }

  /**
   * pone el margen.
   *
   * @param top    margen superior
   * @param left   margen izquierdo
   * @param bottom margen inferior
   * @param right  margen derecho
   */
  public void setMargin(int top, int left, int bottom, int right) {
    textField.putClientProperty("JTextField.padding", new Insets(top, left, bottom, right));
  }

  /**
   * Actualiza el text field.
   *
   * @param textField   el text field
   * @param placeholder el placeholder
   * @param arc         el radio de la esquina
   * @param icon        el icono
   * @param width       el ancho del icono
   * @param height      el alto del icono
   * @param color       el color del borde
   */
  public void actualizarTextField(JTextField textField, String placeholder, int arc, String icon, int width, int height, String color) {
    utilsTextField textFieldCustom = new utilsTextField(textField);
    textFieldCustom.setPlaceholder(placeholder);
    textFieldCustom.setRounded(arc);
    textFieldCustom.setLeadingIcon(new FlatSVGIcon(icon, width, height));
    textFieldCustom.setOutLineColor(color);
    textFieldCustom.setOutLineWidth(1);
    textFieldCustom.setMargin(0, 4, 0, 0);
  }
}


