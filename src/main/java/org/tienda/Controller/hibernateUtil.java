package org.tienda.Controller;

import lombok.Data;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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
      configuration.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");

      return configuration.buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println(ex.getMessage());
    }
    return null;
  }

}