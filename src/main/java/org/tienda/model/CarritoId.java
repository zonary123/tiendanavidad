package org.tienda.model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.*;

import java.io.Serializable;

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
   * Constructor vacio
   */
  public CarritoId() {
  }


  /**
   * Constructor de la clase
   *
   * @param idproducto Id del producto
   * @param idusuario  Id del usuario
   */
  public CarritoId(int idproducto, int idusuario) {
    this.idproducto = idproducto;
    this.idusuario = idusuario;
  }


}