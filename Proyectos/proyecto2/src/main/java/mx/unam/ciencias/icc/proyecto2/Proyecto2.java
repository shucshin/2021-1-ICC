package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.*;
import java.io.*;
import java.text.Collator;

public class Proyecto2 {
    public static void main(String[] args){

        Banderas banderas = new Banderas(args); 
        Boolean banderaR = banderas.BanderaR();
        Boolean banderaO = banderas.BanderaO();
        int indiceBanderaO = banderas.indiceBanderaO();

        Entrada entrada = new Entrada(args); 
        Lista<String> listaRecibida = entrada.cualEntrada(banderaR || banderaO); 
        Lista<String> listaOrdenada = OrdenadorLexicografico(listaRecibida); 
        Salida salida = new Salida(listaOrdenada);

        if (args == null){
            error();
        }
        if (banderaR == true){
            listaOrdenada = OrdenadorReversa(listaOrdenada);
        }
        if (banderaO == true && args[indiceBanderaO + 1] != null){
            salida.salidaGuarda(args[indiceBanderaO + 1], listaOrdenada);
        }
        else {
            salida.salidaEstandar(listaOrdenada);
        }
       
    }

    private static void error(){
        System.out.println("Uso: java -jar target/proyecto2.jar <archivo.txt>");
        System.exit(1);
    }

    private static Lista<String> OrdenadorLexicografico(Lista<String> listaRecibida){
        Lista<String> listaOrdenada;
        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.TERTIARY);
        listaOrdenada = listaRecibida.mergeSort((str1, str2) -> collator.compare
        (str1.replaceAll("\\P{L}+", ""), str2.replaceAll("\\P{L}+", "")));
        return listaOrdenada;
    }

    private static Lista<String> OrdenadorReversa(Lista<String> listaOrdenada){
        Lista<String> listaReversa = listaOrdenada.reversa();
        return listaReversa;
    }

    

    

}
    