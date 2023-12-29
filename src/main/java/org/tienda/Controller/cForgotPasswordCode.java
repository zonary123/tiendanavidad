package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Objects.Usuarios;
import org.tienda.Views.ForgotPasswordCode;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.ForgotPasswordPassword;
import org.tienda.Views.Login;

import javax.persistence.NoResultException;
import javax.swing.*;
import java.io.IOException;
import java.util.Random;

/**
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordCode {
  private ForgotPasswordCode vista;
  private utilsLenguaje lenguaje;
  private int intentos = 0;
  private static final int CODE_CLEAR_TIME = 60 * 1000;


  public cForgotPasswordCode(ForgotPasswordCode vista) throws IOException {
    this.vista = vista;
    lenguaje = new utilsLenguaje();
    initEvents();
  }

  private void initEvents() {
    vista.getJButtonConfirmar().addActionListener(e -> {
      if (comprobarCodigo()) {
        intentos = 0;
        vista.setVisible(false);
        vista.dispose();
        new ForgotPasswordPassword(vista.getUsuario()).setVisible(true);
        borrarCodigo();
      } else {
        intentos++;
        if (intentos > 3) {
          new Login(null).setVisible(true);
          vista.setVisible(false);
          vista.dispose();
        } else {
          JOptionPane.showMessageDialog(null, "Intentos: " + intentos + "/3", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

      }
    });
  }

  public boolean comprobarCodigo() {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    try {
      Usuarios u = session.createQuery("SELECT u FROM Usuarios u WHERE u.email = :email", Usuarios.class)
        .setParameter("email", vista.getUsuario().getEmail())
        .getSingleResult();
      return BCrypt.checkpw(vista.getJTextFieldCodigo().getText(), u.getCodigo());
    } catch (NoResultException e) {
      return false;
    }
  }

  public void borrarCodigo() {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    session.createQuery("UPDATE Usuarios u SET u.codigo = null WHERE u.email = :email")
      .setParameter("email", vista.getUsuario().getEmail())
      .executeUpdate();
    session.getTransaction().commit();
    session.close();
  }
}
