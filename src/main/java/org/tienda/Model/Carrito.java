package org.tienda.Model;

import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.tienda.Controller.hibernateUtil;

import javax.persistence.*;
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
}