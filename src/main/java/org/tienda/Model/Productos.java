package org.tienda.Model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import org.tienda.Controller.hibernateUtil;

/**
 * @author Carlos Varas Alonso
 */
@Entity
@Table(name = "productos")
@ToString(exclude = {"idproducto"})
@Data
@Getter
@Setter
@EqualsAndHashCode
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
  private Set<Carrito> carritos;

  @OneToMany(mappedBy = "productos", fetch = FetchType.LAZY)
  private Set<Compras> comprases;

  public static List<Productos> findAll() throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.createQuery("from Productos").getResultList();
  }

  public static Productos findbyId(int i) throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.get(Productos.class, i);
  }

  public static List findByCategoria(String cat) throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.createQuery("from Productos p where p.categoria = :categoria").setParameter("categoria", cat).getResultList();
  }


}