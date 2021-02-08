package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.*;
import java.io.*;

public class Entrada {

    private String[] args;

    public Entrada(String[] args){
	    this.args = args;
    }
  
    public Lista<String> cualEntrada(boolean banderas){
	    return (args.length == 0 || args.length == 1 && banderas) ? entradaEstandar() : entradaArchivos(banderas);
    }
    
    private Lista<String> entradaEstandar(){
	    Lista<String> lista = new Lista<>();
	    try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
	        String linea = br.readLine();
	        while (linea != null){
		    lista.agregaFinal(linea);
		    linea = br.readLine();
	        }
	    }catch (IOException ioe){
	        System.out.println("Falló la ejecución de la entrada estandar");
	        System.exit(1);
	    }
        return lista;
    }

    private Lista<String> entradaArchivos(boolean banderas){
        Lista<String> lista = new Lista<>();
        for (int i = 0; i < args.length; i++){
            if (!((args[i].contains("-r") || args[i].contains("-o")) && args[i].length() == 2)){
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[i])))){		
                String linea = br.readLine();
                while (linea != null){
                    lista.agregaFinal(linea);
                    linea = br.readLine();
                }
            }catch (IOException ioe){
                if (args[i -1].contains("-o") && args[i-1].length() == 2){
                    continue;
                }
                else {
                    System.out.println("Falló la ejecución en la entrada de archivos");
                    System.exit(1);
                    }
                }
            }
        }
        return lista;
    }
}
