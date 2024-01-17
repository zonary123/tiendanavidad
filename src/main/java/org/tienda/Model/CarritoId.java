package org.tienda.Model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Carrito id.
 *
 * @author Carlos Varas Alonso
 */
@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CarritoId implements Serializable {

  private int idproducto;
  private int idusuario;

  /**
   * Instantiates a new Carrito id.
   */
  public CarritoId() {
  }

  /**
   * Instantiates a new Carrito id.
   *
   * @param idproducto the idproducto
   * @param idusuario  the idusuario
   */
  public CarritoId(int idproducto, int idusuario) {
    this.idproducto = idproducto;
    this.idusuario = idusuario;
  }


}