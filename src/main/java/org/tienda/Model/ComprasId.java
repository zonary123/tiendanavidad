package org.tienda.Model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Compras id.
 *
 * @author Carlos Varas Alonso
 */
@Data
@Getter
@Setter
@EqualsAndHashCode
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
   * Instantiates a new Compras id.
   *
   * @param idcompra   the idcompra
   * @param idproducto the idproducto
   * @param idusuario  the idusuario
   */
  public ComprasId(int idcompra, int idproducto, int idusuario) {
    this.idcompra = idcompra;
    this.idproducto = idproducto;
    this.idusuario = idusuario;
  }


}
