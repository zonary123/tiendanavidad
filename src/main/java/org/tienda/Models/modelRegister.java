package org.tienda.Models;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.tienda.Views.Register;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Utils.utilsTextField;

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

  }

  public void cambiarTextFields() {
    actualizarTextField(this.register.getJTextFieldNombre(), this.lenguaje.getMensaje().getString("register.name.placeholder"), 16, "img/svg/Person.svg", 22, 24, "#575DFB");
    actualizarTextField(this.register.getJTextFieldApellidos(), this.lenguaje.getMensaje().getString("register.lastname.placeholder"), 16, "img/svg/Person.svg", 22, 24, "#575DFB");
    actualizarTextField(this.register.getJTextFieldEmail(), this.lenguaje.getMensaje().getString("register.email.placeholder"), 16, "img/svg/Email.svg", 22, 24, "#575DFB");
    actualizarTextField(this.register.getJTextFieldUsername(), this.lenguaje.getMensaje().getString("register.username.placeholder"), 16, "img/svg/Person.svg", 22, 24, "#575DFB");
    actualizarTextField(this.register.getJPasswordFieldPassword(), this.lenguaje.getMensaje().getString("register.password.placeholder"), 16, "img/svg/Candado.svg", 16, 19, "#575DFB");
  }

  public void actualizarTextField(JTextField textField, String placeholder, int arc, String icon, int width, int height, String color) {
    this.textField = new utilsTextField(textField);
    this.textField.setPlaceholder(placeholder);
    this.textField.setRounded(arc);
    this.textField.setLeadingIcon(new FlatSVGIcon(icon, width, height));
    this.textField.setOutLineColor(color);
    this.textField.setOutLineWidth(1);
  }
}
