package org.tienda.model;

import org.junit.Assert;
import org.junit.Test;
import org.tienda.Model.Usuarios;

/**
 * Pruebas unitarias para la clase Usuarios.
 * Se centra en probar los métodos relacionados con la búsqueda de usuarios.
 *
 * @author Carlos Varas Alonso - 10/01/2024 21:49
 */
public class UsuariosTest {

  private static final int TEST_USER_ID = 1;
  private static final String TEST_EMAIL = "carlos.varalo@educa.jcyl.es";
  private static final String TEST_USERNAME = "test";
  private static final String TEST_NAME = "test";
  private static final String TEST_LAST_NAME = "test";

  /**
   * Prueba el método findAll para asegurar que devuelve una lista no nula de usuarios.
   */
  @Test
  public void findAll() {
    System.out.println("Test findAll");
    // Ejecuta la prueba
    Assert.assertNotNull(Usuarios.findAll());
  }

  /**
   * Prueba el método findById para asegurar que devuelve un usuario con los atributos esperados.
   */
  @Test
  public void findById() {
    System.out.println("Test findById");
    // Ejecuta la prueba
    Assert.assertNotNull(Usuarios.findbyId(TEST_USER_ID));
    Assert.assertEquals(Usuarios.findbyId(TEST_USER_ID).getEmail(), TEST_EMAIL);
    Assert.assertEquals(Usuarios.findbyId(TEST_USER_ID).getNombre(), TEST_NAME);
    Assert.assertEquals(Usuarios.findbyId(TEST_USER_ID).getApellidos(), "test");
  }

  /**
   * Prueba el método findByEmail para asegurar que devuelve un usuario con los atributos esperados.
   */
  @Test
  public void findByEmail() {
    System.out.println("Test findByEmail");
    // Ejecuta la prueba
    Assert.assertNotNull(Usuarios.findByEmail(TEST_EMAIL));
    Assert.assertEquals(Usuarios.findByEmail(TEST_EMAIL).getEmail(), TEST_EMAIL);
    Assert.assertEquals(Usuarios.findByEmail(TEST_EMAIL).getNombre(), TEST_NAME);
    Assert.assertEquals(Usuarios.findByEmail(TEST_EMAIL).getApellidos(), "test");
  }

  /**
   * Prueba el método findByUsername para asegurar que devuelve un usuario con los atributos esperados.
   */
  @Test
  public void findByUsername() {
    System.out.println("Test findByUsername");
    // Ejecuta la prueba
    Assert.assertNotNull("No se encontro al usuario", Usuarios.findByUsername(TEST_USERNAME));
    Assert.assertEquals(Usuarios.findByUsername(TEST_USERNAME).getEmail(), TEST_EMAIL);
    Assert.assertEquals(Usuarios.findByUsername(TEST_USERNAME).getNombre(), TEST_NAME);
    Assert.assertEquals(Usuarios.findByUsername(TEST_USERNAME).getApellidos(), "test");
  }

  @Test
  public void save() {
    System.out.println("Test save");
    Usuarios user = new Usuarios(
      "nombreUsuario",
      "123456",
      "Nombre",
      "Apellidos",
      "correo@example.com",
      "es_ES",
      "[\"admin\"]",
      true
    );
    Assert.assertTrue(Usuarios.save(user));
    Assert.assertFalse(Usuarios.save(new Usuarios()));
    Usuarios.delete(user);
  }

  @Test
  public void checkPassword() {
    System.out.println("Test checkPassword");
    Usuarios user = new Usuarios(
      "nombreUsuario",
      "123456",
      "Nombre",
      "Apellidos",
      "correo@example.com",
      "es_ES",
      "[\"admin\"]",
      true
    );
    Usuarios.save(user);
    Assert.assertTrue(Usuarios.checkPassword("123456", user));
    Assert.assertFalse(Usuarios.checkPassword("1234567", user));
    Usuarios.delete(user);
  }
}