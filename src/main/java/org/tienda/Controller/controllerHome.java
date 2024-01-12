package org.tienda.Controller;

import org.hibernate.engine.jdbc.Size;
import org.tienda.Components.jPanelProducts;
import org.tienda.Interfaces.controllers;
import org.tienda.Model.Productos;
import org.tienda.Utils.utilsTextField;
import org.tienda.Views.HomeUser;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Carlos Varas Alonso
 */
public class controllerHome implements controllers {

  private HomeUser vista;
  private utilsTextField textField = new utilsTextField();

  public controllerHome(HomeUser vista) {
    this.vista = vista;
    initEvents();
    mostrarProductos(Productos.findAll());
    actualizarLenguaje();
    actualizarEstilos();
  }

  @Override public void actualizarLenguaje() {
    vista.getSearch().setText(null);
  }

  @Override public void actualizarEstilos() {
    vista.getUser().setSize(new Dimension((int) (vista.getJLabelUsername().getWidth() * 1.5) + 5, vista.getJLabelUsername().getHeight()));
    // ? Propiedades
    // vista.getScrollContainerProducts().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getContainerProducts().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getHeader().putClientProperty("FlatLaf.style", "arc: 999");
    vista.getUser().putClientProperty("FlatLaf.style", "arc: 999");
    vista.getSignOut().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getSideBar().putClientProperty("FlatLaf.style", "arc: 8");
    // TextField
    textField.actualizarTextField(vista.getSearch(), "Buscar", 999, "img/svg/search.svg", 22, 24, "#FFFFFF");

  }

  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
  }

  public void mostrarProductos(List<Productos> productos) {
    JPanel panelProductos = this.vista.getContainerProducts();
    panelProductos.setLayout(new GridLayout(0, 3, 10, 10));
    panelProductos.removeAll();

    for (Productos producto : productos) {
      panelProductos.add(new jPanelProducts(vista.getJLabelUsername().getText().trim(), producto));
    }

    panelProductos.revalidate();
    panelProductos.repaint();
  }

}
