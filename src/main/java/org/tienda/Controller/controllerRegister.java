package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;

import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.Login;
import org.tienda.Views.Register;
import org.tienda.Objects.Usuarios;

import javax.swing.*;

/**
 * @author Carlos Varas Alonso
 */
public class controllerRegister {
  private static final String RegexEmail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
  private final Register register;
  private final utilsLenguaje lenguaje;
  private Usuarios usuario = null;

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
  }

  /**
   * Inicializacion de eventos de la vista
   */
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

    if (!register.getJTextFieldEmail().getText().matches(RegexEmail)) {
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

    if (!register.getJTextFieldApellidos().getText().isEmpty()) {
      if (!register.getJTextFieldApellidos().getText().matches("^[a-zA-Z]+$")) {
        mensaje += lenguaje.getMensaje().getString("regex.lastname") + "\n";
        register.getJTextFieldApellidos().putClientProperty("JComponent.outline", "warning");
        errores++;
      }
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
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();

    // Verificar si el usuario o el correo ya existen
    String username = register.getJTextFieldUsername().getText();
    String email = register.getJTextFieldEmail().getText();
    Usuarios existingUser = session.createQuery("SELECT u FROM Usuarios u WHERE u.username = :username OR u.email = :email", Usuarios.class)
      .setParameter("username", username)
      .setParameter("email", email)
      .uniqueResult();

    if (existingUser != null) {
      if (existingUser.getEmail().equals(email)) {
        JOptionPane.showMessageDialog(null, "El correo ya existe");
        return false;
      }
      if (existingUser.getUsername().equals(username)) {
        JOptionPane.showMessageDialog(null, "El usuario ya existe");
        return false;
      }
    }

    this.usuario = new Usuarios();
    usuario.setEmail(register.getJTextFieldEmail().getText());
    usuario.setUsername(register.getJTextFieldUsername().getText());
    usuario.setPassword(BCrypt.hashpw(String.valueOf(register.getJPasswordFieldPassword().getPassword()), BCrypt.gensalt()));
    usuario.setNombre(register.getJTextFieldNombre().getText());
    usuario.setApellidos(register.getJTextFieldApellidos().getText());
    usuario.setLenguaje("es_ES");
    usuario.setRoles("[\"user\"]");
    usuario.setActivacion(true);
    session.save(usuario);
    session.getTransaction().commit();
    session.close();
    sessionFactory.close();
    return true;
  }
}
