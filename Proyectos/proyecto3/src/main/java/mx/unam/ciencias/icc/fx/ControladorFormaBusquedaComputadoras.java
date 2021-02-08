package mx.unam.ciencias.icc.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import mx.unam.ciencias.icc.CampoComputadora;

/**
 * Clase para el controlador del contenido del diálogo para buscar computadoras.
 */
public class ControladorFormaBusquedaComputadoras extends ControladorForma {

    /* El combo del campo. */
    @FXML private ComboBox<CampoComputadora> opcionesCampo;
    /* El campo de texto para el valor. */
    @FXML private EntradaVerificable entradaValor;

    /* Inicializa el estado de la forma. */
    @FXML private void initialize() {
        entradaValor.setVerificador(s -> verificaValor(s));
        entradaValor.textProperty().addListener(
            (o, v, n) -> revisaValor(null));
    }

    /* Revisa el valor después de un cambio. */
    @FXML private void revisaValor(ActionEvent evento) {
        Tooltip.install(entradaValor, getTooltip());
        String s = entradaValor.getText();
        botonAceptar.setDisable(!entradaValor.esValida());
    }

    /* Manejador para cuando se activa el botón aceptar. */
    @FXML private void aceptar(ActionEvent evento) {
        aceptado = true;
        escenario.close();
    }

    /* Obtiene la pista. */
    private Tooltip getTooltip() {
        String m = "";
        switch (opcionesCampo.getValue()) {
        case COMPAÑIA:
            m = "Buscar por compañia necesita al menos un carácter";
            break;
        case MEMORIA:
            m = "Buscar por memoria necesita un número de al menos 1";
            break;
        case DISCODURO:
            m = "Buscar por discoDuro necesita un número de al menos 0.0";
            break;
        case COSTO:
            m = "Buscar por costo necesita un número de al menos 0";
            break;
        case SISTEMA:
            m = "Buscar por sistema necesita al menos un carácter";
            break;
        }
        return new Tooltip(m);
    }

    /* Verifica el valor. */
    private boolean verificaValor(String s) {
        switch (opcionesCampo.getValue()) {
        case COMPAÑIA:   return verificaCompañia(s);
        case MEMORIA:   return verificaMemoria(s);
        case DISCODURO: return verificaDiscoDuro(s);
        case COSTO:     return verificaCosto(s);
        case SISTEMA:     return verificaSistema(s);
        default:       return false;
        }
    }

    /* Verifica que el compañia a buscar sea válido. */
    private boolean verificaCompañia(String c) {
        return c != null && !c.isEmpty();
    }

    /* Verifica que el número de memoria a buscar sea válido. */
    private boolean verificaMemoria(String m) {
        if (m == null || m.isEmpty())
            return false;
        int memoria = -1;
        try {
            memoria = Integer.parseInt(m);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return memoria >= 10000000 && memoria < 999999999;
    }

    /* Verifica que el discoDuro a buscar sea válido. */
    private boolean verificaDiscoDuro(String p) {
        if (p == null || p.isEmpty())
            return false;
        double discoDuro = -1.0;
        try {
            discoDuro = Double.parseDouble(p);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return discoDuro >= 0.0 && discoDuro <= 10.0;
    }

    /* Verifica que la costo a buscar sea válida. */
    private boolean verificaCosto(String e) {
        if (e == null || e.isEmpty())
            return false;
        int costo = -1;
        try {
            costo = Integer.parseInt(e);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return costo >= 13 && costo <= 99;
    }

    /* Verifica que el sistema a buscar sea válido. */
    private boolean verificaSistema(String c) {
        return c != null && !c.isEmpty();
    }

    /**
     * Regresa el campo seleccionado.
     * @return el campo seleccionado.
     */
    public CampoComputadora getCampo() {
        return opcionesCampo.getValue();
    }

    /**
     * Regresa el valor ingresado.
     * @return el valor ingresado.
     */
    public Object getValor() {
        switch (opcionesCampo.getValue()) {
        case COMPAÑIA:   return entradaValor.getText();
        case MEMORIA:   return Integer.parseInt(entradaValor.getText());
        case DISCODURO: return Double.parseDouble(entradaValor.getText());
        case COSTO:     return Integer.parseInt(entradaValor.getText());
        case SISTEMA:   return entradaValor.getText();
        default:       return entradaValor.getText(); // No debería ocurrir.
        }
    }

    /**
     * Define el foco incial del diálogo.
     */
    @Override public void defineFoco() {
        entradaValor.requestFocus();
    }
}
