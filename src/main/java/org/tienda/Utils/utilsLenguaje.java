package org.tienda.Utils;

import lombok.Data;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type Utils lenguaje.
 */
@Data
public class utilsLenguaje {
  private String fichero;
  private Locale local;
  private ResourceBundle mensaje;

  /**
   * Instantiates a new Utils lenguaje.
   */
  public utilsLenguaje() {
  }

  /**
   * Instantiates a new Utils lenguaje.
   *
   * @param fichero the fichero
   */
  public utilsLenguaje(String fichero) {
    this.fichero = "lang/" + fichero;
    this.local = new Locale(fichero.split("_")[0]);
    this.mensaje = ResourceBundle.getBundle(this.fichero, this.local);
  }

}
