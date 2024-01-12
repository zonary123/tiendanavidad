package org.tienda.Model;

import javax.persistence.*;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Carlos Varas Alonso
 */
@Entity
@Table(name = "historialusuarios")
@Data
@EqualsAndHashCode
public class Historialusuarios implements java.io.Serializable {

  @EmbeddedId
  private HistorialusuariosId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", nullable = false, insertable = false, updatable = false)
  private Usuarios usuarios;

  @Column(name = "fechafinsesion")
  private Timestamp fechafinsesion;

  // Constructor y getters/setters
}