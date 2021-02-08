package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import mx.unam.ciencias.icc.CampoComputadora;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la enumeración {@link CampoComputadora}.
 */
public class TestCampoComputadora {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Prueba unitaria para {@link CampoComputadora#toString}.
     */
    @Test public void testToString() {
        String s = CampoComputadora.COMPAÑIA.toString();
        Assert.assertTrue(s.equals("Compañia"));
        s = CampoComputadora.MEMORIA.toString();
        Assert.assertTrue(s.equals("Memoria"));
        s = CampoComputadora.DISCODURO.toString();
        Assert.assertTrue(s.equals("Disco Duro"));
        s = CampoComputadora.COSTO.toString();
        Assert.assertTrue(s.equals("Costo"));
        s = CampoComputadora.SISTEMA.toString();
        Assert.assertTrue(s.equals("Sistema Operativo"));
    }
}
