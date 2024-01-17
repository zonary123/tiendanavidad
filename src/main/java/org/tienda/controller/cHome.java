package org.tienda.controller;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.tienda.components.Header;
import org.tienda.components.jPanelProducts;
import org.tienda.model.Historialusuarios;
import org.tienda.model.Productos;
import org.tienda.utils.utilsTextField;
import org.tienda.views.HomeUser;
import org.tienda.views.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.tienda.utils.utilsLenguaje;

/**
 * The type controller home.
 *
 * @author Carlos Varas Alonso
 */
public class cHome {

  private HomeUser vista;
  private utilsTextField textField = new utilsTextField();
  private static utilsLenguaje lenguaje;
  private static Header header;

  /**
   * Constructor de la clase
   *
   * @param vista Vista de la clase
   */
  public cHome(HomeUser vista) {
    this.vista = vista;
    lenguaje = new utilsLenguaje(vista.getUsuario());
    componentes();
    actualizarLenguaje();
    actualizarEstilos();
    initEvents();
  }

  private void componentes() {
    header = new Header(vista, vista.getUsuario());
    vista.getContainer().add(header, new AbsoluteConstraints(15, 10, 1410, 50));
    try {
      mostrarProductos(Productos.findAll());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Actualiza el lenguaje de la vista
   */
  public void actualizarLenguaje() {
    vista.getSignOut().setText(lenguaje.getMensaje().getString("component.jPanelAsideBar.button.cerrarsession"));
  }

  /**
   * Actualiza los estilos de la vista
   */
  public void actualizarEstilos() {

    // vista.getScrollContainerProducts().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getContainerProducts().putClientProperty("FlatLaf.style", "arc: 16");

    vista.getSignOut().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getSideBar().putClientProperty("FlatLaf.style", "arc: 8");

    // Cursores
    vista.getSignOut().setCursor(new Cursor(Cursor.HAND_CURSOR));

  }


  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
    vista.getSignOut().addActionListener(e -> {
      historial();
      new Login(null).setVisible(true);
    });
    int p = 0;
    header.getSearch().addKeyListener(new KeyAdapter() {

      @Override public void keyReleased(KeyEvent e) {
        try {
          mostrarProductos(Productos.findByNombre(header.getSearch().getText()) == null ? Productos.findAll() : Productos.findByNombre(header.getSearch().getText()));
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
    });

    //System.out.println(header.getSearch());

    // Salir del programa
    vista.addWindowListener(new WindowAdapter() {
      @Override public void windowClosing(WindowEvent e) {
        historial();
      }
    });

  }

  private void historial() {
    Historialusuarios historialusuarios = new Historialusuarios();
    historialusuarios.setId(Historialusuarios.findRecent(vista.getUsuario()).getId());
    historialusuarios.setFechafinsesion(Timestamp.valueOf(LocalDateTime.now()));
    Historialusuarios.update(historialusuarios);
    vista.dispose();
  }

  /**
   * Este metodo se encarga de mostrar los productos en la vista
   *
   * @param productos Lista de productos
   */
  public void mostrarProductos(List<Productos> productos) throws IOException {
    JPanel panelProductos = this.vista.getContainerProducts();
    panelProductos.setLayout(new GridLayout(0, 3, 10, 10));
    panelProductos.removeAll();

    for (Productos producto : productos) {
      jPanelProducts jPanelProducts = new jPanelProducts(vista.getUsuario(), producto);
      jPanelProducts.setSize(350, 450);
      panelProductos.add(jPanelProducts);
    }

    panelProductos.revalidate();
    panelProductos.repaint();
  }

}
