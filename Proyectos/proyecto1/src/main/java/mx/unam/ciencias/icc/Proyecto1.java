package mx.unam.ciencias.icc;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Proyecto 1
 */
public class Proyecto1 {

    /* Hace búsquedas por compañia y memoria en la base de datos. */
    private static void busquedas(BaseDeDatosComputadoras bdd) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        System.out.printf("Entra una compañia para buscar: ");
        String compañia = sc.next();

        Lista r = bdd.buscaRegistros(CampoComputadora.COMPAÑIA, compañia);
        if (r.esVacia()) {
            System.out.printf("\nNo se hallaron computadoras " +
                              "con la compañia \"%s\".\n",
                              compañia);
        } else {
            System.out.printf("\nSe hallaron las siguientes " +
                              "computadoras con la compañia \"%s\":\n\n",
                              compañia);
            Lista.Nodo nodo = r.getCabeza();
            while (nodo != null) {
                System.out.println(nodo.get().toString() + "\n");
                nodo = nodo.getSiguiente();
            }
        }

        System.out.printf("Entra una memoria para buscar: ");
        int memoria = 0;
        try {
            memoria = sc.nextInt();
        } catch (InputMismatchException ime) {
            System.out.printf("Se entró una memoria inválida. " +
                              "Se interpretará como cero.\n");
        }

        r = bdd.buscaRegistros(CampoComputadora.MEMORIA, Integer.valueOf(memoria));
        if (r.esVacia()) {
            System.out.printf("\nNo se hallaron computadoras " +
                              "con la memoria mayor o igual a %d.\n",
                              memoria);
        } else {
            System.out.printf("\nSe hallaron las siguientes computadoras " +
                              "con la memoria mayor o igual a %d:\n\n",
                              memoria);
            Lista.Nodo nodo = r.getCabeza();
            while (nodo != null) {
                System.out.println(nodo.get().toString() + "\n");
                nodo = nodo.getSiguiente();
            }
        }
    }

    /* Crea una base de datos y la llena a partir de los datos que el usuario
       escriba a través del teclado. Después la guarda en disco duro y la
       regresa. */
    private static BaseDeDatosComputadoras escritura(String nombreArchivo) {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        File archivo = new File(nombreArchivo);

        if (archivo.exists()) {
            System.out.printf("El archivo \"%s\" ya existe.\n" +
                              "Presiona Ctrl-C si no quieres reescribirlo, " +
                              "o Enter para continuar...\n", nombreArchivo);
            sc.nextLine();
        }

        System.out.println("Entra computadoras a la base de datos.\n" +
                           "Cuando desees terminar, deja el nombre en blanco.\n");

        BaseDeDatosComputadoras bdd = new BaseDeDatosComputadoras();

        do {
            String compañia;
            int memoria = 0;
            double discoDuro = 0.0;
            int costo = 0;
            String sistema;

            System.out.printf("Compañia       : ");
            compañia = sc.next();
            
            if (compañia.equals(""))
                break;
            try {
                System.out.printf("Memoria(GB)    : ");
                memoria = sc.nextInt();
                System.out.printf("Disco Duro(GB) : ");
                discoDuro = sc.nextDouble();
                System.out.printf("Costo (Pesos)  : ");
                costo = sc.nextInt();
                System.out.printf("Sistema Operativo : ");
                sistema = sc.next();
            } catch (InputMismatchException ime) {
                System.out.println("\nNúmero inválido: se descartará " +
                                   "esta computadora.\n");
                continue;
            }
            Computadora e = new Computadora(compañia,
                                          memoria,
                                          discoDuro,
                                          costo,
                                          sistema);
            bdd.agregaRegistro(e);
            System.out.println();
        } while (true);

        int n = bdd.getNumRegistros();
        if (n == 1)
            System.out.printf("\nSe agregó 1 computadora.\n");
        else
            System.out.printf("\nSe agregaron %d computadoras.\n", n);

        try {
            FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            bdd.guarda(out);
            out.close();
        } catch (IOException ioe) {
            System.out.printf("No pude guardar en el archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }

        System.out.printf("\nBase de datos guardada exitosamente en \"%s\".\n",
                          nombreArchivo);

        return bdd;
    }

    /* Crea una base de datos y la llena cargándola del disco duro. Después la
       regresa. */
    private static BaseDeDatosComputadoras lectura(String nombreArchivo) {
        BaseDeDatosComputadoras bdd = new BaseDeDatosComputadoras();

        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            BufferedReader in = new BufferedReader(isIn);
            bdd.carga(in);
            in.close();
        } catch (IOException ioe) {
            System.out.printf("No pude cargar del archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }

        System.out.printf("Base de datos cargada exitosamente de \"%s\".\n\n",
                          nombreArchivo);

        Lista r = bdd.getRegistros();
        Lista.Nodo nodo = r.getCabeza();
        while (nodo != null) {
            System.out.println(nodo.get().toString() + "\n");
            nodo = nodo.getSiguiente();
        }

        return bdd;
    }

    /* Imprime en pantalla cómo debe usarse el programa y lo termina. */
    private static void uso() {
        System.out.println("Uso: java -jar practica5.jar [-g|-c] <archivo>");
        System.exit(1);
    }

    public static void main(String[] args) {
        if (args.length != 2)
            uso();

        String bandera = args[0];
        String nombreArchivo = args[1];

        if (!bandera.equals("-g") && !bandera.equals("-c"))
            uso();

        BaseDeDatosComputadoras bdd;

        if (bandera.equals("-g"))
            bdd = escritura(nombreArchivo);
        else
            bdd = lectura(nombreArchivo);

        busquedas(bdd);
    }
}
