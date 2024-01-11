package org.tienda.Model;

import org.tienda.Utils.utilsLenguaje;
import org.tienda.Utils.utilsTextField;
import org.tienda.Views.ForgotPasswordCode;

import java.io.IOException;

/**
 * @author Carlos Varas Alonso
 */
public class modelForgotPasswordCode {
  private utilsLenguaje lenguaje;
  private ForgotPasswordCode vista;
  private utilsTextField TextField = new utilsTextField();

  /**
   * Constructor de la clase
   *
   * @param vista Vista de la clase
   */
  public modelForgotPasswordCode(ForgotPasswordCode vista) throws IOException {
    this.lenguaje = new utilsLenguaje();
    this.vista = vista;
    actualizarLenguaje();
    actualizarEstilos();
  }

  /**
   * Actualiza el lenguaje de la vista
   */
  public void actualizarLenguaje() {
    vista.getJLabeltitulo().setText((lenguaje.getMensaje().getString("forgot.h1")));
    vista.getJLabelDescripcion().setText(lenguaje.getMensaje().getString("forgot.code.descripcion"));
    vista.getJLabelTFCodigo().setText((lenguaje.getMensaje().getString("forgot.code")));
    vista.getJTextFieldCodigo().setText(null);
    vista.getJButtonConfirmar().setText((lenguaje.getMensaje().getString("forgot.button.confirm")));
  }

  /**
   * Actualiza los estilos de la vista
   */
  public void actualizarEstilos() {
    TextField.actualizarTextField(vista.getJTextFieldCodigo(), lenguaje.getMensaje().getString("forgot.code.placeholder"), 16, "img/svg/Candado.svg", 22, 24, "#575DFB");
    vista.getJButtonConfirmar().putClientProperty("FlatLaf.style", "arc:" + 16);

  }

}
