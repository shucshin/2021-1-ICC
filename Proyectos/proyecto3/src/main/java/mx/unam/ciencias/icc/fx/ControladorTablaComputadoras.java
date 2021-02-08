package mx.unam.ciencias.icc.fx;

import javafx.collections.ListChangeListener.Change;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import mx.unam.ciencias.icc.Computadora;
import mx.unam.ciencias.icc.Lista;

/**
 * Clase para el controlador de la tabla para mostrar la base de datos.
 */
public class ControladorTablaComputadoras {

    /* La tabla. */
    @FXML private TableView<Computadora> tabla;

    /* La columna del compañia. */
    @FXML private TableColumn<Computadora, String> columnaCompañia;
    /* La columna del número de memoria. */
    @FXML private TableColumn<Computadora, Number> columnaMemoria;
    /* La columna del discoDuro. */
    @FXML private TableColumn<Computadora, Number> columnaDiscoDuro;
    /* La columna de la costo. */
    @FXML private TableColumn<Computadora, Number> columnaCosto;
    /* La columna del sistema. */
    @FXML private TableColumn<Computadora, String> columnaSistema;

    /* El modelo de la selección. */
    TableView.TableViewSelectionModel<Computadora> modeloSeleccion;
    /* La selección. */
    private ObservableList<TablePosition> seleccion;
    /* Lista de escuchas de selección. */
    private Lista<EscuchaSeleccion> escuchas;
    /* Los renglones en la tabla. */
    private ObservableList<Computadora> renglones;

    /* Inicializa el controlador. */
    @FXML private void initialize() {
        renglones = tabla.getItems();
        modeloSeleccion = tabla.getSelectionModel();
        modeloSeleccion.setSelectionMode(SelectionMode.MULTIPLE);
        seleccion = modeloSeleccion.getSelectedCells();
        ListChangeListener<TablePosition> lcl = c -> cambioEnSeleccion();
        seleccion.addListener(lcl);
        columnaCompañia.setCellValueFactory(c -> c.getValue().compañiaProperty());
        columnaMemoria.setCellValueFactory(c -> c.getValue().memoriaProperty());
        columnaDiscoDuro.setCellValueFactory(
            c -> c.getValue().discoDuroProperty());
        columnaCosto.setCellValueFactory(c -> c.getValue().costoProperty());
        columnaSistema.setCellValueFactory(c -> c.getValue().sistemaProperty());
        escuchas = new Lista<EscuchaSeleccion>();
    }

    /* Notifica a los escuchas que hubo un cambio en la selección. */
    private void cambioEnSeleccion() {
        for (EscuchaSeleccion escucha : escuchas)
            escucha.renglonesSeleccionados(seleccion.size());
    }

    /**
     * Limpia la tabla.
     */
    public void limpiaTabla() {
        renglones.clear();
    }

    /**
     * Agrega un renglón a la tabla.
     * @param computadora el renglón a agregar.
     */
    public void agregaRenglon(Computadora computadora) {
        renglones.add(computadora);
        tabla.sort();
    }

    /**
     * Elimina un renglón de la tabla.
     * @param computadora el renglón a eliminar.
     */
    public void eliminaRenglon(Computadora computadora) {
        renglones.remove(computadora);
        tabla.sort();
    }

    /**
     * Selecciona renglones de la tabla.
     * @param computadoras los renglones a seleccionar.
     */
    public void seleccionaRenglones(Lista<Computadora> computadoras) {
        modeloSeleccion.clearSelection();
        for (Computadora computadora : computadoras)
            modeloSeleccion.select(computadora);
    }

    /**
     * Regresa una lista con los registros seleccionados en la tabla.
     * @return una lista con los registros seleccionados en la tabla.
     */
    public Lista<Computadora> getSeleccion() {
        Lista<Computadora> seleccionados = new Lista<Computadora>();
        for (TablePosition tp : seleccion) {
            int r = tp.getRow();
            seleccionados.agregaFinal(renglones.get(r));
        }
        return seleccionados;
    }

    /**
     * Regresa el computadora seleccionado en la tabla.
     * @return el computadora seleccionado en la tabla.
     */
    public Computadora getRenglonSeleccionado() {
        int r = seleccion.get(0).getRow();
        return renglones.get(r);
    }

    /**
     * Agrega un escucha de selección.
     * @param escucha el escucha a agregar.
     */
    public void agregaEscuchaSeleccion(EscuchaSeleccion escucha) {
        escuchas.agregaFinal(escucha);
    }

    /**
     * Fuerza un reordenamiento de la tabla.
     */
    public void reordena() {
        tabla.sort();
    }

    /**
     * Enfoca la tabla.
     */
    public void enfocaTabla() {
        tabla.requestFocus();
    }
}
