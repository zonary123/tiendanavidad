package org.tienda.validator;

/**
 * @author Carlos Varas Alonso - 17/01/2024 8:32
 */
public class validator {
  private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
  private static final String NOMBRE_PATTERN = "^[a-zA-Z ]{4,30}$";
  private static final String APELLIDO_PATTERN = "^[a-zA-Z ]{4,30}$";
  private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{8,30}$";
  private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{4,30}$";


  // Usuario

  /**
   * Es un nombre
   *
   * @param nombre Nombre
   *
   * @return devuelve true si es un nombre valido
   */
  public static boolean isNombre(String nombre) {
    return nombre.matches(NOMBRE_PATTERN);
  }

  /**
   * Es un apellido
   *
   * @param apellido Apellido
   *
   * @return devuelve true si es un apellido valido
   */
  public static boolean isApellido(String apellido) {
    return apellido.matches(APELLIDO_PATTERN);
  }

  /**
   * Es un email
   *
   * @param email Email
   *
   * @return devuelve true si es un email valido
   */
  public static boolean isEmail(String email) {
    return email.matches(EMAIL_PATTERN);
  }

  /**
   * Es un username
   *
   * @param username Texto
   *
   * @return devuelve true si es un username valido
   */
  public static boolean isUsername(String username) {
    return username.matches(USERNAME_PATTERN);
  }

  /**
   * Es un password
   *
   * @param password Password
   *
   * @return devuelve true si es un password valido
   */
  public static boolean isPassword(String password) {
    return password.matches(PASSWORD_PATTERN);
  }


}
