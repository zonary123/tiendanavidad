/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tienda.Utils;


import org.tienda.Model.Usuarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Carlos Varas Alonso
 */
public class EmailUtil {
  private final static String FROMEMAIL = "carlosvarasalonso.clases@gmail.com";
  private final static String PASSWORD = "kaptgyvimqwszdva";
  public final static int OPCION_ENVIAR_CODIGO = 1;
  public final static int OPCION_CAMBIO_PASSWORD = 2;
  public final static int OPCION_INICIO_SESION = 3;
  private final static utilsLenguaje lenguaje;


  static {
    try {
      lenguaje = new utilsLenguaje();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Envia un email
   *
   * @param session la sesion
   * @param toEmail el email del destinatario
   * @param subject el asunto
   * @param body    el cuerpo del mensaje
   * @throws MessagingException el error de mensajeria
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
   * @param u el usuario
   * @throws MessagingException el error de mensajeria
   * @throws IOException        el error de entrada y salida
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

  private static boolean opciones(int opcion, Usuarios u, Session session) throws IOException {
    switch (opcion) {
      case OPCION_ENVIAR_CODIGO:
        return sendEmail(session, u.getEmail(), "tienda navidad", emailCode(u.getCodigo()));
      case OPCION_CAMBIO_PASSWORD:
        return sendEmail(session, u.getEmail(), "tienda navidad", "Cambio de contraseña en tienda navidad");
      case OPCION_INICIO_SESION:
        return sendEmail(session, u.getEmail(), "tienda navidad", "Inicio de sesión en tienda navidad");
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
   * @return el cuerpo del email
   * @throws IOException el error de entrada y salida
   */
  public static String emailCode(String codigo) throws IOException {
    StringBuilder body = new StringBuilder();
    String linea;
    BufferedReader bw = new BufferedReader(new FileReader("src\\main\\resources\\email\\Codigo.html"));

    while ((linea = bw.readLine()) != null) {

      if (linea.contains("{{code}}")) {
        body.append(linea.replace("{{code}}", codigo));
      } else if (linea.contains("{{msg}}")) {
        body.append(linea.replace("{{msg}}", "Enviado por Tienda Navidad"));
      } else if (linea.contains("{{title}}")) {
        body.append(linea.replace("{{title}}", "Código de recuperación"));
      } else {
        body.append(linea);
      }
    }
    return body.toString();
  }


}
