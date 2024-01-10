package org.tienda.Objects;

import lombok.Data;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.tienda.Controller.hibernateUtil;

import java.util.HashSet;
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
  

  public static Usuarios findById(int s) throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.createQuery("from Usuarios u WHERE id = :id", Usuarios.class).setParameter("id", s).getSingleResult();
  }

  public static Usuarios findByUsernameOrEmail(String s) throws NoResultException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    return session.createQuery("SELECT u FROM Usuarios u WHERE username = :username", Usuarios.class).setParameter("username", s).getSingleResult();
  }
}