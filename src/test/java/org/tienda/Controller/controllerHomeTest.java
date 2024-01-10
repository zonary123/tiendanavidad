package org.tienda.Controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Carlos Varas Alonso - 10/01/2024 10:15
 */
public class controllerHomeTest {
  @Before
  public void setUp() {
  }

  @Test
  public void getProductosTest() {
    controllerHome controllerHome = new controllerHome();
    System.out.println("--------------------------------------------------");
    Assert.assertNotNull(controllerHome.getProductos());
    System.out.println("--------------------------------------------------");
  }

  @Test
  public void getProductosCategoriasTest() {
    controllerHome controllerHome = new controllerHome();
    System.out.println("--------------------------------------------------");
    Assert.assertNotNull(controllerHome.getProductosCategories("portatiles"));
    Assert.assertNotNull(controllerHome.getProductosCategories("gaming"));
    Assert.assertNotNull(controllerHome.getProductosCategories("componentes"));
    Assert.assertNotNull(controllerHome.getProductosCategories("monitores"));
    Assert.assertNotNull(controllerHome.getProductosCategories("smartphones"));
    Assert.assertNotNull(controllerHome.getProductosCategories("televisores"));
    Assert.assertNotNull(controllerHome.getProductosCategories("hogar"));
    System.out.println("--------------------------------------------------");
  }
}
