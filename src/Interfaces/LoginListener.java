package Interfaces;

/**
 * Una interfaz que define el evento de inicio de sesión exitoso.
 * Los objetos que implementen esta interfaz pueden recibir notificaciones cuando se realiza un inicio de sesión exitoso.
 */
public interface LoginListener {

    /**
     * Método invocado cuando se ha realizado un inicio de sesión exitoso.
     * Los objetos que implementen esta interfaz pueden definir su propio comportamiento al recibir esta notificación.
     */
    void onLoginSuccessful();
}
