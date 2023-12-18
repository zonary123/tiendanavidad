package org.tienda.Objects;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@Data
@ToString(exclude = {"password", "imagen", "idUsuario"})
public class usuarios {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idusuario")
  private int idUsuario;

  @Column(name = "username", unique = true, length = 30)
  private String username;

  @Column(name = "password", length = 100, nullable = false)
  private String password;

  @Column(name = "nombre", nullable = false, length = 30)
  private String nombre;

  @Column(name = "apellidos", length = 50)
  private String apellidos;

  @Column(name = "email", unique = true, nullable = false, length = 75)
  private String email;

  @Lob
  @Column(name = "imagen")
  private byte[] imagen;

  @Column(name = "lenguaje", length = 6, nullable = false, columnDefinition = "VARCHAR(6) DEFAULT 'es_ES'")
  private String lenguaje;

  @Column(name = "permisos", length = 255)
  private String permisos;

  @Column(name = "roles", columnDefinition = "JSON")
  private String roles;

  @Column(name = "activacion", nullable = false)
  private boolean activacion;

  @Column(name = "codigo", length = 8)
  private String codigo;

  public usuarios() {
  }

  public usuarios(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public usuarios(String username, String password, String nombre, String email) {
    this.username = username;
    this.password = password;
    this.nombre = nombre;
    this.email = email;
    this.activacion = false;
  }

  public usuarios(String username, String password, String nombre, String apellidos, String email, byte[] imagen, String lenguaje, String permisos, String roles, boolean activacion, String codigo) {
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
