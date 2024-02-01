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

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.function.Function;

/**
 * @author Carlos Varas Alonso
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class ProductosUser extends javax.swing.JPanel {

  private int id;
  private Usuarios usuario;
  private static utilsLenguaje lenguaje;

  /**
   * Creates new form jPanelProducts
   *
   * @param usuario  the user
   * @param producto the producto
   */
  public ProductosUser(Usuarios usuario, Productos producto) throws IOException {
    this.id = producto.getIdproducto();
    this.usuario = usuario;
    lenguaje = new utilsLenguaje(usuario.getLenguaje());
    initComponents();
    setSize(350, 450);
    setDatos(producto);
    posicionar();
    actualizarEstilos();
    actualizarLenguaje();
    initEvents();

  }

  private void actualizarEstilos() {
    putClientProperty("FlatLaf.style", "arc: 16");
    getComprar().putClientProperty("FlatLaf.style", "arc: 16");
    getComprar().putClientProperty("FlatLaf.leadingIcon", new FlatSVGIcon("img/svg/carrito.svg"));

    FlatSVGIcon icon = new FlatSVGIcon("img/svg/carrito.svg");
    Function<Color, Color> colors = c -> new Color(255, 255, 255);
    icon.setColorFilter(new FlatSVGIcon.ColorFilter(colors));
    getComprar().setIcon(icon);
    getComprar().setIconTextGap(10);
    getComprar().setCursor(new Cursor(Cursor.HAND_CURSOR));
  }

  /**
   * Init events.
   */
  public void initEvents() {
    getComprar().addActionListener(e
      -> {
      Usuarios u = Usuarios.findByUsername(usuario.getUsername());
      if (Carrito.findById(id, u)) {
        Carrito.updateCant(id, u);
      } else {
        Carrito.save(new CarritoId(id, u.getIdusuario()), 1);
      }
    });
  }


  private void actualizarLenguaje() {
    getComprar().setText(lenguaje.getMensaje().getString("comprar"));
  }

  private void posicionar() {
    int anchoOriginal = (int) Precio.getPreferredSize().getWidth();
    Precio.setLocation(Precio.getX(), Precio.getY());
    Precio.setPreferredSize(new Dimension(anchoOriginal * 2, (int) Precio.getPreferredSize().getHeight()));

  }

  private void setDatos(Productos producto) {
    Nombre.setText(producto.getNombre() == null ? "undefined" : producto.getNombre());
    Precio.setText("<html>\n"
      + "  <p style='text-align: right; font-size: 12px;'>" + producto.getPrecio() + "€</p>\n"
      + "</html>\n");
    Descripcion.setText(producto.getDescripcion() == null ? "undefined" : producto.getDescripcion());
    IMG.setText(null);
    IMG.setIcon(new ImageIcon(getClass().getResource("/img/productos/" + new Random().nextInt(1, 4) + ".png")));
    IMG.putClientProperty("FlatLaf.style", "arc: 16");
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
    Comprar.setText("Comprar");
    add(Comprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 403, 302, 35));
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton Comprar;
  private javax.swing.JLabel Descripcion;
  private javax.swing.JLabel IMG;
  private javax.swing.JLabel Nombre;
  private javax.swing.JLabel Precio;
  private javax.swing.JButton jButton1;
  // End of variables declaration//GEN-END:variables
}
