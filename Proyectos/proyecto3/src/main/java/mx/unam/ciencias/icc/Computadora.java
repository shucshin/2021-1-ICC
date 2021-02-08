package mx.unam.ciencias.icc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para representar computadoras. Una computadora tiene compañia, memoria,
 * discoDuro, costo y sistema. La clase implementa {@link Registro}, por lo que
 * puede serializarse en una línea de texto y deserializarse de una línea de
 * texto; además de determinar si sus campos cazan valores arbitrarios y
 * actualizarse con los valores de otra computadora.
 */
public class Computadora implements Registro<Computadora, CampoComputadora> {

    /* Compañia de la computadora. */
    private StringProperty compañia;
    /* Memoria de la computadora. */
    private IntegerProperty memoria;
    /* Disco Duro de la computadora. */
    private DoubleProperty discoDuro;
    /* Costo de la computadora.*/
    private IntegerProperty costo;
    /* Sistema Operativo de la computadora.*/
    private StringProperty sistema;

    /**
     * Define el estado inicial de una computadora.
     * @param compañia la compañia de la computadora.
     * @param memoria la memoria de la computadora.
     * @param discoDuro el disco duro de la computadora.
     * @param costo el costo de la computadora.
     * @param sistema el sistema operativo de la computadora.
     */
    public Computadora(String compañia,
                      int    memoria,
                      double discoDuro,
                      int    costo,
                      String sistema) {
        // Aquí va su código.
        this.compañia = new SimpleStringProperty(compañia);
        this.memoria = new SimpleIntegerProperty(memoria);
        this.discoDuro = new SimpleDoubleProperty(discoDuro);
        this.costo = new SimpleIntegerProperty(costo);
        this.sistema = new SimpleStringProperty(sistema);
    }

    /**
     * Regresa la compañia de la computadora.
     * @return la compañia de la computadora.
     */
    public String getCompañia() {
        // Aquí va su código.
        return compañia.get();
    }

    /**
     * Define la compañia de la computadora.
     * @param nombre la nueva compañia de la computadora.
     */
    public void setCompañia(String compañia) {
        // Aquí va su código.
        this.compañia.set(compañia);
    }

    /**
     * Regresa la propiedad de la compañia.
     * @return la propiedad de la compañia.
     */
    public StringProperty compañiaProperty() {
        // Aquí va su código.
        return this.compañia;
    }

    /**
     * Regresa la memoria de la computadora.
     * @return la memoria de la computadora.
     */
    public int getMemoria() {
        // Aquí va su código.
        return memoria.get();
    }

    /**
     * Define la memoria de la computadora.
     * @param cuenta la nueva memoria de la computadora.
     */
    public void setMemoria(int memoria) {
        // Aquí va su código.
        this.memoria.set(memoria);
    }

    /**
     * Regresa la propiedad del número de memoria.
     * @return la propiedad del número de memoria.
     */
    public IntegerProperty memoriaProperty() {
        // Aquí va su código.
        return this.memoria;
    }

    /**
     * Regresa el disco duro de la computadora.
     * @return el disco duro de la computadora.
     */
    public double getDiscoDuro() {
        // Aquí va su código.
        return discoDuro.get();
    }

    /**
     * Define el disco duro de la computadora.
     * @param promedio el nuevo disco duro de la computadora.
     */
    public void setDiscoDuro(double discoDuro) {
        // Aquí va su código.
        this.discoDuro.set(discoDuro);
    }

    /**
     * Regresa la propiedad del disco duro.
     * @return la propiedad del disco duro.
     */
    public DoubleProperty discoDuroProperty() {
        // Aquí va su código.
        return this.discoDuro;
    }

    /**
     * Regresa el costo de la computadora.
     * @return el costo de la computadora.
     */
    public int getCosto() {
        // Aquí va su código.
        return costo.get();
    }

    /**
     * Define el costo de la computadora.
     * @param edad el nuevo costo de la computadora.
     */
    public void setCosto(int costo) {
        // Aquí va su código.
        this.costo.set(costo);
    }

    /**
     * Regresa la propiedad del costo.
     * @return la propiedad del costo.
     */
    public IntegerProperty costoProperty() {
        // Aquí va su código.
        return this.costo;
    }

    /**
     * Regresa el sistema operativo de la computadora.
     * @return el sistema operativo de la computadora.
     */
    public String getSistema() {
        // Aquí va su código.
        return sistema.get();
    }

    /**
     * Define el sistema operativo de la computadora.
     * @param nombre el nuevo sistema operativo de la computadora.
     */
    public void setSistema(String sistema) {
        // Aquí va su código.
        this.sistema.set(sistema);
    }

    /**
     * Regresa la propiedad del sistema.
     * @return la propiedad del sistema.
     */
    public StringProperty sistemaProperty() {
        // Aquí va su código.
        return this.sistema;
    }

    /**
     * Regresa una representación en cadena de la computadora.
     * @return una representación en cadena de la computadora.
     */
    @Override public String toString() {
        // Aquí va su código.
        String cadenaCompu = String.format("Compañia   : %s\n" + 
                                           "Memoria    : %d GB\n" + 
                                           "Disco Duro : %2.2f GB\n" + 
                                           "Costo      : %d Pesos\n" + 
                                           "Sistema Operativo : %s", 
        compañia.get(), memoria.get(), discoDuro.get(), costo.get(), sistema.get());
        return cadenaCompu;
    }

    /**
     * Nos dice si el objeto recibido es una computadora igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que la computadora se comparará.
     * @return <code>true</code> si el objeto recibido es una computadora con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Computadora))
            return false;
        Computadora computadora = (Computadora)objeto;
        // Aquí va su código.
        return this.getCompañia().equals(computadora.getCompañia()) && this.getMemoria() == computadora.getMemoria() && this.getDiscoDuro() == computadora.getDiscoDuro() && this.getCosto() == computadora.getCosto() && this.getSistema().equals(computadora.getSistema());
    }

    /**
     * Regresa la computadora serializada en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Computadora#deserializa}.
     * @return la serialización de la computadora en una línea de texto.
     */
    @Override public String serializa() {
        // Aquí va su código.
        String linea = String.format("%s\t%d\t%2.2f\t%d\t%s\n",
        compañia.get(), memoria.get(), discoDuro.get(), costo.get(), sistema.get());
        return linea;
    }

    /**
     * Deserializa una línea de texto en las propiedades de la computadora. La
     * serialización producida por el método {@link Computadora#serializa} debe
     * ser aceptada por este método.
     * @param linea la línea a deserializar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una serialización válida de una computadora.
     */
    @Override public void deserializa(String linea) {
        // Aquí va su código.
        if (linea == null){
            throw new ExcepcionLineaInvalida();
        }

        linea = linea.trim();
        String[] split = linea.split("\t");

        if(split.length != 5){
            throw new ExcepcionLineaInvalida();
        }
        
        String compañia = split[0];
        setCompañia(compañia);
        String sistema = split[4];
        setSistema(sistema);
        try{
            Integer memoria = Integer.parseInt(split[1]);
            Double discoDuro = Double.parseDouble(split[2]);
            Integer costo = Integer.parseInt(split[3]);
            setMemoria(memoria);
            setDiscoDuro(discoDuro);
            setCosto(costo);
        }
        catch(NumberFormatException numFE){
            throw new ExcepcionLineaInvalida();
        }
    }

    /**
     * Actualiza los valores de la computadora con los de la computadora recibida.
     * @param computadora la computadora con el cual actualizar los valores.
     * @throws IllegalArgumentException si la computadora es <code>null</code>.
     */
    public void actualiza(Computadora computadora) {
        // Aquí va su código.
        if (computadora == null)
            throw new IllegalArgumentException();
        setCompañia(computadora.getCompañia());
        setMemoria(computadora.getMemoria());
        setDiscoDuro(computadora.getDiscoDuro());
        setCosto(computadora.getCosto());
        setSistema(computadora.getSistema());
    }

    /**
     * Nos dice si la computadora caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param valor el valor con el que debe cazar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoComputadora#COMPAÑIA} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena de la compañia de la computadora.</li>
     *           <li><code>campo</code> es {@link CampoComputadora#MEMORIA} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la memoria de la
     *              computadora.</li>
     *           <li><code>campo</code> es {@link CampoComputadora#DISCODURO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al disco duro de la
     *              computadora.</li>
     *           <li><code>campo</code> es {@link CampoComputadora#COSTO} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual al costo de la
     *              computadora.</li>
     *           <li><code>campo</code> es {@link CampoComputadora#SISTEMA} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del sistema operativo de la computadora.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean caza(CampoComputadora campo, Object valor) {
        // Aquí va su código.
        if (!(campo instanceof CampoComputadora))
            throw new IllegalArgumentException();
        CampoComputadora CampoC = (CampoComputadora)campo;
        switch(CampoC){
            case COMPAÑIA:
                return cazaCompañia(valor);
            case MEMORIA:
                return cazaMemoria(valor);
            case DISCODURO:
                return cazaDiscoDuro(valor);
            case COSTO:
                return cazaCosto(valor);
            case SISTEMA:
                return cazaSistema(valor);
            default: 
                return false;
        }
    }

    private boolean cazaCompañia(Object valorCom){
        if(!(valorCom instanceof String)){
            return false;
        }
        String val = (String)valorCom;
        if(val.isEmpty()){
            return false;
        }
        return compañia.get().indexOf(val) != -1;
    }

    private boolean cazaMemoria(Object valorMem){
        if(!(valorMem instanceof Integer)){
            return false;
        }
        Integer val = (Integer)valorMem;
        return val.intValue() <= memoria.get();
    }

    private boolean cazaDiscoDuro(Object valorDis){
        if(!(valorDis instanceof Double)){
            return false;
        }
        Double val = (Double)valorDis;
        return val.doubleValue() <= discoDuro.get();
    }

    private boolean cazaCosto(Object valorCosto){
        if(!(valorCosto instanceof Integer)){
            return false;
        }
        Integer val = (Integer)valorCosto;
        return val.intValue() <= costo.get();
    }

    private boolean cazaSistema(Object valorSis){
        if(!(valorSis instanceof String)){
            return false;
        }
        String val = (String)valorSis;
        if(val.isEmpty()){
            return false;
        }
        return sistema.get().indexOf(val) != -1;
    }
}
