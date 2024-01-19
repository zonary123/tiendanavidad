package org.tienda.controller;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.tienda.components.Header;
import org.tienda.components.jPanelProducts;
import org.tienda.model.Historialusuarios;
import org.tienda.model.Productos;
import org.tienda.views.Carrito;
import org.tienda.utils.utilsLenguaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import org.tienda.components.carritoProductos;

/**
 * @author Carlos Varas Alonso - 17/01/2024 4:34
 */
public class cCarrito {
  private final Carrito vista;
  private static utilsLenguaje lenguaje;


  public cCarrito(Carrito vista) {
    this.vista = vista;
    lenguaje = new utilsLenguaje(vista.getUsuario());
    actualizarLenguaje();
    actualizarEstilos();
    vista.getComprar().isSelected();
    initEvents();
    try {
      mostrarProductos(org.tienda.model.Carrito.getProductos(vista.getUsuario()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public void actualizarLenguaje() {
    vista.getComprar().setText(lenguaje.getMensaje().getString("comprar"));
    vista.getTotal().setText("Total: " + org.tienda.model.Carrito.calcTotal(vista.getUsuario()) + "€");
  }

  public void actualizarEstilos() {
    // Estilos
    vista.getInformacion().putClientProperty("FlatLaf.style", "arc: 16");
    // Posicion
    Header header = new Header(vista, vista.getUsuario());
    header.remove(header.getSearch());
    vista.getContainer().add(header, new AbsoluteConstraints(15, 10, 1410, 50));

  }

  public void mostrarProductos(List<Productos> productos) throws IOException {
    JPanel panelProductos = vista.getContainerProductosCarrito();
    panelProductos.setLayout(new GridLayout(productos.size() == 0 ? 1 : productos.size(), 0, 0, 24));
    panelProductos.removeAll();

    for (Productos producto : productos) {
      carritoProductos carritoProductos = new carritoProductos(vista, producto);
      carritoProductos.setSize(1392, 96);
      panelProductos.add(carritoProductos);
    }

    panelProductos.revalidate();
    panelProductos.repaint();
  }

  public void initEvents() {
    vista.addWindowListener(new WindowAdapter() {
      @Override public void windowClosed(WindowEvent e) {
        Historialusuarios.sessionCerrada(vista, vista.getUsuario());
        vista.dispose();
      }
    });
  }

}
