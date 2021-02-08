package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de computadoras.
 */
public class BaseDeDatosComputadoras
    extends BaseDeDatos<Computadora, CampoComputadora> {

    /**
     * Crea un computadora en blanco.
     * @return un computadora en blanco.
     */
    @Override public Computadora creaRegistro() {
        // Aquí va su código.
        return new Computadora(null, 0, 0.0, 0, null);
    }
}
