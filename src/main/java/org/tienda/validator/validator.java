package org.tienda.validator;

/**
 * @author Carlos Varas Alonso - 17/01/2024 8:32
 */
public class validator {
  public static final String CODIGO_PATTERN = "^[A-Z0-9]{6}$";
  public static final String EMAIL_PATTERN = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
  public static final String NOMBRE_PATTERN = "^[a-zA-Z ]{4,30}$";
  public static final String APELLIDO_PATTERN = "^[a-zA-Z ]{4,30}$";
  public static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{4,30}$";
  public static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{4,30}$";
  public static final String NOMBRE_PRODUCTO_PATTERN = "^[a-zA-Z0-9 ]{4,30}$";
  public static final String DESCRIPCION_PRODUCTO_PATTERN = "^[a-zA-Z0-9 ]{4,255}$";
  public static final String PRECIO_PRODUCTO_PATTERN = "^[1-9]\\d*(\\.\\d{1,2})?$";
  public static final String DESCUENTO_PRODUCTO_PATTERN = "^[1-9]\\d*(\\.\\d{1,2})?$";


  /**
   * Esta funcion comprueba si el nombre es valido
   *
   * @param nombre Se espera un nombre
   *
   * @return devuelve true si es un nombre valido
   */
  public static boolean isNombre(String nombre) {
    return nombre.matches(NOMBRE_PATTERN);
  }

  /**
   * Esta funcion comprueba si el apellido es valido
   *
   * @param apellido Se espera un apellido
   *
   * @return devuelve true si es un apellido valido
   */
  public static boolean isApellido(String apellido) {
    return apellido.matches(APELLIDO_PATTERN);
  }

  /**
   * Esta funcion comprueba si el email es valido
   *
   * @param email Se espera un correo
   *
   * @return devuelve true si es un email valido
   */
  public static boolean isEmail(String email) {
    return email.matches(EMAIL_PATTERN);
  }

  /**
   * Esta funcion comprueba si el username valido
   *
   * @param username Se espera un username
   *
   * @return devuelve true si es un username valido
   */
  public static boolean isUsername(String username) {
    return username.matches(USERNAME_PATTERN);
  }

  /**
   * Esta funcion comprueba si la password es valida
   *
   * @param password Se espera una password
   *
   * @return devuelve true si es un password valido
   */
  public static boolean isPassword(String password) {
    return password.matches(PASSWORD_PATTERN);
  }


}
