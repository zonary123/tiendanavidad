/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tienda.Utils;


import org.tienda.Objects.Usuarios;

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
  private final static String FROMEMAIL = "carlos.varalo@educa.jcyl.es";
  private final static String PASSWORD = "tontodel123";
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
  public static void sendEmail(Session session, String toEmail, String subject, String body) throws MessagingException {
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
      System.out.println("Message is ready");
      Transport.send(msg);
      System.out.println("Email sent successfully");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Configura el email
   *
   * @param u el usuario
   * @throws MessagingException el error de mensajeria
   * @throws IOException        el error de entrada y salida
   */
  public static void confMail(Usuarios u) throws MessagingException, IOException {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.office365.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    Authenticator auth = new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(FROMEMAIL, PASSWORD);
      }
    };
    System.out.println(u.toString());
    javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, auth);
    if (u.getEmail() != null && !u.getEmail().isEmpty()) {
      sendEmail(session, u.getEmail(), "Tienda Navidad", emailCode(String.valueOf(u.getCodigo())));
    } else {
      System.out.println("Error: La dirección de correo electrónico del destinatario es nula o vacía.");
    }
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
