package vista;
import controlador.Methods;
import modelo.Cartel;
import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * Clase de la ventana principal donde veremos los carteles de las películas
 * @author Antonio Montes Martínez - PROG T9 - 2020
 */
public class CartelesDeCineJavaFX extends Application {
    
    /**************************************************************/
    /**********************ATRIBUTOS PÚBLICOS**********************/
    /**************************************************************/
    /**
     * Variable tipo alerta para ser empleada de forma global
     */
    public static final Alert aviso = new Alert(AlertType.INFORMATION);
    
    /**************************************************************/
    /**********************ATRIBUTOS PRIVADOS**********************/
    /**************************************************************/
    /**
     * Variable que guardará las diferentes instancias de la clase Cartel
     */
    private static ArrayList<Cartel> carteles = new ArrayList<>();
    /**
     * Variable que guardará los elementos que se irán mostrando como String en la lista de carteles
     */
    private static ListView listaPelis = new ListView();
    /**
     * Variable para el control de cambios realizados, normalmente al eliminar o añadir un cartel
     */
    private static boolean cambios = false;
    /**
     * Varible pública y estática para ser vista y usada por otro paquete
     */
    private static Rectangle cartelBox;
    
    
    /*******************************************************/
    /**********************MÉTODOS GET**********************/
    /*******************************************************/
    /**
     * Get para obtener el marco del cartel
     * @return Retorna el marco del cartel
     */
    public static Rectangle getCartelBox(){
        return CartelesDeCineJavaFX.cartelBox;
    }
    /**
     * Get para obtener el array de carteles
     * @return Retorna el array de carteles
     */
    public static ArrayList<Cartel> getCarteles(){
        return CartelesDeCineJavaFX.carteles;
    }
    /**
     * Get para obtener la lista del ListView
     * @return Retorna la lista del ListView
     */
    public static ListView getLista(){
        return CartelesDeCineJavaFX.listaPelis;
    }
    /**
     * Get para obtener el booleano de control de cambios para el guardado
     * @return Retorna un booleado que determina si se ha de guardar o no
     */
    public static boolean getCambios(){
        return CartelesDeCineJavaFX.cambios;
    }
    
    /*******************************************************/
    /**********************MÉTODOS SET**********************/
    /*******************************************************/
    /**
     * Set para establecer la variable booleana de control de cambios para el guardado
     * @param cambio Recibe la variable booleana
     */
    public static void setCambios(boolean cambio){
        CartelesDeCineJavaFX.cambios = cambio;
    }
    /**
     * Set para actualizar el array de carteles una vez se añada o se borre un cartel
     * @param carteles Recibe el array actualizado
     */
    public static void setCarteles(ArrayList<Cartel> carteles){
        CartelesDeCineJavaFX.carteles = carteles;
    }
    /**
     * Método que muestra los diferentes elementos de la ventana y les asigna eventos en caso de acción por parte del usuario
     * @param primaryStage Recibe una instancia de Stage
     */
    @Override
    public void start(Stage primaryStage){
        
        //Carga del archivo principal con llamada al método en la clase Methods
        Methods.cargarArchivo();
        
        //El aviso no llevará header
        aviso.setHeaderText(null);
        
        //Creació del elemento que contendrá la imagen
        cartelBox = new Rectangle(300,400);
        cartelBox.setFill(Color.GRAY);
        
        //Creación de un HBox para colocar el contenedor de la imagen y poder aplicarle un SetAlignment
        HBox cartelFrame = new HBox();
        cartelFrame.setMinHeight(400);
        cartelFrame.setMinWidth(300);
        cartelFrame.getChildren().add(cartelBox);
        
        //Creación de la barra de menú superior
        MenuBar menu = new MenuBar();
        
        //Creación del contender VBox para contener el menú y el Grid 
        VBox vBox = new VBox();
        
        //Añadimos los diferentes botones, agrupados en el botón Archivo
        Menu menuArchivo = new Menu("Archivo");
        MenuItem guardarMenu = new MenuItem("Guardar");
        MenuItem cerrarMenu = new MenuItem("Cerrar");
        menuArchivo.getItems().add(guardarMenu);
        menuArchivo.getItems().add(cerrarMenu);
        
        //Añadimos los diferentes botones, agrupados en el botón Edición
        Menu menuEdicion = new Menu("Edición");
        MenuItem anadirMenu = new MenuItem("Añadir");
        MenuItem borrarMenu = new MenuItem("Borrar");
        menuEdicion.getItems().add(anadirMenu);
        menuEdicion.getItems().add(borrarMenu);
        
        //Añadimos los dos grupos al menú superior
        menu.getMenus().add(menuArchivo);
        menu.getMenus().add(menuEdicion);
        
        //Creación del grid que contendrá todos los elementos
        GridPane panel = new GridPane();
        panel.setHgap(8);
        panel.setVgap(8);
        panel.setPadding(new Insets(10));
        
        //Seteamos el ancho y alto de la lista de carteles
        listaPelis.setMinWidth(420);
        listaPelis.setMinHeight(500);
        
        //Creación de label para la parte superior de la lista (cabecera)
        Label etiquetaPeli = new Label("Película      ||       Año       ||       Ruta Cartel");
        
        //Creación del botón, con la V como mnemotécnica, para poder visualizar el cartel
        Button verCartel = new Button("_Ver cartel");
        
        //Añadimos al Grid los diferentes elementos en las coordenadas que nos convengan
        panel.add(etiquetaPeli, 0, 0);
        panel.add(listaPelis, 0, 1);
        panel.add(verCartel, 0, 2);
        verCartel.setAlignment(Pos.BOTTOM_LEFT);
        panel.add(cartelFrame, 1, 1);
        cartelFrame.setAlignment(Pos.TOP_LEFT);
        
        //Asociamos el menú superior y el grid al contenedor VBox
        vBox.getChildren().addAll(menu,panel);
        Scene scene = new Scene(vBox, 800 , 600);
        
        //Seteamos el título de la ventana, le añadimos la escena y la mostramos
        primaryStage.setTitle("Carteles de cine - Antonio Manuel Montes Martínez - DAW");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Evento para el botón que nos muestra el cartel
        verCartel.setOnAction((ActionEvent e) -> {
            Methods.verCartel();
        });
        
        //Evento que nos permite guardar el cartel
        guardarMenu.setOnAction((ActionEvent e) -> {
            Methods.guardarCarteles();
        });
        
        //Evento que nos permite cerrar la ventana
        cerrarMenu.setOnAction((ActionEvent e) -> {
            if(Methods.cerrarMenu()){
                primaryStage.close();
            }
        });
        
        //Evento que nos permite añadir películas y nos mostrará el formulario para ello
        anadirMenu.setOnAction((ActionEvent e) -> {
            new DialogAltaCartel(CartelesDeCineJavaFX.this);
        });
        
        //Evento que nos borrará un elemento seleccionado en la ListView
        borrarMenu.setOnAction((ActionEvent e) -> {
            Methods.borrarCartel();
        });
        
        //Gestión del evento en caso de cerrar la ventana con la X superior derecha
        primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e->this.closeWindowEvent(e, primaryStage));
    }
    
    /**
     * Método para gestionar el cierre de la ventana con la X superior derecha
     * @param event Tipo de evento
     * @param stage El stage (primaryStage) en este caso sobre el que se aplicará el evento
     */
    private void closeWindowEvent(WindowEvent event, Stage stage) {
        
        // Si hay cambios en la clase, avisamos al usuario con una ventanita
        if(cambios) {  
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getButtonTypes().remove(ButtonType.OK);
            alert.getButtonTypes().add(ButtonType.CANCEL);
            alert.getButtonTypes().add(ButtonType.YES);
            alert.setTitle("Salir de la aplicación");
            alert.setContentText(String.format("¿Salir sin guardar los cambios?"));
            alert.initOwner(stage.getOwner());
            Optional<ButtonType> res = alert.showAndWait();

            if(res.isPresent()) {
                if(res.get().equals(ButtonType.CANCEL))
                    event.consume();
            }
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
