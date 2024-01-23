package org.tienda.controller;

import org.tienda.utils.utilsLenguaje;
import org.tienda.views.datosUsuario;
import org.tienda.utils.utilsTextField;

/**
 * @author Carlos Varas Alonso - 23/01/2024 6:11
 */
public class cDatosUsuario {
  private static datosUsuario vista;
  private static utilsTextField TextField;
  private static utilsLenguaje lenguaje;

  public cDatosUsuario(datosUsuario vista) {
    this.vista = vista;
    lenguaje = new utilsLenguaje(vista.getUsuario());
    TextField = new utilsTextField();
    actualizarLenguaje();
    actualizarEstilos();
    initEvents();
    setDatos();
  }

  private void setDatos() {
    vista.getJTextFieldEmail().setText(vista.getUsuario().getEmail());
    vista.getJTextFieldUsername().setText(vista.getUsuario().getUsername());
    vista.getJTextFieldNombre().setText(vista.getUsuario().getNombre());
    vista.getJTextFieldApellidos().setText(vista.getUsuario().getApellidos());
  }

  private static void initEvents() {

  }

  private static void actualizarLenguaje() {

  }

  private static void actualizarEstilos() {
    TextField.actualizarTextField(vista.getJTextFieldEmail(), lenguaje.getMensaje().getString("register.email.placeholder"), 16, "img/svg/Email.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJTextFieldUsername(), lenguaje.getMensaje().getString("register.username.placeholder"), 16, "img/svg/Person.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJTextFieldNombre(), lenguaje.getMensaje().getString("register.name.placeholder"), 16, "img/svg/Person.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJTextFieldApellidos(), lenguaje.getMensaje().getString("register.lastname.placeholder"), 16, "img/svg/Person.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJTextFieldYourPassword(), "******", 16, "img/svg/Candado.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJTextFieldNewPassword(), "******", 16, "img/svg/Candado.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJTextFieldRepeatPassword(), "******", 16, "img/svg/Candado.svg", 22, 24, "#575DFB");
  }
}
