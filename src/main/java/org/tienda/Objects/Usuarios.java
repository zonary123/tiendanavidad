package org.tienda.Objects;

import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Data
@ToString(exclude = {"idusuario", "activacion", "imagen"})
@Entity
@Table(name = "usuarios")
public class Usuarios implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idusuario", insertable = false, updatable = false)
  private Integer idusuario;

  @Column(name = "username", unique = true, length = 30)
  private String username;

  @Column(name = "password", nullable = false, length = 100)
  private String password;

  @Column(name = "nombre", nullable = false, length = 30)
  private String nombre;

  @Column(name = "apellidos", length = 50)
  private String apellidos;

  @Column(name = "email", unique = true, nullable = false, length = 75)
  private String email;

  @Column(name = "imagen")
  private byte[] imagen;

  @Column(name = "lenguaje", nullable = false, length = 6)
  private String lenguaje;

  @Column(name = "permisos")
  private String permisos;

  @Column(name = "roles")
  private String roles;

  @Column(name = "activacion", nullable = false)
  private boolean activacion;

  @Column(name = "codigo", length = 255)
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

}