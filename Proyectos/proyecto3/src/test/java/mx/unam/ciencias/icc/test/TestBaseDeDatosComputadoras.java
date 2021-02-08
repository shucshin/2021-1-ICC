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
import mx.unam.ciencias.icc.EscuchaBaseDeDatos;
import mx.unam.ciencias.icc.EventoBaseDeDatos;
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
        Lista<Computadora> computadoras = bdd.getRegistros();
        Assert.assertFalse(computadoras == null);
        Assert.assertTrue(computadoras.getLongitud() == 0);
        Assert.assertTrue(bdd.getNumRegistros() == 0);
        bdd.agregaEscucha((e, r1, r2) -> {});
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
        Lista<Computadora> l = bdd.getRegistros();
        Lista<Computadora> r = bdd.getRegistros();
        Assert.assertTrue(l.equals(r));
        Assert.assertFalse(l == r);
        Computadora[] computadoras = new Computadora[total];
        for (int i = 0; i < total; i++) {
            computadoras[i] = TestComputadora.computadoraAleatorio();
            bdd.agregaRegistro(computadoras[i]);
        }
        l = bdd.getRegistros();
        int c = 0;
        for (Computadora e : l)
            Assert.assertTrue(computadoras[c++].equals(e));
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
            Lista<Computadora> l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.getLongitud() - 1).equals(e));
        }
        boolean[] llamado =  { false };
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.REGISTRO_AGREGADO);
                Assert.assertTrue(r1.equals(new Computadora("A", 1, 1, 1, "A")));
                Assert.assertTrue(r2 == null);
                llamado[0] = true;
            });
        bdd.agregaRegistro(new Computadora("A", 1, 1, 1, "A"));
        Assert.assertTrue(llamado[0]);
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
            Computadora e = bdd.getRegistros().get(i);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            bdd.eliminaRegistro(e);
            Assert.assertFalse(bdd.getRegistros().contiene(e));
        }
        boolean[] llamado = { false };
        Computadora computadora = new Computadora("A", 1, 1, 1, "A");
        bdd.agregaRegistro(computadora);
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.REGISTRO_ELIMINADO);
                Assert.assertTrue(r1.equals(new Computadora("A", 1, 1, 1, "A")));
                Assert.assertTrue(r2 == null);
                llamado[0] = true;
            });
        bdd.eliminaRegistro(computadora);
        Assert.assertTrue(llamado[0]);
        bdd = new BaseDeDatosComputadoras();
        llamado[0] = false;
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.REGISTRO_ELIMINADO);
                Assert.assertTrue(r1.equals(new Computadora("A", 1, 1, 1, "A")));
                Assert.assertTrue(r2 == null);
                llamado[0] = true;
            });
        bdd.eliminaRegistro(computadora);
        Assert.assertTrue(llamado[0]);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#modificaRegistro}.
     */
    @Test public void testModificaRegistro() {
        for (int i = 0; i < total; i++) {
            Computadora e = TestComputadora.computadoraAleatorio(total + i);
            Assert.assertFalse(bdd.getRegistros().contiene(e));
            bdd.agregaRegistro(e);
            Assert.assertTrue(bdd.getRegistros().contiene(e));
            Lista<Computadora> l = bdd.getRegistros();
            Assert.assertTrue(l.get(l.getLongitud() - 1).equals(e));
        }
        Computadora a = new Computadora("A", 1, 1, 1, "A");
        Computadora b = new Computadora("B", 2, 2, 2, "B");
        bdd.agregaRegistro(a);
        boolean[] llamado = { false };
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.REGISTRO_MODIFICADO);
                Assert.assertTrue(r1.equals(new Computadora("A", 1, 1, 1, "A")));
                Assert.assertTrue(r2.equals(new Computadora("B", 2, 2, 2, "B")));
                llamado[0] = true;
            });
        Computadora c = new Computadora("A", 1, 1, 1, "A");
        bdd.modificaRegistro(c, b);
        Assert.assertTrue(llamado[0]);
        Assert.assertTrue(c.equals(new Computadora("A", 1, 1, 1, "A")));
        Assert.assertTrue(b.equals(new Computadora("B", 2, 2, 2, "B")));
        int ca = 0, cb = 0;
        for (Computadora e : bdd.getRegistros()) {
            ca += e.equals(c) ? 1 : 0;
            cb += e.equals(b) ? 1 : 0;
        }
        Assert.assertTrue(ca == 0);
        Assert.assertTrue(cb == 1);
        bdd = new BaseDeDatosComputadoras();
        a = new Computadora("A", 1, 1, 1, "A");
        b = new Computadora("B", 2, 2, 2, "B");
        bdd.agregaRegistro(a);
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.REGISTRO_MODIFICADO);
                Assert.assertTrue(r1.equals(new Computadora("A", 1, 1, 1, "A")));
                Assert.assertTrue(r2.equals(new Computadora("B", 2, 2, 2, "B")));
                llamado[0] = true;
            });
        bdd.modificaRegistro(a, b);
        Assert.assertTrue(a.equals(b));
        Assert.assertTrue(llamado[0]);
        bdd = new BaseDeDatosComputadoras();
        llamado[0] = false;
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.REGISTRO_MODIFICADO);
                llamado[0] = true;
            });
        bdd.modificaRegistro(new Computadora("A", 1, 1, 1, "A"),
                             new Computadora("B", 2, 2, 2, "B"));
        Assert.assertFalse(llamado[0]);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#limpia}.
     */
    @Test public void testLimpia() {
        for (int i = 0; i < total; i++) {
            Computadora e = TestComputadora.computadoraAleatorio();
            bdd.agregaRegistro(e);
        }
        boolean[] llamado = { false };
        bdd.agregaEscucha((e, r1, r2) -> {
                Assert.assertTrue(e == EventoBaseDeDatos.BASE_LIMPIADA);
                Assert.assertTrue(r1 == null);
                Assert.assertTrue(r2 == null);
                llamado[0] = true;
            });
        Lista<Computadora> registros = bdd.getRegistros();
        Assert.assertFalse(registros.esVacia());
        Assert.assertFalse(registros.getLongitud() == 0);
        bdd.limpia();
        registros = bdd.getRegistros();
        Assert.assertTrue(registros.esVacia());
        Assert.assertTrue(registros.getLongitud() == 0);
        Assert.assertTrue(llamado[0]);
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
        Lista<Computadora> l = bdd.getRegistros();
        int c = 0;
        for (Computadora e : l) {
            String el = String.format("%s\t%d\t%2.2f\t%d\t%s",
                                      e.getCompañia(),
                                      e.getMemoria(),
                                      e.getDiscoDuro(),
                                      e.getCosto(),
                                      e.getSistema());
            Assert.assertTrue(lineas[c++].equals(el));
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
        int[] contador = { 0 };
        boolean[] llamado = { false };
        bdd.agregaEscucha((e, r1, r2) -> {
                if (e == EventoBaseDeDatos.BASE_LIMPIADA)
                    llamado[0] = true;
                if (e == EventoBaseDeDatos.REGISTRO_AGREGADO) {
                    contador[0] ++;
                    Assert.assertTrue(r1 != null);
                    Assert.assertTrue(r2 == null);
                }
            });
        try {
            StringReader srInt = new StringReader(entrada);
            BufferedReader in = new BufferedReader(srInt, 8192);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            Assert.fail();
        }
        Lista<Computadora> l = bdd.getRegistros();
        Assert.assertTrue(l.getLongitud() == total);
        int c = 0;
        for (Computadora e : l)
            Assert.assertTrue(computadoras[c++].equals(e));
        Assert.assertTrue(llamado[0]);
        Assert.assertTrue(contador[0] == total);
        contador[0] = 0;
        llamado[0] = false;
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
        Assert.assertTrue(llamado[0]);
        Assert.assertTrue(contador[0] == 1);
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
        Computadora e = bdd.creaRegistro();
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
                                           ini+i, (i * 10.0) / total, i, String.valueOf(ini+i));
            computadoras[i] = e;
            bdd.agregaRegistro(e);
        }

        Computadora computadora;
        Lista<Computadora> l;
        int i;

        for (int k = 0; k < total/10 + 3; k++) {
            i = random.nextInt(total);
            computadora = computadoras[i];

            String compañia = computadora.getCompañia();
            l = bdd.buscaRegistros(CampoComputadora.COMPAÑIA, compañia);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            for (Computadora e : l)
                Assert.assertTrue(e.getCompañia().indexOf(compañia) > -1);
            int n = compañia.length();
            String bn = compañia.substring(random.nextInt(2),
                                         2 + random.nextInt(n-2));
            l = bdd.buscaRegistros(CampoComputadora.COMPAÑIA, bn);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            for (Computadora e : l)
                Assert.assertTrue(e.getCompañia().indexOf(bn) > -1);

            int memoria = computadora.getMemoria();
            l = bdd.buscaRegistros(CampoComputadora.MEMORIA, memoria);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            for (Computadora e : l)
                Assert.assertTrue(e.getMemoria() >= memoria);
            int bc = memoria - 10;
            l = bdd.buscaRegistros(CampoComputadora.MEMORIA, bc);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            for (Computadora e : l)
                Assert.assertTrue(e.getMemoria() >= bc);

            double discoDuro = computadora.getDiscoDuro();
            l = bdd.buscaRegistros(CampoComputadora.DISCODURO, discoDuro);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            for (Computadora e : l)
                Assert.assertTrue(e.getDiscoDuro() >= discoDuro);
            double bp = discoDuro - 5.0;
            l = bdd.buscaRegistros(CampoComputadora.DISCODURO, bp);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            for (Computadora e : l)
                Assert.assertTrue(e.getDiscoDuro() >= bp);

            int costo = computadora.getCosto();
            l = bdd.buscaRegistros(CampoComputadora.COSTO, costo);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            for (Computadora e : l)
                Assert.assertTrue(e.getCosto() >= costo);
            int be = costo - 10;
            l = bdd.buscaRegistros(CampoComputadora.COSTO, be);
            Assert.assertTrue(l.getLongitud() > 0);
            Assert.assertTrue(l.contiene(computadora));
            for (Computadora e : l)
                Assert.assertTrue(e.getCosto() >= be);
        }

        l = bdd.buscaRegistros(CampoComputadora.COMPAÑIA,
                               "xxx-compañia");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.MEMORIA, 9123456);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.DISCODURO, 97.12);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.COSTO, 127);
        Assert.assertTrue(l.esVacia());

        l = bdd.buscaRegistros(CampoComputadora.COMPAÑIA, "");
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.MEMORIA, Integer.MAX_VALUE);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.DISCODURO, Double.MAX_VALUE);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.COSTO, Integer.MAX_VALUE);
        Assert.assertTrue(l.esVacia());

        l = bdd.buscaRegistros(CampoComputadora.COMPAÑIA, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.MEMORIA, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.DISCODURO, null);
        Assert.assertTrue(l.esVacia());
        l = bdd.buscaRegistros(CampoComputadora.COSTO, null);
        Assert.assertTrue(l.esVacia());

        try {
            l = bdd.buscaRegistros(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#agregaEscucha}.
     */
    @Test public void testAgregaEscucha() {
        int[] c = new int[total];
        for (int i = 0; i < total; i++) {
            final int j = i;
            bdd.agregaEscucha((e, r1, r2) -> c[j]++);
        }
        bdd.agregaRegistro(new Computadora("A", 1, 1, 1, "A"));
        for (int i = 0; i < total; i++)
            Assert.assertTrue(c[i] == 1);
    }

    /**
     * Prueba unitaria para {@link BaseDeDatos#eliminaEscucha}.
     */
    @Test public void testEliminaEscucha() {
        int[] c = new int[total];
        Lista<EscuchaBaseDeDatos<Computadora>> escuchas =
            new Lista<EscuchaBaseDeDatos<Computadora>>();
        for (int i = 0; i < total; i++) {
            final int j = i;
            EscuchaBaseDeDatos<Computadora> escucha = (e, r1, r2) -> c[j]++;
            bdd.agregaEscucha(escucha);
            escuchas.agregaFinal(escucha);
        }
        int i = 0;
        while (!escuchas.esVacia()) {
            bdd.agregaRegistro(TestComputadora.computadoraAleatorio(i++));
            EscuchaBaseDeDatos<Computadora> escucha = escuchas.eliminaPrimero();
            bdd.eliminaEscucha(escucha);
        }
        for (i = 0; i < total; i++)
            Assert.assertTrue(c[i] == i + 1);
    }
}
