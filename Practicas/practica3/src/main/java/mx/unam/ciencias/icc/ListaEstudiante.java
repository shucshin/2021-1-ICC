package mx.unam.ciencias.icc;


/**
 * <p>Clase para listas de estudiantes doblemente ligadas.</p>
 *
 * <p>Las listas de estudiantes nos permiten agregar elementos al inicio o final
 * de la lista, eliminar elementos de la lista, comprobar si un elemento está o
 * no en la lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas de estudiantes son iterables utilizando sus nodos. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * <p>Los elementos en una lista de estudiantes siempre son instancias de la
 * clase {@link Estudiante}.</p>
 */
public class ListaEstudiante {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Estudiante elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

    


        /* Construye un nodo con un elemento. */
        private Nodo(Estudiante elemento) {
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
        public Estudiante get() {
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


    private Nodo getNodo(Estudiante elemento){
        Nodo n = cabeza;
        if(elemento == null){
            return null;
        }
        else {
            return auxGNodo(n, elemento);
        }
    }

    private Nodo auxGNodo(Nodo n, Estudiante elemento){
        if (n == null){
            return null;
        }
        if(n.elemento.equals(elemento)){
            return n;
        }
        else {
            return auxGNodo(n.siguiente, elemento);
        }
    }

    
    private Nodo getNodo(int i){
        Nodo n = cabeza;
        if(i < 0 || i >= longitud){
            return null;
        }
        else{
            return auxGNodo(n, i, 0);
        }
    }

    private Nodo auxGNodo(Nodo n, int i, int cuenta){
        if (n == null){
            return null;
        }
        if(cuenta == i){
            return n;
        }
        else{
            return auxGNodo(n.siguiente, i, cuenta + 1);
        }
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
        if(cabeza.siguiente == null){
            return 1;
        }
        else{
            return auxGLongitud(longitud, ahora);
        }
    }

    private int auxGLongitud(int longitud, Nodo ahora){
        if (ahora.siguiente == null){
            return longitud;
        }
        else{
            return auxGLongitud(longitud + 1, ahora.siguiente);
        }
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
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaFinal(Estudiante elemento) {
        // Aquí va su código.
        if (elemento == null){
            return;
        }
        if (cabeza == null) {
            cabeza = new Nodo(elemento);
            rabo = cabeza;
            longitud++;
            return;
        }
        
        Nodo ahora = cabeza;
        if (ahora.siguiente != null){
            auxAFinal(ahora, elemento);
        }
        else{
            ahora.siguiente = new Nodo(elemento);
            rabo = ahora.siguiente;
            rabo.anterior = ahora;
            longitud++;
        }
    }

    private void auxAFinal(Nodo ahora, Estudiante elemento){
        if(ahora.siguiente == null){
            ahora.siguiente = new Nodo(elemento);
            rabo = ahora.siguiente;
            rabo.anterior = ahora;
            longitud++;;
            return;
        }
        else{
            auxAFinal(ahora.siguiente, elemento);
        }
    }



    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaInicio(Estudiante elemento) {
        // Aquí va su código.
        if (elemento == null){
            return;
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
     * @param elemento el elemento a insertar. El elemento se inserta únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void inserta(int i, Estudiante elemento) {
        // Aquí va su código.
        if (elemento == null){
            return;
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
    public void elimina(Estudiante elemento) {
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
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Estudiante eliminaPrimero() {
        // Aquí va su código.
        if(cabeza == null){
            return null;
        }
        Estudiante nuevoCabeza = cabeza.elemento;
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
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */ 
    public Estudiante eliminaUltimo() {
        // Aquí va su código.
        if(cabeza == null){
            return null;
        }
        Estudiante nuevoRabo = rabo.elemento;
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
    public boolean contiene(Estudiante elemento) {
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
    public ListaEstudiante reversa() {
        // Aquí va su código.
        ListaEstudiante listaReversa = new ListaEstudiante();
        Nodo original = cabeza;
        if(original == null){
            return listaReversa;
        }
        else{
            return auxReversa(listaReversa, original);
        }
    }

    private ListaEstudiante auxReversa(ListaEstudiante listaReversa, Nodo original){
        if(original == null){
            return listaReversa;
        }
        else{
            listaReversa.agregaInicio(original.elemento);
            auxReversa(listaReversa, original.siguiente);
            return listaReversa;
        }
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public ListaEstudiante copia() {
        // Aquí va su código.
        ListaEstudiante nuevaLista = new ListaEstudiante();
        Nodo original = cabeza;
        if(original == null){
            return nuevaLista;
        }
        else{
            return auxCopia(nuevaLista, original);
        }
    }

    private ListaEstudiante auxCopia(ListaEstudiante nuevaLista, Nodo original){
        if(original == null){
            return nuevaLista;
        }
        else{
            nuevaLista.agregaFinal(original.elemento);
            auxCopia(nuevaLista, original.siguiente);
            return nuevaLista;
        }
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
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getPrimero() {
        // Aquí va su código.
        if (cabeza == null){
            return null;
        }
        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Estudiante getUltimo() {
        // Aquí va su código.
        if (cabeza == null){
            return null;
        }
        Nodo ahora = cabeza;
        if(cabeza.siguiente == null){
            return ahora.elemento;
        }
        else{
            return auxGUltimo(ahora);
        }
    }

    private Estudiante auxGUltimo(Nodo ahora){
        if(ahora.siguiente == null){
            return ahora.elemento;
        }
        else{
            return auxGUltimo(ahora.siguiente);
        }
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, o <code>null</code> si
     *         <em>i</em> es menor que cero o mayor o igual que el número de
     *         elementos en la lista.
     */
    public Estudiante get(int i) {
        // Aquí va su código.
        int e = 0;
        if(i < 0 || i >= longitud){
            return null;
        }
        Nodo ahora = cabeza;
        if(cabeza.siguiente == null){
            return ahora.elemento;
        }
        else{
            return auxGet(ahora, e, i);
        }
    }

    private Estudiante auxGet(Nodo ahora, int e, int i){
        if(e == i){
            return ahora.elemento;
        }
        else{
            return auxGet(ahora.siguiente, e + 1, i);
        }
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(Estudiante elemento) {
        // Aquí va su código.
        int indice = 0;
        Nodo ahora = cabeza;
        if(cabeza == null){
            return -1;
        }
        else{
            return auxIndice(indice, ahora, elemento);
        }
    }

    private int auxIndice(int indice, Nodo ahora, Estudiante elemento){
        if (ahora.elemento == elemento){
            return indice;
        }
        if (ahora.siguiente == null){
            return -1;
        }
        else{
            return auxIndice(indice + 1, ahora.siguiente, elemento);
        }
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    public String toString() {
        // Aquí va su código.
        String cadena = "[";
        Nodo ahora = cabeza;
        if (cabeza == null){
            return "[]";
        }
        else{
            return auxString(cadena, ahora);
        }
    }

    private String auxString(String cadena, Nodo ahora){
        if(ahora.siguiente == null){
            return cadena + ahora.elemento.toString() + "]";
        }
        else {
            return auxString(cadena + ahora.elemento.toString()+ ", ", ahora.siguiente);
        }
    }



    /**
     * Nos dice si la lista es igual a la lista recibida.
     * @param lista la lista con la que hay que comparar.
     * @return <code>true</code> si la lista es igual a la recibida;
     *         <code>false</code> en otro caso.
     */
    public boolean equals(ListaEstudiante lista) {
        // Aquí va su código.
        Nodo a = this.cabeza;
        int i = 0;
        if(lista == null || this.longitud != lista.longitud){
            return false;
        }
        else{
            return auxEquals(lista, a, i);
        }
    }

    private boolean auxEquals(ListaEstudiante lista, Nodo a, int i){
        
        if(!(i < longitud)){
            return true;
        }
        if(!(a.elemento.equals(lista.get(i)))){
            return false;
        }
        
        else{
            return auxEquals(lista, a.siguiente, i + 1);
        }
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
