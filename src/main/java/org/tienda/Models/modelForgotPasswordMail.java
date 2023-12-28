package org.tienda.Models;

import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.ForgotPasswordMail;

import java.io.IOException;

public class modelForgotPasswordMail {
  private utilsLenguaje lenguaje;
  private ForgotPasswordMail vista;

  public modelForgotPasswordMail(ForgotPasswordMail vista) throws IOException {
    this.lenguaje = new utilsLenguaje();
    this.vista = vista;
  }

  public void actualizarLenguaje() {
    vista.getJLabeltitulo().setText((lenguaje.getMensaje().getString("forgot.h1")));
    vista.getJLabelTFEmail().setText((lenguaje.getMensaje().getString("forgot.email")));
    vista.getJButtonConfirmar().setText((lenguaje.getMensaje().getString("forgot.confirm")));
  }

}
