package org.tienda.Controller;

import org.tienda.Views.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controllerLogin {
  private Login login;

  public controllerLogin(Login login) {
    this.login = login;
  }

  public void init() {
    login.getJButtonLogin().addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        validarCredenciales(login.getJTextFieldUsername().getText(), login.getJTextFieldPassword().getText());
      }
    });
  }

  private boolean validarCredenciales(String username, char Password[]) {

    return true;
  }

}
