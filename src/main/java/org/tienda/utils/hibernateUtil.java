package org.tienda.utils;

import lombok.Data;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The type Hibernate util.
 *
 * @author Carlos Varas Alonso
 */
@Data
public class hibernateUtil {
  private static SessionFactory sessionFactory = buildSessionFactory();

  /**
   * Inicializacion de la conexion con la base de datos
   *
   * @return SessionFactory session factory
   */
  public static SessionFactory buildSessionFactory() {
    Configuration configuration = new Configuration();
    configuration.configure(
      "hibernate.cfg.xml");
    return configuration.buildSessionFactory();
  }
}