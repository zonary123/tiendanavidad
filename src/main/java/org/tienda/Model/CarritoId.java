package org.tienda.Model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Carlos Varas Alonso
 */
@Data
@Getter
@Setter
public class CarritoId implements java.io.Serializable {

  private int idproducto;
  private int idusuario;

  public CarritoId() {
  }

  public CarritoId(int idproducto, int idusuario) {
    this.idproducto = idproducto;
    this.idusuario = idusuario;
  }

}
