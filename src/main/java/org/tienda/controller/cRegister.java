package org.tienda.controller;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import org.tienda.utils.utilsLenguaje;
import org.tienda.utils.utilsTextField;
import org.tienda.views.Login;
import org.tienda.views.Register;
import org.tienda.model.Usuarios;
import org.tienda.validator.validator;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;

/**
 * The type controller register.
 *
 * @author Carlos Varas Alonso
 */
public class cRegister {
  private final Register vista;
  private static utilsLenguaje lenguaje;
  private Usuarios usuario = null;
  private utilsTextField textField;

  static {
    try {
      lenguaje = new utilsLenguaje();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Instantiates a new controller login.
   *
   * @param vista El Jframe login
   */
  public cRegister(Register vista) {
    this.vista = vista;

    initEvents();
    actualizarLenguaje();
    actualizarEstilos();
  }

  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
    vista.getRootPane().setDefaultButton(vista.getJButtonRegistrarse());
    vista.getJTextFieldEmail().requestFocus();
    vista.getJButtonBack().addActionListener(e -> {
      vista.dispose();
      new Login(usuario == null ? null : usuario.getUsername()).setVisible(true);
    });
    vista.getJButtonIniciarSesion().addActionListener(e -> {
      vista.dispose();
      new Login(usuario == null ? null : usuario.getUsername()).setVisible(true);
    });
    vista.getJButtonRegistrarse().addActionListener(e -> {
      if (comprobaciones() && registrarse()) {
        vista.dispose();
        new Login(usuario == null ? null : usuario.getUsername()).setVisible(true);
      }
    });
    vista.getJButtonClose().addActionListener(e -> vista.dispose());
  }

  /**
   * Comprobaciones de la vista de registro
   * <p>
   * Comprueba que los campos no esten vacios y que cumplan con las expresiones regulares
   * <br>
   * Si hay algun error, muestra un mensaje de error
   * <br>
   * Si no hay ningun error, devuelve true
   * <br>
   * Si hay algun error, devuelve false
   * <br>
   * Si hay algun error, pone el campo en amarillo
   * <br>
   * </p>
   *
   * @return boolean true si no hay errores, false si hay errores
   */
  private boolean comprobaciones() {
    int errores = 0;
    String mensaje = "";

    if (vista.getJTextFieldEmail().getText().isEmpty()) {
      mensaje += lenguaje.getMensaje().getString("void.email") + "\n";
      vista.getJTextFieldEmail().putClientProperty("JComponent.outline", "warning");
      errores++;
    } else if (!validator.isEmail(vista.getJTextFieldEmail().getText())) {
      mensaje += lenguaje.getMensaje().getString("regex.email") + "\n";
      vista.getJTextFieldEmail().putClientProperty("JComponent.outline", "warning");
      errores++;
    } else {
      vista.getJTextFieldEmail().putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
    }


    if (vista.getJTextFieldUsername().getText().isEmpty()) {
      mensaje += lenguaje.getMensaje().getString("void.username") + "\n";
      vista.getJTextFieldUsername().putClientProperty("JComponent.outline", "warning");
      errores++;
    } else if (!validator.isUsername(vista.getJTextFieldUsername().getText())) {
      mensaje += lenguaje.getMensaje().getString("regex.username") + "\n";
      vista.getJTextFieldUsername().putClientProperty("JComponent.outline", "warning");
      errores++;
    } else {
      vista.getJTextFieldUsername().putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
    }

    if (vista.getJTextFieldNombre().getText().isEmpty()) {
      mensaje += lenguaje.getMensaje().getString("void.name") + "\n";
      vista.getJTextFieldNombre().putClientProperty("JComponent.outline", "warning");
      errores++;
    } else if (!validator.isNombre(vista.getJTextFieldNombre().getText())) {
      mensaje += lenguaje.getMensaje().getString("regex.name") + "\n";
      vista.getJTextFieldNombre().putClientProperty("JComponent.outline", "warning");
      errores++;
    } else {
      vista.getJTextFieldNombre().putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
    }

    String apellidosText = vista.getJTextFieldApellidos().getText();

    if (!apellidosText.isEmpty()) {
      if (!validator.isApellido(apellidosText)) {
        mensaje += lenguaje.getMensaje().getString("regex.lastname") + "\n";
        vista.getJTextFieldApellidos().putClientProperty("JComponent.outline", "warning");
        errores++;
      } else {
        vista.getJTextFieldApellidos().putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
      }
    }

    // Hadouken
    if (String.valueOf(vista.getJPasswordFieldPassword().getPassword()).isEmpty()) {
      mensaje += lenguaje.getMensaje().getString("void.password") + "\n";
      vista.getJPasswordFieldPassword().putClientProperty("JComponent.outline", "warning");
      errores++;
    } else {
      if (!String.valueOf(vista.getJPasswordFieldPassword().getPassword()).matches(validator.PASSWORD_PATTERN)) {
        mensaje += lenguaje.getMensaje().getString("regex.password") + "\n";
        vista.getJPasswordFieldPassword().putClientProperty("JComponent.outline", "warning");
        errores++;
      } else {
        if (!String.valueOf(vista.getJPasswordFieldPassword1().getPassword()).matches(validator.PASSWORD_PATTERN)) {
          mensaje += lenguaje.getMensaje().getString("regex.password") + "\n";
          vista.getJPasswordFieldPassword1().putClientProperty("JComponent.outline", "warning");
          errores++;
        } else {
          if (!String.valueOf(vista.getJPasswordFieldPassword().getPassword()).equals(String.valueOf(vista.getJPasswordFieldPassword1().getPassword()))) {
            mensaje += lenguaje.getMensaje().getString("error.password.notequals") + "\n";
            vista.getJPasswordFieldPassword().putClientProperty("JComponent.outline", "warning");
            vista.getJPasswordFieldPassword1().putClientProperty("JComponent.outline", "warning");
            errores++;
          } else {
            vista.getJPasswordFieldPassword().putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
            vista.getJPasswordFieldPassword1().putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
          }
        }
      }

    }

    if (errores > 0)
      JOptionPane.showMessageDialog(null, mensaje);

    return errores == 0;
  }

  /**
   * Registra al usuario en la base de datos
   *
   * @return boolean true si se ha registrado, false si no
   */
  private boolean registrarse() {

    // Verificar si el usuario o el correo ya existen
    String username = vista.getJTextFieldUsername().getText();
    String email = vista.getJTextFieldEmail().getText();
    Usuarios existingUser = new Usuarios();
    existingUser.setUsername(username);
    existingUser.setEmail(email);
    if (Usuarios.existUser(existingUser) != null) {
      if (existingUser.getEmail().equals(email)) {
        JOptionPane.showMessageDialog(null, "El correo ya existe");
      }
      if (existingUser.getUsername().equals(username)) {
        JOptionPane.showMessageDialog(null, "El usuario ya existe");
      }
      return false;
    }
    this.usuario = new Usuarios();
    usuario.setEmail(vista.getJTextFieldEmail().getText());
    usuario.setUsername(vista.getJTextFieldUsername().getText());
    usuario.setPassword(String.valueOf(vista.getJPasswordFieldPassword().getPassword()));
    usuario.setNombre(vista.getJTextFieldNombre().getText());
    usuario.setApellidos(vista.getJTextFieldApellidos().getText());
    usuario.setLenguaje(Locale.getDefault().toString());
    usuario.setRoles("[\"user\"]");
    usuario.setActivacion(true);
    Usuarios.save(usuario);
    return true;
  }

  public void actualizarLenguaje() {
    vista.getJLabelEmail().setText(lenguaje.getMensaje().getString("register.label.email"));
    vista.getJLabelNombre().setText(lenguaje.getMensaje().getString("register.label.name"));
    vista.getJLabelApellidos().setText(lenguaje.getMensaje().getString("register.label.lastname"));
    vista.getJLabelPassword().setText(lenguaje.getMensaje().getString("register.label.password"));
    vista.getJLabelPassword1().setText(lenguaje.getMensaje().getString("register.label.repeatpassword"));
    vista.getJLabelUsername().setText(lenguaje.getMensaje().getString("register.label.username"));
    vista.getJLabelRegistrar().setText(lenguaje.getMensaje().getString("register.signup"));
    vista.getJButtonRegistrarse().setText(lenguaje.getMensaje().getString("register.signup"));
    vista.getJButtonIniciarSesion().setText(lenguaje.getMensaje().getString("register.signin"));
    vista.getJLabelConCuenta().setText(lenguaje.getMensaje().getString("register.label.ConCuenta"));
    vista.getJTextFieldEmail().setToolTipText(lenguaje.getMensaje().getString("tooltip.regex.Emaiil"));
    vista.getJTextFieldUsername().setToolTipText(lenguaje.getMensaje().getString("tooltip.regex.username"));
    vista.getJTextFieldNombre().setToolTipText(lenguaje.getMensaje().getString("tooltip.regex.name"));
    vista.getJTextFieldApellidos().setToolTipText(lenguaje.getMensaje().getString("tooltip.regex.lastname"));
    vista.getJPasswordFieldPassword().setToolTipText(lenguaje.getMensaje().getString("tooltip.regex.password"));
    vista.getJPasswordFieldPassword1().setToolTipText(lenguaje.getMensaje().getString("tooltip.regex.password"));
  }

  /**
   * Actualiza los estilos de la vista
   */
  public void actualizarEstilos() {
    actualizarTextField(this.vista.getJTextFieldNombre(), this.lenguaje.getMensaje().getString("register.name.placeholder"), 16, "img/svg/Person.svg", 16, 19, "#575DFB");
    actualizarTextField(this.vista.getJTextFieldApellidos(), this.lenguaje.getMensaje().getString("register.lastname.placeholder"), 16, "img/svg/Person.svg", 16, 19, "#575DFB");
    actualizarTextField(this.vista.getJTextFieldEmail(), this.lenguaje.getMensaje().getString("register.email.placeholder"), 16, "img/svg/Email.svg", 19, 19, "#575DFB");
    actualizarTextField(this.vista.getJTextFieldUsername(), this.lenguaje.getMensaje().getString("register.username.placeholder"), 16, "img/svg/Person.svg", 16, 19, "#575DFB");
    actualizarTextField(this.vista.getJPasswordFieldPassword(), this.lenguaje.getMensaje().getString("register.password.placeholder"), 16, "img/svg/Candado.svg", 16, 19, "#575DFB");
    actualizarTextField(this.vista.getJPasswordFieldPassword1(), this.lenguaje.getMensaje().getString("register.password.placeholder"), 16, "img/svg/Candado.svg", 16, 19, "#575DFB");
    actualizarBoton(vista.getJButtonRegistrarse(), 16);

    vista.getJButtonRegistrarse().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getJButtonBack().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getJButtonIniciarSesion().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getJButtonClose().setCursor(new Cursor(Cursor.HAND_CURSOR));
  }

  /**
   * Actualizar text field.
   *
   * @param textField   the text field
   * @param placeholder the placeholder
   * @param arc         the arc
   * @param icon        the icon
   * @param width       the width
   * @param height      the height
   * @param color       the color
   */
  public void actualizarTextField(JTextField textField, String placeholder, int arc, String icon, int width, int height, String color) {
    this.textField = new utilsTextField(textField);
    this.textField.setPlaceholder(placeholder);
    this.textField.setRounded(arc);
    this.textField.setLeadingIcon(new FlatSVGIcon(icon, width, height));
    this.textField.setOutLineColor(color);
    this.textField.setOutLineWidth(1);
    this.textField.setMargin(0, 14, 0, 0);
  }

  /**
   * Actualizar boton.
   *
   * @param boton the boton
   * @param arc   the arc
   */
  public void actualizarBoton(JButton boton, int arc) {
    boton.putClientProperty("FlatLaf.style", "arc:" + arc);
  }
}
