package org.tienda.Utils;

import lombok.Data;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Carlos Varas Alonso
 */
@Data
public class utilsLenguaje {
  private String fichero;
  private Locale local;
  private ResourceBundle mensaje;
  private final static String PATH = "src/main/resources/configuracion.csv";

  /**
   * Instantiates a new Utils lenguaje.
   */
  public utilsLenguaje() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(PATH));
    this.fichero = "lang/" + br.readLine().split(",")[0];
    this.local = new Locale(this.fichero.split("_")[0]);
    this.mensaje = ResourceBundle.getBundle(this.fichero, this.local);
  }

}
