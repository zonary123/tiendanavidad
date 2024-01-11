package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.ForgotPasswordPassword;
import org.tienda.Views.Login;

import javax.swing.*;
import java.io.IOException;

/**
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordPassword {
  private ForgotPasswordPassword vista;
  private utilsLenguaje lenguaje;

  /**
   * Constructor de la clase
   *
   * @param vista Vista de la clase
   * @throws IOException Error de lectura de archivo
   */
  public cForgotPasswordPassword(ForgotPasswordPassword vista) throws IOException {
    this.vista = vista;
    this.lenguaje = new utilsLenguaje();
    initEvents();
  }

  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
    vista.getJButtonClose().addActionListener(
      e -> {
        vista.dispose();
      });
    vista.getJButtonBack().addActionListener(
      e -> {
        vista.dispose();
        new Login(null).setVisible(true);
      });
    vista.getJButtonConfirmar().addActionListener(
      e -> {
        if (vista.getJPasswordFieldPassword().getPassword().length > 5) {
          changePassword();
          vista.dispose();
          new Login(null).setVisible(true);
        } else {
          if (vista.getJPasswordFieldPassword().getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "La contrasenya no puede estar vacia", "Error", JOptionPane.INFORMATION_MESSAGE);
          } else {
            JOptionPane.showMessageDialog(null, "La contrasenya debe tener mas de 5 caracteres", "Error", JOptionPane.INFORMATION_MESSAGE);
          }
        }
      });
  }

  /**
   * Cambia la contraseña del usuario
   */
  public void changePassword() {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    System.out.println(vista.getJPasswordFieldPassword().getPassword());
    System.out.println(vista.getUsuario().toString());
    session.createQuery("UPDATE Usuarios SET password = :password WHERE email = :email")
      .setParameter("password", BCrypt.hashpw(String.valueOf(vista.getJPasswordFieldPassword().getPassword()), BCrypt.gensalt()))
      .setParameter("email", vista.getUsuario().getEmail())
      .executeUpdate();
    session.getTransaction().commit();
  }
}
