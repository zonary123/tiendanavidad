package org.tienda.Model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.Data;

/**
 * @author Carlos Varas Alonso
 */
@Data
public class ComprasId implements java.io.Serializable {

  private int idcompra;
  private int idproducto;
  private int idusuario;

  public ComprasId() {
  }

  public ComprasId(int idcompra, int idproducto, int idusuario) {
    this.idcompra = idcompra;
    this.idproducto = idproducto;
    this.idusuario = idusuario;
  }


}
