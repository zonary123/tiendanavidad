package org.tienda.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.tienda.test.Views.HomeTest;

/**
 * @author Carlos Varas Alonso - 10/01/2024 22:22
 */
@RunWith(org.junit.runners.Suite.class)
@Suite.SuiteClasses(
  {org.tienda.model.UsuariosTest.class,
    org.tienda.model.ProductosTest.class,
    org.tienda.lang.langTest.class,
    HomeTest.class,
    org.tienda.utils.EmailTest.class,
  }
)
public class TestSuite {
}