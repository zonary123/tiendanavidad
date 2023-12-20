package org.tienda.Objects;
// Generated 20 dic 2023 19:54:47 by Hibernate Tools 6.3.1.Final

/**
 * ComprasId generated by hbm2java
 */
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

  public int getIdcompra() {
    return this.idcompra;
  }

  public void setIdcompra(int idcompra) {
    this.idcompra = idcompra;
  }

  public int getIdproducto() {
    return this.idproducto;
  }

  public void setIdproducto(int idproducto) {
    this.idproducto = idproducto;
  }

  public int getIdusuario() {
    return this.idusuario;
  }

  public void setIdusuario(int idusuario) {
    this.idusuario = idusuario;
  }

  public boolean equals(Object other) {
    if ((this == other))
      return true;
    if ((other == null))
      return false;
    if (!(other instanceof ComprasId))
      return false;
    ComprasId castOther = (ComprasId) other;

    return (this.getIdcompra() == castOther.getIdcompra()) && (this.getIdproducto() == castOther.getIdproducto())
      && (this.getIdusuario() == castOther.getIdusuario());
  }

  public int hashCode() {
    int result = 17;

    result = 37 * result + this.getIdcompra();
    result = 37 * result + this.getIdproducto();
    result = 37 * result + this.getIdusuario();
    return result;
  }

}
