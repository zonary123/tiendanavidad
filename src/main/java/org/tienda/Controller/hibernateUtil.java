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

  public static SessionFactory buildSessionFactory() {
    try {
      Configuration configuration = new Configuration();
      configuration.configure(
        "hibernate.cfg.xml");
      return configuration.buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("No pudo establecerse la conexion con la base de datos");
      JOptionPane.showMessageDialog(null, "No pudo establecerse la conexion con la base de datos", "Error", JOptionPane.INFORMATION_MESSAGE);
      throw new RuntimeException(ex);
    }

  }

}