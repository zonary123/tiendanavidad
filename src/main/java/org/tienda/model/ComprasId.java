package org.tienda.model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.*;

/**
 * The type Compras id.
 *
 * @author Carlos Varas Alonso
 */
@Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ComprasId implements java.io.Serializable {

  private int idcompra;
  private int idproducto;
  private int idusuario;

  /**
   * Instantiates a new Compras id.
   */
  public ComprasId() {
  }

  /**
   * Constructor de la clase
   *
   * @param idcompra   El id de la compra
   * @param idproducto El id del producto
   * @param idusuario  EL id del usuario
   */
  public ComprasId(int idcompra, int idproducto, int idusuario) {
    this.idcompra = idcompra;
    this.idproducto = idproducto;
    this.idusuario = idusuario;
  }
}
