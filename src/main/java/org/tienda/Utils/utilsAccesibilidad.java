package org.tienda.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * @author Carlos Varas Alonso
 */
public class utilsAccesibilidad {
  private Component component;

  /**
   * Instantiates a new Utils accesibilidad.
   *
   * @param componente the componente
   */
  public utilsAccesibilidad(Component componente) {
    this.component = componente;
  }

  /**
   * Borrar todo.
   *
   * @param component the component
   */
  public void borrarTodo(Component component) {
    if (component instanceof TextField)
      ((TextField) component).setText("");
    if (component instanceof JPasswordField)
      ((JPasswordField) component).setText("");
    if (component instanceof JTextArea)
      ((JTextArea) component).setText("");
  }
}


