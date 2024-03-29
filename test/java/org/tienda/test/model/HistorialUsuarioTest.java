package org.tienda.test.model;

import org.junit.Test;
import org.tienda.model.Historialusuarios;
import org.tienda.model.HistorialusuariosId;
import org.tienda.model.Usuarios;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Carlos Varas Alonso - 16/01/2024 7:10
 */
public class HistorialUsuarioTest {

  
  public void findRecent() {
    Historialusuarios historialusuarios = new Historialusuarios();
    HistorialusuariosId historialusuariosId = new HistorialusuariosId();
    historialusuariosId.setIdusuario(Usuarios.findByUsername("test").getIdusuario());
    historialusuariosId.setFechainiciosesion(Timestamp.valueOf(LocalDateTime.now()));
    historialusuarios.setId(historialusuariosId);
    Historialusuarios.save(historialusuarios);
    System.out.println(historialusuarios);
    System.out.println(Historialusuarios.findAll());
    System.out.println(Historialusuarios.findRecent(Usuarios.findByUsername("test")));


  }
}
