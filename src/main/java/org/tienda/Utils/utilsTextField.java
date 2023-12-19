package org.tienda.Utils;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;

/**
 * The type Utils text field.
 */
public class utilsTextField {
  private JTextField textField;

  /**
   * Instantiates a new Utils text field.
   *
   * @param textField the text field
   */
  public utilsTextField(JTextField textField) {
    this.textField = textField;
  }

  /**
   * Sets placeholder.
   *
   * @param placeholder the placeholder
   */
  public void setPlaceholder(String placeholder) {
    textField.putClientProperty("JTextField.placeholderText", placeholder);
  }

  /**
   * Sets rounded.
   *
   * @param arc the arc
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

  public void setOutLineColor(String color) {
    textField.putClientProperty("JComponent.outline", new Color[]{Color.decode(color), Color.decode(color)});
  }

  public void setOutLineWidth(int width) {
    textField.putClientProperty("Component.innerOutlineWidth", width);
  }
}


