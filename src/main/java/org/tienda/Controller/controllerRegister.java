package org.tienda.Controller;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.bytebuddy.asm.Advice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import org.tienda.Interfaces.controllers;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Utils.utilsTextField;
import org.tienda.Views.Login;
import org.tienda.Views.Register;
import org.tienda.Model.Usuarios;

import javax.swing.*;
import java.util.Locale;

/**
 * @author Carlos Varas Alonso
 */
public class controllerRegister implements controllers {
  private static final String REGEX_EMAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
  private final Register register;
  private final utilsLenguaje lenguaje;
  private Usuarios usuario = null;
  private utilsTextField textField;

  /**
   * Instantiates a new Controller login.
   *
   * @param register El Jframe login
   * @param lenguaje Objeto utilsLenguaje para el idioma
   */
  public controllerRegister(Register register, utilsLenguaje lenguaje) {
    this.register = register;
    this.lenguaje = lenguaje;
    initEvents();
    actualizarEstilos();
  }

  /**
   * Inicializacion de eventos de la vista
   */
  @Override
  public void initEvents() {
    register.getJButtonBack().addActionListener(e -> {
      register.dispose();
      new Login(usuario == null ? null : usuario.getUsername()).setVisible(true);
    });
    register.getJButtonIniciarSesion().addActionListener(e -> {
      register.dispose();
      new Login(usuario == null ? null : usuario.getUsername()).setVisible(true);
    });
    register.getJButtonRegistrarse().addActionListener(e -> {
      if (comprobaciones() && registrarse()) {
        register.dispose();
        new Login(usuario == null ? null : usuario.getUsername()).setVisible(true);
      }
    });
    register.getJButtonClose().addActionListener(e -> register.dispose());
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

    if (register.getJTextFieldEmail().getText().isEmpty()) {
      mensaje += lenguaje.getMensaje().getString("void.email") + "\n";
      register.getJTextFieldEmail().putClientProperty("JComponent.outline", "warning");
      errores++;
    }

    if (!register.getJTextFieldEmail().getText().matches(REGEX_EMAIL)) {
      mensaje += lenguaje.getMensaje().getString("regex.email") + "\n";
      register.getJTextFieldEmail().putClientProperty("JComponent.outline", "warning");
      errores++;
    }

    if (register.getJTextFieldUsername().getText().isEmpty()) {
      mensaje += lenguaje.getMensaje().getString("void.username") + "\n";
      register.getJTextFieldUsername().putClientProperty("JComponent.outline", "warning");
      errores++;
    }

    if (register.getJTextFieldNombre().getText().isEmpty()) {
      mensaje += lenguaje.getMensaje().getString("void.name") + "\n";
      register.getJTextFieldNombre().putClientProperty("JComponent.outline", "warning");
      errores++;
    }

    if (!register.getJTextFieldNombre().getText().matches("^[a-zA-Z]+$")) {
      mensaje += lenguaje.getMensaje().getString("regex.name") + "\n";
      register.getJTextFieldNombre().putClientProperty("JComponent.outline", "warning");
      errores++;
    }

    String apellidosText = register.getJTextFieldApellidos().getText();

    if (!apellidosText.isEmpty() && !apellidosText.matches("^[a-zA-Z\\s]+$")) {
      mensaje += lenguaje.getMensaje().getString("regex.lastname") + "\n";
      register.getJTextFieldApellidos().putClientProperty("JComponent.outline", "warning");
      errores++;
    }

    if (String.valueOf(register.getJPasswordFieldPassword().getPassword()).isEmpty()) {
      mensaje += lenguaje.getMensaje().getString("void.password") + "\n";
      register.getJPasswordFieldPassword().putClientProperty("JComponent.outline", "warning");
      errores++;
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
    String username = register.getJTextFieldUsername().getText();
    String email = register.getJTextFieldEmail().getText();
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
    usuario.setEmail(register.getJTextFieldEmail().getText());
    usuario.setUsername(register.getJTextFieldUsername().getText());
    usuario.setPassword(String.valueOf(register.getJPasswordFieldPassword().getPassword()));
    usuario.setNombre(register.getJTextFieldNombre().getText());
    usuario.setApellidos(register.getJTextFieldApellidos().getText());
    usuario.setLenguaje(Locale.getDefault().toString());
    usuario.setRoles("[\"user\"]");
    usuario.setActivacion(true);
    Usuarios.save(usuario);
    return true;
  }

  @Override
  public void actualizarLenguaje() {
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

  /**
   * Actualiza los estilos de la vista
   */
  @Override
  public void actualizarEstilos() {
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
    this.textField.setMargin(0, 14, 0, 0);
  }

  public void actualizarBoton(JButton boton, int arc) {
    boton.putClientProperty("FlatLaf.style", "arc:" + arc);
  }
}
