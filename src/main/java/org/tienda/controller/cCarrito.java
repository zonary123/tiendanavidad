package org.tienda.controller;

import lombok.Getter;
import lombok.Setter;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.tienda.components.Header;
import org.tienda.components.ProductosUser;
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
  @Setter private static utilsLenguaje lenguaje;


  /**
   * Constructor del controlador del carrito
   *
   * @param vista Vista del carrito
   */
  public cCarrito(Carrito vista) {
    this.vista = vista;
    this.lenguaje = new utilsLenguaje(vista.getUsuario());
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


  /**
   * Actualiza el idioma del componente
   */
  public void actualizarLenguaje() {
    vista.getComprar().setText(lenguaje.getMensaje().getString("comprar"));
    vista.getTotal().setText("Total: " + org.tienda.model.Carrito.calcTotal(vista.getUsuario()) + "€");
  }

  /**
   * Actualiza los estilos del componente
   */
  public void actualizarEstilos() {
    // Estilos
    vista.getInformacion().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getCerrar().putClientProperty("FlatLaf.style", "arc: 999");
    vista.getCerrar().setCursor(new Cursor(Cursor.HAND_CURSOR));
    // Posicion
    Header header = new Header(vista, vista.getUsuario());
    header.remove(header.getSearch());
    vista.getContainer().add(header, new AbsoluteConstraints(15, 10, 1410, 50));

  }

  /**
   * Muestra los productos del carrito
   *
   * @param productos Productos del carrito
   *
   * @throws IOException Error al cargar el archivo de idioma
   */
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

  /**
   * Inicializa los eventos del componente
   */
  public void initEvents() {
    vista.addWindowListener(new WindowAdapter() {
      @Override public void windowClosed(WindowEvent e) {
        Historialusuarios.sessionCerrada(vista, vista.getUsuario());
        vista.dispose();
      }
    });
  }
}
