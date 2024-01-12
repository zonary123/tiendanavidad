package org.tienda.Views;

import org.junit.Assert;
import org.junit.Test;
import org.tienda.Model.Usuarios;

/**
 * @author Carlos Varas Alonso - 12/01/2024 0:03
 */
public class HomeTest {

  @Test
  public void testHome() {
    HomeUser home = new HomeUser(Usuarios.findbyId(1));
    Assert.assertEquals(home.getJLabelUsername().getText(),  "test");
  }
}
