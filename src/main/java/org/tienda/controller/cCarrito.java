package org.tienda.controller;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.tienda.components.Header;
import org.tienda.model.Historialusuarios;
import org.tienda.model.Usuarios;
import org.tienda.views.Carrito;
import org.tienda.utils.utilsLenguaje;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    initEvents();
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
    vista.getContainer().add(header, new AbsoluteConstraints(15, 10, 1410, 50));

  }

  public void initEvents() {
    vista.addWindowListener(new WindowAdapter() {
      @Override public void windowClosed(WindowEvent e) {
        historial();
        vista.dispose();
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
}
