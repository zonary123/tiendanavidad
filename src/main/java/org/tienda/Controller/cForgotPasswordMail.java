package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.tienda.Utils.EmailUtil;
import org.tienda.Objects.Usuarios;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Views.ForgotPasswordMail;
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
public class cForgotPasswordMail {
  private static ForgotPasswordMail vista;
  private static utilsLenguaje lenguaje;

  public cForgotPasswordMail(ForgotPasswordMail vista) throws IOException {
    cForgotPasswordMail.vista = vista;
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
            JOptionPane.showMessageDialog(null, "Se ha enviado el correo a " + vista.getJTextFieldEmail().getText(),
              "Codigo", JOptionPane.INFORMATION_MESSAGE);
            vista.dispose();
            new Login(null).setVisible(true);
          } else {
            JOptionPane.showMessageDialog(null, "No se ha podido enviar el correo a " + vista.getJTextFieldEmail().getText(),
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

    Usuarios u = new Usuarios();
    u.setEmail(vista.getJTextFieldEmail().getText());
    u.setCodigo(generarCodigo(6));

    // Usar el correo
    boolean update = session.createQuery("update Usuarios set codigo = :codigo where email = :email")
      .setParameter("codigo", u.getCodigo())
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
