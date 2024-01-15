package org.tienda.Utils;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Utils lenguaje.
 *
 * @author Carlos Varas Alonso
 */
@Data
@Getter
@Setter
public class utilsLenguaje {

  private String fichero;
  private Locale local;
  private ResourceBundle mensaje;
  private final static String PATH = "src/main/resources/configuracion.csv";

  /**
   * Constructor de la clase utilsLenguaje.
   * <br>
   * Lee el fichero de configuración y establece el lenguaje y el país.
   *
   * @throws IOException error de lectura del fichero
   */
  public utilsLenguaje() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(PATH));
    this.fichero = "lang/" + br.readLine().split(",")[0];
    this.local = new Locale(this.fichero.split("_")[0]);
    this.mensaje = ResourceBundle.getBundle(this.fichero, this.local);
  }

  public String getText(String clave) {
    //return mensaje.getStri ng(clave);
    return null;
  }

  /**
   * Constructor de la clase utilsLenguaje.
   * <br>
   * Establece el lenguaje y el país.
   *
   * @param lenguaje el lenguaje
   *
   * @throws IOException error de lectura del fichero
   */
  public utilsLenguaje(String lenguaje) throws IOException {
    this.local = new Locale(new Locale(lenguaje).getLanguage());
    this.mensaje = ResourceBundle.getBundle("lang/" + lenguaje, this.local);
  }

}
