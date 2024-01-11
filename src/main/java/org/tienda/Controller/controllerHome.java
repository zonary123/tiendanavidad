package org.tienda.Controller;

import org.hibernate.engine.jdbc.Size;
import org.tienda.Components.jPanelProducts;
import org.tienda.Interfaces.controllers;
import org.tienda.Model.Productos;
import org.tienda.Views.HomeUser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Carlos Varas Alonso
 */
public class controllerHome implements controllers {

  private HomeUser vista;

  public controllerHome(HomeUser vista) {
    this.vista = vista;
    initEvents();
    mostrarProductos(Productos.findAll());
    actualizarLenguaje();
    actualizarEstilos();
  }

  @Override public void actualizarLenguaje() {

  }

  @Override public void actualizarEstilos() {
    vista.getJLabelUsername().setPreferredSize(new Dimension( vista.getJLabelUsername().getWidth() - 30, vista.getJLabelUsername().getHeight()));

    // ? Propiedades
    // vista.getScrollContainerProducts().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getContainerProducts().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getHeader().putClientProperty("FlatLaf.style", "arc: 999");
    vista.getUser().putClientProperty("FlatLaf.style", "arc: 999");
  }

  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
  }

  public void mostrarProductos(List<Productos> productos) {
    JPanel panelProductos = this.vista.getContainerProducts();
    panelProductos.setLayout(new GridLayout(0, 3, 16, 13));
    panelProductos.removeAll();

    for (Productos producto : productos) {
      panelProductos.add(new jPanelProducts(producto));
    }

    panelProductos.revalidate();
    panelProductos.repaint();
  }

}
