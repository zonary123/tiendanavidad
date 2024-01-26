package org.tienda.controller;

import lombok.Getter;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.tienda.components.Header;
import org.tienda.components.ProductosAdmin;
import org.tienda.model.Historialusuarios;
import org.tienda.model.Productos;
import org.tienda.utils.utilsTextField;
import org.tienda.views.HomeUser;
import org.tienda.views.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

import org.tienda.utils.utilsLenguaje;

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
  @Getter private static List<Productos> productos;

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
    ponercategorias();
  }

  private void ponercategorias() {
    JPanel sidebar = vista.getContainerCategories();
    List<String> categorias = Productos.getAllProductos();
    sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

    for (String c : categorias) {
      JLabel jlabel = new JLabel("• " + c);
      jlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
      jlabel.addMouseListener(new MouseAdapter() {

        @Override public void mouseReleased(MouseEvent e) {
          try {
            mostrarProductos(Productos.findByCategoria(c));
          } catch (IOException ioException) {
            ioException.printStackTrace();
          }
        }
      });
      sidebar.add(jlabel);
    }

    JLabel jlabel = new JLabel("Todos");
    jlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    jlabel.addMouseListener(new MouseAdapter() {

      @Override public void mouseReleased(MouseEvent e) {
        try {
          mostrarProductos(Productos.findAll());
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
    });
    sidebar.add(jlabel);
  }

  private void componentes() {
    header = new Header(vista, vista.getUsuario());
    vista.getContainer().add(header, new AbsoluteConstraints(15, 10, 1410, 50));
    if (vista.getUsuario().getRoles().split("\"")[1].equals("admin")) {
      Admin();
    }
    try {
      mostrarProductos(Productos.findAll());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  private void Admin() {
    System.out.println("Vista admin");
    ajustarComponente(vista.getScrollContainerProducts(), 858, 130);
    ajustarComponente(vista.getContainerProducts(), 858, 130);
    vista.revalidate();
    vista.repaint();
  }

  private void ajustarComponente(Component componente, int altura, int ubicacionY) {
    componente.setSize(componente.getWidth(), altura);
    componente.setLocation(componente.getX(), ubicacionY);
    componente.revalidate();
    componente.repaint();
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
    vista.getTitleCategorias().putClientProperty("FlatLaf.style", "arc: 8");
    vista.getCerrar().putClientProperty("FlatLaf.style", "arc: 999");
    // Cursores
    vista.getSignOut().setCursor(new Cursor(Cursor.HAND_CURSOR));
    vista.getCerrar().setCursor(new Cursor(Cursor.HAND_CURSOR));
  }


  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() {
    vista.getCerrar().addActionListener(e -> {
      Historialusuarios.sessionCerrada(vista, vista.getUsuario());
      vista.dispose();
    });
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
      //new CrearModificarProducto().setVisible(true);
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
    this.productos = productos;
    JPanel panelProductos = this.vista.getContainerProducts();
    panelProductos.setLayout(new GridLayout(0, 3, 10, 10));
    panelProductos.removeAll();

    for (Productos producto : productos) {
      JPanel jPanelProducts;
/*      if (!vista.getUsuario().getRoles().split("\"")[1].equals("admin")) {
        jPanelProducts = new ProductosUser(vista.getUsuario(), producto);
      } else {
        jPanelProducts = new ProductosAdmin(vista.getUsuario(), producto);
      }*/
      jPanelProducts = new ProductosAdmin(vista.getUsuario(), producto, vista);
      jPanelProducts.setSize(350, 450);
      panelProductos.add(jPanelProducts);
    }

    panelProductos.revalidate();
    panelProductos.repaint();
  }


}
