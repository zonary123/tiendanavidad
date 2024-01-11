package org.tienda.Controller;

import org.tienda.Interfaces.controllers;
import org.tienda.Utils.EmailUtil;
import org.tienda.Model.Usuarios;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.ForgotPasswordCode;
import org.tienda.Views.ForgotPasswordEmail;
import org.tienda.Views.Login;

import javax.mail.MessagingException;
import javax.swing.*;
import java.io.*;
import java.util.Random;

import org.tienda.Utils.utilsTextField;

/**
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordEmail implements controllers {
  private static ForgotPasswordEmail vista;
  private static utilsLenguaje lenguaje;

  static {
    try {
      lenguaje = new utilsLenguaje();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static Usuarios u;
  private static utilsTextField TextField = new utilsTextField();

  /**
   * Constructor de la clase
   *
   * @param vista Vista de la clase
   * @throws IOException Error de lectura de archivo
   */
  public cForgotPasswordEmail(ForgotPasswordEmail vista) throws IOException {
    cForgotPasswordEmail.vista = vista;
    initEvents();
    actualizarEstilos();
    actualizarLenguaje();
  }

  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
    vista.getJButtonClose().addActionListener(e -> vista.dispose());
    vista.getJButtonBack().addActionListener(
      e -> {
        vista.dispose();
        new Login(null).setVisible(true);
      });
    vista.getJButtonConfirmar().addActionListener(e -> {
      try {
        if (sendCode()) {
          JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("forgot.email.send") + " " + vista.getJTextFieldEmail().getText(),
            "Codigo", JOptionPane.INFORMATION_MESSAGE);
          vista.dispose();
          System.out.println(u);
          new ForgotPasswordCode(u).setVisible(true);
        } else {
          JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("forgot.email.notexist"),
            "Error", JOptionPane.INFORMATION_MESSAGE);
        }

      } catch (MessagingException | IOException ex) {
        throw new RuntimeException(ex);
      }
    });
  }

  /**
   * Genera un codigo aleatorio de 6 caracteres y lo envia al correo
   *
   * @return boolean
   * @throws IOException        Error de lectura de archivo
   * @throws MessagingException Error de envio de correo
   */
  public static boolean sendCode() throws MessagingException, IOException {
    u = new Usuarios();
    u.setEmail(vista.getJTextFieldEmail().getText());
    u.setCodigo(generarCodigo(6));

    if (Usuarios.updateCodigo(u)) {
      EmailUtil.confMail(u);
    } else {
      return false;
    }
    return true;
  }

  /**
   * Genera un codigo aleatorio de la longitud especificada
   *
   * @param longitud Longitud del codigo
   * @return String
   */
  public static String generarCodigo(int longitud) {
    String caracteres = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    Random random = new Random();

    StringBuilder codigoBuilder = new StringBuilder(longitud);

    for (int i = 0; i < longitud; i++) {
      int indiceCaracter = random.nextInt(caracteres.length());
      char caracter = caracteres.charAt(indiceCaracter);
      codigoBuilder.append(caracter);
    }

    return codigoBuilder.toString();
  }

  public void actualizarLenguaje() {
    try {
      lenguaje = new utilsLenguaje();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    vista.getJLabeltitulo().setText((lenguaje.getMensaje().getString("forgot.h1")));
    vista.getJLabelDescripcion().setText(lenguaje.getMensaje().getString("forgot.email.descripcion"));
    vista.getJLabelTFEmail().setText((lenguaje.getMensaje().getString("forgot.email")));
    vista.getJTextFieldEmail().setText(null);
    vista.getJButtonConfirmar().setText((lenguaje.getMensaje().getString("forgot.button.confirm")));
  }

  public void actualizarEstilos() {
    TextField.actualizarTextField(vista.getJTextFieldEmail(), lenguaje.getMensaje().getString("forgot.email.placeholder"), 16, "img/svg/Email.svg", 22, 24, "#575DFB");
    vista.getJButtonConfirmar().putClientProperty("FlatLaf.style", "arc:" + 16);
    vista.getJPanelForgot().putClientProperty("FlatLaf.style", "arc:" + 16);
    vista.getJButtonClose().putClientProperty("FlatLaf.style", "arc:" + 999);
  }
}
