package org.tienda.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.tienda.Controller.hibernateUtil;

/**
 * The type Historialusuarios.
 *
 * @author Carlos Varas Alonso
 */
@Entity
@Table(name = "historialusuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Historialusuarios implements java.io.Serializable {

  @EmbeddedId
  private HistorialusuariosId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false, insertable = false, updatable = false)
  private Usuarios usuarios;

  @Column(name = "fechafinsesion")
  private Timestamp fechafinsesion;

  /**
   * Instantiates a new Historialusuarios.
   *
   * @param id the id
   */
  public Historialusuarios(HistorialusuariosId id) {
    this.id = id;
  }

  /**
   * Instantiates a new Historialusuarios.
   *
   * @param id       the id
   * @param usuarios the usuarios
   */
  public Historialusuarios(HistorialusuariosId id, Usuarios usuarios) {
    this.id = id;
    this.usuarios = usuarios;
  }

  /**
   * Find by id historialusuarios.
   *
   * @param id the id
   *
   * @return the historialusuarios
   */
  public Historialusuarios findById(int id) {
    return null;
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Historialusuarios> findAll() {
    return null;
  }

  /**
   * Find recent historialusuarios.
   *
   * @param user the user
   *
   * @return the historialusuarios
   */
  public static Historialusuarios findRecent(Usuarios user) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      Historialusuarios historialusuarios = session.createQuery("FROM Historialusuarios WHERE idusuario = :idusuario ORDER BY fechainiciosesion DESC", Historialusuarios.class)
        .setParameter("idusuario", user.getIdusuario())
        .setMaxResults(1)
        .uniqueResult();
      session.getTransaction().commit();
      return historialusuarios;
    } catch (Exception e) {
      session.getTransaction().rollback();
      return null;
    } finally {
      session.close();
    }
  }

  /**
   * Save boolean.
   *
   * @param historialusuarios the historialusuarios
   *
   * @return the boolean
   */
  public static boolean save(Historialusuarios historialusuarios) {

    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      session.save(historialusuarios);
      session.getTransaction().commit();
      return true;
    } catch (Exception e) {
      session.getTransaction().rollback();
      return false;
    } finally {
      session.close();
    }
  }

  /**
   * Update boolean.
   *
   * @param historialusuarios the historialusuarios
   *
   * @return the boolean
   */
  public static boolean update(Historialusuarios historialusuarios) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      session.getTransaction().commit();
      session.update(historialusuarios);
      return true;
    } catch (Exception e) {
      session.getTransaction().rollback();
      return false;
    } finally {
      session.close();
    }
  }
}