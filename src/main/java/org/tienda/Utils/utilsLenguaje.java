package org.tienda.Utils;

import lombok.Data;

import java.io.*;
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
  public utilsLenguaje() throws IOException {
    System.out.println(new File("src/main/resources/lang/lenguaje.csv").getPath());
    BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/lang/lenguaje.csv")));
    this.fichero = "lang/" + br.readLine().trim();
    this.local = new Locale(this.fichero.split("_")[0]);
    this.mensaje = ResourceBundle.getBundle(this.fichero, this.local);
  }

}
