package org.tienda.Model;

import org.tienda.Views.Pruebas;
import org.tienda.Interfaces.controllers;
import org.tienda.Components.jPanelProducts;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Carlos Varas Alonso
 */
public class mHome implements controllers {
  private Pruebas vista;

  /**
   * Constructor de la clase
   *
   * @param vista Vista de la clase
   */
  public mHome(Pruebas vista) {
    this.vista = vista;
    actualizarLenguaje();
    actualizarEstilos();
  }

  /**
   * Actualiza el lenguaje de la vista
   */
  @Override public void actualizarLenguaje() {

  }

  /**
   * Actualiza los estilos de la vista
   */
  @Override public void actualizarEstilos() {

  }

  /**
   * Muestra los productos en la vista y construye los paneles
   *
   * @param productos Lista de productos
   */
  public void mostrarProductos(List<Productos> productos) {
    JPanel panelProductos = this.vista.getContainer();
    panelProductos.setLayout(new GridLayout(0, 3, 16, 13));
    panelProductos.removeAll();

    for (Productos producto : productos) {
      System.out.println(producto.toString());
      panelProductos.add(new jPanelProducts(producto));
    }

    panelProductos.revalidate();
    panelProductos.repaint();
  }
}
