package vista;
import modelo.Cartel;
import controlador.Methods;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.*;
/**
 * Clase de la ventana secundaria (form) para incluir un cartel nuevo
 * @author Antonio Montes Martínez - PROG T9 - 2020
 */
public class DialogAltaCartel extends Stage {
        
    /**************************************************************/
    /**********************ATRIBUTOS PÚBLICOS**********************/
    /**************************************************************/
    /**
     * Variable de tipo Alert para los casos de error
     */
    static public Alert aviso = new Alert(Alert.AlertType.INFORMATION);
    
    /**************************************************************/
    /**********************ATRIBUTOS PRIVADOS**********************/
    /**************************************************************/
    /**
     * Variable estática privada para guardar el cartel que se crea y se pasa a la variable que contiene todos los carteles
     */
    private static Cartel cartelTemp;
    /**
     * Variable de tipo File para almacenar o abrir el archivo
     */
    private static File file;
    /**
     * Variable para pasar el TextField con el título de la película
     */
    private static TextField tituloText;
    /**
     * Variable para pasar el TextField con el año de la película
     */
    private static TextField anyoText;
    /**
     * Variable para pasar el TextField con la ruta del archivo de imagen del cartel
     */
    private static TextField rutaText;
    /**
     * Variable para la creación del segundo Stage
     */
    private static Stage secunStage = new Stage();
    /**
     * Variable de tipo FileChooser para elegir el archivo
     */
    private static FileChooser elegirRuta;
    
    protected static boolean modal = false;
    
    /*******************************************************/
    /**********************MÉTODOS GET**********************/
    /*******************************************************/
    /**
     * Get para obtener el cartel creado por formulario
     * @return Retorna una instancia de dicho cartel
     */
    public static Cartel getCartelTemp(){
        return DialogAltaCartel.cartelTemp;
    }
    /**
     * Get para obtener una variable de tipo File
     * @return Retorna dicha instancia de tipo File
     */
    public static File getFile(){
        return DialogAltaCartel.file;
    }
    /**
     * Get para obtener de manera privada el campo de texto del título del cartel
     * @return Retorna el valor del campo
     */
    public static TextField getTituloText(){
        return DialogAltaCartel.tituloText;
    }
    /**
     * Get para obtener de manera privada el campo de texto del año de la película
     * @return Retorna el valor del campo
     */
    public static TextField getAnyoText(){
        return DialogAltaCartel.anyoText;
    }
    /**
     * Get para obtener de manera privada el campo de texto de la ruta del archivo del cartel
     * @return Retorna el valor del campo
     */
    public static TextField getRutaText(){
        return DialogAltaCartel.rutaText;
    }
    /**
     * Get para obtener una instancia del segundo stage (segunda ventana)
     * @return Retorna dicha instanacia de tipo Stage
     */
    public static Stage getStage(){
        return DialogAltaCartel.secunStage;
    }
    /**
     * Get para obtener una instancia de tipo FileChooser para abrir el cuadro de diálogo y elegir cartel
     * @return Retorna dicha instanacia de tipo FileChooser
     */
    public static FileChooser getFileChooser(){
        return DialogAltaCartel.elegirRuta;
    }
    
    /*******************************************************/
    /**********************MÉTODOS SET**********************/
    /*******************************************************/
    /**
     * Set para establecer el cartel recién creado en el formulario
     * @param cartelTemp Recibe como parámetro dicho cartel creado
     */
    public static void setCartelTemp(Cartel cartelTemp){
        DialogAltaCartel.cartelTemp = cartelTemp;
    }
    /**
     * Set para establecer la instancia de tipo File
     * @param file Recibe como parámetro una variable de tipo File
     */
    public static void setFile(File file){
        DialogAltaCartel.file = file;
    }
    
    public DialogAltaCartel(CartelesDeCineJavaFX aThis) {
        
        //Una vez seteada la modalidad, si la ventana ya ha sido seteada como visible, no te deja setearla de nuevo
        if(modal == false){
            secunStage.initModality(Modality.APPLICATION_MODAL);
            modal = true;
        }
        
        //Creación del FileChooser con el nombre de ventana "Elegir ruta"
        elegirRuta = new FileChooser();
        elegirRuta.setTitle("Elegir ruta");
       //Filtros para la selección de archivo
        elegirRuta.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Todos los ficheros", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        //Creación de las etiquetas de los diferentes campos de texto
        Label titulo = new Label("Título: ");
        Label anyo = new Label("Año: ");
        Label ruta = new Label("Ruta imagen: ");
        
        //Redimensionado del campo del título
        tituloText = new TextField();
        tituloText.setPrefColumnCount(50);
        //Redimensionado del campo del año
        anyoText = new TextField();
        anyoText.setPrefColumnCount(15);
        //Redimensionado del campo de la ruta
        rutaText = new TextField();
        rutaText.setPrefColumnCount(15);
        
        //Contenedor de tipo HBox para redimensionar correctamente el campo de texto de año
        HBox cajaTexto1 = new HBox(anyoText);
        cajaTexto1.setMinWidth(50);
        cajaTexto1.setMaxWidth(50);
        
        //Creación de los botones
        Button aceptar = new Button("Aceptar");
        Button cancelar = new Button("Cancelar");
        Button elegir = new Button("Elegir imagen...");
        elegir.setAlignment(Pos.CENTER_RIGHT);
        
        //Creación del grid para posiciones los diferentes elementos y además, añadirle el estilo de background como PINK
        GridPane panel = new GridPane();
        panel.setHgap(8);
        panel.setVgap(8);
        panel.setPadding(new Insets(10));
        panel.setStyle("-fx-background-color: pink;");
        
        //Añadimos los diferentes elementos al grid en las coordenadas correspondientes
        panel.add(titulo,0,0);
        panel.add(anyo,0,1);
        panel.add(ruta,0,2);
        panel.add(tituloText,1,0);
        panel.add(cajaTexto1,1,1);
        panel.add(rutaText,1,2);
        panel.add(aceptar,0,3);
        panel.add(cancelar,1,3);
        panel.add(elegir,2,2);
        
        //Creación de la escena, añadiendo el panel y definiendo sus dimensiones
        Scene scene = new Scene(panel, 780, 140);
        
        secunStage.setTitle("Añadir cartel");
        secunStage.setScene(scene);
        secunStage.show();
        
        //Evento para el botón aceptar tras introducir los datos
        aceptar.setOnAction((ActionEvent e) -> {
            //En caso de que el método devuelva true, se cerrará la ventana (se habrán dado por válidos los campos)
            if(Methods.aceptarCartelNuevo()){
                secunStage.close();
            }
        });
        
        //Evento para el botón cancelar, el cual cerrará la ventana
        cancelar.setOnAction((ActionEvent e) -> {
            secunStage.close();
        });
        
        //Evento para abrir la ventana de elección de archivo 
        elegir.setOnAction((ActionEvent e) -> {
            Methods.elegirCartel();
        });
    }
}
