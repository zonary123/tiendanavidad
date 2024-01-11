package org.tienda.lang;

import org.junit.Assert;
import org.junit.Test;
import org.tienda.Utils.utilsLenguaje;

import java.io.IOException;
import java.util.Locale;

/**
 * @author Carlos Varas Alonso - 11/01/2024 5:48
 */
public class langTest {
  private static utilsLenguaje lenguaje;

  @Test
  public void langES() throws IOException {
    System.out.println("Test de lenguaje");
    lenguaje = new utilsLenguaje("es_ES");
    // ? Vista Login
    Assert.assertEquals(lenguaje.getMensaje().getString("login.h1"), "Inicio Sesion");
  }
}
