package org.tienda.Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.NoResultException;
import javax.swing.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.tienda.Model.Historialusuarios;
import org.tienda.Model.HistorialusuariosId;
import org.tienda.Model.Usuarios;
import org.tienda.Utils.EmailUtil;
import org.tienda.Utils.utilsLenguaje;
import org.tienda.Utils.utilsTextField;
import org.tienda.Views.ForgotPasswordEmail;
import org.tienda.Views.HomeUser;
import org.tienda.Views.Login;
import org.tienda.Views.Register;

/**
 * The type Controller login.
 *
 * @author Carlos Varas Alonso
 */
@Getter
@Setter
public class cLogin {

  private final Login vista;
  private utilsLenguaje lenguaje;
  private Usuarios usuario;
  private static utilsTextField TextField = new utilsTextField();


  /**
   * Constructor de la clase
   *
   * @param vista la vista de login
   */
  public cLogin(Login vista) throws IOException {
    this.vista = vista;
    lenguaje = new utilsLenguaje();
    System.out.println(lenguaje.getLocal());
    initEvents();
    actualizarEstilos();
    actualizarLenguaje();
  }

  /**
   * Actualiza el lenguaje de la vista
   */
  public void actualizarLenguaje() {
    vista.getJButtonPasswordOlvidada().setText(lenguaje.getMensaje().getString("login.button.forgotpassword"));
    vista.getJButtonLogin().setText(lenguaje.getMensaje().getString("login.button.login"));
    vista.getJButtonRegistrarse().setText(lenguaje.getMensaje().getString("login.button.register"));
    vista.getJLabelLogin().setText(lenguaje.getMensaje().getString("login.h1"));
    vista.getJLabelPassword().setText(lenguaje.getMensaje().getString("login.label.password"));
    vista.getJLabelNoCuenta().setText(lenguaje.getMensaje().getString("login.label.notaccount"));
  }

  /**
   * Actualiza los estilos de la vista
   */
  public void actualizarEstilos() {
    TextField.actualizarTextField(vista.getJTextFieldUsername(), lenguaje.getMensaje().getString("forgot.email.placeholder"), 16, "img/svg/Email.svg", 22, 24, "#575DFB");
    TextField.actualizarTextField(vista.getJPasswordFieldPassword(), lenguaje.getMensaje().getString("forgot.password.descripcion"), 16, "img/svg/Candado.svg", 22, 24, "#575DFB");
    vista.getJButtonClose().putClientProperty("FlatLaf.style", "arc:" + 999);
    vista.getJButtonLogin().putClientProperty("FlatLaf.style", "arc:" + 16);
    vista.getJPanelLogin().putClientProperty("FlatLaf.style", "arc:" + 8);
  }

  /**
   * Inicializacion de eventos de la vista
   */
  public void initEvents() throws NoResultException {
    vista.getJTextFieldUsername().requestFocus();
    // ! Eventos Presionar teclado
    vista.getJTextFieldUsername().addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && e.isControlDown()) {
          vista.getJTextFieldUsername().setText(null);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          vista.getJPasswordFieldPassword().requestFocus();
        }
      }
    });
    vista.getJPasswordFieldPassword().addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && e.isControlDown()) {
          vista.getJPasswordFieldPassword().setText(null);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          vista.getJButtonLogin().requestFocus();
        }
      }
    });
    // ! Eventos Presionar boton
    vista.getJButtonLogin().addActionListener(e -> {
      // Llevar a la vista principal
      if (vista.getJTextFieldUsername().getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.void.username"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
        vista.getJTextFieldUsername().putClientProperty("JComponent.outline", "warning");
      }
      if (String.valueOf(vista.getJPasswordFieldPassword().getPassword()).isEmpty()) {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.void.password"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
        vista.getJPasswordFieldPassword().putClientProperty("JComponent.outline", "warning");
        return;
      }
      if (validarCredenciales(vista.getJTextFieldUsername().getText(), vista.getJPasswordFieldPassword().getPassword()) == null) {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.joptionpanel.notexist"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
      } else if (Boolean.TRUE.equals(validarCredenciales(vista.getJTextFieldUsername().getText(), vista.getJPasswordFieldPassword().getPassword()))) {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.joptionpanel.true.credenciales"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.INFORMATION_MESSAGE);
        vista.removeAll();
        vista.dispose();
        this.usuario = vista.getJTextFieldUsername().getText().contains("@") ? Usuarios.findByEmail(vista.getJTextFieldUsername().getText()) : Usuarios.findByUsername(vista.getJTextFieldUsername().getText());
        Historialusuarios historialusuarios = new Historialusuarios();
        HistorialusuariosId historialusuariosId = new HistorialusuariosId();
        historialusuariosId.setIdusuario(usuario.getIdusuario());
        historialusuariosId.setFechainiciosesion(Timestamp.valueOf(LocalDateTime.now()));
        historialusuarios.setId(historialusuariosId);
        Historialusuarios.save(historialusuarios);
        new HomeUser(usuario).setVisible(true);
      } else {
        JOptionPane.showMessageDialog(null, lenguaje.getMensaje().getString("login.joptionpanel.false.credenciales"), lenguaje.getMensaje().getString("login.joptionpanel.title"), JOptionPane.ERROR_MESSAGE);
      }
    });
    vista.getJButtonRegistrarse().addActionListener(e -> {
      vista.removeAll();
      vista.dispose();
      new Register().setVisible(true);
    });
    vista.getJButtonPasswordOlvidada().addActionListener(e -> {
      vista.removeAll();
      vista.dispose();
      new ForgotPasswordEmail().setVisible(true);

    });
    // ! Eventos Cerrar ventana
    vista.getJButtonClose().addActionListener(e -> System.exit(0));
    vista.getJButtonBack().addActionListener(e -> System.exit(0));
  }

  /**
   * Valida las credenciales del usuario
   *
   * @param username Nombre del usuario
   * @param password Contraseña del usuario
   *
   * @return true si las credenciales son correctas
   */
  private Boolean validarCredenciales(String username, char[] password) {
    try {
      if (username.contains("@")) {
        return Usuarios.findByEmail(username) != null && Usuarios.checkPassword(String.valueOf(password), Usuarios.findByEmail(username));
      } else {
        return Usuarios.findByUsername(username) != null && Usuarios.checkPassword(String.valueOf(password), Usuarios.findByUsername(username));
      }
    } catch (NoResultException e) {
      return null;
    }
  }

}
