package org.tienda.test.model;

import org.junit.Assert;
import org.junit.Test;
import org.tienda.model.Productos;

/**
 * @author Carlos Varas Alonso - 11/01/2024 4:23
 */
public class ProductosTest {


  public void findAll() {
    System.out.println("Test findAll");
    Assert.assertNotNull(Productos.findAll());
  }


  public void findById() {
    System.out.println("Test findById");
    Assert.assertNotNull(Productos.findbyId(1));
  }


  public void findByCategoria() {
    System.out.println("Test findByCategoria");
    Assert.assertNotNull(Productos.findByCategoria("test"));
  }
}