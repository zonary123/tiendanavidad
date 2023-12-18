package org.tienda.Utils;

import javax.swing.*;
import java.awt.*;

public class UtilsJTextField {
  private JTextField textField;
  public UtilsJTextField() {super();}

  public UtilsJTextField(JTextField textField) {
    this.textField = textField;
  }
  public void changeAllTextFields(JFrame frame){
      
  }

  public UtilsJTextField(JTextField textField, String texto, int arc, String colorBorder) {
    textField.setText(texto);
    textField.putClientProperty("JComponent.outline", new BasicStroke(1f));
    textField.putClientProperty("Component.focusWidth", 0);
    textField.putClientProperty("TextField.arc", arc);
    textField.putClientProperty("JComponent.outline", colorBorder);
  }

}
