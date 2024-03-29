/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.tienda.components;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.tienda.model.Carrito;
import org.tienda.model.CarritoId;
import org.tienda.model.Productos;
import org.tienda.model.Usuarios;
import org.tienda.utils.utilsLenguaje;
import org.tienda.views.HomeUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * @author Carlos Varas Alonso
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class ProductosAdmin extends javax.swing.JPanel {

  private int id;
  private Usuarios usuario;
  private static utilsLenguaje lenguaje;
  private static HomeUser vista;
  private Random random = new Random();

  /**
   * Constructor del componente ProductosAdmin
   *
   * @param usuario  Usuario que realiza la acción
   * @param producto Producto a mostrar
   * @param vista    Vista que contiene el componente
   *
   * @throws IOException
   */
  public ProductosAdmin(Usuarios usuario, Productos producto, HomeUser vista) throws IOException {
    this.id = producto.getIdproducto();
    this.usuario = usuario;
    this.vista = vista;
    lenguaje = new utilsLenguaje(usuario.getLenguaje());
    initComponents();
    setSize(350, 450);
    setDatos(producto);
    posicionar();
    actualizarEstilos();
    initEvents(producto);
  }


  /**
   * Actualiza los estilos del componente
   */
  private void actualizarEstilos() {
    putClientProperty("FlatLaf.style", "arc: 16");
    getComprar().putClientProperty("FlatLaf.style", "arc: 16");
    getEditar().putClientProperty("FlatLaf.style", "arc: 16");
    getEliminar().putClientProperty("FlatLaf.style", "arc: 16");
    getComprar().setIcon(obtenerIconoBlanco("img/svg/carrito.svg", new Dimension(26, 26)));
    getComprar().setCursor(new Cursor(Cursor.HAND_CURSOR));
    getEditar().setIcon(obtenerIconoBlanco("img/svg/editar.svg", new Dimension(26, 26)));
    getEditar().setCursor(new Cursor(Cursor.HAND_CURSOR));
    getEliminar().setIcon(obtenerIconoBlanco("img/svg/basura.svg", new Dimension(14, 18)));
    getEliminar().setCursor(new Cursor(Cursor.HAND_CURSOR));
  }

  /**
   * Obtiene un icono SVG con el color blanco
   *
   * @param path        Ruta del icono
   * @param dimensiones Dimensiones del icono
   *
   * @return Icono SVG
   */

  private FlatSVGIcon obtenerIconoBlanco(String path, Dimension dimensiones) {
    FlatSVGIcon icon = new FlatSVGIcon(path, (int) dimensiones.getWidth(), (int) dimensiones.getHeight());
    Function<Color, Color> colors = c -> new Color(255, 255, 255);
    icon.setColorFilter(new FlatSVGIcon.ColorFilter(colors));
    return icon;
  }

  /**
   * Inicializa los eventos del componente
   *
   * @param producto Producto con el que se inicializan los eventos
   */
  public void initEvents(Productos producto) {
    getComprar().addActionListener(e
      -> {
      Usuarios u = Usuarios.findByUsername(usuario.getUsername());
      if (Carrito.findById(id, u)) {
        Carrito.updateCant(id, u);
      } else {
        Carrito.save(new CarritoId(id, u.getIdusuario()), 1);
      }
      JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("joptionpane.carrito.add"), "Carrito", JOptionPane.INFORMATION_MESSAGE);
    });
    getEditar().addActionListener(e -> new CrearModificarProducto(producto, getUsuario(), CrearModificarProducto.EDITAR, vista).setVisible(true));
    getEliminar().addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        Productos.delete(producto);
        List<Productos> productos = vista.getChome().getProductos();
        productos.remove(producto);
        try {
          vista.getChome().mostrarProductos(productos);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
  }

  /**
   * Posiciona el precio
   */
  private void posicionar() {
    int anchoOriginal = (int) Precio.getPreferredSize().getWidth();
    Precio.setLocation(Precio.getX(), Precio.getY());
    Precio.setPreferredSize(new Dimension(anchoOriginal * 2, (int) Precio.getPreferredSize().getHeight()));
  }

  /**
   * Establece los datos del producto en el componente
   *
   * @param producto Producto a mostrar
   */
  private void setDatos(Productos producto) {
    int limiteCaracteres = 12;
    String nombreProducto = producto.getNombre();
    String nombreFormateado;

    if (nombreProducto.length() > limiteCaracteres) {
      nombreFormateado = "<html><p style='text-align: center;'>" + nombreProducto.substring(0, limiteCaracteres) + "...</p></html>";
      Nombre.setToolTipText(producto.getNombre());
    } else {
      nombreFormateado = "<html><p style='text-align: center;'>" + nombreProducto + "</p></html>";
    }

    Nombre.setText(nombreFormateado);
    Precio.setText("<html>\n"
      + "  <p style='text-align: right; font-size: 12px; direction: rtl;'>" + producto.getPrecio() + "€</p>\n"
      + "</html>\n");
    Descripcion.setText("<html>\n"
      + "  <p style='text-align: justify;'>" + (producto.getDescripcion() == null ? "undefined" : producto.getDescripcion()) + "</p>\n"
      + "</html>\n");
    IMG.setText(null);
    IMG.setIcon(new ImageIcon(getClass().getResource(producto.getImagen() == null ? "" : producto.getImagen())));
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jButton1 = new javax.swing.JButton();
    IMG = new javax.swing.JLabel();
    Nombre = new javax.swing.JLabel();
    Precio = new javax.swing.JLabel();
    Descripcion = new javax.swing.JLabel();
    Comprar = new javax.swing.JButton();
    Editar = new javax.swing.JButton();
    Eliminar = new javax.swing.JButton();

    jButton1.setText("jButton1");

    setBackground(new java.awt.Color(231, 231, 231));
    setPreferredSize(new java.awt.Dimension(350, 450));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    IMG.setBackground(new java.awt.Color(255, 255, 255));
    IMG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    IMG.setText("IMG");
    IMG.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    add(IMG, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 25, 300, 200));

    Nombre.setFont(new java.awt.Font("Inter SemiBold", 1, 24)); // NOI18N
    Nombre.setText("Nombre");
    add(Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 252, -1, -1));
    Nombre.getAccessibleContext().setAccessibleParent(this);

    Precio.setFont(new java.awt.Font("Inter SemiBold", 0, 20)); // NOI18N
    Precio.setForeground(new java.awt.Color(87, 93, 251));
    Precio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    Precio.setText("200€");
    Precio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    Precio.setPreferredSize(new java.awt.Dimension(100, 15));
    add(Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, -1, 20));
    Precio.getAccessibleContext().setAccessibleParent(this);

    Descripcion.setText("Descripcion");
    add(Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 291, 300, 78));
    Descripcion.getAccessibleContext().setAccessibleParent(this);

    Comprar.setBackground(new java.awt.Color(15, 109, 142));
    Comprar.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    Comprar.setForeground(new java.awt.Color(255, 255, 255));
    add(Comprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 403, 90, 35));

    Editar.setBackground(new java.awt.Color(78, 108, 84));
    Editar.setForeground(new java.awt.Color(255, 255, 255));
    add(Editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 403, 90, 35));

    Eliminar.setBackground(new java.awt.Color(255, 86, 86));
    Eliminar.setForeground(new java.awt.Color(255, 255, 255));
    add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 403, 90, 35));
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton Comprar;
  private javax.swing.JLabel Descripcion;
  private javax.swing.JButton Editar;
  private javax.swing.JButton Eliminar;
  private javax.swing.JLabel IMG;
  private javax.swing.JLabel Nombre;
  private javax.swing.JLabel Precio;
  private javax.swing.JButton jButton1;
  // End of variables declaration//GEN-END:variables
}
