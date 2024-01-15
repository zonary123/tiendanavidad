package org.tienda.DB;

import org.hibernate.SessionFactory;
import org.tienda.Controller.hibernateUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * The type Alimentacion.
 *
 * @author Carlos Varas Alonso - 10/01/2024 10:45
 */
public class Alimentacion {
  private final static String PATH = "src/main/resources/sql/alimentacion.sql";

  /**
   * Instantiates a new Alimentacion.
   */
  public Alimentacion() throws FileNotFoundException {
  }

  /**
   * Inserta los datos de la base de datos a partir de un archivo sql que se encuentra en la ruta
   *
   * @throws FileNotFoundException si no encuentra el archivo
   */
  public static void insertarDatos() throws FileNotFoundException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    BufferedReader br = new BufferedReader(new FileReader(PATH));
    String archivo = br.lines().reduce("", (a, b) -> a + b);
    String[] sentencias = archivo.split(";");
    sessionFactory.getCurrentSession().beginTransaction();
    for (String sentencia : sentencias) {
      if (sentencia.contains("INSERT INTO") || sentencia.contains("INSERT IGNORE INTO")) {
        sessionFactory.getCurrentSession().createQuery(sentencia).executeUpdate();
      }
    }
    System.out.println("Se han creado todos los datos");
  }
}
