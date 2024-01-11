package org.tienda.model;

import org.junit.Assert;
import org.junit.Test;
import org.tienda.Model.Productos;

/**
 * @author Carlos Varas Alonso - 11/01/2024 4:23
 */
public class ProductosTest {

  @Test
  public void findAll() {
    System.out.println("Test findAll");
    Assert.assertNotNull(Productos.findAll());
  }

  @Test
  public void findById() {
    System.out.println("Test findById");
    System.out.println(Productos.findbyId(1));
    Assert.assertNotNull(Productos.findbyId(1));
  }

  @Test
  public void findByCategoria() {
    System.out.println("Test findByCategoria");
    System.out.println(Productos.findByCategoria("mueble"));
    Assert.assertNotNull(Productos.findByCategoria("mueble"));
  }
}
