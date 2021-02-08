package mx.unam.ciencias.icc.red.test;

import mx.unam.ciencias.icc.BaseDeDatosComputadoras;
import mx.unam.ciencias.icc.Computadora;
import mx.unam.ciencias.icc.test.TestComputadora;

/**
 * Clase con métodos estáticos utilitarios para las pruebas unitarias de red.
 */
public class UtilRed {

    /* Evitamos instanciación. */
    private UtilRed() {}

    /* Número de cuenta inicial. */
    public static final int CUENTA_INICIAL = 1000000;

    /* Contador de números de cuenta. */
    private static int contador;

    /**
     * Espera el número de milisegundos de forma segura.
     * @param milisegundos el número de milisegundos a esperar.
     */
    public static void espera(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException ie) {}
    }

    /**
     * Llena una base de datos de computadoras.
     * @param bdd la base de datos a llenar.
     * @param total el total de computadoras.
     */
    public static void llenaBaseDeDatos(BaseDeDatosComputadoras bdd, int total) {
        for (int i = 0; i < total; i++) {
            int c = CUENTA_INICIAL + i;
            bdd.agregaRegistro(TestComputadora.computadoraAleatorio(c));
        }
    }

    /**
     * Crea un computadora aleatorio.
     * @param total el total de computadoras.
     * @return un computadora aleatorio con un número de cuenta único.
     */
    public static Computadora computadoraAleatorio(int total) {
        int nc = CUENTA_INICIAL + total*2 + contador++;
        return TestComputadora.computadoraAleatorio(nc);
    }

}
