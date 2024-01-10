package org.tienda.Model;

import org.tienda.Objects.Productos;
import org.tienda.Views.Pruebas;
import org.tienda.interfaces.models;
import org.tienda.Components.jPanelProducts;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class mHome implements models {
  private Pruebas vista;

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
