package controlador;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.*;
import java.nio.file.Path;
import javafx.scene.shape.PathElement;
import modelo.Cartel;
import static vista.CartelesDeCineJavaFX.aviso;
import static vista.CartelesDeCineJavaFX.getCartelBox;
import static vista.CartelesDeCineJavaFX.getCarteles;
import static vista.CartelesDeCineJavaFX.getLista;
import static vista.CartelesDeCineJavaFX.getCambios;
import static vista.CartelesDeCineJavaFX.setCambios;
import static vista.CartelesDeCineJavaFX.setCarteles;
import static vista.DialogAltaCartel.getCartelTemp;
import static vista.DialogAltaCartel.getFileChooser;
import static vista.DialogAltaCartel.getFile;
import static vista.DialogAltaCartel.getStage;
import static vista.DialogAltaCartel.getTituloText;
import static vista.DialogAltaCartel.getAnyoText;
import static vista.DialogAltaCartel.getRutaText;
import static vista.DialogAltaCartel.setCartelTemp;
import static vista.DialogAltaCartel.setFile;

/**
 * Clase para recoger todos los métodos y separar la lógica del modelo y la vista
 * @author MSI
 */
public class Methods {
    
     /**
     * Método que carga el archivo inicial
     */
    public static void cargarArchivo(){
        aviso.setTitle("Archivo no encontrado");
        aviso.setContentText("No se cargaron datos previos en la aplicación.\nFichero no encontrado o vacío.");
        //Intentaremos cargar el archivo inicial
        try{
            try (FileInputStream archivoPelis = new FileInputStream("carteles.dat"); ObjectInputStream objectIn = new ObjectInputStream(archivoPelis)) {
                //Leemos los objetos serializados de carteles.dat y los cargamos en nuestro ArrayList<Cartel>
                setCarteles((ArrayList) objectIn.readObject());
            }
            //Llamada al método que carga los carteles en la ListView
            cargarCarteles();
        }
        catch(IOException | ClassNotFoundException e){
            aviso.showAndWait();
        }
    }
    /**
     * Método que carga los carteles desde el archivo de inicio
     */
    public static void cargarCarteles(){
        //Recorremos el ArrayList<Cartel> y añadimos mediante getItems().add() los diferentes elementos al ListView referenciado
        getCarteles().forEach((cartel) -> {
            getLista().getItems().add(cartel.getNombre() + " || " + cartel.getAnyo() + " || " + cartel.getRuta());
        });
    }
    /**
     * Método que carga el cartel guardado en la segunda ventana
     * @param cartelTemp Recibe como parámetro el cartel creado mediante el formulario
     */
    public static void cargarCartel(Cartel cartelTemp){
        //Añadimos el cartel creado en la segunda ventana en nuestro ArrayList<Cartel>
        getCarteles().add(cartelTemp);
        //Añadimos sus atributos como String al ListView
        getLista().getItems().add(cartelTemp.getNombre() + " || " + cartelTemp.getAnyo() + " || " +  cartelTemp.getRuta());
        //Variable de control de cambios a true
        setCambios(true);
    }
    /**
     * Método para visualizar la foto del cartel mediante su ruta
     * @throws IllegalArgumentException Excepción en caso de ruta incorrecta
     * @throws NullPointerException Excepción en caso de creación de imagen con ruta incorrecta
     */
    public static void verCartel(){
        aviso.setTitle("Archivo de imagen no cargado");
        aviso.setHeaderText(null);
        aviso.setContentText("Por favor, seleccione un cartel de la lista con URL válida");
        //Intentamos cargar la imagen mediante su ruta y de no poder, capturar su excepción
        try{
            int cartelInd = getLista().getSelectionModel().getSelectedIndex();
            Cartel cartelTemp = getCarteles().get(cartelInd);
            String rutaTemp = cartelTemp.getRuta();
            File fileTemp = new File(rutaTemp);
            if(rutaTemp==null || !fileTemp.exists()){
                aviso.showAndWait();
            }
            else{
                //La ruta del archivo de imagen debe de llevar delante "file:///"
                Image img = new Image("file:///" + rutaTemp);
                getCartelBox().setFill(new ImagePattern(img));
            }
        }
        catch (IllegalArgumentException | NullPointerException | ArrayIndexOutOfBoundsException ex){
            aviso.showAndWait();
        }
    }
    /**
     * Método para borrar el cartel seleccionado en la ListView
     */
    public static void borrarCartel(){
        aviso.setTitle("No se puede borrar");
        aviso.setContentText("No existe ningún cartel que borrar");
        if(!getCarteles().isEmpty()){
            //Creación de variable int que guardará el index del elemento seleccionado, que coincidará con el index de nuestro elemento en el ArrayList<Cartel>
            int cartelInd = getLista().getSelectionModel().getSelectedIndex();
            //Eliminación del cartel del ListView
            getLista().getItems().remove(cartelInd);
            //El contenedor del cartel tendrá el color gris, color original sin imagen
            getCartelBox().setFill(Color.GRAY);
            //Usamos el índice anterior para borrar la posición de nuestro ArrayList<Cartel>
            getCarteles().remove(cartelInd);
            //Variable de control de cambios a true
            setCambios(true);
        }
        else{
            aviso.showAndWait();
        }
    }
    /**
     * Método para guardar el archivo serializado de todos los carteles listados
     */
    public static void guardarCarteles(){
        try {
            FileOutputStream fosS = new FileOutputStream("carteles.dat");
            ObjectOutputStream ooS = new ObjectOutputStream(fosS);
            ooS.writeObject(getCarteles());
            aviso.setTitle("Guardado en archivo");
            aviso.setContentText("El archivo se ha guardado correctamente");
            aviso.showAndWait();
        }
        catch (FileNotFoundException ex) {
            aviso.setTitle("Archivo no cargado con éxito");
            aviso.setContentText("No se cargaron datos previos en la aplicación.\nFichero no encontrado, vacío o corrupto.");
            aviso.showAndWait();
        } 
        catch (IOException ex) {
            aviso.setTitle("Archivo no cargado con éxito");
            aviso.setContentText("No se cargaron datos previos en la aplicación.\nFichero no encontrado, vacío o corrupto.");
            aviso.showAndWait();
        }
        //Variable de control de cambios a false (ahora se nos permite cerrar la ventana sin aviso de control de guardado)
        setCambios(false);
    }
    /**
     * Método para cerrar el menú y comprobar si se ha guardado o no previamente
     * @return Booleano de control para cerrar o no la ventana
     */
    public static boolean cerrarMenu(){
        if(getCambios()){
            aviso.getButtonTypes().remove(ButtonType.OK);
            aviso.getButtonTypes().add(ButtonType.CANCEL);
            aviso.getButtonTypes().add(ButtonType.YES);
            aviso.setTitle("Cerrar la aplicación");
            aviso.setContentText("¿Salir sin guardar?");
            Optional<ButtonType> result = aviso.showAndWait();
            aviso.getButtonTypes().add(ButtonType.OK);
            aviso.getButtonTypes().remove(ButtonType.CANCEL);
            aviso.getButtonTypes().remove(ButtonType.YES);
            if(!result.isPresent()){
                return false;
            } 
            //Si el botón pulsado es OK, retornamos true a la llamada y se cerrará la ventana
            else if(result.get() == ButtonType.OK){
                return true;
            }
            //Si el botón pulsado es CANCEL, retornamos false a la llamada y no se cerrará la ventana
            else if(result.get() == ButtonType.CANCEL){
                aviso.close();
                return false;
            }
        }
        //En caso de que no se haya realizado cambios, retornaremos true y se cerrará la ventana
        return true;
    }
    /**
     * Método para aceptar el cartel nuevo, crear el objeto y actualizar el ListView y la variable de nuestro ArrayList
     * @return Booleano de control para cerrar o no el formulario
     */
    public static boolean aceptarCartelNuevo(){
        //En caso de que uno de los campos esté vacío, se nos mostrará una alerta y no saldremos del formulario
        if(getTituloText().getText().equals("") || getAnyoText().getText().equals("") || getRutaText().getText().equals("") || !new File(getRutaText().getText()).exists()){
            aviso.setTitle("No es posible añadir cartel nuevo");
            aviso.setHeaderText(null);
            aviso.setContentText("Por favor, rellene todos los campos adecuádamente y seleccione un fichero");
            aviso.showAndWait();
            return false;
        }
        else{
            try{
                String tituloTemp = getTituloText().getText();
                int anyoTemp = Integer.parseInt(getAnyoText().getText());
                String rutaTemp = getRutaText().getText();
                //Creación del objeto temporal creado
                setCartelTemp(new Cartel(tituloTemp,anyoTemp,rutaTemp));
                //Inserción en la variable ArrayList<Cartel> de la nueva instancia de objeto
                Methods.cargarCartel(getCartelTemp());
                return true;
            }
            catch (NumberFormatException ex){
                aviso.setTitle("Formato no válido para el anho");
                aviso.setHeaderText(null);
                aviso.setContentText("Por favor, para el anho, introduzca un anho");
                aviso.showAndWait();
                return false;
            }
        }
    }
    /**
     * Método para mostrar el cuadro de diálogo donde tendremos que seleccionar el archivo de imagen
     */
    public static void elegirCartel(){
        setFile(getFileChooser().showOpenDialog(getStage()));
        //Guardamos en una variable tipo String la ruta del archivo seleccionado
        String rutaArchivo = getFile().getAbsolutePath();
        //Pasamos la ruta al cuadro de texto de la ruta
        getRutaText().setText(rutaArchivo);
    }
}