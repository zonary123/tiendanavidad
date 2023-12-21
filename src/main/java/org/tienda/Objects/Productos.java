package org.tienda.Objects;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "productos")
public class Productos {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idproducto")
  private Integer idproducto;

  @Column(name = "nombre", nullable = false, length = 50)
  private String nombre;

  @Column(name = "descripcion", nullable = false, length = 255)
  private String descripcion;

  @Column(name = "precio", nullable = false, precision = 12, scale = 0)
  private float precio;

  @Column(name = "descuento", precision = 12, scale = 0)
  private Float descuento;

  @Column(name = "categoria", length = 30)
  private String categoria;

  @Column(name = "imagen")
  private byte[] imagen;

  @Column(name = "stock")
  private Integer stock;

  @OneToMany(mappedBy = "productos", fetch = FetchType.LAZY)
  private Set<Carrito> carritos;

  @OneToMany(mappedBy = "productos", fetch = FetchType.LAZY)
  private Set<Compras> comprases;

  // Getters and setters

  // Other methods
}