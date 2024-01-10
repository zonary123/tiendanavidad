package org.tienda.Controller;

import lombok.Data;
import lombok.Getter;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.swing.*;

/**
 * @author Carlos Varas Alonso
 */
@Data
public class hibernateUtil {
  private static SessionFactory sessionFactory = buildSessionFactory();

  /**
   * Inicializacion de la conexion con la base de datos
   *
   * @return SessionFactory
   */
  public static SessionFactory buildSessionFactory() {
    Configuration configuration = new Configuration();
    configuration.configure(
      "hibernate.cfg.xml");
    return configuration.buildSessionFactory();
  }
}