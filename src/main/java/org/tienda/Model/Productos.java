package org.tienda.Model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.tienda.Controller.hibernateUtil;

import javax.persistence.*;
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
@ToString
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

  @Column(name = "imagen")
  private byte[] imagen;

  @Column(name = "stock")
  private Integer stock;

  @OneToMany(mappedBy = "productos", fetch = FetchType.LAZY)
  @ToString.Exclude
  private Set<Carrito> carritos;

  @OneToMany(mappedBy = "productos", fetch = FetchType.LAZY)
  @ToString.Exclude
  private Set<Compras> comprases;

  /**
   * Este método se encarga de devolver todos los productos de la base de datos
   *
   * @return con todos los productos de la base de datos
   *
   * @throws NoResultException si no hay productos en la base de datos
   */
  public static List<Productos> findAll() throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Productos> productos = session.createQuery("from Productos").getResultList();
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
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    return session.get(Productos.class, id);
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
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    return session.createQuery("from Productos p where p.categoria = :categoria").setParameter("categoria", cat).getResultList();
  }

  public static List<Productos> findByNombre(String nombre) throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();

    return session.createQuery("from Productos p WHERE p.nombre LIKE  :nombre")
      .setParameter("nombre", nombre + "%")
      .getResultList();
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
}