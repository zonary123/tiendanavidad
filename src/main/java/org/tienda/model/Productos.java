package org.tienda.model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.tienda.utils.hibernateUtil;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The type Productos.
 *
 * @author Carlos Varas Alonso
 */
@Entity
@Table(name = "productos")
@RequiredArgsConstructor
@Getter
@Setter
public class Productos {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idproducto")
  private Integer idproducto;

  @Column(name = "nombre", nullable = false, length = 50)
  private String nombre;

  @Column(name = "descripcion", nullable = false)
  private String descripcion;

  @Column(name = "precio", nullable = false, precision = 12)
  private float precio;

  @Column(name = "descuento", precision = 12)
  private Float descuento;

  @Column(length = 30)
  private String categoria;

  @Column(name = "imagen", length = 50)
  private String imagen;

  @Column(name = "stock")
  private Integer stock;

  @OneToMany(mappedBy = "productos", fetch = FetchType.LAZY)
  private Set<Carrito> carritos;

  @OneToMany(mappedBy = "productos", fetch = FetchType.LAZY)
  private Set<Compras> comprases;

  /**
   * Este método se encarga de devolver todos los productos de la base de datos
   *
   * @return con todos los productos de la base de datos
   *
   * @throws NoResultException si no hay productos en la base de datos
   */
  public static List<Productos> findAll() throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Productos> productos = session.createQuery("from Productos").getResultList();
    session.close();
    return productos;
  }

  /**
   * Este método se encarga de devolver un producto de la base de datos
   *
   * @param id id del producto a devolver
   *
   * @return Productos con el id especificado
   *
   * @throws NoResultException si no hay productos con el id especificado
   */
  public static Productos findbyId(int id) throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    Productos producto = session.get(Productos.class, id);
    session.close();
    return producto;
  }

  /**
   * Este método se encarga de devolver todos los productos de la base de datos
   * que pertenezcan a una categoría
   *
   * @param cat categoría de los productos a devolver
   *
   * @return List<Productos>   con todos los productos de la base de datos que pertenezcan a una categoría
   *
   * @throws NoResultException si no hay productos en la categoría especificada
   */
  public static List<Productos> findByCategoria(String cat) throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    List<Productos> productos = session.createQuery("from Productos p where p.categoria = :categoria").setParameter("categoria", cat).getResultList();
    session.close();
    return productos;
  }

  /**
   * Este método se encarga de devolver todos los productos de la base de datos
   * que contengan el nombre especificado
   *
   * @param nombre nombre de los productos a devolver (no es necesario que sea el nombre completo)
   *
   * @return List<Productos>   con todos los productos de la base de datos que contengan el nombre especificado
   *
   * @throws NoResultException si no hay productos con el nombre especificado
   */
  public static List<Productos> findByNombre(String nombre) throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    List<Productos> productos = session.createQuery("from Productos p WHERE p.nombre LIKE  :nombre")
      .setParameter("nombre", nombre + "%")
      .getResultList();
    session.close();
    return productos;
  }

  /**
   * Este método se encarga de devolver todas las categorías de los productos de la base de datos
   *
   * @return List<String>   con todas las categorías de los productos de la base de datos
   *
   * @throws NoResultException si no hay productos en la base de datos
   */
  public static List<String> getAllProductos() {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    List<String> categorias = session.createQuery("SELECT distinct(categoria) from Productos").getResultList();
    session.close();
    return categorias;
  }

  /**
   * Este método se encarga de eliminar un producto de la base de datos
   *
   * @param producto producto a eliminar
   */
  public static void delete(Productos producto) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.delete(producto);
    session.getTransaction().commit();
    session.close();
  }

  /**
   * Este método se encarga de actualizar un producto de la base de datos
   *
   * @param producto producto a actualizar
   */
  public static void update(Productos producto) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.update(producto);
    session.getTransaction().commit();
    session.close();
  }

  /**
   * Este método se encarga de guardar un producto en la base de datos
   *
   * @param producto producto a guardar
   */
  public static void save(Productos producto) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(producto);
    session.getTransaction().commit();
    session.close();
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Productos productos = (Productos) o;
    return getIdproducto() != null && Objects.equals(getIdproducto(), productos.getIdproducto());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }

  @Override public String toString() {
    return "Productos{" +
      "idproducto=" + idproducto +
      ", nombre='" + nombre + '\'' +
      ", descripcion='" + descripcion + '\'' +
      ", precio=" + precio +
      ", descuento=" + descuento +
      ", categoria='" + categoria + '\'' +
      ", stock=" + stock +
      ", carritos=" + carritos +
      ", comprases=" + comprases +
      '}';
  }
}