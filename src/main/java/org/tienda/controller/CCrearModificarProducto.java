package org.tienda.controller;

import org.tienda.model.Productos;
import org.tienda.components.CrearModificarProducto;
import org.tienda.utils.utilsLenguaje;
import org.tienda.utils.utilsTextField;
import org.tienda.views.HomeUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author Carlos Varas Alonso - 25/01/2024 3:56
 */
public class CCrearModificarProducto {
  private final CrearModificarProducto vista;
  private final Productos producto;
  private final utilsLenguaje lenguaje;
  private final HomeUser vistaHome;

  public CCrearModificarProducto(CrearModificarProducto vista, HomeUser vistaHome, Productos producto) {
    this.vista = vista;
    this.producto = producto;
    this.vistaHome = vistaHome;
    lenguaje = new utilsLenguaje(vista.getUsuario());
    actualizarEstilos();
    actualizarLenguaje();
    initEvents();
    if (vista.getOpcion() == CrearModificarProducto.EDITAR) {
      setDatos(producto);
    }
  }

  private void setDatos(Productos producto) {
    vista.getJTextFieldNombre().setText(producto.getNombre());
    vista.getJTextFieldDescripcion().setText(producto.getDescripcion());
    vista.getJTextFieldPrecio().setText(String.valueOf(producto.getPrecio()));
    vista.getJTextFieldDescuento().setText(String.valueOf(producto.getDescuento()));
  }

  private void actualizarLenguaje() {
    if (vista.getOpcion() == CrearModificarProducto.CREAR) {
      vista.getTitulo().setText(lenguaje.getMensaje().getString("crearmodificarproducto.create.h1"));
      vista.getBoton().setText(lenguaje.getMensaje().getString("crearmodificarproducto.button.create"));
    } else if (vista.getOpcion() == CrearModificarProducto.EDITAR) {
      vista.getTitulo().setText(lenguaje.getMensaje().getString("crearmodificarproducto.modify.h1"));
      vista.getBoton().setText(lenguaje.getMensaje().getString("crearmodificarproducto.button.modify"));
    }
    vista.getNombre().setText(lenguaje.getMensaje().getString("crearmodificarproducto.label.name"));
    vista.getDescripcion().setText(lenguaje.getMensaje().getString("crearmodificarproducto.label.description"));
    vista.getPrecio().setText(lenguaje.getMensaje().getString("crearmodificarproducto.label.price"));
    vista.getDescuento().setText(lenguaje.getMensaje().getString("crearmodificarproducto.label.descount"));
    vista.getImagen().setText(lenguaje.getMensaje().getString("crearmodificarproducto.label.image"));
    vista.getSeleccionarImagen().setText(lenguaje.getMensaje().getString("crearmodificarproducto.button.selectImage"));

    vista.getJTextFieldNombre().putClientProperty("JTextField.placeholderText", lenguaje.getMensaje().getString("crearmodificarproducto.placeholder.name"));
    vista.getJTextFieldDescripcion().putClientProperty("JTextField.placeholderText", lenguaje.getMensaje().getString("crearmodificarproducto.placeholder.description"));
    vista.getJTextFieldPrecio().putClientProperty("JTextField.placeholderText", lenguaje.getMensaje().getString("crearmodificarproducto.placeholder.price"));
    vista.getJTextFieldDescuento().putClientProperty("JTextField.placeholderText", lenguaje.getMensaje().getString("crearmodificarproducto.placeholder.descount"));
  }

  private void actualizarEstilos() {
    vista.getContainer().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getJPanelProducto().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getBoton().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getSeleccionarImagen().putClientProperty("FlatLaf.style", "arc: 16");

    utilsTextField.actualizarTextField(vista.getJTextFieldNombre(), lenguaje.getMensaje().getString("crearmodificarproducto.placeholder.name"), 16, "img/svg/Email.svg", 19, 19, "#575DFB");
    utilsTextField.actualizarTextField(vista.getJTextFieldPrecio(), lenguaje.getMensaje().getString("crearmodificarproducto.placeholder.price"), 16, "img/svg/precio.svg", 24, 24, "#575DFB");
    utilsTextField.actualizarTextField(vista.getJTextFieldDescuento(), lenguaje.getMensaje().getString("crearmodificarproducto.placeholder.descount"), 16, "img/svg/descuento.svg", 24, 24, "#575DFB");
    utilsTextField.actualizarTextField(vista.getJTextFieldDescripcion(), lenguaje.getMensaje().getString("crearmodificarproducto.placeholder.description"), 16, "img/svg/descuento.svg", 20, 20, "#575DFB");

    vista.getBoton().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getSeleccionarImagen().putClientProperty("FlatLaf.style", "arc: 16");
    vista.getCerrar().putClientProperty("FlatLaf.style", "arc: 999");
    vista.getSeleccionarImagen().putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});
    vista.getContainer().putClientProperty("JComponent.outline", new Color[]{Color.decode("#575DFB"), Color.decode("#575DFB")});

    vista.getCerrar().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getSeleccionarImagen().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getBoton().setCursor(new Cursor(Cursor.HAND_CURSOR));
  }

  private void initEvents() {
    vista.getJTextFieldNombre().requestFocus();
    vista.getBoton().requestFocusInWindow();

    if (vista.getOpcion() == CrearModificarProducto.CREAR) {
      vista.getBoton().addActionListener(new ActionListener() {
        @Override public void actionPerformed(ActionEvent e) {

        }
      });
    } else if (vista.getOpcion() == CrearModificarProducto.EDITAR) {
      vista.getBoton().addActionListener(e -> {
        producto.setNombre(vista.getJTextFieldNombre().getText());
        producto.setDescripcion(vista.getJTextFieldDescripcion().getText());
        producto.setPrecio(Float.valueOf(vista.getJTextFieldPrecio().getText()));
        producto.setDescuento(Float.valueOf(vista.getJTextFieldDescuento().getText()));
        Productos.update(producto);
        vista.dispose();
        try {
          vistaHome.getChome().mostrarProductos(vistaHome.getChome().getProductos());
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      });
    }
    vista.getCerrar().addActionListener(e -> vista.dispose());
  }


}
