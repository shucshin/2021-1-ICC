package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.*;
import java.io.*;

public class Salida {

    private Lista<String> lista;

    public Salida(Lista<String> lista) {
		this.lista = lista;
    }

    public void salidaEstandar(Lista<String> listaOrdenada){
        for (String imprimeConsola : listaOrdenada){
            System.out.println(imprimeConsola);
        }    
    }

    public void salidaGuarda(String nombreSalida, Lista<String> lista){
        try {
            File file = new File(nombreSalida);
            FileWriter fileW = new FileWriter(file, false);
            for (String linea : lista){
                fileW.write(linea + "\n");
            }
            fileW.close();
        } catch (IOException ioe){
            System.out.printf("No pude escribir en el archivo \"%s\".\n",
                              nombreSalida);
            System.exit(1);
        }
    }
}
