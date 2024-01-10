package org.tienda.Objects;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Carlos Varas Alonso
 */
@Data
@Entity
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

  public Compras(ComprasId idComppras, int cantidad) {
    this.id = idComppras;
    this.cantidad = cantidad;
    this.fechacompra = new Date(System.currentTimeMillis());
    this.fechaentrega = new Date(System.currentTimeMillis());
  }

  public Compras() {

  }
}