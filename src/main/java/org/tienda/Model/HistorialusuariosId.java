package org.tienda.Model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.*;

import javax.persistence.Column;
import java.sql.Timestamp;

/**
 * The type Historialusuarios id.
 *
 * @author Carlos Varas Alonso
 */
@Data
@EqualsAndHashCode
@ToString
public class HistorialusuariosId implements java.io.Serializable {

  @Column(name = "fechainiciosesion", nullable = false)
  private Timestamp fechainiciosesion;
  @Column(name = "idusuario", nullable = false)
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
