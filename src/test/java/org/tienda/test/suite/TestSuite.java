package org.tienda.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.tienda.test.Views.HomeTest;
import org.tienda.test.lang.langTest;
import org.tienda.test.model.ProductosTest;
import org.tienda.test.model.UsuariosTest;
import org.tienda.test.utils.EmailTest;

/**
 * @author Carlos Varas Alonso - 10/01/2024 22:22
 */
@RunWith(org.junit.runners.Suite.class)
@Suite.SuiteClasses(
  {UsuariosTest.class,
    ProductosTest.class,
    langTest.class,
    HomeTest.class,
    EmailTest.class
  }
)
public class TestSuite {
}