package org.tienda.Models;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Utils.utilsTextField;
import org.tienda.Views.Register;

import javax.swing.*;

public class modelRegister {

  private Register register;
  private utilsLenguaje lenguaje;
  private utilsTextField textField;

  public modelRegister(Register register, utilsLenguaje lenguaje) {
    this.register = register;
    this.lenguaje = lenguaje;
    cambiarIdioma();
    cambiarTextFields();
  }

  public void cambiarIdioma() {
    register.getJLabelEmail().setText(lenguaje.getMensaje().getString("register.label.email"));
    register.getJLabelNombre().setText(lenguaje.getMensaje().getString("register.label.name"));
    register.getJLabelApellidos().setText(lenguaje.getMensaje().getString("register.label.lastname"));
    register.getJLabelPassword().setText(lenguaje.getMensaje().getString("register.label.password"));
    register.getJLabelUsername().setText(lenguaje.getMensaje().getString("register.label.username"));
    register.getJLabelRegistrar().setText(lenguaje.getMensaje().getString("register.signup"));
    register.getJButtonRegistrarse().setText(lenguaje.getMensaje().getString("register.signup"));
    register.getJButtonIniciarSesion().setText(lenguaje.getMensaje().getString("register.signin"));
    register.getJLabelConCuenta().setText(lenguaje.getMensaje().getString("register.label.ConCuenta"));
  }

  public void cambiarTextFields() {
    actualizarTextField(this.register.getJTextFieldNombre(), this.lenguaje.getMensaje().getString("register.name.placeholder"), 16, "img/svg/Person.svg", 16, 19, "#575DFB");
    actualizarTextField(this.register.getJTextFieldApellidos(), this.lenguaje.getMensaje().getString("register.lastname.placeholder"), 16, "img/svg/Person.svg", 16, 19, "#575DFB");
    actualizarTextField(this.register.getJTextFieldEmail(), this.lenguaje.getMensaje().getString("register.email.placeholder"), 16, "img/svg/Email.svg", 19, 19, "#575DFB");
    actualizarTextField(this.register.getJTextFieldUsername(), this.lenguaje.getMensaje().getString("register.username.placeholder"), 16, "img/svg/Person.svg", 16, 19, "#575DFB");
    actualizarTextField(this.register.getJPasswordFieldPassword(), this.lenguaje.getMensaje().getString("register.password.placeholder"), 16, "img/svg/Candado.svg", 16, 19, "#575DFB");

    actualizarBoton(register.getJButtonRegistrarse(), 16);
  }

  public void actualizarTextField(JTextField textField, String placeholder, int arc, String icon, int width, int height, String color) {
    this.textField = new utilsTextField(textField);
    this.textField.setPlaceholder(placeholder);
    this.textField.setRounded(arc);
    this.textField.setLeadingIcon(new FlatSVGIcon(icon, width, height));
    this.textField.setOutLineColor(color);
    this.textField.setOutLineWidth(1);
  }

  public void actualizarBoton(JButton boton, int arc) {
    boton.putClientProperty("FlatLaf.style", "arc:" + arc);
  }
}
