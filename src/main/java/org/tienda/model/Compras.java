package org.tienda.model;
// Generated 21 dic 2023 17:36:10 by Hibernate Tools 6.3.1.Final

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * The type Compras.
 *
 * @author Carlos Varas Alonso
 */
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "compras")
public class Compras {

  @EmbeddedId
  private ComprasId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idproducto", insertable = false, updatable = false) @ToString.Exclude
  private Productos productos;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idusuario", insertable = false, updatable = false) @ToString.Exclude
  private Usuarios usuarios;

  @Column(name = "cantidad", nullable = false)
  private int cantidad;

  @Column(name = "fechacompra")
  private Date fechacompra;

  @Column(name = "fechaentrega")
  private Date fechaentrega;

  /**
   * Constructor de la clase
   *
   * @param idComppras Id de la compra
   * @param cantidad   Cantidad de productos
   */
  public Compras(ComprasId idComppras, int cantidad) {
    this.id = idComppras;
    this.cantidad = cantidad;
    this.fechacompra = new Date(System.currentTimeMillis());
    this.fechaentrega = new Date(System.currentTimeMillis());
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Compras compras = (Compras) o;
    return getId() != null && Objects.equals(getId(), compras.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override public String toString() {
    return "Compras{" +
      "id=" + id +
      ", productos=" + productos +
      ", usuarios=" + usuarios.getUsername() +
      ", cantidad=" + cantidad +
      ", fechacompra=" + fechacompra +
      ", fechaentrega=" + fechaentrega +
      '}';
  }
}