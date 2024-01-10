package org.tienda.Model;

import org.tienda.Utils.utilsLenguaje;
import org.tienda.Utils.utilsTextField;
import org.tienda.Views.ForgotPasswordEmail;
import org.tienda.Interfaces.models;

import java.io.IOException;

/**
 * @author Carlos Varas Alonso
 */
public class modelForgotPasswordEmail implements models {
  private utilsLenguaje lenguaje;
  private ForgotPasswordEmail vista;
  private utilsTextField TextField = new utilsTextField();

  public modelForgotPasswordEmail(ForgotPasswordEmail vista) throws IOException {
    this.lenguaje = new utilsLenguaje();
    this.vista = vista;
    actualizarLenguaje();
    actualizarEstilos();
  }


  public void actualizarLenguaje() {
    vista.getJLabeltitulo().setText((lenguaje.getMensaje().getString("forgot.h1")));
    vista.getJLabelDescripcion().setText(lenguaje.getMensaje().getString("forgot.email.descripcion"));
    vista.getJLabelTFEmail().setText((lenguaje.getMensaje().getString("forgot.email")));
    vista.getJTextFieldEmail().setText(null);
    vista.getJButtonConfirmar().setText((lenguaje.getMensaje().getString("forgot.button.confirm")));
  }

  public void actualizarEstilos() {
    TextField.actualizarTextField(vista.getJTextFieldEmail(), lenguaje.getMensaje().getString("forgot.email.placeholder"), 16, "img/svg/Email.svg", 22, 24, "#575DFB");
    vista.getJButtonConfirmar().putClientProperty("FlatLaf.style", "arc:" + 16);
  }
}
