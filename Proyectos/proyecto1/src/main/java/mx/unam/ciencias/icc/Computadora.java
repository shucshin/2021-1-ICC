package mx.unam.ciencias.icc;

/**
 * Clase para representar computadoras. Una computadora tiene compañia, memoria,
 * discoDuro, costo y sistema. La clase implementa {@link Registro}, por lo que
 * puede serializarse en una línea de texto y deserializarse de una línea de
 * texto; además de determinar si sus campos cazan valores arbitrarios y
 * actualizarse con los valores de otra computadora.
 */
public class Computadora implements Registro {

    /* Compañia de la computadora. */
    private String compañia;
    /* Memoria de la computadora. */
    private int memoria;
    /* Disco Duro de la computadora. */
    private double discoDuro;
    /* Costo de la computadora.*/
    private int costo;
    /* Sistema Operativo de la computadora.*/
    private String sistema;


    

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
        this.compañia = compañia;
        this.memoria = memoria;
        this.discoDuro = discoDuro;
        this.costo = costo;
        this.sistema = sistema;
    }

    /**
     * Regresa la compañia de la computadora.
     * @return la compañia de la computadora.
     */
    public String getCompañia() {
        // Aquí va su código.
        return compañia;
    }

    /**
     * Define la compañia de la computadora.
     * @param nombre la nueva compañia de la computadora.
     */
    public void setCompañia(String compañia) {
        // Aquí va su código.
        this.compañia = compañia;
    }

    /**
     * Regresa la memoria de la computadora.
     * @return la memoria de la computadora.
     */
    public int getMemoria() {
        // Aquí va su código.
        return memoria;
    }

    /**
     * Define la memoria de la computadora.
     * @param cuenta la nueva memoria de la computadora.
     */
    public void setMemoria(int memoria) {
        // Aquí va su código.
        this.memoria = memoria;
    }

    /**
     * Regresa el disco duro de la computadora.
     * @return el disco duro de la computadora.
     */
    public double getDiscoDuro() {
        // Aquí va su código.
        return discoDuro;
    }

    /**
     * Define el disco duro de la computadora.
     * @param promedio el nuevo disco duro de la computadora.
     */
    public void setDiscoDuro(double discoDuro) {
        // Aquí va su código.
        this.discoDuro = discoDuro;
    }

    /**
     * Regresa el costo de la computadora.
     * @return el costo de la computadora.
     */
    public int getCosto() {
        // Aquí va su código.
        return costo;
    }

    /**
     * Define el costo de la computadora.
     * @param edad el nuevo costo de la computadora.
     */
    public void setCosto(int costo) {
        // Aquí va su código.
        this.costo = costo;
    }

    /**
     * Regresa el sistema operativo de la computadora.
     * @return el sistema operativo de la computadora.
     */
    public String getSistema() {
        // Aquí va su código.
        return sistema;
    }

    /**
     * Define el sistema operativo de la computadora.
     * @param nombre el nuevo sistema operativo de la computadora.
     */
    public void setSistema(String sistema) {
        // Aquí va su código.
        this.sistema = sistema;
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
        compañia, memoria, discoDuro, costo, sistema);
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
        return this.compañia.equals(computadora.compañia) && this.memoria == computadora.memoria && this.discoDuro == computadora.discoDuro && this.costo == computadora.costo && this.sistema.equals(computadora.sistema);
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
        compañia, memoria, discoDuro, costo, sistema);
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
        
        this.compañia = split[0];
        this.sistema = split[4];
        try{
            this.memoria = Integer.parseInt(split[1]);
            this.discoDuro = Double.parseDouble(split[2]);
            this.costo = Integer.parseInt(split[3]);
        }
        catch(NumberFormatException numFE){
            throw new ExcepcionLineaInvalida();
        }
    }

    /**
     * Actualiza los valores de la computadora con los del registro recibido.
     * @param registro el registro con el cual actualizar los valores.
     * @throws IllegalArgumentException si el registro no es instancia de {@link
     *         Computadora}.
     */
    public void actualiza(Registro registro) {
        if (!(registro instanceof Computadora))
            throw new IllegalArgumentException("El registro debe ser " +
                                               "Computadora");
        Computadora computadora = (Computadora)registro;
        // Aquí va su código.
        this.compañia = computadora.compañia;
        this.memoria = computadora.memoria;
        this.discoDuro = computadora.discoDuro;
        this.costo = computadora.costo;
        this.sistema = computadora.sistema;
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
     * @throws IllegalArgumentException si el campo no es instancia de {@link
     *         CampoComputadora}.
     */
    public boolean caza(Enum campo, Object valor) {
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
        return compañia.indexOf(val) != -1;
    }

    private boolean cazaMemoria(Object valorMem){
        if(!(valorMem instanceof Integer)){
            return false;
        }
        Integer val = (Integer)valorMem;
        return val.intValue() <= memoria;
    }

    private boolean cazaDiscoDuro(Object valorDis){
        if(!(valorDis instanceof Double)){
            return false;
        }
        Double val = (Double)valorDis;
        return val.doubleValue() <= discoDuro;
    }

    private boolean cazaCosto(Object valorCosto){
        if(!(valorCosto instanceof Integer)){
            return false;
        }
        Integer val = (Integer)valorCosto;
        return val.intValue() <= costo;
    }

    private boolean cazaSistema(Object valorSis){
        if(!(valorSis instanceof String)){
            return false;
        }
        String val = (String)valorSis;
        if(val.isEmpty()){
            return false;
        }
        return sistema.indexOf(val) != -1;
    }
}
