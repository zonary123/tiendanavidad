package org.tienda.model;

import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.tienda.utils.hibernateUtil;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * The type Carrito.
 *
 * @author Carlos Varas Alonso
 */
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "carrito")
public class Carrito {

  @EmbeddedId
  private CarritoId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idproducto", insertable = false, updatable = false) @ToString.Exclude
  private Productos productos;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idusuario", insertable = false, updatable = false) @ToString.Exclude
  private Usuarios usuarios;

  @Column(name = "cantidad")
  private Integer cantidad;

  /**
   * Instantiates a new Carrito.
   *
   * @param id       the id
   * @param cantidad the cantidad
   */
  public Carrito(CarritoId id, Integer cantidad) {
    this.id = id;
    this.cantidad = cantidad;
  }
  // ? SELECTS

  /**
   * Busca un producto en el carrito
   *
   * @param idproducto id del producto
   * @param u          Usuario
   *
   * @return true si existe, false si no
   */
  public static boolean findById(int idproducto, Usuarios u) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      Carrito carrito = session.get(Carrito.class, new CarritoId(idproducto, u.getIdusuario()));
      if (carrito != null) {
        return true;
      }
    } catch (Exception e) {
      session.getTransaction().rollback();
      return false;
    } finally {
      session.close();
    }
    return false;
  }

  public static Carrito findByProductoAndUsuario(Productos producto, Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      return (Carrito) session.createQuery("SELECT c FROM Carrito c WHERE c.id.idproducto = :idproducto AND c.id.idusuario = :idusuario").setParameter("idproducto", producto.getIdproducto()).setParameter("idusuario", usuario.getIdusuario()).getSingleResult();
    } catch (Exception e) {
      session.getTransaction().rollback();
      return null;
    } finally {
      session.close();
    }
  }

  public static List<Productos> getProductos(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      return session.createQuery("SELECT p FROM Carrito c INNER JOIN Productos p ON c.id.idproducto = p.idproducto AND c.id.idusuario = :id").setParameter("id", usuario.getIdusuario()).list();
    } catch (Exception e) {
      session.getTransaction().rollback();
      return null;
    } finally {
      session.close();
    }
  }
  // ? UPDATES

  /**
   * Actualiza la cantidad de un producto en el carrito
   *
   * @param id      id del producto
   * @param usuario Usuario
   *
   * @return true si se ha actualizado, false si no
   */
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

  // ? INSERTS

  /**
   * Actualiza la cantidad de un producto en el carrito
   *
   * @param id       id del producto
   * @param cantidad cantidad del producto
   *
   * @return true si se ha actualizado, false si no
   */
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

  public static double calcTotal(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      return (double) session.createQuery("SELECT SUM(p.precio * c.cantidad) FROM Carrito c INNER JOIN Productos p ON c.id.idproducto = p.idproducto AND c.id.idusuario = :id").setParameter("id", usuario.getIdusuario()).getSingleResult();
    } catch (Exception e) {
      session.getTransaction().rollback();
      return 0;
    } finally {
      session.close();
    }
  }

  public static void deleteProducto(Usuarios usuario, Productos producto) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      session.createQuery("DELETE FROM Carrito WHERE id.idproducto = :idproducto AND id.idusuario = :idusuario").setParameter("idproducto", producto.getIdproducto()).setParameter("idusuario", usuario.getIdusuario()).executeUpdate();
      session.getTransaction().commit();
    } catch (Exception e) {
      session.getTransaction().rollback();
    } finally {
      session.close();
    }
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Carrito carrito = (Carrito) o;
    return getId() != null && Objects.equals(getId(), carrito.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @PostUpdate
  public void postUpdate() {
    System.out.println("PostUpdate");
  }

  @PostPersist
  public void postPersist() {
    System.out.println("PostPersist");
  }

  @PostRemove
  public void postRemove() {
    System.out.println("PostRemove");
  }
}