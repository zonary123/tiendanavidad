package org.tienda.Objects;
// Generated 20 dic 2023 19:54:47 by Hibernate Tools 6.3.1.Final

/**
 * Carrito generated by hbm2java
 */
public class Carrito implements java.io.Serializable {

  private CarritoId id;
  private Productos productos;
  private Usuarios usuarios;
  private Integer cantidad;

  public Carrito() {
  }

  public Carrito(CarritoId id, Productos productos, Usuarios usuarios, Integer cantidad) {
    this.id = id;
    this.productos = productos;
    this.usuarios = usuarios;
    this.cantidad = cantidad;
  }

  public CarritoId getId() {
    return this.id;
  }

  public void setId(CarritoId id) {
    this.id = id;
  }

  public Productos getProductos() {
    return this.productos;
  }

  public void setProductos(Productos productos) {
    this.productos = productos;
  }

  public Usuarios getUsuarios() {
    return this.usuarios;
  }

  public void setUsuarios(Usuarios usuarios) {
    this.usuarios = usuarios;
  }

  public Integer getCantidad() {
    return this.cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

}
