package org.tienda.Controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.tienda.Components.jPanelProducts;
import org.tienda.Model.Productos;
import org.tienda.Views.HomeUser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Carlos Varas Alonso
 */
public class controllerHome {
  private HomeUser vista;

  public controllerHome(HomeUser vista) {
    this.vista = vista;
    initEvents();
    mostrarProductos(Productos.findAll());
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
