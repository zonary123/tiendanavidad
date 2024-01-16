package org.tienda.DB;

import org.hibernate.SessionFactory;
import org.tienda.Controller.hibernateUtil;
import org.tienda.Utils.utilsLenguaje;

import java.io.*;

/**
 * The type Estructura.
 *
 * @author Carlos Varas Alonso - 10/01/2024 10:45
 */
public class Estructura {
  private final static String PATH = "sql/estructura.sql";

  /**
   * Instantiates a new Estructura.
   *
   * @throws FileNotFoundException the file not found exception
   */
  public Estructura() throws FileNotFoundException {
    crearEstructura();
  }

  /**
   * Crea la estructura de la base de datos a partir de un archivo sql que se encuentra en la ruta
   * resources/sql/estructura.sql
   *
   * @throws FileNotFoundException si no encuentra el archivo
   */
  public static void crearEstructura() throws FileNotFoundException {
    SessionFactory sessionFactory = hibernateUtil.buildSessionFactory();
    InputStream inputStream = utilsLenguaje.class.getClassLoader().getResourceAsStream(PATH);
    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
    String archivo = br.lines().reduce("", (a, b) -> a + b);
    String[] sentencias = archivo.split(";");
    sessionFactory.getCurrentSession().beginTransaction();
    for (String sentencia : sentencias) {
      if (sentencia.contains("CREATE TABLE")) {
        sessionFactory.getCurrentSession().createSQLQuery(sentencia).executeUpdate();
        System.out.println("creando la tabla -> " + sentencia.split(" ")[5]);
      }
    }
  }
}
