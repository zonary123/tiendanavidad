/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.tienda.components;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.tienda.model.Carrito;
import org.tienda.model.Productos;

/**
 * @author Carlos Varas Alonso
 */
public class carritoProductos extends javax.swing.JPanel {
  private final Productos producto;
  private final org.tienda.views.Carrito vista;
  private Carrito carrito;

  /**
   * Creates new form carritoProductos
   */
  public carritoProductos(org.tienda.views.Carrito vista, Productos producto) {
    this.producto = producto;
    this.vista = vista;
    carrito = Carrito.findProductoByCarrito(producto.getIdproducto(), vista.getUsuario());
    initComponents();
    setSize(1392, 96);
    setPreferredSize(new Dimension(1392, 96));
    setDatos();
    actualizarEstilos();
    initEvents();
  }

  private void setDatos() {
    jLabelInformacion.setText("<html>" + producto.getNombre() + " <span style='color: #575DFB;'>" + (producto.getPrecio() * carrito.getCantidad()) + "€" + "</span></html>");
    cantidad.setValue(carrito.getCantidad());
    IMG.setText(null);
    IMG.setIcon(new ImageIcon(getClass().getResource(producto.getImagen() == null ? "" : producto.getImagen())));
  }

  private void initEvents() {
    delete.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        Carrito.deleteProducto(vista.getUsuario(), producto);
        try {
          mostrarProductos(Carrito.getProductos(vista.getUsuario()));
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
        vista.getTotal().setText("Total: " + Carrito.calcTotal(vista.getUsuario()) + "€");
      }
    });
    cantidad.addChangeListener(new ChangeListener() {
      @Override public void stateChanged(ChangeEvent e) {

        Carrito.updateCant(producto.getIdproducto(), vista.getUsuario(), (Integer) cantidad.getValue());
        carrito.setCantidad((Integer) cantidad.getValue());
        jLabelInformacion.setText("<html>" + producto.getNombre() + " <span style='color: #575DFB;'>" + (producto.getPrecio() * carrito.getCantidad()) + "€" + "</span></html>");
        if ((int) cantidad.getValue() < 1) {
          Carrito.deleteProducto(vista.getUsuario(), producto);
          try {
            mostrarProductos(Carrito.getProductos(vista.getUsuario()));
          } catch (IOException ioException) {
            ioException.printStackTrace();
          }
        }
        vista.getTotal().setText("Total: " + Carrito.calcTotal(vista.getUsuario()) + "€");
      }
    });
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

  private void actualizarEstilos() {
    delete.putClientProperty("FlatLaf.style", "arc: 8");
    delete.setIcon(new FlatSVGIcon("img/svg/basura.svg"));

    //precio.setLocation(0, 0);
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    IMG = new javax.swing.JLabel();
    jLabelInformacion = new javax.swing.JLabel();
    delete = new javax.swing.JButton();
    cantidad = new javax.swing.JSpinner();

    setBackground(new java.awt.Color(231, 231, 231));
    setPreferredSize(new java.awt.Dimension(1392, 96));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    IMG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    IMG.setText("IMG");
    add(IMG, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 64, 64));

    jLabelInformacion.setFont(new java.awt.Font("Inter SemiBold", 0, 32)); // NOI18N
    jLabelInformacion.setText("Nombre");
    add(jLabelInformacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 32, -1, -1));

    delete.setBackground(new java.awt.Color(255, 86, 86));
    delete.setForeground(new java.awt.Color(255, 255, 255));
    add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1254, 16, 124, 64));

    cantidad.setFont(new java.awt.Font("Inter", 1, 16)); // NOI18N
    add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(1143, 16, 90, 64));
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel IMG;
  private javax.swing.JSpinner cantidad;
  private javax.swing.JButton delete;
  private javax.swing.JLabel jLabelInformacion;
  // End of variables declaration//GEN-END:variables
}
