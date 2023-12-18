package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Views.Login;

import javax.persistence.NoResultException;
import javax.swing.*;

import org.tienda.Objects.usuario;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.tienda.Utils.utilsLenguaje;

/**
 * The type Controller login.
 */
public class controllerLogin {
  private Login login;

  private utilsLenguaje lenguaje;

  /**
   * Instantiates a new Controller login.
   *
   * @param login    El Jframe login
   * @param lenguaje Objeto utilsLenguaje para el idioma
   */
  public controllerLogin(Login login, utilsLenguaje lenguaje) {
    this.login = login;
    this.lenguaje = lenguaje;
    initEvents();
  }

  /**
   * Init events.
   */
  public void initEvents() {
    // ! Eventos Presionar teclado
    login.getJTextFieldUsername().addKeyListener(new KeyAdapter() {
      @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && e.isControlDown()) {
          login.getJTextFieldUsername().setText(null);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          login.getJPasswordFieldPassword().requestFocus();
        }
      }
    });
    login.getJPasswordFieldPassword().addKeyListener(new KeyAdapter() {
      @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && e.isControlDown()) {
          login.getJPasswordFieldPassword().setText(null);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          login.getJButtonLogin().requestFocus();
        }
      }
    });
    // ! Eventos Presionar boton
    login.getJButtonLogin().addActionListener(e -> {
      // Llevar a la vista principal
      if (validarCredenciales(login.getJTextFieldUsername().getText(), login.getJPasswordFieldPassword().getPassword())) {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.joptionpanel.true.credenciales"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.joptionpanel.false.credenciales"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
      }
    });
    login.getJButtonRegistrarse().addActionListener(e -> {
      // Llevar a la vista de registro
    });
    login.getJButtonPasswordOlvidada().addActionListener(e -> {
      // Llevar a la vista de recuperar contraseña
      if (login.getJTextFieldUsername().getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.credenciales.vacio.username"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
        return;
      }
      // Llevar a la vista de recuperar contraseña

    });
    // ! Eventos Cerrar ventana
    login.getJButtonClose().addActionListener(e -> System.exit(0));
    login.getJButtonBack().addActionListener(e -> System.exit(0));
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
    configuration.addAnnotatedClass(usuario.class);
    configuration.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");

    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();

    String encryptedPassword = BCrypt.hashpw(String.valueOf(password), BCrypt.gensalt());
    System.out.println(encryptedPassword);

    usuario usuario = session.createQuery("SELECT u FROM usuario u WHERE u.username = :username AND u.activacion = true", org.tienda.Objects.usuario.class)
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
