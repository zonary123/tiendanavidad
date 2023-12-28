package org.tienda.Controller;

import org.tienda.Views.ForgotPasswordMail;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.Login;

/**
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordPassword {
  private ForgotPasswordMail vista;
  private utilsLenguaje lenguaje;

  public cForgotPasswordPassword(ForgotPasswordMail vista, utilsLenguaje lenguaje) {
    this.vista = vista;
    this.lenguaje = lenguaje;
  }

  public void initEvents() {
    vista.getJButtonClose().addActionListener(
      e -> {
        this.vista.dispose();
      });
    vista.getJButtonBack().addActionListener(
      e -> {
        this.vista.dispose();
        new Login(null).setVisible(true);
      });
    vista.getJButtonConfirmar().addActionListener(
      e -> {


        this.vista.dispose();

      });
  }
}
