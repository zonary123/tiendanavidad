package org.tienda.controller;

import org.tienda.model.Productos;
import org.tienda.views.crearProducto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Carlos Varas Alonso - 25/01/2024 3:56
 */
public class cProducto {
  private static crearProducto vista;
  private static Productos producto;

  public cProducto(crearProducto vista, Productos producto) {
    this.vista = vista;
    this.producto = producto;
    actualizarEstilos();
    actualizarLenguaje();
    initEvents();
    if (vista.getOpcion() == crearProducto.EDITAR) {
      setDatos(producto);
    }
  }

  private void initEvents() {
    if (vista.getOpcion() == crearProducto.CREAR) {
      vista.getBoton().addActionListener(new ActionListener() {
        @Override public void actionPerformed(ActionEvent e) {

        }
      });
    } else if (vista.getOpcion() == crearProducto.EDITAR) {
      vista.getBoton().addActionListener(new ActionListener() {
        @Override public void actionPerformed(ActionEvent e) {
          producto.setNombre("");
          producto.setDescripcion("");
          producto.setPrecio(10);
          producto.setDescuento(9.0f);
          Productos.update(producto);
        }
      });
    }
  }

  private void setDatos(Productos producto) {

  }

  private void actualizarLenguaje() {

  }

  private void actualizarEstilos() {

  }
}
