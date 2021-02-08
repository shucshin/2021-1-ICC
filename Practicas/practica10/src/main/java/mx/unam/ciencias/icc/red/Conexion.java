package mx.unam.ciencias.icc.red;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import mx.unam.ciencias.icc.EventoBaseDeDatos;
import mx.unam.ciencias.icc.ExcepcionLineaInvalida;
import mx.unam.ciencias.icc.BaseDeDatos;
import mx.unam.ciencias.icc.BaseDeDatosEstudiantes;
import mx.unam.ciencias.icc.Lista;
import mx.unam.ciencias.icc.Registro;

/**
 * Clase para conexiones de la base de datos.
 */
public class Conexion<R extends Registro<R, ?>> {

    /* Contador de números seriales. */
    private static int contadorSerial;

    /* La entrada de la conexión. */
    private BufferedReader in;
    /* La salida de la conexión. */
    private BufferedWriter out;
    /* La base de datos. */
    private BaseDeDatos<R, ?> bdd;
    /* Lista de escuchas de conexión. */
    private Lista<EscuchaConexion<R>> escuchas;
    /* El enchufe. */
    private Socket enchufe;
    /* Si la conexión está activa. */
    private boolean activa;
    /* El número serial único de la conexión. */
    private int serial;

    /**
     * Define el estado inicial de una nueva conexión.
     * @param bdd la base de datos.
     * @param enchufe el enchufe de la conexión ya establecida.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    public Conexion(BaseDeDatos<R, ?> bdd, Socket enchufe) throws IOException {
        // Aquí va su código.
        try{
            this.bdd = bdd;
            this.enchufe = enchufe;
            in = new BufferedReader(new InputStreamReader(enchufe.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(enchufe.getOutputStream()));
            escuchas = new Lista<EscuchaConexion<R>>();
            activa = true;
            serial = ++contadorSerial;
        }
        catch(Exception e){
            throw new IOException();
        }
    }

    /**
     * Recibe los mensajes de la conexión. El método no termina hasta que la
     * conexión sea cerrada. Al ir leyendo su entrada, la conexión convertirá lo
     * que lea en mensajes y reportará cada uno a los escuchas.
     */
    public void recibeMensajes() {
        // Aquí va su código.
        try{
            String linea = null;
            while((linea = in.readLine()) != null){
                for(EscuchaConexion<R> escucha : escuchas)
                    escucha.mensajeRecibido(this, Mensaje.getMensaje(linea));
            }
            activa = false;
        }catch(IOException e){
            if(activa){
                for(EscuchaConexion<R> escucha : escuchas)
                    escucha.mensajeRecibido(this, Mensaje.INVALIDO);
            }
        }
        for(EscuchaConexion<R> escucha : escuchas)
            escucha.mensajeRecibido(this, Mensaje.DESCONECTAR);
    }

    /**
     * Recibe la base de datos del otro lado de la conexión.
     * @throws IOException si la base de datos no puede recibirse.
     */
    public void recibeBaseDeDatos() throws IOException {
        // Aquí va su código.
        bdd.limpia();
        bdd.carga(in);
    }

    /**
     * Envía la base de datos al otro lado de la conexión.
     * @throws IOException si la base de datos no puede enviarse.
     */
    public void enviaBaseDeDatos() throws IOException {
        // Aquí va su código.
        bdd.guarda(out);
        out.newLine();
        out.flush();
    }

    /**
     * Recibe un registro del otro lado de la conexión.
     * @return un registro del otro lado de la conexión.
     * @throws IOException si el registro no puede recibirse.
     */
    public R recibeRegistro() throws IOException {
        // Aquí va su código.
        String linea = in.readLine();
        R registro = bdd.creaRegistro();
        registro.deserializa(linea);
        return registro; 
    }

    /**
     * Envía un registro al otro lado de la conexión.
     * @param registro el registro a enviar.
     * @throws IOException si el registro no puede enviarse.
     */
    public void enviaRegistro(R registro) throws IOException {
        // Aquí va su código.
        out.write(registro.serializa());
        out.flush();
    }

    /**
     * Envía mensaje registro al otro lado de la conexión.
     * @param mensaje el mensaje a enviar.
     * @throws IOException si el mensaje no puede enviarse.
     */
    public void enviaMensaje(Mensaje mensaje) throws IOException {
        // Aquí va su código.
        out.write(mensaje.toString());
        out.newLine();
        out.flush();
    }

    /**
     * Regresa un número serial para cada conexión.
     * @return un número serial para cada conexión.
     */
    public int getSerial() {
        // Aquí va su código.
        return serial;
    }

    /**
     * Cierra la conexión.
     */
    public void desconecta() {
        // Aquí va su código.
        try{
            activa = false;
            enchufe.close();
        }
        catch(IOException e){
            System.out.println("No se pudo cerrar la conexión.");
        }
    }

    /**
     * Nos dice si la conexión es activa.
     * @return <code>true</code> si la conexión es activa; <code>false</code> en
     *         otro caso.
     */
    public boolean isActiva() {
        // Aquí va su código.
        return activa;
    }

    /**
     * Agrega un escucha de conexión.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscucha(EscuchaConexion<R> escucha) {
        // Aquí va su código.
        this.escuchas.agregaFinal(escucha);
    }
}
