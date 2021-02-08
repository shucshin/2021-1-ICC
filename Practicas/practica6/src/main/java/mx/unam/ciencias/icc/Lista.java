package mx.unam.ciencias.icc;

import java.util.NoSuchElementException;

/**
 * <p>Clase para listas genéricas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas son iterables utilizando sus nodos. Las listas no aceptan a
 * <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            // Aquí va su código.
            this.elemento = elemento;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            // Aquí va su código.
            return anterior;
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            // Aquí va su código.
            return siguiente;
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public T get() {
            // Aquí va su código.
            return elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;




    private Nodo getNodo(Object elemento){
        Nodo n = cabeza;
        while(n != null){
            if(n.elemento.equals(elemento)){
                return n;
            }
            n = n.siguiente;
        }
        return null;
    }
        
    private Nodo getNodo(int i){
        if(i < 0 || i >= longitud){
            return null;
        }
        int cuenta = 0;
        Nodo n = cabeza;
        while(cuenta < i){      
            n = n.siguiente;
            cuenta++;
        }
        return n;
    }
        

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        // Aquí va su código.
        if (cabeza == null){
            return 0;
        }
        int longitud = 1;
        Nodo ahora = cabeza;
        while (ahora.siguiente != null){
            ahora = ahora.siguiente;
            longitud += 1;
        }
        return longitud;       
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        // Aquí va su código.
        if(cabeza == null){
            return true;
        }
        else{
            return false;
        }      
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        // Aquí va su código.
        if (elemento == null){
            throw new IllegalArgumentException();
        }
        if (cabeza == null) {
            cabeza = new Nodo(elemento);
            rabo = cabeza;
            longitud++;
            return;
        }
                    
        Nodo ahora = cabeza;
        while (ahora.siguiente != null) {
            ahora = ahora.siguiente;
        }
        ahora.siguiente = new Nodo(elemento);
        rabo = ahora.siguiente;
        rabo.anterior = ahora;
        longitud++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        // Aquí va su código.
        if (elemento == null){
            throw new IllegalArgumentException();
        }
        if (cabeza == null){
            cabeza = new Nodo(elemento);
            rabo = cabeza;
            longitud++;
            return;
        }          
        Nodo nuevaCabeza = new Nodo(elemento);
        nuevaCabeza.siguiente = cabeza;
        cabeza.anterior = nuevaCabeza;
        cabeza = nuevaCabeza;
        longitud++;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        // Aquí va su código.
        if (elemento == null){
            throw new IllegalArgumentException();
        } 
        if (i <= 0){
            agregaInicio(elemento);
            return;
        }
        if (i >= longitud){
            agregaFinal(elemento);
            return;
        }
        Nodo n = new Nodo(elemento);
        Nodo m = getNodo(i);
                    
        m.anterior.siguiente = n;
        n.anterior = m.anterior;
            
        m.anterior = n;
        n.siguiente = m;
        longitud++;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
        // Aquí va su código.
        Nodo m = getNodo(elemento);
        if (cabeza == null){
            return;
        }
        if (elemento == null){
            return;
        }
        if(cabeza == rabo && cabeza.elemento.equals(elemento)){
            limpia();
            return;
        }
        if(m == cabeza){
            eliminaPrimero();
            return;
        }
        if(m == rabo){
            eliminaUltimo();
            return;
        }
        m.anterior.siguiente = m.siguiente;
        m.siguiente.anterior = m.anterior;
        longitud--;
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        // Aquí va su código.
        if(cabeza == null){
            throw new NoSuchElementException();
        }
        T nuevoCabeza = cabeza.elemento;
        if(cabeza == rabo){
            limpia();
            return nuevoCabeza;
        }
        cabeza = cabeza.siguiente;
        cabeza.anterior = null;
        longitud--;
        return nuevoCabeza;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        // Aquí va su código.
        if(cabeza == null){
            throw new NoSuchElementException();
        }
        T nuevoRabo = rabo.elemento;
        if(cabeza == rabo){
            limpia();
            return nuevoRabo;
        }
        rabo = rabo.anterior;
        rabo.siguiente = null;
        longitud--;
        return nuevoRabo;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(T elemento) {
        // Aquí va su código.
        if (getNodo(elemento) == null){
            return false;
        }
        return true;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        // Aquí va su código.
        Lista<T> listaReversa = new Lista<>();
        Nodo original = cabeza;
        if(original == null){
            return listaReversa;
        }
        while(original != null){
            listaReversa.agregaInicio(original.elemento);
            original = original.siguiente;
        }
        return listaReversa;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        // Aquí va su código.
        Lista<T> nuevaLista = new Lista<>();
        Nodo original = cabeza;
        if(original == null){
            return nuevaLista;
        }
        while(original != null){
            nuevaLista.agregaFinal(original.elemento);
            original = original.siguiente;
        }
        return nuevaLista;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        // Aquí va su código.
        cabeza = rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        // Aquí va su código.
        if (cabeza == null){
            throw new NoSuchElementException();
        }
        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        // Aquí va su código.
        if (cabeza == null){
            throw new NoSuchElementException();
        }
        Nodo ahora = cabeza;
        while (ahora.siguiente != null) {
            ahora = ahora.siguiente;
        }
        return ahora.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        // Aquí va su código.
        int e = 0;
        if(i < 0 || i >= longitud){
            throw new ExcepcionIndiceInvalido();
        }
        Nodo ahora = cabeza;
        while (e++ < i){
            ahora = ahora.siguiente;
        }
        return ahora.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        // Aquí va su código.
        int indice = 0;
        Nodo ahora = cabeza;
        while(ahora != null){
            if (ahora.elemento == elemento) {
                return indice;
            }
            
            ahora = ahora.siguiente;
            indice++;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        // Aquí va su código.
        if (cabeza == null){
            return "[]";
        }
        String cadena = "[";
        Nodo ahora = cabeza;
        while(ahora != null){
            cadena = cadena + ahora.elemento.toString();
            if(ahora.siguiente != null){
                cadena = cadena + ", ";
            }
            ahora = ahora.siguiente;
        }
        cadena = cadena + "]";
        return cadena;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        // Aquí va su código.
        if(lista == null || this.longitud != lista.longitud){
            return false;
        }
        Nodo a = this.cabeza;
        for(int i = 0;i < longitud; i++){
            if(!(a.elemento.equals(lista.get(i)))){
                return false;
            }
            a = a.siguiente;
        }
        return true;
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        // Aquí va su código.
        return cabeza;
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        // Aquí va su código.
        return rabo;
    }
}
