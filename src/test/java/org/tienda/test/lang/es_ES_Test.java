package org.tienda.test.lang;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tienda.utils.utilsLenguaje;
import org.tienda.views.Login;

import java.io.IOException;

/**
 * @author Carlos Varas Alonso - 11/01/2024 5:48
 */
public class es_ES_Test {
  private static utilsLenguaje lenguaje;
  private static final String idioma = "es_ES";

  static {
    try {
      lenguaje = new utilsLenguaje(idioma);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  public void Login() throws IOException {
    System.out.println("Test de lenguaje - Vista Login");
    Login vista = new Login(null);
    vista.getCLogin().setLenguaje(lenguaje);
    vista.getCLogin().actualizarLenguaje();
    Assert.assertEquals(lenguaje.getMensaje().getString("login.h1"), vista.getJLabelLogin().getText());
    Assert.assertEquals(lenguaje.getMensaje().getString("login.button.login"), vista.getJButtonLogin().getText());
    Assert.assertEquals(lenguaje.getMensaje().getString("login.button.register"), vista.getJButtonRegistrarse().getText());
    Assert.assertEquals(lenguaje.getMensaje().getString("login.button.forgotpassword"), vista.getJButtonPasswordOlvidada().getText());
    Assert.assertEquals(lenguaje.getMensaje().getString("login.label.notaccount"), vista.getJLabelNoCuenta().getText());
    Assert.assertEquals(lenguaje.getMensaje().getString("login.label.username"), vista.getJLabelUsername().getText());
    Assert.assertEquals(lenguaje.getMensaje().getString("login.label.password"), vista.getJLabelPassword().getText());
  }

  @Test
  public void Register() throws IOException {
    System.out.println("Test de lenguaje - Vista Register");
    lenguaje = new utilsLenguaje(idioma);
  }

  @Test
  public void ForgotPasswordEmail() throws IOException {
    System.out.println("Test de lenguaje - Vista ForgotPasswordEmail");
    lenguaje = new utilsLenguaje(idioma);
  }

  @Test
  public void ForgotPasswordCode() throws IOException {
    System.out.println("Test de lenguaje - Vista ForgotPasswordCode");
    lenguaje = new utilsLenguaje(idioma);
  }

  @Test
  public void ForgotPasswordNewPassword() throws IOException {
    System.out.println("Test de lenguaje - Vista ForgotPasswordNewPassword");
    lenguaje = new utilsLenguaje(idioma);
  }

  @Test
  public void Home() throws IOException {
    System.out.println("Test de lenguaje - Vista Home");
    lenguaje = new utilsLenguaje(idioma);
  }
}