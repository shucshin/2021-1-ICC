package mx.unam.ciencias.icc.red;

import java.io.IOException;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosComputadoras;
import mx.unam.ciencias.icc.CampoComputadora;
import mx.unam.ciencias.icc.Computadora;

/**
 * Clase para servidores de bases de datos de computadoras.
 */
public class ServidorBaseDeDatosComputadoras
    extends ServidorBaseDeDatos<Computadora> {

    /**
     * Construye un servidor de base de datos de computadoras.
     * @param puerto el puerto dónde escuchar por conexiones.
     * @param archivo el archivo en el disco del cual cargar/guardar la base de
     *                datos.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public ServidorBaseDeDatosComputadoras(int puerto, String archivo)
        throws IOException {
        super(puerto, archivo);
    }

    /**
     * Crea una base de datos de computadoras.
     * @return una base de datos de computadoras.
     */
    @Override public
    BaseDeDatos<Computadora, CampoComputadora> creaBaseDeDatos() {
        // Aquí va su código.
        BaseDeDatosComputadoras bdde = new BaseDeDatosComputadoras();
        return bdde;
    }
}
