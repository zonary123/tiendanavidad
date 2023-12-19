package org.tienda.Controller;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class hibernateUtil {
  private static final SessionFactory sessionFactory = buildSessionFactory();

  public static SessionFactory buildSessionFactory() {
    try {
      return new Configuration().configure("/hibernate/hibernate.cfg.xml").
        buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
    } catch (Throwable ex) {
      System.err.println(ex.getMessage());
    }
    return null;
  }
}