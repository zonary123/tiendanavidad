package org.tienda.utils;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.tienda.model.Usuarios;

/**
 * The type utils lenguaje.
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

  /**
   * Constructor de la clase utilsLenguaje.
   * <br>
   * Lee el fichero de configuración y establece el lenguaje y el país.
   *
   * @throws IOException error de lectura del fichero
   */
  public utilsLenguaje() throws IOException {
    this.fichero = "lang/" + (Locale.getDefault().toString().equals("es_ES") ? "es_ES" : "en_US");
    this.local = new Locale(Locale.getDefault().getLanguage().equals("es") ? "es" : "en");
    Locale.setDefault(Locale.getDefault());
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
    Locale.setDefault(local);
    this.mensaje = ResourceBundle.getBundle("lang/" + lenguaje, this.local);
  }


  public utilsLenguaje(Usuarios usuario) {
    this.local = new Locale(usuario.getLenguaje());
    Locale.setDefault(local);
    this.mensaje = ResourceBundle.getBundle("lang/" + usuario.getLenguaje(), this.local);
  }


}


