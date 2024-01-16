package org.tienda.test.Views;

import org.junit.Assert;
import org.junit.Test;
import org.tienda.Model.Usuarios;
import org.tienda.Views.HomeUser;

/**
 * The type Home test.
 *
 * @author Carlos Varas Alonso - 12/01/2024 0:03
 */
public class HomeTest {

  /**
   * Test home.
   */
  @Test
  public void testHome() {
    HomeUser home = new HomeUser(Usuarios.findbyId(1));
  }
}