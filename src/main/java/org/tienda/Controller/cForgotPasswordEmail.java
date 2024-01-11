package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Utils.EmailUtil;
import org.tienda.Model.Usuarios;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.ForgotPasswordCode;
import org.tienda.Views.ForgotPasswordEmail;
import org.tienda.Views.Login;

import javax.mail.MessagingException;
import javax.swing.*;
import java.io.*;
import java.util.Random;

/**
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordEmail {
  private static ForgotPasswordEmail vista;
  private static utilsLenguaje lenguaje;
  private static Usuarios u;

  /**
   * Constructor de la clase
   *
   * @param vista Vista de la clase
   * @throws IOException Error de lectura de archivo
   */
  public cForgotPasswordEmail(ForgotPasswordEmail vista) throws IOException {
    cForgotPasswordEmail.vista = vista;
    lenguaje = new utilsLenguaje();
    initEvents();
  }

  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
    vista.getJButtonClose().addActionListener(e -> vista.dispose());
    vista.getJButtonBack().addActionListener(
      e -> {
        vista.dispose();
        new Login(null).setVisible(true);
      });
    vista.getJButtonConfirmar().addActionListener(e -> {
      try {
        if (sendCode()) {
          JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("forgot.email.send") + " " + vista.getJTextFieldEmail().getText(),
            "Codigo", JOptionPane.INFORMATION_MESSAGE);
          vista.dispose();
          new ForgotPasswordCode(u).setVisible(true);
        } else {
          JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("forgot.email.notexist"),
            "Error", JOptionPane.INFORMATION_MESSAGE);
        }

      } catch (MessagingException | IOException ex) {
        throw new RuntimeException(ex);
      }
    });
  }

  /**
   * Genera un codigo aleatorio de 6 caracteres y lo envia al correo
   *
   * @return boolean
   * @throws IOException        Error de lectura de archivo
   * @throws MessagingException Error de envio de correo
   */
  public static boolean sendCode() throws MessagingException, IOException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    assert sessionFactory != null;
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();

    u = new Usuarios();
    u.setEmail(vista.getJTextFieldEmail().getText());
    u.setCodigo(generarCodigo(6));

    // Usar el correo
    boolean update = session.createQuery("update Usuarios set codigo = :codigo where email = :email")
      .setParameter("codigo", BCrypt.hashpw(u.getCodigo(), BCrypt.gensalt()))
      .setParameter("email", u.getEmail())
      .executeUpdate() > 0;
    session.getTransaction().commit();
    sessionFactory.close();
    if (update) {
      EmailUtil.confMail(u);
    } else {
      return false;
    }

    return true;
  }

  /**
   * Genera un codigo aleatorio de la longitud especificada
   *
   * @param longitud Longitud del codigo
   * @return String
   */
  public static String generarCodigo(int longitud) {
    String caracteres = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    Random random = new Random();

    StringBuilder codigoBuilder = new StringBuilder(longitud);

    for (int i = 0; i < longitud; i++) {
      int indiceCaracter = random.nextInt(caracteres.length());
      char caracter = caracteres.charAt(indiceCaracter);
      codigoBuilder.append(caracter);
    }

    return codigoBuilder.toString();
  }
}
