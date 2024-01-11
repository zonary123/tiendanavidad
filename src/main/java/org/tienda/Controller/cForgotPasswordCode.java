package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Model.Usuarios;
import org.tienda.Utils.utilsTextField;
import org.tienda.Views.ForgotPasswordCode;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.ForgotPasswordPassword;
import org.tienda.Views.Login;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.io.IOException;

/**
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordCode {
  private static utilsLenguaje lenguaje;
  private static utilsTextField TextField = new utilsTextField();
  private ForgotPasswordCode vista;

  static {
    try {
      lenguaje = new utilsLenguaje();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private int intentos = 0;


  /**
   * Constructor de la clase
   *
   * @param vista Vista de la clase
   * @throws IOException Error de lectura de archivo
   */
  public cForgotPasswordCode(ForgotPasswordCode vista) throws IOException {
    this.vista = vista;
    lenguaje = new utilsLenguaje();
    initEvents();
    actualizarEstilos();
    actualizarLenguaje();
  }

  /**
   * Inicializacion de eventos de la vista
   */
  private void initEvents() {
    vista.getJButtonConfirmar().addActionListener(e -> {
      if (Usuarios.checkCodigo(vista.getUsuario())) {
        intentos = 0;
        vista.setVisible(false);
        vista.dispose();
        new ForgotPasswordPassword(vista.getUsuario()).setVisible(true);
        borrarCodigo();
      } else {
        intentos++;
        if (intentos > 3) {
          new Login(null).setVisible(true);
          vista.setVisible(false);
          vista.dispose();
        } else {
          JOptionPane.showMessageDialog(null, "Intentos: " + intentos + "/3", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

      }
    });
  }

  /**
   * Borra el codigo de la base de datos
   */
  public void borrarCodigo() {
    Usuarios user = new Usuarios();
    user.setCodigo(null);
    user.setEmail(vista.getUsuario().getEmail());
    Usuarios.update(user);
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
