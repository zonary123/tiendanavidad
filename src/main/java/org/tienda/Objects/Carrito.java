package org.tienda.Objects;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "carrito")
public class Carrito {

  @EmbeddedId
  private CarritoId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idproducto", insertable = false, updatable = false)
  private Productos productos;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idusuario", insertable = false, updatable = false)
  private Usuarios usuarios;

  @Column(name = "cantidad")
  private Integer cantidad;

}