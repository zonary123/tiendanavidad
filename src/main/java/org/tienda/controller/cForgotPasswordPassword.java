package org.tienda.controller;

import org.tienda.model.Usuarios;
import org.tienda.utils.utilsLenguaje;
import org.tienda.utils.utilsTextField;
import org.tienda.views.ForgotPasswordPassword;
import org.tienda.views.Login;
import org.tienda.validator.validator;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;

/**
 * The type C forgot password password.
 *
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordPassword {
  private ForgotPasswordPassword vista;
  private static utilsLenguaje lenguaje;

  private static utilsTextField TextField = new utilsTextField();

  /**
   * Constructor de la clase
   *
   * @param vista Vista de la clase
   *
   * @throws IOException Error de lectura de archivo
   */
  public cForgotPasswordPassword(ForgotPasswordPassword vista) throws IOException {
    this.vista = vista;
    lenguaje = new utilsLenguaje();
    initEvents();
    actualizarEstilos();
    actualizarLenguaje();
  }

  /**
   * Actualiza el lenguaje de la vista
   */
  public void actualizarLenguaje() {
    vista.getJLabeltitulo().setText(lenguaje.getMensaje().getString("forgot.h1"));
    vista.getJLabelDescripcion().setText(lenguaje.getMensaje().getString("forgot.password.descripcion"));
    vista.getJLabelPassword().setText(lenguaje.getMensaje().getString("forgot.password.placeholder"));
    vista.getJLabelPasswordRepeat().setText(lenguaje.getMensaje().getString("forgot.password.repeat"));
    vista.getJButtonConfirmar().setText(lenguaje.getMensaje().getString("forgot.button.confirm"));
    vista.getJPasswordFieldPassword().setToolTipText(lenguaje.getMensaje().getString("tooltip.regex.password"));
    vista.getJPasswordFieldPassword1().setToolTipText(lenguaje.getMensaje().getString("tooltip.regex.password"));
  }

  /**
   * Actualiza los estilos de la vista
   */
  public void actualizarEstilos() {
    TextField.actualizarTextField(vista.getJPasswordFieldPassword(), lenguaje.getMensaje().getString("register.password.placeholder"), 16, "img/svg/Email.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJPasswordFieldPassword1(), lenguaje.getMensaje().getString("register.label.repeatpassword"), 16, "img/svg/Email.svg", 22, 24, "#575DFB");

    vista.getJButtonConfirmar().putClientProperty("FlatLaf.style", "arc:" + 16);
    vista.getJPanelForgot().putClientProperty("FlatLaf.style", "arc:" + 16);
    vista.getJButtonClose().putClientProperty("FlatLaf.style", "arc:" + 999);

    vista.getJButtonClose().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getJButtonBack().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getJButtonConfirmar().setCursor(new Cursor(Cursor.HAND_CURSOR));
  }

  private static boolean comprobarPassword(JPasswordField newpassword, JPasswordField repeatpassword) {
    String newPassword = String.valueOf(newpassword.getPassword()).trim();
    String repeatPassword = String.valueOf(repeatpassword.getPassword()).trim();
    if (newPassword.isEmpty() || repeatPassword.isEmpty()) {
      newpassword.putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
      repeatpassword.putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
      return false;
    }
    if (newPassword.equals(repeatPassword)) {
      newpassword.putClientProperty("JComponent.outline", new Color[]{Color.decode("#9bd185"), Color.decode("#9bd185")});
      repeatpassword.putClientProperty("JComponent.outline", new Color[]{Color.decode("#9bd185"), Color.decode("#9bd185")});
      return true;
    } else {
      newpassword.putClientProperty("JComponent.outline", "error");
      repeatpassword.putClientProperty("JComponent.outline", "error");
      return false;
    }
  }

  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
    vista.getRootPane().setDefaultButton(vista.getJButtonConfirmar());
    vista.getJPasswordFieldPassword().requestFocus();
    vista.getJButtonClose().addActionListener(e -> vista.dispose());
    vista.getJButtonBack().addActionListener(
      e -> {
        vista.dispose();
        new Login(null).setVisible(true);
      });
    vista.getJButtonConfirmar().addActionListener(
      e -> {
        if (comprobarPassword(vista.getJPasswordFieldPassword(), vista.getJPasswordFieldPassword1())) {
          vista.getUsuario().setPassword(String.valueOf(vista.getJPasswordFieldPassword().getPassword()).trim());
          if (!Usuarios.updatePassword(vista.getUsuario()))
            JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("error.update.user.password"), "Error", JOptionPane.INFORMATION_MESSAGE);
          vista.dispose();
          new Login(null).setVisible(true);
        } else {
          vista.getJPasswordFieldPassword().putClientProperty("JComponent.outline", "error");
          vista.getJPasswordFieldPassword1().putClientProperty("JComponent.outline", "error");
        }
      });
  }
}
