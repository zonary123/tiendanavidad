package org.tienda.Controller;

import lombok.Data;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Data
public class hibernateUtil {
  private static SessionFactory sessionFactory = buildSessionFactory();


  public static SessionFactory buildSessionFactory() {
    try {
      return new Configuration().configure("hibernate.cfg.xml").setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext").
        buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
    } catch (Throwable ex) {
      System.err.println(ex.getMessage());
    }
    return null;
  }

}