package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Utils.EmailUtil;
import org.tienda.Objects.Usuarios;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.ForgotPasswordCode;
import org.tienda.Views.ForgotPasswordEmail;
import org.tienda.Views.Login;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

/**
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordEmail {
  private static ForgotPasswordEmail vista;
  private static utilsLenguaje lenguaje;
  private static Usuarios u;

  public cForgotPasswordEmail(ForgotPasswordEmail vista) throws IOException {
    cForgotPasswordEmail.vista = vista;
    lenguaje = new utilsLenguaje();
    initEvents();
  }

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
    vista.getJButtonConfirmar().addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
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
      }
    });
  }

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

  public static String generarCodigo(int longitud) {
    // Caracteres permitidos en el código
    String caracteres = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Crear un objeto Random
    Random random = new Random();

    // Usar StringBuilder para construir el código
    StringBuilder codigoBuilder = new StringBuilder(longitud);

    // Generar el código aleatorio
    for (int i = 0; i < longitud; i++) {
      int indiceCaracter = random.nextInt(caracteres.length());
      char caracter = caracteres.charAt(indiceCaracter);
      codigoBuilder.append(caracter);
    }

    // Convertir StringBuilder a String y devolver el código
    return codigoBuilder.toString();
  }
}
