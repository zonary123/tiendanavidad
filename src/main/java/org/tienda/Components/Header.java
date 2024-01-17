/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.tienda.Components;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import lombok.Getter;
import lombok.Setter;
import org.tienda.Model.Usuarios;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Utils.utilsTextField;
import org.tienda.Views.Carrito;
import org.tienda.Views.HomeUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author carlos
 */
@Getter
@Setter
public class Header extends javax.swing.JPanel {
  private utilsTextField textField = new utilsTextField();
  private Usuarios usuario;
  private static utilsLenguaje lenguaje;
  private JFrame vista;


  /**
   * Creates new form Header
   */
  public Header(JFrame vista, Usuarios usuario) {
    initComponents();
    this.usuario = usuario;
    this.vista = vista;
    lenguaje = new utilsLenguaje(usuario);
    idioma(usuario.getLenguaje());
    actualizarEstilos();
    actualizarLenguaje();
    initEvents();
  }

  private void initEvents() {
    getHome().addMouseListener(new MouseListener() {
      @Override public void mouseClicked(MouseEvent e) {

      }

      @Override public void mousePressed(MouseEvent e) {

      }

      @Override public void mouseReleased(MouseEvent e) {
        vista.dispose();
        new HomeUser(getUsuario()).setVisible(true);
      }

      @Override public void mouseEntered(MouseEvent e) {

      }

      @Override public void mouseExited(MouseEvent e) {

      }
    });

    getCarrito().addMouseListener(new MouseListener() {
      @Override public void mouseClicked(MouseEvent e) {

      }

      @Override public void mousePressed(MouseEvent e) {

      }

      @Override public void mouseReleased(MouseEvent e) {
        vista.dispose();
        new Carrito(usuario).setVisible(true);
      }

      @Override public void mouseEntered(MouseEvent e) {

      }

      @Override public void mouseExited(MouseEvent e) {

      }
    });

    getIdioma().addActionListener(e -> {
      switch (getIdioma().getSelectedIndex()) {
        case 1:
          lang("en_US");
          break;
        case 2:
          lang("de_DE");
          break;
        default:
          lang("es_ES");
          break;
      }
    });
  }

  private void lang(String locale) {
    getUsuario().setLenguaje(locale);
    Usuarios.update(getUsuario());
    vistas();
  }

  private void vistas() {
    vista.dispose();
    if (vista.getClass().getName().equals("org.tienda.Views.HomeUser")) {
      new HomeUser(getUsuario()).setVisible(true);
    }
    if (vista.getClass().getName().equals("org.tienda.Views.Carrito")) {
      new Carrito(usuario).setVisible(true);
    }
  }

  private void actualizarEstilos() {
    // Props
    this.putClientProperty("FlatLaf.style", "arc: 999");
    getUser().putClientProperty("FlatLaf.style", "arc: 999");
    // Posicion
    getUser().setSize(new Dimension((int) (this.getJLabelUsername().getWidth() * 1.5) + 5, this.getJLabelUsername().getHeight()));
    getUser().setLocation(5, 7);
    // Iconos
    getCarrito().setIcon(new FlatSVGIcon("img/svg/carrito.svg"));
    getCampana().setIcon(new FlatSVGIcon("img/svg/mdi_bell.svg"));
    getHome().setIcon(new FlatSVGIcon("img/svg/clarity_home-solid.svg"));

    // Cursores
    getCarrito().setCursor(new Cursor(Cursor.HAND_CURSOR));
    getCampana().setCursor(new Cursor(Cursor.HAND_CURSOR));
    getHome().setCursor(new Cursor(Cursor.HAND_CURSOR));

    // textFields
    textField.actualizarTextField(getSearch(), lenguaje.getMensaje().getString("buscar"), 999, "img/svg/search.svg", 22, 24, "#FFFFFF");
  }

  private void actualizarLenguaje() {
    jLabelUsername.setText(usuario.getUsername());
    getSearch().setText("");

  }

  private void idioma(String idioma) {
    switch (idioma) {
      case "es_ES":
        getIdioma().setSelectedIndex(0);
        break;
      case "en_US":
        getIdioma().setSelectedIndex(1);
        break;
      default:
        getIdioma().setSelectedIndex(0);
        break;
    }
    actualizarLenguaje();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    User = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabelUsername = new javax.swing.JLabel();
    Search = new javax.swing.JTextField();
    Idioma = new javax.swing.JComboBox<>();
    Campana = new javax.swing.JLabel();
    Carrito = new javax.swing.JLabel();
    Home = new javax.swing.JLabel();

    setBackground(new java.awt.Color(231, 231, 231));

    User.setBackground(new java.awt.Color(255, 255, 255));
    User.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel1.setText("img");
    User.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 5, 26, 26));

    jLabelUsername.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    jLabelUsername.setText("Username");
    User.add(jLabelUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

    Search.setText("Buscar");

    Idioma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Español", "Ingles"}));

    Campana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Campana.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    Carrito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Carrito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    Home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Home.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addGap(0, 9, Short.MAX_VALUE)
          .addComponent(User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGap(429, 429, 429)
          .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGap(373, 373, 373)
          .addComponent(Campana, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGap(14, 14, 14)
          .addComponent(Carrito, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGap(14, 14, 14)
          .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGap(14, 14, 14)
          .addComponent(Idioma, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGap(0, 9, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addGap(0, 7, Short.MAX_VALUE)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(User, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
              .addGap(5, 5, 5)
              .addComponent(Campana, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
              .addGap(7, 7, 7)
              .addComponent(Carrito, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
              .addGap(6, 6, 6)
              .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
              .addGap(1, 1, 1)
              .addComponent(Idioma, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addGap(0, 7, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel Campana;
  private javax.swing.JLabel Carrito;
  private javax.swing.JLabel Home;
  private javax.swing.JComboBox<String> Idioma;
  private javax.swing.JTextField Search;
  private javax.swing.JPanel User;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabelUsername;
  // End of variables declaration//GEN-END:variables
}
