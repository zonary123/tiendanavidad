package org.tienda.controller;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.tienda.components.Header;
import org.tienda.components.jPanelProducts;
import org.tienda.model.Carrito;
import org.tienda.model.Historialusuarios;
import org.tienda.model.Productos;
import org.tienda.model.Usuarios;
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
import org.tienda.views.crearProducto;

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
  private static JButton btnAddProduct = new JButton();


  /**
   * Constructor de la clase
   *
   * @param vista Vista de la clase
   */
  public cHome(HomeUser vista) {
    this.vista = vista;
    lenguaje = new utilsLenguaje(vista.getUsuario());
    actualizarLenguaje();
    actualizarEstilos();
    componentes();
    initEvents();
  }

  private void componentes() {
    header = new Header(vista, vista.getUsuario());
    vista.getContainer().add(header, new AbsoluteConstraints(15, 10, 1410, 50));
    if (vista.getUsuario().getRoles().split("\"")[1].equals("admin")) {
      System.out.println("Tiene un gran porro en la mano");
      botonAñadir();

      System.out.println(vista.getScrollContainerProducts().getSize() + " - " + vista.getScrollContainerProducts().getLocation());
      vista.getScrollContainerProducts().setSize(vista.getScrollContainerProducts().getWidth(), 858);
      vista.getScrollContainerProducts().setLocation(vista.getScrollContainerProducts().getX(), 130);
      vista.getContainerProducts().setSize(vista.getContainerProducts().getWidth(), 858);
      vista.getContainerProducts().setLocation(vista.getContainerProducts().getX(), 130);
      vista.getContainerProducts().revalidate();
      vista.getContainerProducts().repaint();
      vista.getScrollContainerProducts().revalidate();
      vista.getScrollContainerProducts().repaint();


    }
    try {
      mostrarProductos(Productos.findAll());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // No va
  private void botonAñadir() {
    btnAddProduct.setText(lenguaje.getMensaje().getString("Añadir"));
    btnAddProduct.setForeground(Color.WHITE);
    btnAddProduct.setBackground(Color.decode("#58D12E"));
    btnAddProduct.setSize(279, 56);
    btnAddProduct.setLocation(728, 67);
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
    btnAddProduct.putClientProperty("FlatLaf.style", "arc: 16");
    vista.getSignOut().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getSideBar().putClientProperty("FlatLaf.style", "arc: 8");
    vista.getContainer().putClientProperty("FlatLaf.style", "arc: 8");
    // Cursores
    vista.getSignOut().setCursor(new Cursor(Cursor.HAND_CURSOR));

  }


  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
    vista.getSignOut().addActionListener(e -> {
      Historialusuarios.sessionCerrada(vista, vista.getUsuario());
      new Login(null).setVisible(true);
    });

    header.getSearch().addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        try {
          mostrarProductos(Productos.findByNombre(header.getSearch().getText()) == null ? Productos.findAll() : Productos.findByNombre(header.getSearch().getText()));
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
    });

    btnAddProduct.addActionListener(e -> {
      new crearProducto().setVisible(true);
    });
    //System.out.println(header.getSearch());

    // Salir del programa
    vista.addWindowListener(new WindowAdapter() {
      @Override public void windowClosing(WindowEvent e) {
        Historialusuarios.sessionCerrada(vista, vista.getUsuario());
      }
    });

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
