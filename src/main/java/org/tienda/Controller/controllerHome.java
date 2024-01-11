package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.tienda.Model.Productos;

import java.util.List;

/**
 * @author Carlos Varas Alonso
 */
public class controllerHome {
  public controllerHome() {
  }

  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
  }

  /**
   * Obtencion de todos los productos de la base de datos
   *
   * @return List<Productos>
   */
  public List<Productos> getProductos() {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.createQuery("from Productos").list();
  }

  /**
   * Obtencion de todos los productos de la base de datos
   *
   * @param categoria Categoria de los productos
   * @return List<Productos>
   */
  public List<Productos> getProductosCategories(String categoria) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.createQuery("from Productos p WHERE p.categoria = :categoria").setParameter("categoria", categoria).list();
  }
}
