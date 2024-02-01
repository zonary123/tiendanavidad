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
  private static final SessionFactory sessionFactory = buildSessionFactory("hibernate.cfg.xml");

  private hibernateUtil() {
    // Private constructor to enforce Singleton pattern
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static SessionFactory buildSessionFactory(String configFilePath) {
    try {
      Configuration configuration = new Configuration();
      configuration.configure(configFilePath);
      return configuration.buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
}