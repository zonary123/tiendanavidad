package org.tienda.controller;

import org.tienda.utils.utilsLenguaje;
import org.tienda.views.datosUsuario;
import org.tienda.utils.utilsTextField;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

  /**
   * Inicializa todos los eventos de la vista
   */
  private static void initEvents() {
    vista.getJTextFieldRepeatPassword().addKeyListener(new KeyAdapter() {
      @Override public void keyReleased(KeyEvent e) {
        if (vista.getJTextFieldRepeatPassword().getText().equals(vista.getJTextFieldNewPassword().getText())) {
          vista.getJTextFieldNewPassword().putClientProperty("JComponent.outline", new Color[]{Color.decode("#9bd185"), Color.decode("#9bd185")});
          vista.getJTextFieldRepeatPassword().putClientProperty("JComponent.outline", new Color[]{Color.decode("#9bd185"), Color.decode("#9bd185")});
        } else {
          vista.getJTextFieldNewPassword().putClientProperty("JComponent.outline", "error");
          vista.getJTextFieldRepeatPassword().putClientProperty("JComponent.outline", "error");
        }
      }
    });

  }

  private static void actualizarLenguaje() {
    vista.getEmail().setText(lenguaje.getMensaje().getString("register.label.email"));
    vista.getNombre().setText(lenguaje.getMensaje().getString("register.label.name"));
    vista.getApellidos().setText(lenguaje.getMensaje().getString("register.label.lastname"));
    vista.getUsername().setText(lenguaje.getMensaje()
      .getString("login.label.username"));
    vista.getPassword().setText(lenguaje.getMensaje().getString("login.label.password"));
    vista.getNewpassword().setText(lenguaje.getMensaje().getString("label.newpassword"));
    vista.getRepeatpassword().setText(lenguaje.getMensaje().getString("label.repeatpassword"));
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
