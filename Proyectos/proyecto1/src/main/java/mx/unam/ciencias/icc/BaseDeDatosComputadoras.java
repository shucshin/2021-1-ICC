package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de computadoras.
 */
public class BaseDeDatosComputadoras extends BaseDeDatos {

    /**
     * Crea una computadora en blanco.
     * @return una computadora en blanco.
     */
    @Override public Registro creaRegistro() {
        // Aquí va su código.
        return new Computadora(null, 0, 0.0, 0, null);
    }
}
