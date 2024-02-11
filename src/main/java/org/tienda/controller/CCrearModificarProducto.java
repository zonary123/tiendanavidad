package org.tienda.controller;

import org.tienda.model.Productos;
import org.tienda.components.CrearModificarProducto;
import org.tienda.utils.utilsLenguaje;
import org.tienda.utils.utilsTextField;
import org.tienda.validator.validator;
import org.tienda.views.HomeUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

/**
 * @author Carlos Varas Alonso - 25/01/2024 3:56
 */
public class CCrearModificarProducto {
  private static CrearModificarProducto vista;
  private Productos producto = null;
  private final utilsLenguaje lenguaje;
  private final HomeUser vistaHome;

  /**
   * Constructor de la clase
   *
   * @param vista     Vista de la clase
   * @param vistaHome Vista de la clase HomeUser
   * @param producto  Producto a modificar
   */
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

  /**
   * Constructor de la clase
   *
   * @param vista     Vista de la clase
   * @param vistaHome Vista de la clase HomeUser
   */
  public CCrearModificarProducto(CrearModificarProducto vista, HomeUser vistaHome) {
    this.vista = vista;
    this.vistaHome = vistaHome;
    lenguaje = new utilsLenguaje(vista.getUsuario());
    actualizarEstilos();
    actualizarLenguaje();
    initEvents();
  }

  /**
   * Actualiza el idioma del componente
   *
   * @param producto Producto a mostrar
   */
  private void setDatos(Productos producto) {
    vista.getJTextFieldNombre().setText(producto.getNombre());
    vista.getJTextFieldDescripcion().setText(producto.getDescripcion());
    vista.getJTextFieldPrecio().setText(String.valueOf(producto.getPrecio()));
    vista.getJTextFieldDescuento().setText(String.valueOf(producto.getDescuento()));
  }

  /**
   * Actualiza el idioma del componente
   */
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

  /**
   * Actualiza los estilos del componente
   */
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

  /**
   * Inicializa los eventos del componente
   */
  private void initEvents() {
    vista.getRootPane().setDefaultButton(vista.getBoton());
    vista.getJTextFieldNombre().requestFocus();
    vista.getBoton().requestFocusInWindow();


    // No es necesario tanto if
    if (vista.getOpcion() == CrearModificarProducto.CREAR) {
      vista.getBoton().addActionListener(e -> {
        producto = new Productos();
        producto.setNombre(vista.getJTextFieldNombre().getText());
        producto.setDescripcion(vista.getJTextFieldDescripcion().getText());
        producto.setPrecio(vista.getJTextFieldPrecio().getText().isEmpty() ? 0 : Float.parseFloat(vista.getJTextFieldPrecio().getText()));
        producto.setDescuento(vista.getJTextFieldDescuento().getText().isEmpty() ? 0 : Float.parseFloat(vista.getJTextFieldDescuento().getText()));
        producto.setCategoria("PC");
        producto.setImagen("/img/productos/" + new Random().nextInt(1, 3) + ".png");
        if (comprobarDatosProducto(producto)) {
          Productos.save(producto);
          vista.dispose();
          try {
            vistaHome.getChome().mostrarProductos(cHome.getProductos());
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        } else {
          System.out.println("Errores");
        }
      });
    } else if (vista.getOpcion() == CrearModificarProducto.EDITAR) {
      vista.getBoton().addActionListener(e -> {
        producto.setNombre(vista.getJTextFieldNombre().getText());
        producto.setDescripcion(vista.getJTextFieldDescripcion().getText());
        producto.setPrecio(vista.getJTextFieldPrecio().getText().isEmpty() ? 0 : Float.parseFloat(vista.getJTextFieldPrecio().getText()));
        producto.setDescuento(vista.getJTextFieldDescuento().getText().isEmpty() ? 0 : Float.parseFloat(vista.getJTextFieldDescuento().getText()));
        producto.setCategoria("PC");
        producto.setImagen("/img/productos/" + new Random().nextInt(1, 3) + ".png");
        if (comprobarDatosProducto(producto)) {
          Productos.update(producto);
          vista.dispose();
          try {
            vistaHome.getChome().mostrarProductos(cHome.getProductos());
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        } else {
          System.out.println("Errores");
        }
      });
    }
    vista.getCerrar().addActionListener(e -> vista.dispose());
  }

  /**
   * Comprueba los datos del producto
   *
   * @param p Producto a comprobar
   *
   * @return true si los datos son correctos, false si no
   */
  private static boolean comprobarDatosProducto(Productos p) {
    int fallos = 0;
    String mensaje = "";
    if (!p.getNombre().matches(validator.NOMBRE_PRODUCTO_PATTERN)) {
      vista.getJTextFieldNombre().putClientProperty("JComponent.outline", "warning");
      fallos++;
      mensaje += "Nombre incorrecto\n";
    } else {
      vista.getJTextFieldNombre().putClientProperty("JComponent.outline", "default");
    }
    if (!p.getDescripcion().matches(validator.DESCRIPCION_PRODUCTO_PATTERN)) {
      vista.getJTextFieldDescripcion().putClientProperty("JComponent.outline", "warning");
      fallos++;
      mensaje += "Descripcion incorrecta\n";
    } else {
      vista.getJTextFieldDescripcion().putClientProperty("JComponent.outline", "default");
    }
    if (p.getPrecio() == 0 || p.getPrecio() <= 1) {
      vista.getJTextFieldPrecio().putClientProperty("JComponent.outline", "warning");
      fallos++;
      mensaje += "Precio incorrecto o menor a 1€\n";
    } else {
      vista.getJTextFieldPrecio().putClientProperty("JComponent.outline", "default");
    }

/*
    if (p.getDescuento() == null) {
      vista.getJTextFieldDescuento().putClientProperty("JComponent.outline", "warning");
      fallos++;
      mensaje += "Descuento vacio\n";
    }
*/

    if (fallos > 0) {
      JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return true;
  }

}
