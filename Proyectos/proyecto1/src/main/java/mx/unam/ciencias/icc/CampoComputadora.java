package mx.unam.ciencias.icc;

/**
 * Enumeración para los campos de un {@link Computadora}.
 */
public enum CampoComputadora {

    /** La compañia de la computadora */
    COMPAÑIA,
    /** La memoria de la computadora. */
    MEMORIA,
    /** El disco duro de la computadora */
    DISCODURO,
    /** El costo de la computadora. */
    COSTO,
    /** El sistema operativo de la computadora. */
    SISTEMA;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
        // Aquí va su código.
        switch(this){
            case COMPAÑIA: 
                return "Compañia";
            case MEMORIA: 
                return "Memoria";
            case DISCODURO: 
                return "Disco Duro";
            case COSTO: 
                return "Costo";
            case SISTEMA:
                return "Sistema Operativo";
            default: 
                throw new IllegalArgumentException();
        }
    }
}
