package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Views.Login;

import javax.persistence.NoResultException;
import javax.swing.*;

import org.tienda.Objects.usuarios;

import java.util.Locale;
import java.util.ResourceBundle;

public class controllerLogin {
  private Login login;
  private String lang;
  private ResourceBundle mensaje;

  /**
   * @param login El Jframe login
   * @param lang  El lenguaje en el que se quiere mostrar ej: "es_ES", por defecto es el español
   */
  public controllerLogin(Login login, String lang) {
    this.login = login;
    this.lang = lang;
    Locale local = new Locale(this.lang.split("_")[0]);
    this.mensaje = ResourceBundle.getBundle("lang/" + this.lang, local);
    initEvents();
  }

  /**
   *
   */
  public void initEvents() {
    login.getJButtonLogin().addActionListener(e -> {
      if (validarCredenciales(login.getJTextFieldUsername().getText(), login.getJPasswordFieldPassword().getPassword())) {
        JOptionPane.showMessageDialog(null, mensaje.getString("login.joptionpanel.true.credenciales"), mensaje.getString("login.joptionpanel.title"), JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null, mensaje.getString("login.joptionpanel.false.credenciales"), mensaje.getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
      }
    });
    login.getJButtonClose().addActionListener(e -> System.exit(0));
    login.getJButtonBack().addActionListener(e -> System.exit(0));
    login.getJButtonRegistrarse().addActionListener(e -> {
      // Llevar a la vista de registro
    });
    login.getJButtonPasswordOlvidada().addActionListener(e -> {
      // Llevar a la vista de recuperar contraseña
      if (login.getJTextFieldUsername().getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, mensaje.getString("login.credenciales.vacio.username"), mensaje.getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
        return;
      }
      // Llevar a la vista de recuperar contraseña

    });
  }

  /**
   * @param username Nombre del usuario
   * @param password Contraseña del usuario
   * @return true si las credenciales son correctas
   */
  private boolean validarCredenciales(String username, char[] password) throws NoResultException {
    System.out.println(username + " " + String.valueOf(password));

    Configuration configuration = new Configuration();
    configuration.configure("/hibernate/hibernate.cfg.xml");
    configuration.addAnnotatedClass(usuarios.class);
    configuration.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");

    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();

    String encryptedPassword = BCrypt.hashpw(String.valueOf(password), BCrypt.gensalt());
    System.out.println(encryptedPassword);

    usuarios usuario = session.createQuery("SELECT u FROM usuarios u WHERE u.username = :username AND u.activacion = true", usuarios.class)
      .setParameter("username", username)
      .getSingleResult();

    if (!BCrypt.checkpw(String.valueOf(password), usuario.getPassword()))
      return false;

    System.out.println("Usuario encontrado: " + usuario);

    session.getTransaction().commit();
    session.close();
    sessionFactory.close();
    return true;
  }

}
