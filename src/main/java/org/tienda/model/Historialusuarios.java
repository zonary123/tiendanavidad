package org.tienda.model;

import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.tienda.utils.hibernateUtil;

import javax.persistence.*;
import javax.swing.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
   * Constructor de la clase
   *
   * @param id El id del historial de sesiones
   */
  public Historialusuarios(HistorialusuariosId id) {
    this.id = id;
  }

  /**
   * Constructor de la clase
   *
   * @param id       El id del historial de sesiones
   * @param usuarios El usuario al que pertenece el historial
   */
  public Historialusuarios(HistorialusuariosId id, Usuarios usuarios) {
    this.id = id;
    this.usuarios = usuarios;
  }

  /**
   * Encontrar un historial de sesiones por su id
   *
   * @param id El id del historial de sesiones
   *
   * @return devuelve el historial de sesiones
   */
  public Historialusuarios findById(int id) {
    return null;
  }

  /**
   * Encontrar todos los historiales de sesiones
   *
   * @return devuelve una lista con todos los historiales de sesiones
   */
  public static List<Historialusuarios> findAll() {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
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


  /**
   * Encuentra el historial de sesiones más reciente de un usuario
   *
   * @param usuario El usuario del que se quiere obtener el historial
   *
   * @return devuelve el historial de sesiones más reciente de un usuario
   */
  public static Historialusuarios findRecent(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
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
   * Guardar un historial de usuario
   *
   * @param historialusuarios Objeto de tipo Historialusuarios
   *
   * @return devuelve true si se ha guardado correctamente, false en caso contrario
   */
  public static boolean save(Historialusuarios historialusuarios) {

    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
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
   * Actualizar un historial de usuario
   *
   * @param historialusuarios Objeto de tipo Historialusuarios
   *
   * @return true si se ha actualizado correctamente, false en caso contrario
   */
  public static boolean update(Historialusuarios historialusuarios) {
    SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
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

  /**
   * Este metodo se encarga de cerrar la sesion del usuario
   * y de actualizar el historial de sesiones
   */
  public static void sessionCerrada(JFrame vista, Usuarios usuario) {
    Historialusuarios historialusuarios = new Historialusuarios();
    historialusuarios.setId(Historialusuarios.findRecent(usuario).getId());
    historialusuarios.setFechafinsesion(Timestamp.valueOf(LocalDateTime.now()));
    historialusuarios.setUsuarios(usuario);
    Historialusuarios.update(historialusuarios);
    vista.dispose();
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