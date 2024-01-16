package org.tienda.Controller;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import org.tienda.Components.Header;
import org.tienda.Components.jPanelProducts;
import org.tienda.Model.Historialusuarios;
import org.tienda.Model.HistorialusuariosId;
import org.tienda.Model.Productos;
import org.tienda.Model.Usuarios;
import org.tienda.Utils.utilsTextField;
import org.tienda.Views.HomeUser;
import org.tienda.Views.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.tienda.Utils.utilsLenguaje;

/**
 * The type Controller home.
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
      Historialusuarios historialusuarios = new Historialusuarios();
      historialusuarios.setId(Historialusuarios.findRecent(vista.getUsuario()).getId());
      historialusuarios.setFechafinsesion(Timestamp.valueOf(LocalDateTime.now()));
      System.out.println("Historial ->" + historialusuarios);
      System.out.println("Mas reciente -> " + Historialusuarios.findRecent(vista.getUsuario()).getId());
      Historialusuarios.update(historialusuarios);
      vista.dispose();
      new Login(null).setVisible(true);
    });
    int p = 0;
    header.getSearch().addKeyListener(new KeyListener() {
      @Override public void keyTyped(KeyEvent e) {

      }

      @Override public void keyPressed(KeyEvent e) {

      }

      @Override public void keyReleased(KeyEvent e) {
        try {
          mostrarProductos(Productos.findByNombre(header.getSearch().getText()) == null ? Productos.findAll() : Productos.findByNombre(header.getSearch().getText()));
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
    });

    //System.out.println(header.getSearch());

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
