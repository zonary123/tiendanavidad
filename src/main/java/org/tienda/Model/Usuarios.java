package org.tienda.Model;

import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Controller.hibernateUtil;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The type Usuarios.
 *
 * @author Carlos Varas Alonso
 */
@ToString @RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuarios implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(insertable = false, updatable = false)
  private Integer idusuario;

  @Column(unique = true, length = 30)
  private String username;

  @Column(nullable = false, length = 100)
  private String password;

  @Column(nullable = false, length = 30)
  private String nombre;

  @Column(length = 50)
  private String apellidos;

  @Column(unique = true, length = 75)
  private String email;
  private byte[] imagen;

  @Column(nullable = false, length = 6, columnDefinition = "varchar(6) default 'es_ES'")
  private String lenguaje;
  private String permisos;
  private String roles;

  @Column(nullable = false)
  private boolean activacion;
  private String codigo;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuarios")
  @ToString.Exclude
  private Set<Historialusuarios> historialusuarioses = new HashSet<>();

  @OneToMany(mappedBy = "usuarios")
  @ToString.Exclude
  private Set<Carrito> carritos = new HashSet<>();

  @OneToMany(mappedBy = "usuarios")
  @ToString.Exclude
  private Set<Compras> comprases = new HashSet<>();

  /**
   * Constructor de Usuarios
   *
   * @param username   username
   * @param password   password
   * @param nombre     nombre
   * @param apellidos  apellidos
   * @param email      email
   * @param lenguaje   lenguaje ej: es_ES, en_US
   * @param roles      roles
   * @param activacion activacion
   */
  public Usuarios(String username, String password, String nombre, String apellidos, String email, String lenguaje, String roles, boolean activacion) {
    this.username = username;
    this.password = password;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.email = email;
    this.lenguaje = lenguaje;
    this.roles = roles;
    this.activacion = activacion;
  }

  /**
   * Constructor de Usuarios
   *
   * @param username   username
   * @param password   password
   * @param nombre     nombre
   * @param apellidos  apellidos
   * @param email      email
   * @param lenguaje   lenguaje
   * @param permisos   permisos
   * @param roles      roles
   * @param activacion activacion
   * @param codigo     the codigo
   */
  public Usuarios(String username, String password, String nombre, String apellidos, String email, String lenguaje, String permisos, String roles, boolean activacion, String codigo) {
    this.username = username;
    this.password = password;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.email = email;
    this.lenguaje = lenguaje;
    this.permisos = permisos;
    this.roles = roles;
    this.activacion = activacion;
    this.codigo = codigo;
  }

  /**
   * Constructor de Usuarios
   *
   * @param username   username
   * @param password   password
   * @param nombre     nombre
   * @param apellidos  apellidos
   * @param email      email
   * @param imagen     imagen
   * @param lenguaje   lenguaje
   * @param permisos   permisos
   * @param roles      roles
   * @param activacion activacion
   * @param codigo     the codigo
   */
  public Usuarios(String username, String password, String nombre, String apellidos, String email, byte[] imagen, String lenguaje, String permisos, String roles, boolean activacion, String codigo) {
    this.username = username;
    this.password = password;
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.email = email;
    this.imagen = imagen;
    this.lenguaje = lenguaje;
    this.permisos = permisos;
    this.roles = roles;
    this.activacion = activacion;
    this.codigo = codigo;
  }

  /**
   * Este metodo devuelve un usuario por su id
   *
   * @param id id del usuario
   *
   * @return usuario usuarios
   */
  public static Usuarios findbyId(Integer id) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    return session.get(Usuarios.class, id);
  }

  /**
   * Este metodo devuelve un usuario por su mail
   *
   * @param mail mail del usuario
   *
   * @return usuario usuarios
   *
   * @throws NoResultException NoResultException
   */
  public static Usuarios findByEmail(String mail) throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    return session.createQuery("from Usuarios where email = :mail", Usuarios.class).setParameter("mail", mail).getSingleResult();
  }

  /**
   * Este metodo devuelve un usuario por su username
   *
   * @param Username username del usuario
   *
   * @return usuario usuarios
   *
   * @throws NoResultException NoResultException
   */
  public static Usuarios findByUsername(String Username) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      return session.createQuery("from Usuarios where username = :username", Usuarios.class).setParameter("username", Username).getSingleResult();
    } catch (NoResultException e) {
      return null;
    } finally {
      session.close();
    }
  }

  /**
   * Este metodo devuelve una lista de todos los usuarios
   *
   * @return lista de usuarios
   */
  public static List<Usuarios> findAll() {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    return session.createQuery("from Usuarios", Usuarios.class).list();
  }

  /**
   * Este metodo guarda un usuario
   *
   * @param usuario the usuario
   *
   * @return true si se ha guardado correctamente
   */
  public static boolean save(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    try {
      session.beginTransaction();
      usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt(12)));
      session.save(usuario);
      session.getTransaction().commit();
      return true;
    } catch (Exception e) {
      if (session.getTransaction() != null) {
        session.getTransaction().rollback();
      }
      e.printStackTrace();
      return false;
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  /**
   * Este metodo borra un usuario
   *
   * @param usuario the usuario
   *
   * @return true si se ha borrado correctamente
   */
  public static boolean delete(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();

    try {
      session.beginTransaction();
      session.delete(usuario);
      session.getTransaction().commit();
      return true;
    } catch (Exception e) {
      if (session.getTransaction() != null) {
        session.getTransaction().rollback();
      }
      e.printStackTrace();
      return false;
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  /**
   * Este metodo actualiza un usuario
   *
   * @param usuario the usuario
   *
   * @return true si se ha actualizado correctamente
   */
  public static boolean update(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();

    try {
      session.beginTransaction();
      session.update(usuario);
      session.getTransaction().commit();
      return true;
    } catch (Exception e) {
      if (session.getTransaction() != null) {
        session.getTransaction().rollback();
      }
      e.printStackTrace();
      return false;
    } finally {
      if (session != null && session.isOpen()) {
        session.close();
      }
    }
  }

  /**
   * Este metodo comprueba la contraseña de un usuario
   *
   * @param password the password
   * @param usuario  the usuario
   *
   * @return true si la contraseña es correcta
   */
  public static boolean checkPassword(String password, Usuarios usuario) {
    return BCrypt.checkpw(password, usuario.getPassword());
  }

  /**
   * Este metodo comprueba si existe un usuario
   *
   * @param usuario the usuario
   *
   * @return true si existe
   */
  public static Usuarios existUser(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Usuarios user;
    try {
      user = session.createQuery("from Usuarios where username = :username or email = :email", Usuarios.class)
        .setParameter("username", usuario.getUsername())
        .setParameter("email", usuario.getEmail())
        .getSingleResult();
    } catch (Exception e) {
      return null;
    }
    return user;
  }

  /**
   * Este metodo comprueba si el codigo de activacion es correcto
   *
   * @param u      the u
   * @param codigo the codigo
   *
   * @return true si el codigo es correcto
   */
  public static boolean checkCodigo(Usuarios u, String codigo) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    System.out.println(u);
    try {
      session.createQuery("from Usuarios where email = :email AND codigo = :codigo", Usuarios.class)
        .setParameter("email", u.getEmail())
        .setParameter("codigo", codigo)
        .getSingleResult();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Este metodo actualiza el codigo de activacion de un usuario
   *
   * @param u the u
   *
   * @return true si se ha actualizado correctamente
   */
  public static boolean updateCodigo(Usuarios u) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      session.createQuery("update Usuarios set codigo = :codigo where email = :email")
        .setParameter("codigo", u.getCodigo())
        .setParameter("email", u.getEmail())
        .executeUpdate();
      session.getTransaction().commit();
      return true;
    } catch (Exception e) {
      if (session.getTransaction() != null) {
        session.getTransaction().rollback();
      }
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Este metodo actualiza la contraseña de un usuario
   *
   * @param u the u
   *
   * @return true si se ha actualizado correctamente
   */
  public static boolean updatePassword(Usuarios u) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      session.createQuery("update Usuarios set password = :password where email = :email")
        .setParameter("password", BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12)))
        .setParameter("email", u.getEmail())
        .executeUpdate();
      session.getTransaction().commit();
      return true;
    } catch (Exception e) {
      if (session.getTransaction() != null) {
        session.getTransaction().rollback();
      }
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Usuarios usuarios = (Usuarios) o;
    return getIdusuario() != null && Objects.equals(getIdusuario(), usuarios.getIdusuario());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
