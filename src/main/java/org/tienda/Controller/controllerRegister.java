package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Objects.usuario;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.Login;
import org.tienda.Views.Register;

import javax.swing.*;

public class controllerRegister {
  private static final String RegexEmail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
  private final Register register;
  private final utilsLenguaje lenguaje;

  public controllerRegister(Register register, utilsLenguaje lenguaje) {
    this.register = register;
    this.lenguaje = lenguaje;
    initEvents();
  }

  public void initEvents() {
    register.getJButtonBack().addActionListener(e -> {
      register.dispose();
      new Login().setVisible(true);
    });
    register.getJButtonIniciarSesion().addActionListener(e -> {
      register.dispose();
      new Login().setVisible(true);
    });
    register.getJButtonRegistrarse().addActionListener(e -> {
      if (comprobaciones() && registrarse()) {
        register.dispose();
        new Login().setVisible(true);
      } else {
        JOptionPane.showMessageDialog(null, "Ocurrieorn cosas");
      }
    });
    register.getJButtonClose().addActionListener(e -> register.dispose());
  }

  private boolean comprobaciones() {
    int errores = 0;
    String mensaje = "";
    if (!register.getJTextFieldEmail().getText().matches(RegexEmail)) {
      mensaje += "Email incorrecto\n";
      register.getJTextFieldEmail().putClientProperty("JComponent.outline", "warning");
      errores++;
    }

    if (register.getJTextFieldUsername().getText().isEmpty()) {
      mensaje += "Usuario vacio\n";
      register.getJTextFieldUsername().putClientProperty("JComponent.outline", "warning");
      errores++;
    }

    if (!register.getJTextFieldNombre().getText().matches("^[a-zA-Z]+$")) {
      mensaje += "Nombre incorrecto\n";
      register.getJTextFieldNombre().putClientProperty("JComponent.outline", "warning");
      errores++;
    }

    if (!register.getJTextFieldApellidos().getText().isEmpty()) {
      if (!register.getJTextFieldNombre().getText().matches("^[a-zA-Z]+$")) {
        mensaje += "Apellidos incorrectos\n";
        register.getJTextFieldApellidos().putClientProperty("JComponent.outline", "warning");
        errores++;
      }
    }

    if (register.getJPasswordFieldPassword().getText().isEmpty()) {
      mensaje += "Contraseña vacia\n";
      register.getJPasswordFieldPassword().putClientProperty("JComponent.outline", "warning");
      errores++;
    }
    if (errores > 0) {
      JOptionPane.showMessageDialog(null, mensaje);
    }
    return errores == 0 ? true : false;
  }

  private boolean registrarse() {
    Configuration configuration = new Configuration();
    configuration.configure("/hibernate/hibernate.cfg.xml");
    configuration.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");

    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();

    // Verificar si el usuario o el correo ya existen
    String username = register.getJTextFieldUsername().getText();
    String email = register.getJTextFieldEmail().getText();
    usuario existingUser = session.createQuery("SELECT u FROM usuario u WHERE u.username = :username OR u.email = :email", usuario.class)
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

    usuario usuario = new usuario();
    usuario.setEmail(register.getJTextFieldEmail().getText());
    usuario.setUsername(register.getJTextFieldUsername().getText());
    usuario.setPassword(BCrypt.hashpw(register.getJPasswordFieldPassword().getText(), BCrypt.gensalt()));
    usuario.setNombre(register.getJTextFieldNombre().getText());
    usuario.setApellidos(register.getJTextFieldApellidos().getText());
    usuario.setLenguaje("es_ES");
    usuario.setRoles("[\"user\"]");
    usuario.setActivacion(false);
    session.save(usuario);
    session.getTransaction().commit();
    session.close();
    sessionFactory.close();
    return true;
  }
}
