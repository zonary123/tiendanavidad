package org.tienda.Model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * The type Historialusuarios id.
 *
 * @author Carlos Varas Alonso
 */
@Data
@EqualsAndHashCode
public class HistorialusuariosId implements java.io.Serializable {

  private Timestamp fechainiciosesion;
  private int idusuario;

  /**
   * Instantiates a new Historialusuarios id.
   */
  public HistorialusuariosId() {
  }

  /**
   * Instantiates a new Historialusuarios id.
   *
   * @param idusuario         the idusuario
   * @param fechainiciosesion the fechainiciosesion
   */
  public HistorialusuariosId(int idusuario, Timestamp fechainiciosesion) {
    this.fechainiciosesion = fechainiciosesion;
    this.idusuario = idusuario;
  }


}
