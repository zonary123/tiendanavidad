package org.tienda.controller;

import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.tienda.components.Header;
import org.tienda.model.Usuarios;
import org.tienda.utils.utilsLenguaje;
import org.tienda.views.datosUsuario;
import org.tienda.utils.utilsTextField;
import org.tienda.validator.validator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Carlos Varas Alonso - 23/01/2024 6:11
 */
public class cDatosUsuario {
  private static datosUsuario vista;
  private static utilsTextField TextField;
  @Setter private static utilsLenguaje lenguaje;
  private static Header header;

  public cDatosUsuario(datosUsuario vista) {
    cDatosUsuario.vista = vista;
    lenguaje = new utilsLenguaje(vista.getUsuario());
    TextField = new utilsTextField();
    actualizarLenguaje();
    actualizarEstilos();
    initEvents();
    componentes();
    setDatos();
  }

  private void componentes() {
    header = new Header(vista, vista.getUsuario());
    header.remove(header.getSearch());
    vista.getContainer().add(header, new AbsoluteConstraints(15, 10, 1410, 50));
  }

  private void setDatos() {
/*    vista.getJTextFieldEmail().setText(vista.getUsuario().getEmail());
    vista.getJTextFieldUsername().setText(vista.getUsuario().getUsername());*/
    vista.getJTextFieldNombre().setText(vista.getUsuario().getNombre());
    vista.getJTextFieldApellidos().setText(vista.getUsuario().getApellidos());
  }

  /**
   * Inicializa todos los eventos de la vista
   */
  private static void initEvents() {
    // Comprobar passwords
    vista.getJPasswordFieldNewPassword().addKeyListener(new KeyAdapter() {
      @Override public void keyReleased(KeyEvent e) {
        comprobarPassword(vista.getJPasswordFieldNewPassword(), vista.getJPasswordFieldRepeatPassword());
      }
    });
    vista.getJPasswordFieldRepeatPassword().addKeyListener(new KeyAdapter() {
      @Override public void keyReleased(KeyEvent e) {
        comprobarPassword(vista.getJPasswordFieldNewPassword(), vista.getJPasswordFieldRepeatPassword());
      }
    });

    vista.getJButtonImagen().addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        j.setFileFilter(new FileNameExtensionFilter("JPG & PNG imagenes", "jpg", "png"));
        int estado = j.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
          vista.getJButtonImagen().setText(j.getSelectedFile().getName());
        }
      }
    });

    vista.getEnviar().addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        String newPassword = String.valueOf(vista.getJPasswordFieldNewPassword().getPassword());
        String repeatPassword = String.valueOf(vista.getJPasswordFieldRepeatPassword().getPassword());

        if (comprobarDatos()) {
          return;
        }
        if (Usuarios.checkPassword(String.valueOf(vista.getJPasswordFieldYourPassword().getPassword()), vista.getUsuario())) {
          vista.getJPasswordFieldYourPassword().putClientProperty("JComponent.outline", new Color[]{Color.decode("#9bd185"), Color.decode("#9bd185")});
        } else {
          vista.getJPasswordFieldYourPassword().putClientProperty("JComponent.outline", "error");
          return;
        }

        if (newPassword.equals(repeatPassword)) {
/*          vista.getUsuario().setEmail(vista.getJTextFieldEmail().getText());
          vista.getUsuario().setUsername(vista.getJTextFieldUsername().getText());*/
          vista.getUsuario().setNombre(vista.getJTextFieldNombre().getText());
          vista.getUsuario().setApellidos(vista.getJTextFieldApellidos().getText());
          vista.getUsuario().setPassword(newPassword);
          Usuarios.update(vista.getUsuario());
          vista.dispose();
          new datosUsuario(vista.getUsuario()).setVisible(true);
        }
      }
    });
  }

  /**
   * Comprueba que los datos introducidos son correctos
   *
   * @return true si hay algún error, false si no hay errores
   */
  private static boolean comprobarDatos() {
    int fallos = 0;
    String password = String.valueOf(vista.getJPasswordFieldYourPassword().getPassword());
    String newPassword = String.valueOf(vista.getJPasswordFieldNewPassword().getPassword());
    String repeatPassword = String.valueOf(vista.getJPasswordFieldRepeatPassword().getPassword());
/*    if (vista.getJTextFieldEmail().getText().isEmpty() || !validator.isEmail(vista.getJTextFieldEmail().getText())) {
      vista.getJTextFieldEmail().putClientProperty("JComponent.outline", "error");
      fallos++;
    }

    if (vista.getJTextFieldUsername().getText().isEmpty() || !validator.isUsername(vista.getJTextFieldUsername().getText())) {
      vista.getJTextFieldUsername().putClientProperty("JComponent.outline", "error");
      fallos++;
    }*/

    if (vista.getJTextFieldNombre().getText().isEmpty() || !validator.isNombre(vista.getJTextFieldNombre().getText())) {
      vista.getJTextFieldNombre().putClientProperty("JComponent.outline", "error");
      fallos++;
    }

    if (vista.getJTextFieldApellidos().getText().isEmpty() || !validator.isApellido(vista.getJTextFieldApellidos().getText())) {
      vista.getJTextFieldApellidos().putClientProperty("JComponent.outline", "error");
      fallos++;
    }

    if (password.isEmpty() || !validator.isPassword(password)) {
      vista.getJPasswordFieldYourPassword().putClientProperty("JComponent.outline", "error");
      fallos++;
    }

    if (newPassword.isEmpty() || !validator.isPassword(newPassword)) {
      vista.getJPasswordFieldNewPassword().putClientProperty("JComponent.outline", "error");
      fallos++;
    }

    if (repeatPassword.isEmpty() || !validator.isPassword(repeatPassword)) {
      vista.getJPasswordFieldRepeatPassword().putClientProperty("JComponent.outline", "error");
      fallos++;
    }
    return fallos != 0;
  }

  private static void comprobarPassword(JPasswordField newpassword, JPasswordField repeatpassword) {
    String newPassword = String.valueOf(newpassword.getPassword());
    String repeatPassword = String.valueOf(repeatpassword.getPassword());
    if (newPassword.isEmpty() || repeatPassword.isEmpty()) {
      newpassword.putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
      repeatpassword.putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
      return;
    }
    if (newPassword.equals(repeatPassword)) {
      newpassword.putClientProperty("JComponent.outline", new Color[]{Color.decode("#9bd185"), Color.decode("#9bd185")});
      repeatpassword.putClientProperty("JComponent.outline", new Color[]{Color.decode("#9bd185"), Color.decode("#9bd185")});
    } else {
      newpassword.putClientProperty("JComponent.outline", "error");
      repeatpassword.putClientProperty("JComponent.outline", "error");
    }
  }

  public static void actualizarLenguaje() {
    // vista.getEmail().setText(lenguaje.getMensaje().getString("register.label.email"));
    vista.getNombre().setText(lenguaje.getMensaje().getString("register.label.name"));
    vista.getApellidos().setText(lenguaje.getMensaje().getString("register.label.lastname"));
/*    vista.getUsername().setText(lenguaje.getMensaje()
      .getString("login.label.username"));*/
    vista.getPassword().setText(lenguaje.getMensaje().getString("login.label.password"));
    vista.getNewpassword().setText(lenguaje.getMensaje().getString("label.newpassword"));
    vista.getRepeatpassword().setText(lenguaje.getMensaje().getString("label.repeatpassword"));
    vista.getJButtonImagen().setText(lenguaje.getMensaje().getString("button.selectImage"));
    vista.getEnviar().setText(lenguaje.getMensaje().getString("button.send"));
  }

  public static void actualizarEstilos() {
    //TextField.actualizarTextField(vista.getJTextFieldEmail(), lenguaje.getMensaje().getString("register.email.placeholder"), 16, "img/svg/Email.svg", 22, 24, "#575DFB");
    //TextField.actualizarTextField(vista.getJTextFieldUsername(), lenguaje.getMensaje().getString("register.username.placeholder"), 16, "img/svg/Person.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJTextFieldNombre(), lenguaje.getMensaje().getString("register.name.placeholder"), 16, "img/svg/Person.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJTextFieldApellidos(), lenguaje.getMensaje().getString("register.lastname.placeholder"), 16, "img/svg/Person.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJPasswordFieldYourPassword(), lenguaje.getMensaje().getString("register.password.placeholder"), 16, "img/svg/Candado.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJPasswordFieldNewPassword(), lenguaje.getMensaje().getString("label.newpassword"), 16, "img/svg/Candado.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJPasswordFieldRepeatPassword(), lenguaje.getMensaje().getString("register.label.repeatpassword"), 16, "img/svg/Candado.svg", 22, 24, "#575DFB");
    vista.getContainerDatos().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getEnviar().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getJButtonImagen().putClientProperty("FlatLaf.style", "arc: 999");
    vista.getJButtonImagen().putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
    vista.getEnviar().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getJButtonImagen().setCursor(new Cursor(Cursor.HAND_CURSOR));
  }


}
