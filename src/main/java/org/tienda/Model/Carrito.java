package org.tienda.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.tienda.Controller.hibernateUtil;

import javax.persistence.*;

/**
 * @author Carlos Varas Alonso
 */
@Data
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "carrito")
public class Carrito {

  @EmbeddedId
  private CarritoId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idproducto", insertable = false, updatable = false)
  private Productos productos;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idusuario", insertable = false, updatable = false)
  private Usuarios usuarios;

  @Column(name = "cantidad")
  private Integer cantidad;

  public Carrito() {
  }

  public Carrito(CarritoId id, Integer cantidad) {
    this.id = id;
    this.cantidad = cantidad;
  }

  public static boolean updateCant(int id, Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      Carrito carrito = session.get(Carrito.class, new CarritoId(id, usuario.getIdusuario()));
      carrito.setCantidad(carrito.getCantidad() + 1);
      session.update(carrito);
      session.getTransaction().commit();
    } catch (Exception e) {
      session.getTransaction().rollback();
      return false;
    } finally {
      session.close();
    }
    return true;
  }

  public static boolean save(CarritoId id, Integer cantidad) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      Carrito carrito = new Carrito(id, cantidad);
      session.save(carrito);
      session.getTransaction().commit();
    } catch (Exception e) {
      session.getTransaction().rollback();
      return false;
    } finally {
      session.close();
    }
    return true;
  }

  public static boolean findById(int idproducto, Usuarios u) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      Carrito carrito = new Carrito();
      Productos p = new Productos();
      u.setIdusuario(u.getIdusuario());
      p.setIdproducto(idproducto);
      carrito.setId(new CarritoId(idproducto, u.getIdusuario()));
      carrito.setProductos(p);
      carrito.setUsuarios(u);
      System.out.println(carrito);
      session.get(Carrito.class, new CarritoId(idproducto, u.getIdusuario()));
      return true;
    } catch (NoResultException e) {
      e.printStackTrace();
      return false;
    }
  }
}