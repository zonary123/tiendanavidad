package org.tienda.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.tienda.test.lang.en_US_Test;
import org.tienda.test.lang.es_ES_Test;
import org.tienda.test.model.HistorialUsuarioTest;
import org.tienda.test.model.ProductosTest;
import org.tienda.test.utils.EmailTest;

/**
 * @author Carlos Varas Alonso - 10/01/2024 22:22
 */
@RunWith(org.junit.runners.Suite.class)
@Suite.SuiteClasses(
  {
    EmailTest.class,
    es_ES_Test.class,
    en_US_Test.class,
    ProductosTest.class,
    HistorialUsuarioTest.class
  }
)
public class TestSuite {
}
