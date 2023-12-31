package org.tienda.Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.persistence.NoResultException;
import javax.swing.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.ForgotPasswordEmail;
import org.tienda.Views.Login;
import org.tienda.Views.Register;
import org.tienda.Objects.Usuarios;

/**
 * @author Carlos Varas Alonso
 */
public class controllerLogin {

  private final Login login;
  private final utilsLenguaje lenguaje;

  /**
   * Instantiates a new Controller login.
   *
   * @param login    El Jframe login
   * @param lenguaje Objeto utilsLenguaje para el idioma
   */
  public controllerLogin(Login login, utilsLenguaje lenguaje) {
    this.login = login;
    this.lenguaje = lenguaje;
  }

  /**
   * Init events.
   */
  public void initEvents() throws NoResultException {
    // ! Eventos Presionar teclado
    login.getJTextFieldUsername().addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && e.isControlDown()) {
          login.getJTextFieldUsername().setText(null);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          login.getJPasswordFieldPassword().requestFocus();
        }
      }
    });
    login.getJPasswordFieldPassword().addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
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
      login.initTextFields();
      // Llevar a la vista principal
      if (login.getJTextFieldUsername().getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.void.username"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
        login.getJTextFieldUsername().putClientProperty("JComponent.outline", "warning");
      }
      if (String.valueOf(login.getJPasswordFieldPassword().getPassword()).isEmpty()) {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.void.password"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
        login.getJPasswordFieldPassword().putClientProperty("JComponent.outline", "warning");
        return;
      }
      if (validarCredenciales(login.getJTextFieldUsername().getText(), login.getJPasswordFieldPassword().getPassword()) == null) {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.joptionpanel.notexist"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
      } else if (Boolean.TRUE.equals(validarCredenciales(login.getJTextFieldUsername().getText(), login.getJPasswordFieldPassword().getPassword()))) {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.joptionpanel.true.credenciales"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.joptionpanel.false.credenciales"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
      }
    });
    login.getJButtonRegistrarse().addActionListener(e -> {
      login.removeAll();
      login.dispose();
      new Register().setVisible(true);
    });
    login.getJButtonPasswordOlvidada().addActionListener(e -> {
      // Llevar a la vista de recuperar contraseña
      login.removeAll();
      login.dispose();
      new ForgotPasswordEmail().setVisible(true);

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
  private Boolean validarCredenciales(String username, char[] password) throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    Usuarios usuario;

    try {
      String query = "SELECT u FROM Usuarios u WHERE u.username = :username OR u.email = :username AND u.activacion = true";
      usuario = session.createQuery(query, Usuarios.class)
        .setParameter("username", username)
        .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
    System.out.println(usuario);
    if (!BCrypt.checkpw(String.valueOf(password), usuario.getPassword())) {
      return false;

    }

    session.getTransaction().commit();
    session.close();
    sessionFactory.close();
    return true;
  }

}
