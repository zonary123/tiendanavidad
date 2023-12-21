package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.tienda.Utils.EmailUtil;
import org.tienda.Objects.Usuarios;

import javax.mail.MessagingException;
import java.io.*;
import java.util.Random;

/**
 * @author Carlos Varas Alonso
 */
public class cForgotPasswordMail {
  private static String toEmail = "carlosvarasalonso10@gmail.com";

  public static void main(String[] args) throws MessagingException, IOException {
    try {
      EmailUtil.confMail(setCode(), toEmail);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public static Usuarios setCode() {
    Configuration configuration = new Configuration();
    configuration.configure("hibernate.cfg.xml");
    configuration.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");

    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();

    Usuarios u = new Usuarios();
    u.setCodigo(generarCodigo(6));

    // Usar el correo
    session.createQuery("update Usuarios set codigo = :codigo where id = :id")
      .setString("codigo", u.getCodigo())
      .setInteger("id", 11)
      .executeUpdate();
    session.getTransaction().commit();
    sessionFactory.close();
    return u;
  }

  public static String generarCodigo(int longitud) {
    // Caracteres permitidos en el código
    String caracteres = "0123456789";

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
