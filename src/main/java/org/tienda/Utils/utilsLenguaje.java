package org.tienda.Utils;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.tienda.Model.Usuarios;

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
  private final static String PATH = "configuracion.csv";

  /**
   * Constructor de la clase utilsLenguaje.
   * <br>
   * Lee el fichero de configuración y establece el lenguaje y el país.
   *
   * @throws IOException error de lectura del fichero
   */
  public utilsLenguaje() throws IOException {
    InputStream is = getClass().getClassLoader().getResourceAsStream(PATH);
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    this.fichero = "lang/" + br.readLine();
    this.local = new Locale(this.fichero.split("_")[0]);
    System.out.println(local + "---" + fichero);
    this.mensaje = ResourceBundle.getBundle(this.fichero, this.local);
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


  public utilsLenguaje(Usuarios usuario) {
    this.local = new Locale(usuario.getLenguaje());
    this.mensaje = ResourceBundle.getBundle("lang/" + usuario.getLenguaje(), this.local);
  }


}


