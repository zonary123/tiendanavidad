package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Objects.Productos;

import java.util.List;

public class controllerHome {
  public controllerHome() {
  }

  public void initEvents() {
  }

  public List<Productos> getProductos() {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.createQuery("from Productos").list();
  }
}
