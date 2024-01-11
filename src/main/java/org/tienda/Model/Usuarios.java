package org.tienda.Model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.tienda.Controller.hibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * @author Carlos Varas Alonso
 */
@Data
@ToString(exclude = {"idusuario", "activacion", "imagen"})
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

  @Column(unique = true, nullable = false, length = 75)
  private String email;
  private byte[] imagen;

  @Column(nullable = false, length = 6)
  private String lenguaje;
  private String permisos;
  private String roles;

  @Column(nullable = false)
  private boolean activacion;
  private String codigo;

  @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
  private Set<Historialusuarios> historialusuarioses = new HashSet<>();

  @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
  private Set<Carrito> carritos = new HashSet<>();

  @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
  private Set<Compras> comprases = new HashSet<>();

  public Usuarios() {

  }

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

  public static void main(String[] args) {
    System.out.println(" Guardado de usuario -> " + save(new Usuarios(
      "nombreUsuario",
      "contrasenaSecreta",
      "Nombre",
      "Apellidos",
      "correo@example.com",
      "es_ES",
      "[\"admin\"]",
      true
    )));
  }

  public static Usuarios findbyId(Integer id) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.get(Usuarios.class, id);
  }

  public static Usuarios findByEmail(String mail) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.createQuery("from Usuarios where email = :mail", Usuarios.class).setParameter("mail", mail).getSingleResult();
  }

  public static Usuarios findByUsername(String Username) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.createQuery("from Usuarios where username = :username", Usuarios.class).setParameter("username", Username).getSingleResult();
  }

  public static List<Usuarios> findAll() {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.createQuery("from Usuarios", Usuarios.class).list();
  }

  public static boolean save(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();

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

  public static boolean delete(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();

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

  public static boolean update(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();

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

  public static boolean checkPassword(String password, Usuarios usuario) {
    return BCrypt.checkpw(password, usuario.getPassword());
  }

  public static Usuarios existUser(Usuarios usuario) {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
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
}