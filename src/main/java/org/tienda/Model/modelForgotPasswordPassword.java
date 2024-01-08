/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tienda.Model;

import org.tienda.Views.ForgotPasswordPassword;
import org.tienda.Utils.utilsLenguaje;

import java.io.IOException;

/**
 * @author Carlos Varas Alonso
 */
public class modelForgotPasswordPassword {
  private ForgotPasswordPassword vista;
  private utilsLenguaje lenguaje;

  public modelForgotPasswordPassword(ForgotPasswordPassword vista) throws IOException {
    this.vista = vista;
    this.lenguaje = new utilsLenguaje();
  }
}
