package org.tienda.Model;

import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.tienda.Controller.hibernateUtil;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * The type Historialusuarios.
 *
 * @author Carlos Varas Alonso
 */
@Entity
@Table(name = "historialusuarios")
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Historialusuarios implements java.io.Serializable {

  @EmbeddedId
  private HistorialusuariosId id;

  @ManyToOne(fetch = FetchType.EAGER)
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
  public static List<Historialusuarios> findAll() {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      try {
        List<Historialusuarios> historialusuarios = session.createQuery("FROM Historialusuarios", Historialusuarios.class).list();
        session.getTransaction().commit();
        return historialusuarios;
      } catch (Exception e) {
        session.getTransaction().rollback();
        throw e; // Re-lanza la excepción para que pueda ser manejada en un nivel superior si es necesario
      }
    }
  }


  public static Historialusuarios findRecent(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();

      try {
        Query query = session.createQuery(
            "FROM Historialusuarios h WHERE h.id.idusuario = :idUsuario " +
              "ORDER BY h.id.fechainiciosesion DESC", Historialusuarios.class)
          .setParameter("idUsuario", usuario.getIdusuario())
          .setMaxResults(1);

        Historialusuarios resultado = (Historialusuarios) query.getSingleResult();

        session.getTransaction().commit();
        return resultado;
      } catch (Exception e) {
        session.getTransaction().rollback();
        throw e;
      }
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
      session.update(historialusuarios);
      session.getTransaction().commit();
      return true;
    } catch (Exception e) {
      session.getTransaction().rollback();
      return false;
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
    Historialusuarios that = (Historialusuarios) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}