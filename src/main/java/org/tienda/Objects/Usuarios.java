package org.tienda.Objects;

import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Data
@ToString(exclude = {"idusuario"})
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

  @Column(name = "permisos", length = 255)
  private String permisos;

  @Column(name = "roles")
  private String roles;

  @Column(name = "activacion", nullable = false)
  private boolean activacion;

  @Column(name = "codigo", length = 8)
  private String codigo;

  @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
  private Set<Historialusuarios> historialusuarioses = new HashSet<>();

  @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
  private Set<Carrito> carritos = new HashSet<>();

  @OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
  private Set<Compras> comprases = new HashSet<>();

  // Constructor(s) omitidos

  // Getters y Setters omitidos
}