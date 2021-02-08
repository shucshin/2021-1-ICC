package mx.unam.ciencias.icc;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede serializarse en una línea de texto y deserializarse de una línea de
 * texto; además de determinar si sus campos cazan valores arbitrarios y
 * actualizarse con los valores de otro estudiante.
 */
public class Estudiante implements Registro<Estudiante, CampoEstudiante> {

    /* Nombre del estudiante. */
    private String nombre;
    /* Número de cuenta. */
    private int cuenta;
    /* Pormedio del estudiante. */
    private double promedio;
    /* Edad del estudiante.*/
    private int edad;

    /**
     * Define el estado inicial de un estudiante.
     * @param nombre el nombre del estudiante.
     * @param cuenta el número de cuenta del estudiante.
     * @param promedio el promedio del estudiante.
     * @param edad la edad del estudiante.
     */
    public Estudiante(String nombre,
                      int    cuenta,
                      double promedio,
                      int    edad) {
        // Aquí va su código.
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.promedio = promedio;
        this.edad = edad;
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        // Aquí va su código.
        return nombre;
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        // Aquí va su código.
        this.nombre = nombre;
    }

    /**
     * Regresa el número de cuenta del estudiante.
     * @return el número de cuenta del estudiante.
     */
    public int getCuenta() {
        // Aquí va su código.
        return cuenta;
    }

    /**
     * Define el número cuenta del estudiante.
     * @param cuenta el nuevo número de cuenta del estudiante.
     */
    public void setCuenta(int cuenta) {
        // Aquí va su código.
        this.cuenta = cuenta;
    }

    /**
     * Regresa el promedio del estudiante.
     * @return el promedio del estudiante.
     */
    public double getPromedio() {
        // Aquí va su código.
        return promedio;
    }

    /**
     * Define el promedio del estudiante.
     * @param promedio el nuevo promedio del estudiante.
     */
    public void setPromedio(double promedio) {
        // Aquí va su código.
        this.promedio = promedio;
    }

    /**
     * Regresa la edad del estudiante.
     * @return la edad del estudiante.
     */
    public int getEdad() {
        // Aquí va su código.
        return edad;
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setEdad(int edad) {
        // Aquí va su código.
        this.edad = edad;
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
        // Aquí va su código.
        String cadenaEst = String.format("Nombre   : %s\n" + "Cuenta   : %09d\n" + "Promedio : %2.2f\n" + "Edad     : %d", 
        nombre, cuenta, promedio, edad);
        return cadenaEst;
    }

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el estudiante se comparará.
     * @return <code>true</code> si el objeto recibido es un estudiante con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Estudiante))
            return false;
        Estudiante estudiante = (Estudiante)objeto;
        // Aquí va su código.
        return this.nombre.equals(estudiante.nombre) && this.cuenta == estudiante.cuenta && this.promedio == estudiante.promedio && this.edad == estudiante.edad;

    }

    /**
     * Regresa el estudiante serializado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Estudiante#deserializa}.
     * @return la serialización del estudiante en una línea de texto.
     */
    @Override public String serializa() {
        // Aquí va su código.
        String linea = String.format("%s\t%d\t%2.2f\t%d\n",
        nombre, cuenta, promedio, edad);
        return linea;
    }

    /**
     * Deserializa una línea de texto en las propiedades del estudiante. La
     * serialización producida por el método {@link Estudiante#serializa} debe
     * ser aceptada por este método.
     * @param linea la línea a deserializar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una serialización válida de un estudiante.
     */
    @Override public void deserializa(String linea) {
        // Aquí va su código.
        if (linea == null){
            throw new ExcepcionLineaInvalida();
        }

        linea = linea.trim();
        String[] split = linea.split("\t");

        if(split.length != 4){
            throw new ExcepcionLineaInvalida();
        }
        
        this.nombre = split[0];
        try{
            this.cuenta = Integer.parseInt(split[1]);
            this.promedio = Double.parseDouble(split[2]);
            this.edad = Integer.parseInt(split[3]);
        }
        catch(NumberFormatException numFE){
            throw new ExcepcionLineaInvalida();
        }
    }

    /**
     * Actualiza los valores del estudiante con los del estudiante recibido.
     * @param estudiante el estudiante con el cual actualizar los valores.
     * @throws IllegalArgumentException si el estudiante es <code>null</code>.
     */
    @Override public void actualiza(Estudiante estudiante) {
        // Aquí va su código.
        if (estudiante == null){
            throw new IllegalArgumentException();
        }
        nombre = estudiante.nombre;
        cuenta = estudiante.cuenta;
        promedio = estudiante.promedio;
        edad = estudiante.edad;
    }

    /**
     * Nos dice si el estudiante caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param valor el valor con el que debe cazar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoEstudiante#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#CUENTA} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la cuenta del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#PROMEDIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al promedio del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la edad del
     *              estudiante.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean caza(CampoEstudiante campo, Object valor) {
        // Aquí va su código.
        if (!(campo instanceof CampoEstudiante))
            throw new IllegalArgumentException();
        CampoEstudiante CampoE = (CampoEstudiante)campo;
        switch(CampoE){
            case NOMBRE:
                return cazaNombre(valor);
            case CUENTA:
                return cazaCuenta(valor);
            case PROMEDIO:
                return cazaPromedio(valor);
            case EDAD:
                return cazaEdad(valor);
            default: 
                return false;
        }
    }

    private boolean cazaNombre(Object valorNom){
        if(!(valorNom instanceof String)){
            return false;
        }
        String val = (String)valorNom;
        if(val.isEmpty()){
            return false;
        }
        return nombre.indexOf(val) != -1;
    }

    private boolean cazaCuenta(Object valorCue){
        if(!(valorCue instanceof Integer)){
            return false;
        }
        Integer val = (Integer)valorCue;
        return val.intValue() <= cuenta;
    }

    private boolean cazaPromedio(Object valorPro){
        if(!(valorPro instanceof Double)){
            return false;
        }
        Double val = (Double)valorPro;
        return val.doubleValue() <= promedio;
    }

    private boolean cazaEdad(Object valorEdad){
        if(!(valorEdad instanceof Integer)){
            return false;
        }
        Integer val = (Integer)valorEdad;
        return val.intValue() <= edad;
    }
}
