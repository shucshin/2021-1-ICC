package mx.unam.ciencias.icc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mx.unam.ciencias.icc.fx.ControladorInterfazComputadoras;
import mx.unam.ciencias.icc.fx.ControladorTablaComputadoras;

/**
 * ClienteProyecto3: Parte del cliente para práctica 10: Hilos de ejecución y
 * enchufes.
 */
public class ClienteProyecto3 extends Application {

    /* Vista de la interfaz computadoras. */
    private static final String INTERFAZ_COMPUTADORAS_FXML =
        "fxml/interfaz-computadoras.fxml";
    /* Vista de la tabla de computadoras. */
    private static final String TABLA_COMPUTADORAS_FXML =
        "fxml/tabla-computadoras.fxml";
    /* Ícono de la Facultad de Ciencias. */
    private static final String ICONO_CIENCIAS =
        "icons/ciencias.png";

    /**
     * Inicia la aplicación.
     * @param escenario la ventana principal de la aplicación.
     * @throws Exception si algo sale mal. Literalmente así está definido.
     */
    @Override public void start(Stage escenario) throws Exception {
        ClassLoader cl = getClass().getClassLoader();
        String url = cl.getResource(ICONO_CIENCIAS).toString();
        escenario.getIcons().add(new Image(url));
        escenario.setTitle("Administrador de Computadoras");

        FXMLLoader cargador =
            new FXMLLoader(cl.getResource(TABLA_COMPUTADORAS_FXML));
        GridPane tablaComputadora = (GridPane)cargador.load();
        ControladorTablaComputadoras controladorTablaComputadoras =
            cargador.getController();

        cargador = new FXMLLoader(cl.getResource(INTERFAZ_COMPUTADORAS_FXML));
        BorderPane interfazComputadoras = (BorderPane)cargador.load();
        interfazComputadoras.setCenter(tablaComputadora);
        ControladorInterfazComputadoras controladorInterfazComputadoras =
            cargador.getController();
        controladorInterfazComputadoras.setEscenario(escenario);
        controladorInterfazComputadoras.setControladorTablaComputadoras(
            controladorTablaComputadoras);

        Scene escena = new Scene(interfazComputadoras);
        escenario.setScene(escena);
        escenario.setOnCloseRequest(e -> controladorInterfazComputadoras.salir(null));
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
