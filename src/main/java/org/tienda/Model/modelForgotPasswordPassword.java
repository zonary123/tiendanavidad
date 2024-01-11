/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tienda.Model;

import org.tienda.Utils.utilsTextField;
import org.tienda.Views.ForgotPasswordPassword;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Interfaces.controllers;

import java.io.IOException;

/**
 * @author Carlos Varas Alonso
 */
public class modelForgotPasswordPassword implements controllers {
  private ForgotPasswordPassword vista;
  private utilsLenguaje lenguaje;
  private utilsTextField textField = new utilsTextField();

  public modelForgotPasswordPassword(ForgotPasswordPassword vista) throws IOException {
    this.vista = vista;
    this.lenguaje = new utilsLenguaje();
    actualizarEstilos();
  }

  public void actualizarLenguaje() {
    vista.getJLabeltitulo().setText((lenguaje.getMensaje().getString("forgot.h1")));
    vista.getJLabelDescripcion().setText(lenguaje.getMensaje().getString("forgot.email.descripcion"));
    vista.getJLabelTFEmail().setText((lenguaje.getMensaje().getString("forgot.email")));
    vista.getJPasswordFieldPassword().setText("");
    vista.getJButtonConfirmar().setText((lenguaje.getMensaje().getString("forgot.button.confirm")));
  }

  public void actualizarEstilos() {
    textField.actualizarTextField(vista.getJPasswordFieldPassword(), "******", 16, "img/svg/Email.svg", 22, 24, "#575DFB");
    vista.getJButtonConfirmar().putClientProperty("FlatLaf.style", "arc:" + 16);
  }
}
