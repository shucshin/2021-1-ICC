package mx.unam.ciencias.icc.test;

import java.util.Random;
import mx.unam.ciencias.icc.CampoComputadora;
import mx.unam.ciencias.icc.Computadora;
import mx.unam.ciencias.icc.ExcepcionLineaInvalida;
import mx.unam.ciencias.icc.Registro;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Computadora}.
 */
public class TestComputadora {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /* Compañias. */
    private static final String[] COMPAÑIAS = {
        "Apple", "Lenovo", "Dell", "Asus", "HP",
        "Microsoft", "Acer", "Razer", "Alienware", "MSI"
    };

    /* Sistemas Operativos. */
    private static final String[] SISTEMAS = {
        "macOS", "Windows", "Linux", "Android", "iOS"
    };

    /* Generador de números aleatorios. */
    private static Random random;

    /**
     * Genera una compañia aleatorio.
     * @return una compañia aleatorio.
     */
    public static String compañiaAleatorio() {
        int c = random.nextInt(COMPAÑIAS.length);
        return COMPAÑIAS[c];
    }

    /**
     * Genera una memoria aleatoria.
     * @return una memoria aleatoria.
     */
    public static int memoriaAleatorio() {
        return 4 + random.nextInt(32);
    }

    /**
     * Genera un discoDuro aleatorio.
     * @return un discoDuro aleatorio.
     */
    public static double discoDuroAleatorio() {
        return 64.00 + random.nextInt(1024);
    }

    /**
     * Genera un costo aleatorio.
     * @return un costo aleatorio.
     */
    public static int costoAleatorio() {
        return 5000 + random.nextInt(70000);
    }

    /**
     * Genera un sistema operativo aleatorio.
     * @return un sistema operativo aleatorio.
     */
    public static String sistemaAleatorio() {
        int s = random.nextInt(SISTEMAS.length);
        return SISTEMAS[s];
    }

    /**
     * Genera una computadora aleatorio.
     * @return una computadora aleatorio.
     */
    public static Computadora computadoraAleatorio() {
        return new Computadora(compañiaAleatorio(),
                              memoriaAleatorio(),
                              discoDuroAleatorio(),
                              costoAleatorio(),
                              sistemaAleatorio());
    }

    /**
     * Genera un computadora aleatorio con un número de memoria dado.
     * @param memoria el número de memoria de la nueva computadora.
     * @return una computadora aleatorio.
     */
    public static Computadora computadoraAleatorio(int memoria) {
        return new Computadora(compañiaAleatorio(),
                              memoria,
                              discoDuroAleatorio(),
                              costoAleatorio(),
                              sistemaAleatorio());
    }

    /* El computadora. */
    private Computadora computadora;

    /* Enumeración espuria. */
    private enum X {
        /* Campo espurio. */
        A;
    }

    /**
     * Prueba unitaria para {@link
     * Computadora#Computadora(String,int,double,int,String)}.
     */
    @Test public void testConstructor() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.getCompañia().equals(compañia));
        Assert.assertTrue(computadora.getMemoria() == memoria);
        Assert.assertTrue(computadora.getDiscoDuro() == discoDuro);
        Assert.assertTrue(computadora.getCosto() == costo);
        Assert.assertTrue(computadora.getSistema().equals(sistema));
    }

    /**
     * Prueba unitaria para {@link Computadora#getCompañia}.
     */
    @Test public void testGetCompañia() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.getCompañia().equals(compañia));
        Assert.assertFalse(computadora.getCompañia().equals(compañia + " X"));
    }

    /**
     * Prueba unitaria para {@link Computadora#setCompañia}.
     */
    @Test public void testSetCompañia() {
        String compañia = compañiaAleatorio();
        String nuevaCompañia = compañia + " X";
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.getCompañia().equals(compañia));
        Assert.assertFalse(computadora.getCompañia().equals(nuevaCompañia));
        computadora.setCompañia(nuevaCompañia);
        Assert.assertFalse(computadora.getCompañia().equals(compañia));
        Assert.assertTrue(computadora.getCompañia().equals(nuevaCompañia));
    }

    /**
     * Prueba unitaria para {@link Computadora#getMemoria}.
     */
    @Test public void testGetMemoria() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.getMemoria() == memoria);
        Assert.assertFalse(computadora.getMemoria() == memoria + 100);
    }

    /**
     * Prueba unitaria para {@link Computadora#setMemoria}.
     */
    @Test public void testSetMemoria() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        int nuevaMemoria = memoria + 100;
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.getMemoria() == memoria);
        Assert.assertFalse(computadora.getMemoria() == memoria + 100);
        computadora.setMemoria(nuevaMemoria);
        Assert.assertFalse(computadora.getMemoria() == memoria);
        Assert.assertTrue(computadora.getMemoria() == nuevaMemoria);
    }

    /**
     * Prueba unitaria para {@link Computadora#getDiscoDuro}.
     */
    @Test public void testGetDiscoDuro() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.getDiscoDuro() == discoDuro);
        Assert.assertFalse(computadora.getDiscoDuro() == discoDuro + 1.0);
    }

    /**
     * Prueba unitaria para {@link Computadora#setDiscoDuro}.
     */
    @Test public void testSetDiscoDuro() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        double nuevoDiscoDuro = discoDuro + 1.0;
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.getDiscoDuro() == discoDuro);
        Assert.assertFalse(computadora.getDiscoDuro() == nuevoDiscoDuro);
        computadora.setDiscoDuro(nuevoDiscoDuro);
        Assert.assertFalse(computadora.getDiscoDuro() == discoDuro);
        Assert.assertTrue(computadora.getDiscoDuro() == nuevoDiscoDuro);
    }

    /**
     * Prueba unitaria para {@link Computadora#getCosto}.
     */
    @Test public void testGetCosto() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.getCosto() == costo);
        Assert.assertFalse(computadora.getCosto() == costo + 50);
    }

    /**
     * Prueba unitaria para {@link Computadora#setCosto}.
     */
    @Test public void testSetCosto() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        int nuevoCosto = costo + 50;
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.getCosto() == costo);
        Assert.assertFalse(computadora.getCosto() == nuevoCosto);
        computadora.setCosto(nuevoCosto);
        Assert.assertFalse(computadora.getCosto() == costo);
        Assert.assertTrue(computadora.getCosto() == nuevoCosto);
    }

    /**
     * Prueba unitaria para {@link Computadora#getSistema}.
     */
    @Test public void testGetSistema() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.getSistema().equals(sistema));
        Assert.assertFalse(computadora.getSistema().equals(sistema + " X"));
    }

    /**
     * Prueba unitaria para {@link Computadora#setSistema}.
     */
    @Test public void testSetSistema() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        String nuevoSistema = sistema + " X";
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.getSistema().equals(sistema));
        Assert.assertFalse(computadora.getSistema().equals(nuevoSistema));
        computadora.setSistema(nuevoSistema);
        Assert.assertFalse(computadora.getSistema().equals(sistema));
        Assert.assertTrue(computadora.getSistema().equals(nuevoSistema));
    }

    /**
     * Prueba unitaria para {@link Computadora#toString}.
     */
    @Test public void testToString() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        String cadena = String.format("Compañia   : %s\n" +
                                      "Memoria    : %d GB\n" +
                                      "Disco Duro : %2.2f GB\n" +
                                      "Costo      : %d Pesos\n" +
                                      "Sistema Operativo : %s",
                                      compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.toString().equals(cadena));
        memoria = 16;
        discoDuro = 512.00;
        computadora.setMemoria(memoria);
        computadora.setDiscoDuro(discoDuro);
        cadena = String.format("Compañia   : %s\n" +
                               "Memoria    : %d GB\n" +
                               "Disco Duro : %2.2f GB\n" +
                               "Costo      : %d Pesos\n" +
                               "Sistema Operativo : %s",
                               compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.toString().equals(cadena));
    }

    /**
     * Prueba unitaria para {@link Computadora#equals}.
     */
    @Test public void testEquals() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        Computadora igual = new Computadora(new String(compañia),
                                          memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.equals(igual));
        String otraCompañia = compañia + " Segundo";
        int otraMemoria = memoria + 8;
        double otroDiscoDuro = discoDuro + 128.00;
        int otroCosto = costo + 5000;
        String otroSistema = sistema + " Segundo";

        Computadora distinto =
            new Computadora(otraCompañia, memoria, discoDuro, costo, sistema);
        Assert.assertFalse(computadora.equals(distinto));
        distinto = new Computadora(compañia, otraMemoria, discoDuro, costo, sistema);
        Assert.assertFalse(computadora.equals(distinto));
        distinto = new Computadora(compañia, memoria, otroDiscoDuro, costo, sistema);
        Assert.assertFalse(computadora.equals(distinto));
        distinto = new Computadora(compañia, memoria, discoDuro, otroCosto, sistema);
        Assert.assertFalse(computadora.equals(distinto));
        distinto = new Computadora(compañia, memoria, discoDuro, costo, otroSistema);
        Assert.assertFalse(computadora.equals(distinto));
        distinto = new Computadora(otraCompañia, otraMemoria,
                                  otroDiscoDuro, otroCosto, otroSistema);
        Assert.assertFalse(computadora.equals(distinto));
        Assert.assertFalse(computadora.equals("Una cadena"));
        Assert.assertFalse(computadora.equals(null));
    }

    /**
     * Prueba unitaria para {@link Computadora#serializa}.
     */
    @Test public void testSerializa() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        String linea = String.format("%s\t%d\t%2.2f\t%d\t%s\n",
                                     compañia, memoria, discoDuro, costo, sistema);
        Assert.assertTrue(computadora.serializa().equals(linea));
    }

    /**
     * Prueba unitaria para {@link Computadora#deserializa}.
     */
    @Test public void testDeserializa() {
        computadora = new Computadora(null, 0, 0.0, 0, null);

        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();

        String linea = String.format("%s\t%d\t%2.2f\t%d\t%s\n",
                                     compañia, memoria, discoDuro, costo, sistema);

        try {
            computadora.deserializa(linea);
        } catch (ExcepcionLineaInvalida eli) {
            Assert.fail();
        }

        Assert.assertTrue(computadora.getCompañia().equals(compañia));
        Assert.assertTrue(computadora.getMemoria() == memoria);
        Assert.assertTrue(computadora.getDiscoDuro() == discoDuro);
        Assert.assertTrue(computadora.getCosto() == costo);
        Assert.assertTrue(computadora.getSistema().equals(sistema));

        String[] invalidas = {"", " ", "\t", "  ", "\t\t",
                              " \t", "\t ", "\n", "a\ta\ta",
                              "a\ta\ta\ta"};

        for (int i = 0; i < invalidas.length; i++) {
            linea = invalidas[i];
            try {
                computadora.deserializa(linea);
                Assert.fail();
            } catch (ExcepcionLineaInvalida eli) {}
        }
    }

    /**
     * Prueba unitaria para {@link Computadora#actualiza}.
     */
    @Test public void testActualiza() {
        computadora = new Computadora("A", 1, 1, 1, "C");
        Computadora e = new Computadora("B", 2, 2, 2, "D");
        Assert.assertFalse(computadora == e);
        Assert.assertFalse(computadora.equals(e));
        computadora.actualiza(e);
        Assert.assertFalse(computadora == e);
        Assert.assertTrue(computadora.equals(e));
        try {
            computadora.actualiza(null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            computadora.actualiza(new Registro() {
                    @Override public String serializa() { return null; }
                    @Override public void deserializa(String linea) {}
                    @Override public void actualiza(Registro registro) {}
                    @Override public boolean caza(Enum campo, Object valor) {
                        return false;
                    }
                });
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
    }

    /**
     * Prueba unitaria para {@link Computadora#caza}.
     */
    @Test public void testCaza() {
        String compañia = compañiaAleatorio();
        int memoria = memoriaAleatorio();
        double discoDuro = discoDuroAleatorio();
        int costo = costoAleatorio();
        String sistema = sistemaAleatorio();
        computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);

        String n = computadora.getCompañia();
        int m = computadora.getCompañia().length();
        Assert.assertTrue(computadora.caza(CampoComputadora.COMPAÑIA, n));
        n = computadora.getCompañia().substring(0, m/2);
        Assert.assertTrue(computadora.caza(CampoComputadora.COMPAÑIA, n));
        n = computadora.getCompañia().substring(m/2, m);
        Assert.assertTrue(computadora.caza(CampoComputadora.COMPAÑIA, n));
        Assert.assertFalse(computadora.caza(CampoComputadora.COMPAÑIA, ""));
        Assert.assertFalse(computadora.caza(CampoComputadora.COMPAÑIA, "XXX"));
        Assert.assertFalse(computadora.caza(CampoComputadora.COMPAÑIA,
                                           Integer.valueOf(1000)));
        Assert.assertFalse(computadora.caza(CampoComputadora.COMPAÑIA, null));

        Integer c = Integer.valueOf(computadora.getMemoria());
        Assert.assertTrue(computadora.caza(CampoComputadora.MEMORIA, c));
        c = Integer.valueOf(computadora.getMemoria() - 100);
        Assert.assertTrue(computadora.caza(CampoComputadora.MEMORIA, c));
        c = Integer.valueOf(computadora.getMemoria() + 100);
        Assert.assertFalse(computadora.caza(CampoComputadora.MEMORIA, c));
        Assert.assertFalse(computadora.caza(CampoComputadora.MEMORIA, "XXX"));
        Assert.assertFalse(computadora.caza(CampoComputadora.MEMORIA, null));

        Double p = Double.valueOf(computadora.getDiscoDuro());
        Assert.assertTrue(computadora.caza(CampoComputadora.DISCODURO, p));
        p = Double.valueOf(computadora.getDiscoDuro() - 5.0);
        Assert.assertTrue(computadora.caza(CampoComputadora.DISCODURO, p));
        p = Double.valueOf(computadora.getDiscoDuro() + 5.0);
        Assert.assertFalse(computadora.caza(CampoComputadora.DISCODURO, p));
        Assert.assertFalse(computadora.caza(CampoComputadora.DISCODURO, "XXX"));
        Assert.assertFalse(computadora.caza(CampoComputadora.DISCODURO, null));

        Integer e = Integer.valueOf(computadora.getCosto());
        Assert.assertTrue(computadora.caza(CampoComputadora.COSTO, e));
        e = Integer.valueOf(computadora.getCosto() - 10);
        Assert.assertTrue(computadora.caza(CampoComputadora.COSTO, e));
        e = Integer.valueOf(computadora.getCosto() + 10);
        Assert.assertFalse(computadora.caza(CampoComputadora.COSTO, e));
        Assert.assertFalse(computadora.caza(CampoComputadora.COSTO, "XXX"));
        Assert.assertFalse(computadora.caza(CampoComputadora.COSTO, null));

        String s = computadora.getSistema();
        int t = computadora.getSistema().length();
        Assert.assertTrue(computadora.caza(CampoComputadora.SISTEMA, s));
        s = computadora.getSistema().substring(0, t/2);
        Assert.assertTrue(computadora.caza(CampoComputadora.SISTEMA, s));
        s = computadora.getSistema().substring(t/2, t);
        Assert.assertTrue(computadora.caza(CampoComputadora.SISTEMA, s));
        s = computadora.getSistema().substring(t/3, 2*(t/3));
        Assert.assertTrue(computadora.caza(CampoComputadora.SISTEMA, s));
        Assert.assertFalse(computadora.caza(CampoComputadora.SISTEMA, ""));
        Assert.assertFalse(computadora.caza(CampoComputadora.SISTEMA, "XXX"));
        Assert.assertFalse(computadora.caza(CampoComputadora.SISTEMA,
                                           Integer.valueOf(1000)));
        Assert.assertFalse(computadora.caza(CampoComputadora.SISTEMA, null));

        try {
            computadora.caza(null, null);
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            computadora.caza(X.A, computadora.getCompañia());
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Integer.valueOf(computadora.getMemoria());
            computadora.caza(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Double.valueOf(computadora.getDiscoDuro());
            computadora.caza(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            Object o = Integer.valueOf(computadora.getCosto());
            computadora.caza(X.A, o);
        } catch (IllegalArgumentException iae) {}
        try {
            computadora.caza(X.A, computadora.getSistema());
            Assert.fail();
        } catch (IllegalArgumentException iae) {}
        try {
            Assert.assertFalse(computadora.caza(X.A, null));
        } catch (IllegalArgumentException iae) {}
    }

    /* Inicializa el generador de números aleatorios. */
    static {
        random = new Random();
    }
}
