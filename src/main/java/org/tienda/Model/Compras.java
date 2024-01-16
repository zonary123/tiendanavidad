package org.tienda.Model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

/**
 * The type Compras.
 *
 * @author Carlos Varas Alonso
 */
@Data
@Getter
@Setter
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "compras")
public class Compras {

  @EmbeddedId
  private ComprasId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idproducto", insertable = false, updatable = false)
  private Productos productos;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idusuario", insertable = false, updatable = false)
  private Usuarios usuarios;

  @Column(name = "cantidad", nullable = false)
  private int cantidad;

  @Column(name = "fechacompra")
  private Date fechacompra;

  @Column(name = "fechaentrega")
  private Date fechaentrega;

  /**
   * Constructor vacio de la clase
   */
  public Compras() {

  }

  /**
   * Constructor de la clase
   *
   * @param idComppras Id de la compra
   * @param cantidad   Cantidad de productos
   */
  public Compras(ComprasId idComppras, int cantidad) {
    this.id = idComppras;
    this.cantidad = cantidad;
    this.fechacompra = new Date(System.currentTimeMillis());
    this.fechaentrega = new Date(System.currentTimeMillis());
  }
}