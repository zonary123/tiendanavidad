/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.tienda.components;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import lombok.Getter;
import lombok.Setter;
import org.tienda.controller.cHome;
import org.tienda.model.Productos;
import org.tienda.model.Usuarios;
import org.tienda.utils.utilsLenguaje;
import org.tienda.utils.utilsTextField;
import org.tienda.views.Carrito;
import org.tienda.views.HomeUser;
import org.tienda.views.datosUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * @author Carlos Varas Alonso
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
    actualizarLenguaje();
    actualizarEstilos();
    initEvents();
  }

  private void initEvents() {
    getHome().addMouseListener(new MouseAdapter() {
      @Override public void mouseReleased(MouseEvent e) {
        vista.dispose();
        new HomeUser(getUsuario()).setVisible(true);
      }
    });

    getCarrito().addMouseListener(new MouseAdapter() {

      @Override public void mouseReleased(MouseEvent e) {
        vista.dispose();
        new Carrito(usuario).setVisible(true);
      }

    });

    getUser().addMouseListener(new MouseAdapter() {
      @Override public void mouseReleased(MouseEvent e) {
        vista.dispose();
        new datosUsuario(usuario).setVisible(true);
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
    usuario.setLenguaje(locale);
    Usuarios.updateLang(getUsuario());
    vistas();
  }

  private void vistas() {
    if (vista.getClass().getName().equals("org.tienda.views.HomeUser")) {
      HomeUser homeUser = (HomeUser) vista;
      homeUser.getChome().setLenguaje(new utilsLenguaje(usuario));
      homeUser.getChome().actualizarLenguaje();
      homeUser.getChome().actualizarEstilos();
      try {
        homeUser.getChome().mostrarProductos(Productos.findAll());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      vista.repaint();
      vista.revalidate();
    }
    if (vista.getClass().getName().equals("org.tienda.views.Carrito")) {
      Carrito carrito = (Carrito) vista;
      carrito.getControlador().setLenguaje(new utilsLenguaje(usuario));
      carrito.getControlador().actualizarLenguaje();
      carrito.getControlador().actualizarEstilos();
      try {
        carrito.getControlador().mostrarProductos(org.tienda.model.Carrito.getProductos(usuario));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      vista.repaint();
      vista.revalidate();
    }
    if (vista.getClass().getName().equals("org.tienda.views.datosUsuario")) {
      datosUsuario carrito = (datosUsuario) vista;
      carrito.getControlador().setLenguaje(new utilsLenguaje(usuario));
      carrito.getControlador().actualizarLenguaje();
      carrito.getControlador().actualizarEstilos();
      vista.repaint();
      vista.revalidate();
    }
  }

  private void actualizarEstilos() {
    // Props
    this.putClientProperty("FlatLaf.style", "arc: 999");
    getUser().putClientProperty("FlatLaf.style", "arc: 999");
    // Posicion
    //getUser().setSize(new Dimension((int) (this.getJLabelUsername().getWidth() * 1.5) + 5, this.getJLabelUsername().getHeight()));
    //getUser().setLocation(5, 7);
    getUser().setSize(new Dimension((int) getUser().getWidth() + 10, getUser().getHeight()));
    // Iconos
    getCarrito().setIcon(new FlatSVGIcon("img/svg/carrito.svg"));
    getCampana().setIcon(new FlatSVGIcon("img/svg/mdi_bell.svg"));
    getHome().setIcon(new FlatSVGIcon("img/svg/clarity_home-solid.svg"));
    getJLabel1().setIcon(new ImageIcon(getClass().getResource("/img/user/user.png")));

    // Cursores
    getCarrito().setCursor(new Cursor(Cursor.HAND_CURSOR));
    getCampana().setCursor(new Cursor(Cursor.HAND_CURSOR));
    getHome().setCursor(new Cursor(Cursor.HAND_CURSOR));
    getUser().setCursor(new Cursor(Cursor.HAND_CURSOR));
    // textFields
    textField.actualizarTextField(getSearch(), lenguaje.getMensaje().getString("buscar"), 999, "img/svg/search.svg", 22, 24, "#FFFFFF");
  }

  private void actualizarLenguaje() {
    jLabelUsername.setText(usuario.getUsername() + "   ");
    getSearch().setText("");
    jLabel1.setText(null);

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
    setPreferredSize(new java.awt.Dimension(1410, 50));
    setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    User.setBackground(new java.awt.Color(255, 255, 255));
    User.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel1.setText("img");
    User.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 5, 26, 26));

    jLabelUsername.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
    jLabelUsername.setText("Username");
    User.add(jLabelUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

    add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 7, -1, 36));

    Search.setText("Buscar");
    add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 7, 300, 36));

    Idioma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Español", "Ingles"}));
    add(Idioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(1291, 8, 110, 34));

    Campana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Campana.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    add(Campana, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 12, 26, 26));

    Carrito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Carrito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    add(Carrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 14, 26, 24));

    Home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    Home.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    add(Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 13, 26, 26));
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
