package org.tienda.model;
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
   * Constructor vacio
   */
  public HistorialusuariosId() {
  }

  /**
   * Constructor de la clase
   *
   * @param idusuario         El id del usuario
   * @param fechainiciosesion La fecha de inicio de sesion
   */
  public HistorialusuariosId(int idusuario, Timestamp fechainiciosesion) {
    this.fechainiciosesion = fechainiciosesion;
    this.idusuario = idusuario;
  }


}
