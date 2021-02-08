package mx.unam.ciencias.icc;

import java.util.Comparator;

/**
 * Clase para ordenar y buscar arreglos genéricos.
 */
public class Arreglos {

    /* Constructor privado para evitar instanciación. */
    private Arreglos() {}

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
    selectionSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
        int n = arreglo.length;
        for (int i = 0; i < n-1; i++){
            int min = i;
            for (int j = i+1; j < n; j++){
                if (comparador.compare(arreglo[j], arreglo[min]) < 0){
                    min = j;
                }
            }
            T temporal = arreglo[min];
            arreglo[min] = arreglo[i];
            arreglo[i] = temporal;
        }
    }

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
        // Aquí va su código.
        quickSort(arreglo, 0, arreglo.length-1, comparador);
    }

    private static <T> void
    quickSort(T[] arreglo, int left, int right, Comparator<T> comparador){
        if (arreglo == null || arreglo.length == 0 || left >= right){
            return;
        }
        int medium = left + (right - left) / 2;
        T pivot = arreglo[medium];

        int i = left;
        int j = right;
        while (i <= j){
            while (comparador.compare(arreglo[i], pivot) < 0){
                i++;
            }
            while (comparador.compare(arreglo[j], pivot) > 0){
                j--;
            }
            if (i <= j){
                T temporal = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = temporal;
                i++;
                j--;
            }
        }
        if (left < j){
            quickSort(arreglo, left, j, comparador);
        }
        if (right > i){
            quickSort(arreglo, i, right, comparador);
        }
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
        // Aquí va su código.
        int left = 0;
        int right = arreglo.length - 1;
        while (left <= right){
            int medium = (left + right) / 2;
            if(comparador.compare(arreglo[medium], elemento) == 0){
                return medium;
            }
            else if(comparador.compare(arreglo[medium], elemento) > 0){
                if(comparador.compare(arreglo[left], elemento) == 0){
                    return left;
                }
                right = medium - 1;
                left++;
            }
            else{
                if(comparador.compare(arreglo[right], elemento) == 0){
                return right;
                }
                right--;
                left = medium + 1;
            }
        }
        return -1;
    }
}
