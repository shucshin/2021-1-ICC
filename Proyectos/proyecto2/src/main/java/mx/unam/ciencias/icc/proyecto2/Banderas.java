package mx.unam.ciencias.icc.proyecto2;

import mx.unam.ciencias.icc.*;
import java.io.*;

public class Banderas {

    private String[] args;
    
    public Banderas(String[] args){
        this.args = args;
    }
    
    public boolean BanderaR(){
        for (int i = 0; i < args.length; i++){
            if (args[i].contains("-r") && args[i].length() == 2){
                return true;
            }
        }
        return false;
    }

    public boolean BanderaO(){
        for (int i = 0; i < args.length; i++){
            if (args[i].contains("-o") && args[i].length() == 2){
                return true;
            }
        }
        return false;
    }

    public int indiceBanderaO(){
        for (int i = 0; i < args.length; i++){
            if (args[i].contains("-o") && args[i].length() == 2){
                return i;
            }
        }
        return -1;
    } 
}
