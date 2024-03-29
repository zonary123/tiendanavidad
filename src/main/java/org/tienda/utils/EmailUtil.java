/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tienda.utils;


import org.tienda.model.Usuarios;

import java.io.*;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The type Email util.
 *
 * @author Carlos Varas Alonso
 */
public class EmailUtil {
  private final static String FROMEMAIL = "carlosvarasalonso.clases@gmail.com";
  private final static String PASSWORD = "kaptgyvimqwszdva";
  public final static int OPCION_ENVIAR_CODIGO = 1;
  public final static int OPCION_BIENVENIDA = 2;

  /**
   * Envia un email
   *
   * @param session la sesion
   * @param toEmail el email del destinatario
   * @param subject el asunto
   * @param body    el cuerpo del mensaje
   *
   * @return the boolean
   */
  public static boolean sendEmail(Session session, String toEmail, String subject, String body) {
    try {
      MimeMessage msg = new MimeMessage(session);
      msg.addHeader("Content-type", "text/HTML; charset-UTF-8");
      msg.addHeader("format", "flowed");
      msg.addHeader("Content-Transfer-Encoding", "8bit");
      msg.setFrom(new InternetAddress(FROMEMAIL, "NoReply-JD"));
      msg.setSubject(subject, "UTF-8");
      msg.setSentDate(new Date());
      msg.setContent(body, "text/html; charset=UTF-8");  // Corrige esta línea
      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
      Transport.send(msg);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Configura el email
   *
   * @param u      el usuario
   * @param opcion the opcion
   *
   * @return the boolean
   *
   * @throws IOException el error de entrada y salida
   */
  public static boolean confMail(Usuarios u, int opcion) throws IOException {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.user", FROMEMAIL);
    props.put("mail.smtp.ssl.enable", "TLSv1.2");

    Authenticator auth = new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(FROMEMAIL, PASSWORD);
      }
    };

    javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, auth);
    if (u.getEmail() != null && !u.getEmail().isEmpty()) {
      return opciones(opcion, u, session);
    } else {
      System.out.println("Error: La dirección de correo electrónico del destinatario es nula o vacía.");
      return false;
    }
  }

  /**
   * Opciones boolean.
   *
   * @param opcion  La opcion a elegir
   * @param u       el usuario al que se le envia el email
   * @param session la sesion de email
   *
   * @return the boolean
   *
   * @throws IOException el error de entrada y salida
   */
  private static boolean opciones(int opcion, Usuarios u, Session session) throws IOException {
    switch (opcion) {
      case OPCION_ENVIAR_CODIGO:
        return sendEmail(session, u.getEmail(), "tienda navidad", emailCode("email/Codigo.html", u.getCodigo()));
      case OPCION_BIENVENIDA:
        return sendEmail(session, u.getEmail(), "tienda navidad", leerHtml("email/Bienvenida.html"));
      default:
        System.out.println("Error: Opción no válida.");
        break;
    }
    return false;
  }

  /**
   * Crea el cuerpo del email
   *
   * @param codigo el codigo
   *
   * @return el cuerpo del email
   *
   * @throws IOException el error de entrada y salida
   */
  public static String emailCode(String path, String codigo) throws IOException {
    StringBuilder body = new StringBuilder();
    String linea;
    InputStream inputStream = utilsLenguaje.class.getClassLoader().getResourceAsStream(path);
    BufferedReader bw = new BufferedReader(new InputStreamReader(inputStream));

    while ((linea = bw.readLine()) != null) {

      if (linea.contains("{{code}}")) {
        body.append(linea.replace("{{code}}", codigo));
      } else if (linea.contains("{{msg}}")) {
        body.append(linea.replace("{{msg}}", "Enviado por Tienda Navidad"));
      } else if (linea.contains("{{title}}")) {
        body.append(linea.replace("{{title}}", "Codigo de recuperación"));
      } else {
        body.append(linea);
      }
    }
    bw.close();
    inputStream.close();
    return body.toString();
  }

  /**
   * Lee el html
   *
   * @param path el path del html a leer
   *
   * @return el html
   *
   * @throws IOException el error de entrada y salida
   */
  public static String leerHtml(String path) throws IOException {
    StringBuilder body = new StringBuilder();
    String linea;
    InputStream inputStream = utilsLenguaje.class.getClassLoader().getResourceAsStream(path);
    BufferedReader bw = new BufferedReader(new InputStreamReader(inputStream));

    while ((linea = bw.readLine()) != null) {
      body.append(linea);
    }
    bw.close();
    inputStream.close();
    return body.toString();
  }
}
