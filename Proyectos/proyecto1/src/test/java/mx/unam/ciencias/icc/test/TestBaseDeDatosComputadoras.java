package mx.unam.ciencias.icc.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosComputadoras;
import mx.unam.ciencias.icc.CampoComputadora;
import mx.unam.ciencias.icc.Computadora;
import mx.unam.ciencias.icc.Lista;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link BaseDeDatosComputadoras}.
 */
public class TestBaseDeDatosComputadoras {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Generador de números aleatorios. */
    private Random random;
    /* Base de datos de computadoras. */
    private BaseDeDatosComputadoras bdd;
    /* Número total de computadoras. */
    private int total;

    /* Enumeración espuria. */
    private enum X {
        /* Campo espurio. */
        A;
    }

    /**
     * Crea un generador de números aleatorios para cada prueba y una base de
     * datos de computadoras.
     */
    public TestBaseDeDatosComputadoras() {
        random = new Random();
        bdd = new BaseDeDatosComputadoras();
        total = 1 + random.nextInt(100);
    }

    /**
     * Prueba unitaria para {@link
     * BaseDeDatosComputadoras#BaseDeDatosComputadoras}.
     */
    @Test public void testConstructor() {
        Lista computadoras = bdd.getRegistros();
        Assert.assertFalse(computadoras == null);
        Assert.assertTrue(computadoras.getLongitud() == 0);
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getNumRegistros}.
     */
    @Test public void testGetNumRegistros() {
        Assert.assertTrue(bdd.getNumRegistros() == 0);
        for (int i = 0; i < total; i++) {
            Computadora e = TestComputadora.computadoraAleatorio();
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getNumRegistros() == i+1);
        }
        Assert.assertTrue(bdd.getNumRegistros() == total);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#getRegistros}.
     */
    @Test public void testGetRegistros() {
        Lista l = bdd.getRegistros();
        Lista r = bdd.getRegistros();
        Assert.assertTrue(l.equals(r));
        Assert.assertFalse(l == r);
        Computadora[] computadoras = new Computadora[total];
        for (int i = 0; i < total; i++) {
            computadoras[i] = TestComputadora.computadoraAleatorio();
            bdd.agregaRegistro(computadoras[i]);
        }
        l = bdd.getRegistros();
        int c = 0;
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Assert.assertTrue(computadoras[c++].equals(nodo.get()));
            nodo = nodo.getSiguiente();
        }
        l.elimina(computadoras[0]);
        Assert.assertFalse(l.equals(bdd.getRegistros()));
        Assert.assertFalse(l.getLongitud() == bdd.getNumRegistros());
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#agregaRegistro}.
     */
    @Test public void testAgregaRegistro() {
        for (int i = 0; i < total; i++) {
            Computadora e = TestComputadora.computadoraAleatorio();
            Assert.assertFalse(bdd.getRegistros().contiene(e));
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            Lista l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.getLongitud() - 1).equals(e));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#eliminaRegistro}.
     */
    @Test public void testEliminaRegistro() {
        int ini = random.nextInt(1000000);
        for (int i = 0; i < total; i++) {
            Computadora e = TestComputadora.computadoraAleatorio(ini + i);
            bdd.agregaRegistro(e);
        }
        while (bdd.getNumRegistros() > 0) {
            int i = random.nextInt(bdd.getNumRegistros());
            Computadora e = (Computadora)bdd.getRegistros().get(i);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            bdd.eliminaRegistro(e);
            Assert.assertFalse(bdd.getRegistros().contiene(e));
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#limpia}.
     */
    @Test public void testLimpia() {
        for (int i = 0; i < total; i++) {
            Computadora e = TestComputadora.computadoraAleatorio();
            bdd.agregaRegistro(e);
        }
        Lista registros = bdd.getRegistros();
        Assert.assertFalse(registros.esVacia());
        Assert.assertFalse(registros.getLongitud() == 0);
        bdd.limpia();
        registros = bdd.getRegistros();
        Assert.assertTrue(registros.esVacia());
        Assert.assertTrue(registros.getLongitud() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#guarda}.
     */
    @Test public void testGuarda() {
        for (int i = 0; i < total; i++) {
            Computadora e = TestComputadora.computadoraAleatorio();
            bdd.agregaRegistro(e);
        }
        String guardado = "";
        try {
            StringWriter swOut = new StringWriter();
            BufferedWriter out = new BufferedWriter(swOut);
            bdd.guarda(out);
            out.close();
            guardado = swOut.toString();
        } catch (IOException ioe) {
            Assert.fail();
        }
        String[] lineas = guardado.split("\n");
        Assert.assertTrue(lineas.length == total);
        Lista l = bdd.getRegistros();
        int c = 0;
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Computadora e = (Computadora)nodo.get();
            String el = String.format("%s\t%d\t%2.2f\t%d\t%s",
                                      e.getCompañia(),
                                      e.getMemoria(),
                                      e.getDiscoDuro(),
                                      e.getCosto(),
                                      e.getSistema());
            Assert.assertTrue(lineas[c++].equals(el));
            nodo = nodo.getSiguiente();
        }
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#carga}.
     */
    @Test public void testCarga() {
        int ini = random.nextInt(1000000);
        String entrada = "";
        Computadora[] computadoras = new Computadora[total];
        for (int i = 0; i < total; i++) {
            computadoras[i] = TestComputadora.computadoraAleatorio(ini + i);
            entrada += String.format("%s\t%d\t%2.2f\t%d\t%s\n",
                                     computadoras[i].getCompañia(),
                                     computadoras[i].getMemoria(),
                                     computadoras[i].getDiscoDuro(),
                                     computadoras[i].getCosto(),
                                     computadoras[i].getSistema());
            bdd.agregaRegistro(computadoras[i]);
        }
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == total);
        int c = 0;
        Lista l = bdd.getRegistros();
        Lista.Nodo nodo = l.getCabeza();
        while (nodo != null) {
            Assert.assertTrue(computadoras[c++].equals(nodo.get()));
            nodo = nodo.getSiguiente();
        }
        entrada = String.format("%s\t%d\t%2.2f\t%d\t%s\n",
                                computadoras[0].getCompañia(),
                                computadoras[0].getMemoria(),
                                computadoras[0].getDiscoDuro(),
                                computadoras[0].getCosto(),
                                computadoras[0].getSistema());
        entrada += " \n";
        entrada += String.format("%s\t%d\t%2.2f\t%d\t%s\n",
                                 computadoras[1].getCompañia(),
                                 computadoras[1].getMemoria(),
                                 computadoras[1].getDiscoDuro(),
                                 computadoras[1].getCosto(),
                                 computadoras[1].getSistema());
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == 1);
        entrada = "";
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Assert.assertTrue(bdd.getNumRegistros() == 0);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosComputadoras#creaRegistro}.
     */
    @Test public void testCreaRegistro() {
        Computadora e = (Computadora)bdd.creaRegistro();
        Assert.assertTrue(e.getCompañia() == null);
        Assert.assertTrue(e.getMemoria() == 0);
        Assert.assertTrue(e.getDiscoDuro() == 0.0);
        Assert.assertTrue(e.getCosto() == 0);
        Assert.assertTrue(e.getSistema() == null);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatosComputadoras#buscaRegistros}.
     */
    @Test public void testBuscaRegistros() {
        Computadora[] computadoras = new Computadora[total];
        int ini = 1000000 + random.nextInt(999999);
        for (int i = 0; i < total; i++) {
            Computadora e =  new Computadora(String.valueOf(ini+i),
                                           ini+i, (i*10) / total, ini+i, String.valueOf(ini+i));
            computadoras[i] = e;
            bdd.agregaRegistro(e);
        }

        Computadora computadora;
        Lista l;
        Lista.Nodo nodo;
        int i;

        for (int k = 0; k < total/10 + 3; k++) {
            i = random.nextInt(total);
            computadora = computadoras[i];

            String compañia = computadora.getCompañia();
            l = bdd.buscaRegistros(CampoComputadora.COMPAÑIA, compañia);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            nodo = l.getCabeza();
            while (nodo != null) {
                Computadora e = (Computadora)nodo.get();
                Assert.assertTrue(e.getCompañia().indexOf(compañia) > -1);
                nodo = nodo.getSiguiente();
            }
            int n = compañia.length();
            String bn = compañia.substring(random.nextInt(2),
                                         2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoComputadora.COMPAÑIA, bn);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            nodo = l.getCabeza();
            while (nodo != null) {
                Computadora e = (Computadora)nodo.get();
                Assert.assertTrue(e.getCompañia().indexOf(bn) > -1);
                nodo = nodo.getSiguiente();
            }

            Integer memoria = Integer.valueOf(computadora.getMemoria());
            l = bdd.buscaRegistros(CampoComputadora.MEMORIA, memoria);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            nodo = l.getCabeza();
            while (nodo != null) {
                Computadora e = (Computadora)nodo.get();
                Assert.assertTrue(e.getMemoria() >= memoria.intValue());
                nodo = nodo.getSiguiente();
            }
            Integer bc = Integer.valueOf(memoria.intValue() - 10);
            l = bdd.buscaRegistros(CampoComputadora.MEMORIA, bc);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            nodo = l.getCabeza();
            while (nodo != null) {
                Computadora e = (Computadora)nodo.get();
                Assert.assertTrue(e.getMemoria() >= bc.intValue());
                nodo = nodo.getSiguiente();
            }

            Double discoDuro = Double.valueOf(computadora.getDiscoDuro());
            l = bdd.buscaRegistros(CampoComputadora.DISCODURO, discoDuro);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            nodo = l.getCabeza();
            while (nodo != null) {
                Computadora e = (Computadora)nodo.get();
                Assert.assertTrue(e.getDiscoDuro() >= discoDuro.doubleValue());
                nodo = nodo.getSiguiente();
            }
            Double bp = Double.valueOf(discoDuro.doubleValue() - 5.0);
            l = bdd.buscaRegistros(CampoComputadora.DISCODURO, bp);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            nodo = l.getCabeza();
            while (nodo != null) {
                Computadora e = (Computadora)nodo.get();
                Assert.assertTrue(e.getDiscoDuro() >= bp.doubleValue());
                nodo = nodo.getSiguiente();
            }

            Integer costo = Integer.valueOf(computadora.getCosto());
            l = bdd.buscaRegistros(CampoComputadora.COSTO, costo);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            nodo = l.getCabeza();
            while (nodo != null) {
                Computadora e = (Computadora)nodo.get();
                Assert.assertTrue(e.getCosto() >= costo.intValue());
                nodo = nodo.getSiguiente();
            }
            Integer be = Integer.valueOf(costo.intValue() - 10);
            l = bdd.buscaRegistros(CampoComputadora.COSTO, be);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            nodo = l.getCabeza();
            while (nodo != null) {
                Computadora e = (Computadora)nodo.get();
                Assert.assertTrue(e.getCosto() >= be.intValue());
                nodo = nodo.getSiguiente();
            }

            String sistema = computadora.getSistema();
            l = bdd.buscaRegistros(CampoComputadora.SISTEMA, sistema);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            nodo = l.getCabeza();
            while (nodo != null) {
                Computadora e = (Computadora)nodo.get();
                Assert.assertTrue(e.getSistema().indexOf(sistema) > -1);
                nodo = nodo.getSiguiente();
            }
            int s = sistema.length();
            String bs = sistema.substring(random.nextInt(2),
                                         2 + random.nextInt(s-2));
            l = bdd.buscaRegistros(CampoComputadora.SISTEMA, bs);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            nodo = l.getCabeza();
            while (nodo != null) {
                Computadora e = (Computadora)nodo.get();
                Assert.assertTrue(e.getSistema().indexOf(bs) > -1);
                nodo = nodo.getSiguiente();
            }
        }

        l = bdd.buscaRegistros(CampoComputadora.COMPAÑIA,
                               "xxx-compañia");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.MEMORIA,
                               Integer.valueOf(9123456));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.DISCODURO,
                               Double.valueOf(970230290.12));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.COSTO,
                               Integer.valueOf(25000000));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.SISTEMA,
                               "xxx-sistema");
        Assert.assertTrue(l.esVacia());

        l = bdd.buscaRegistros(CampoComputadora.COMPAÑIA, "");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.MEMORIA,
                               Integer.valueOf(Integer.MAX_VALUE));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.DISCODURO,
                               Double.valueOf(Double.MAX_VALUE));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.COSTO,
                               Integer.valueOf(Integer.MAX_VALUE));
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.SISTEMA, "");
        Assert.assertTrue(l.esVacia());

        l = bdd.buscaRegistros(CampoComputadora.COMPAÑIA, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.MEMORIA, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.DISCODURO, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.COSTO, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.SISTEMA, null);
        Assert.assertTrue(l.esVacia());

        try {
            l = bdd.buscaRegistros(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            l = bdd.buscaRegistros(X.A, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
    }
}
