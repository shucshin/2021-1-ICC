package mx.unam.ciencias.icc.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import mx.unam.ciencias.icc.Computadora;

/**
 * Clase para el controlador del contenido del diálogo para editar y crear
 * computadoras.
 */
public class ControladorFormaComputadora extends ControladorForma {

    /* La entrada verificable para el compañia. */
    @FXML private EntradaVerificable entradaCompañia;
    /* La entrada verificable para el número de memoria. */
    @FXML private EntradaVerificable entradaMemoria;
    /* La entrada verificable para el discoDuro. */
    @FXML private EntradaVerificable entradaDiscoDuro;
    /* La entrada verificable para la costo. */
    @FXML private EntradaVerificable entradaCosto;
     /* La entrada verificable para el sistema. */
     @FXML private EntradaVerificable entradaSistema;

    /* El valor del compañia. */
    private String compañia;
    /* El valor del número de memoria. */
    private int memoria;
    /* El valor del discoDuro. */
    private double discoDuro;
    /* El valor de la costo. */
    private int costo;
    /* El valor del sistema. */
    private String sistema;

    /* El computadora creado o editado. */
    private Computadora computadora;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaCompañia.setVerificador(n -> verificaCompañia(n));
        entradaMemoria.setVerificador(c -> verificaMemoria(c));
        entradaDiscoDuro.setVerificador(p -> verificaDiscoDuro(p));
        entradaCosto.setVerificador(e -> verificaCosto(e));
        entradaSistema.setVerificador(s -> verificaSistema(s));

        entradaCompañia.textProperty().addListener(
            (o, v, n) -> verificaComputadora());
        entradaMemoria.textProperty().addListener(
            (o, v, n) -> verificaComputadora());
        entradaDiscoDuro.textProperty().addListener(
            (o, v, n) -> verificaComputadora());
        entradaCosto.textProperty().addListener(
            (o, v, n) -> verificaComputadora());
        entradaSistema.textProperty().addListener(
            (o, v, n) -> verificaComputadora());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        actualizaComputadora();
        aceptado = true;
        escenario.close();
    }

    /**
     * Define el computadora del diálogo.
     * @param computadora el nuevo computadora del diálogo.
     */
    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
        if (computadora == null)
            return;
        this.computadora = new Computadora(null, 0, 0, 0, null);
        this.computadora.actualiza(computadora);
        entradaCompañia.setText(computadora.getCompañia());
        String c = String.format("%d", computadora.getMemoria());
        entradaMemoria.setText(c);
        String p = String.format("%2.2f", computadora.getDiscoDuro());
        entradaDiscoDuro.setText(p);
        entradaCosto.setText(String.valueOf(computadora.getCosto()));
        entradaSistema.setText(computadora.getSistema());
    }

    /* Verifica que los cuatro campos sean válidos. */
    private void verificaComputadora() {
        boolean n = entradaCompañia.esValida();
        boolean c = entradaMemoria.esValida();
        boolean p = entradaDiscoDuro.esValida();
        boolean e = entradaCosto.esValida();
        boolean s = entradaSistema.esValida();
        botonAceptar.setDisable(!n || !c || !p || !e || !s);
    }

    /* Verifica que el compañia sea válido. */
    private boolean verificaCompañia(String n) {
        if (n == null || n.isEmpty())
            return false;
        compañia = n;
        return true;
    }

    /* Verifica que el número de memoria sea válido. */
    private boolean verificaMemoria(String c) {
        if (c == null || c.isEmpty())
            return false;
        try {
            memoria = Integer.parseInt(c);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return memoria >= 0 && memoria < 999999999;
    }

    /* Verifica que el discoDuro sea válido. */
    private boolean verificaDiscoDuro(String p) {
        if (p == null || p.isEmpty())
            return false;
        try {
            discoDuro = Double.parseDouble(p);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return discoDuro >= 0.0 && discoDuro <= 9999999.0;
    }

    /* Verifica que la costo sea válida. */
    private boolean verificaCosto(String e) {
        if (e == null || e.isEmpty())
            return false;
        try {
            costo = Integer.parseInt(e);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return costo >= 0 && costo <= 999999999;
    }

    /* Verifica que el sistema sea válido. */
    private boolean verificaSistema(String n) {
        if (n == null || n.isEmpty())
            return false;
        compañia = n;
        return true;
    }

    /* Actualiza al computadora, o lo crea si no existe. */
    private void actualizaComputadora() {
        if (computadora != null) {
            computadora.setCompañia(compañia);
            computadora.setMemoria(memoria);
            computadora.setDiscoDuro(discoDuro);
            computadora.setCosto(costo);
            computadora.setSistema(sistema);
        } else {
            computadora = new Computadora(compañia, memoria, discoDuro, costo, sistema);
        }
    }

    /**
     * Regresa el computadora del diálogo.
     * @return el computadora del diálogo.
     */
    public Computadora getComputadora() {
        return computadora;
    }

    /**
     * Define el verbo del botón de aceptar.
     * @param verbo el nuevo verbo del botón de aceptar.
     */
    public void setVerbo(String verbo) {
        botonAceptar.setText(verbo);
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaCompañia.requestFocus();
    }
}
