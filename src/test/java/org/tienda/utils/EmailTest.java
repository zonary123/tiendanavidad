package org.tienda.utils;

import org.junit.Test;
import org.tienda.Model.Usuarios;
import org.tienda.Utils.EmailUtil;

import java.io.IOException;

/**
 * @author Carlos Varas Alonso - 12/01/2024 2:23
 */
public class EmailTest {
  private final static String EMAIL = "carlosvarasalonso10@gmail.com";

  @Test
  public void sendEmail() throws IOException {
    Usuarios u = Usuarios.findByEmail(EMAIL);
    u.setCodigo("123456");
    EmailUtil.confMail(u, EmailUtil.OPCION_ENVIAR_CODIGO);
    EmailUtil.confMail(u, EmailUtil.OPCION_CAMBIO_PASSWORD);
    EmailUtil.confMail(u, EmailUtil.OPCION_INICIO_SESION);
  }
}
