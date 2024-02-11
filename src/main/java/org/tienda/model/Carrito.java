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
@Table(name = "carrito")
public class Carrito {

  @EmbeddedId
  private CarritoId id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "idproducto", insertable = false, updatable = false)
  private Productos productos;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "idusuario", insertable = false, updatable = false)
  private Usuarios usuarios;

  @Column(name = "cantidad")
  private Integer cantidad;


  /**
   * Constructor de la clase
   *
   * @param id       id del carrito
   * @param cantidad cantidad del producto
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
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
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

  /**
   * Busca un producto en el carrito
   *
   * @param producto Producto a buscar
   * @param usuario  Usuario que tiene el carrito
   *
   * @return Carrito si existe, null si no
   */
  public static Carrito findByProductoAndUsuario(Productos producto, Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
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

  /**
   * Busca un producto en el carrito
   *
   * @param usuario Usuario que tiene el carrito
   *
   * @return Carrito si existe, null si no
   */
  public static List<Productos> getProductos(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
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
   * @param usuario Usuario que tiene el carrito
   *
   * @return true si se ha actualizado, false si no
   */
  public static boolean updateCant(int id, Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      Carrito carrito = session.get(Carrito.class, new CarritoId(id, usuario.getIdusuario()));
      carrito.setCantidad(carrito.getCantidad() + 1);
      session.update(carrito);
      session.getTransaction().commit();
      session.close();
    } catch (Exception e) {
      session.getTransaction().rollback();
      return false;
    } finally {
      session.close();
    }
    return true;
  }

  /**
   * Actualiza la cantidad de un producto en el carrito
   *
   * @param id       id del producto
   * @param usuario  Usuario que tiene el carrito
   * @param cantidad cantidad del producto
   *
   * @return true si se ha actualizado, false si no
   */
  public static boolean updateCant(int id, Usuarios usuario, int cantidad) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      Carrito carrito = session.get(Carrito.class, new CarritoId(id, usuario.getIdusuario()));
      if (cantidad == 0) {
        session.delete(carrito);
        session.getTransaction().commit();
        session.close();
        return true;
      }
      carrito.setCantidad(cantidad);
      session.update(carrito);
      session.getTransaction().commit();
      session.close();
      return true;
    } catch (Exception e) {
      session.getTransaction().rollback();
      return false;
    }
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
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      Carrito carrito = new Carrito(id, cantidad);
      session.save(carrito);
      session.getTransaction().commit();
      session.close();
    } catch (Exception e) {
      session.getTransaction().rollback();
      return false;
    } finally {
      session.close();
    }
    return true;
  }

  /**
   * Calcula el precio total de los artículos en el carrito de compras para un usuario dado.
   *
   * @param usuario El usuario para el cual se calculará el precio total.
   *
   * @return El precio total de los artículos en el carrito de compras, o 0 si ocurre un error.
   */
  public static double calcTotal(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    try {
      double d = (double) session.createQuery("SELECT SUM(p.precio * c.cantidad) FROM Carrito c INNER JOIN Productos p ON c.id.idproducto = p.idproducto AND c.id.idusuario = :id").setParameter("id", usuario.getIdusuario()).getSingleResult();
      session.close();
      return d;
    } catch (Exception e) {
      session.getTransaction().rollback();
      return 0;
    } finally {
      session.close();
    }
  }

  /**
   * Elimina un producto del carrito de compras de un usuario.
   *
   * @param usuario  el usuario cuyo carrito de compras será modificado
   * @param producto el producto que se eliminará del carrito de compras
   */
  public static void deleteProducto(Usuarios usuario, Productos producto) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
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

  /**
   * Busca un producto en el carrito
   *
   * @param idproducto id del producto
   * @param usuario    Usuario que tiene el carrito
   *
   * @return Carrito si existe, null si no
   */
  public static Carrito findProductoByCarrito(Integer idproducto, Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    try {
      Carrito c = session.createQuery("FROM Carrito c WHERE c.id.idproducto = :idproducto AND c.id.idusuario = :idusuario", Carrito.class).setParameter("idproducto", idproducto).setParameter("idusuario", usuario.getIdusuario()).getSingleResult();
      session.close();
      return c;
    } catch (Exception e) {
      session.getTransaction().rollback();
      return null;
    } finally {
      session.close();
    }
  }

  /**
   * Calcula el precio total de un producto en el carrito de compras de un usuario.
   *
   * @param usuario    el usuario para el cual se calculará el precio total
   * @param idproducto el identificador del producto para el cual se calculará el precio total
   *
   * @return el precio total del producto en el carrito de compras, o 0 si ocurre un error
   */
  public static double calcTotal(Usuarios usuario, Integer idproducto) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    try {
      String sql = "SELECT c.cantidad * c.productos.precio " +
        "FROM Carrito c " +
        "WHERE c.usuarios.id = :idUsuario AND c.productos.id = :idProducto";
      return (double) session.createQuery(sql).setParameter("idProducto", idproducto).setParameter("idUsuario", usuario.getIdusuario()).uniqueResult();
    } catch (Exception e) {
      return 0;
    } finally {
      session.close();
    }
  }

  /**
   * Indica si algún otro objeto es "igual a" este.
   *
   * @param o el objeto de referencia con el que se va a comparar.
   *
   * @return true si este objeto es igual al argumento o; false en caso contrario.
   */
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

  /**
   * Devuelve el valor del código hash para este objeto Carrito.
   * El código hash se basa en el campo id.
   *
   * @return el valor del código hash para este objeto Carrito.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override public String toString() {
    return "Carrito{" +
      "id=" + id +
      ", productos=" + productos +
      ", usuarios=" + usuarios +
      ", cantidad=" + cantidad +
      '}';
  }
}