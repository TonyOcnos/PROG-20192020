/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaut8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Clase agenda que contendrá una serie de métodos para creación de citas, borrado, comprobación de fechas...
 * @author Antonio Manuel Montes Martinez - PROG T8 - DAW
 */
public class Agenda {
    
    /**Variable constante de clase de máximas citas permitidas en cualquier día de la agenda*/
    public static final int MAX_CITAS_DIA_CUALQUIER_AGENDA = 40;
    /**Variable constante de clase de mínimas citas permitidas en cualquier día de la agenda*/
    public static final int MIN_CITAS_DIA_CUALQUIER_AGENDA = 5;
    
    /**Variable constante de instancia con el número máximo de citas permitidas en el día*/
    private final int MAX_CITAS_DIA;
    /**Variable tipo String para guardar el nombre de la agenda*/
    private String nombreAgenda;
    /**Variable tipo Lista de Citas para ir almacenando las citas creadas*/
    private List<Cita> listaCitas = new ArrayList<>();
    /**Variable tipo Set de Días de la semana (DayOfWeek) para almacenar los días no hábiles, normalmente Sábado y Domingo*/
    private Set<DayOfWeek> diasNoHabiles = new HashSet<>();
    /**Variable tipo Map de Periodo vacaciones/Intervalo de fechas (Key/Value) para almacenar los periodos donde no se trabaja*/
    private Map<String,IntervaloFechas> fechasNoLaborables = new HashMap<>();
    
    /**
     * Constructor de 4 parámetros para la creación de Agenda
     * @param nombreAgenda Parámetro que contiene el nombre de la agenda. Será "DEFAULT" en caso de parámetro "null"
     * @param maxCitas Parámetro que contiene el número máximo de citas dentro de unos límites
     * @param diasNoHabiles Parámetro que contiene una lista de los días no hábiles. Estará vacío en caso de parámetro "null"
     * @param diasNoLaborables Parámetro que contiene un mapa con los oeriodos no laborables. Estará vacío en caso de parámetro "null"
     * @throws IllegalArgumentException Excepción definida de manera personal para cada caso
     */
    public Agenda(String nombreAgenda, int maxCitas, HashSet<DayOfWeek> diasNoHabiles, HashMap diasNoLaborables) throws IllegalArgumentException{
        
        if(nombreAgenda==null){
            this.nombreAgenda = "DEFAULT";
        }
        else {
            this.nombreAgenda = nombreAgenda;
        }
        
        if(maxCitas < MIN_CITAS_DIA_CUALQUIER_AGENDA || maxCitas > MAX_CITAS_DIA_CUALQUIER_AGENDA){
            throw new IllegalArgumentException ("No puede haber menos de 5, ni más de 40, citas al día. En tu caso se han indicado: " + maxCitas);
        }
        else {
            this.MAX_CITAS_DIA = maxCitas;
        }
        
        if(diasNoHabiles == null){
            this.diasNoHabiles = Collections.<DayOfWeek>emptySet();
        }
        else {
            this.diasNoHabiles = diasNoHabiles;
        }
        
        if(diasNoLaborables == null){
            this.fechasNoLaborables = Collections.<String,IntervaloFechas>emptyMap();
        }
        else {
            this.fechasNoLaborables = diasNoLaborables;
        }
    }
    
    /**
     * Método para construir un objeto de la clase Agenda con valores predefinidos
     * @return Retorna un objeto creado de la clase Agenda
     */
    public static Agenda buildSampleAgenda(){
        
        //Creación del HashSet que contendrá los días no hábiles.
        //Dado que DayOfWeek contiene un ENUM de tipo DayOfWeek, al insertarlo lo haremos a través del método de clase valueOf
        HashSet<DayOfWeek> hsTemp = new HashSet<>();
        hsTemp.add(DayOfWeek.valueOf("SATURDAY"));
        hsTemp.add(DayOfWeek.valueOf("SUNDAY"));
        
        //Creación del HashMap que contendrá los periodos no laborables
        //Se usará una String como nombre del intervalo y un objeto tipo IntervaloFechas con dos periodos como parámetros
        HashMap<String,IntervaloFechas> hmTemp = new HashMap<>();
        hmTemp.put("SEMANASANTA", new IntervaloFechas(LocalDate.of(2020,4,2),LocalDate.of(2020,4,12)));
        hmTemp.put("PUENTEDIC", new IntervaloFechas(LocalDate.of(2019,12,5),LocalDate.of(2019,12,9)));
        hmTemp.put("MERECIDASVACACIONES", new IntervaloFechas(LocalDate.of(2019,11,3),LocalDate.of(2019,12,7)));
        hmTemp.put("NAVIDADES", new IntervaloFechas(LocalDate.of(2019,12,21),LocalDate.of(2020,1,7)));
        hmTemp.put("PUENTENOV", new IntervaloFechas(LocalDate.of(2019,11,1),LocalDate.of(2019,11,4)));
        
        //Llamada la método de clase estático buildSamplePersonas() para crear un array de 4 Clientes
        Cliente[] clientes=Persona.buildSamplePersonas();
        
        //Creación de 4 citas con los diferentes clientes creados previamente
        Cita c1 = new Cita(clientes[0], LocalDateTime.parse("2019-02-19T20:00:00"), "Arreglar frigorífico.");
        Cita c2 = new Cita(clientes[1], LocalDateTime.parse("2019-02-19T18:00:00"), "Arreglar caldera.");
        Cita c3 = new Cita(clientes[2], LocalDateTime.parse("2019-02-19T17:00:00"), "Arreglar lavadora.");    
        Cita c4 = new Cita(clientes[3], LocalDateTime.parse("2019-02-19T21:00:00"), "Arreglar secador.");
        
        //Creación de una instancia de clase Agenda con las variables creadas previamente
        Agenda agendaSample = new Agenda("SAMPLE", 10, hsTemp, hmTemp);
        
        //Añadimos a la lista de citas las listas creadas previamente
        agendaSample.listaCitas.add(c1);
        agendaSample.listaCitas.add(c2);
        agendaSample.listaCitas.add(c3);
        agendaSample.listaCitas.add(c4);
        
        //Retornamos la instancia de Agenda
        return agendaSample;
    }
    
    /**
     * Método para comprobar si el día pasado por parámetro es día hábil o no
     * @param diaComprobacion Parámetro tipo DayOfWeek con el día a comprobar
     * @return Retorna true en caso de ser hábil y false en caso de no serlo
     */
    public boolean esDiaHabil(DayOfWeek diaComprobacion){
        
        return !this.diasNoHabiles.contains(diaComprobacion);
    }
    
    /**
     * Método para comprobar si el día (LocalDate) pasado por parámetro está dentro de periodo no laborable
     * @param fecha Parámetro que contiene la fecha a comprobar
     * @return Retorna un ArrayList de String con los periodos que coinciden con dicha fecha
     */
    public ArrayList<String> noLaborable(LocalDate fecha){
        
        ArrayList<String> arrayFechas = new ArrayList<>();
        //Bucle para recorrer el HashMap de fechas no laborables
        for (String llave:this.fechasNoLaborables.keySet()){ 
            //Creación de variable tipo IntervaloFecha temporal para almacenar la llave del HashMap (K, V)
            IntervaloFechas ifTemp = this.fechasNoLaborables.get(llave);
            //Hacemos uso del método de instancia IntervaloFecha.estaDentroDelIntervalo para la comprobación
            if (ifTemp.estaDentroDelIntervalo(fecha)){
                //En caso de estar dentro se añade el valor llave al array de fechas que se va a devolver al final
                arrayFechas.add(llave);
            }
        }
        
        return arrayFechas;
    }
    /**
     * Método para buscar si el cliente pasado como parámetro con su id, ya tiene cita con nosotros
     * @param id Parámetro que contiene el id de nuestro cliente
     * @return Retorna la primera cita que encuentre y además coincida con el cliente que hemos pasado por parámetro
     */
    public Cita buscarCitaDeCliente(long id){
        
        //Bucle para recorrer la lista con citas
        for(Cita cita:this.listaCitas){
            Cliente clienteTemp = cita.getCliente();
            //En caso de que el id pasado por parámetro coincida con el id del cliente de la cita consultada 
            //se retornará dicha cita
            if(id==(clienteTemp.getId())){
                return cita;
            }
        }
        //De no encontrar ningún cliente, retorna null
        return null;        
    }
    
    /**
     * Método para buscar si el cliente pasado como parámetro ya tiene cita con nosotros
     * @param cliente Parámetro que contiene una instancia de la clase Cliente
     * @return Retorna la cita que tiene este cliente en concreto
     * @throws IllegalArgumentException Excepción definida de manera personal
     */
    public Cita buscarCitaDeCliente(Cliente cliente) throws IllegalArgumentException{
        
        //De ser null, se lanzará una excepción de tipo IllegalArgumentException personalizada
        if(cliente==null){
            throw new IllegalArgumentException ("El cliente a buscar no puede ser null.");
        }
        else{
            //De no ser null, haremos uso del método anterior que acepta el id del cliente. 
            //Para ello usaremos el método Cliente.getId() como parámetro
            return buscarCitaDeCliente(cliente.getId());
        }      
    }
    
    /**
     * Método para buscar y borrar la cita de un cliente pasando por parámetro su id
     * @param id Parámetro que contiene el id de nuestro cliente
     * @return Retorna la cita borrada
     */
    public Cita borrarCitaCliente(long id){
        
        //Bucle para recorrer la lista con citas
        for(Cita cita:this.listaCitas){
            Cliente clienteTemp = cita.getCliente();
            if(id==(clienteTemp.getId())){
                //El método remove en un método sobrecargado que acepta o bien un número int para acceder a cierta posición o bien mediante objeto
                this.listaCitas.remove(cita);
                return cita;
            }
        }
        return null;        
    }
    
    /**
     * Método para buscar y borrar la cita de un cliente pasando por parámetro una instancia de la clase Cliente
     * @param cliente Parámetro que contiene la instancia de la clase Cliente
     * @return Retorna la cita borrada
     * @throws IllegalArgumentException Excepción definida de manera personal 
     */
    public Cita borrarCitaCliente(Cliente cliente) throws IllegalArgumentException{
        
        //De ser null, se lanzará una excepción de tipo IllegalArgumentException personalizada
        if(cliente==null){
            throw new IllegalArgumentException ("El cliente no puede ser null al eliminar la cita.");
        }
        else{
            //De no ser null, haremos uso del método anterior que acepta el id del cliente. 
            //Para ello usaremos el método Cliente.getId() como parámetro
            return borrarCitaCliente(cliente.getId());
        }      
    }
    
    /**
     * Método para obtener todas las citas de una fecha en concreto
     * @param fecha Parámetro que tiene la fecha a consultar
     * @param orden Parámetro booleano que nos determina el orden. De ser true, se ordenará en orden cronológico. De ser false, se mostrarán por orden de inserción
     * @return Retorna una variable tipo List con todas las citas contenidas en esa fecha
     */
    public List<Cita> obtenerCitasFecha(LocalDate fecha, boolean orden){
        
        List<Cita> arrayCitas = new ArrayList<>();
        //Bucle para recorrer la lista con citas
        for(Cita cita:this.listaCitas){
            //Si coinciden los objetos, la fecha de la cita con la fecha pasada por parámetro, se añade dicha cita a la lista que retornará
            if(cita.getFecha().equals(fecha)){
                arrayCitas.add(cita);
            }
        }
        
        if(!orden){
            return arrayCitas;
        }
        else{
            //Para hacer uso del método Collections.sort(List l) hemos tenido que implementar la interface Comparable<Cita>
            //Ello nos permite sobreescribir el método compareTo de dicha interface y adaptarla a nuestro gusto
            //En este caso, el orden se ha establecido comparado la hora mediante Cita.getTime()
            Collections.sort(arrayCitas);
            return arrayCitas;
        }
    }
    
    /**
     * Método para agregar una cita a lista de citas
     * @param cita Parámetro que contiene la cita que queremos añadir
     * @throws IllegalArgumentException Excepción definida de manera personal 
     * @throws IllegalAgendaStateException Excepción personalizada y que requiere la instancia de un objeto de la misma
     */
    public void agregarCita(Cita cita) throws IllegalArgumentException, IllegalAgendaStateException {
        
        //Si la cita es null, se lanzará una expceción personalizada para tal caso
        if(cita==null){
            throw new IllegalArgumentException ("La cita no puede ser null.");
        }
        //Si la Cita es en día no habil, se instanciará un objecto de la clase IllegalAgendaStateException con mensaje y motivo
        else if(!esDiaHabil(cita.getFecha().getDayOfWeek())){
            throw new IllegalAgendaStateException  ("La cita no se puede crear porque no es un día hábil: " + cita.getFecha().getDayOfWeek().name(), IllegalAgendaStateException.Motivo.DIA_NO_DISPONIBLE);
        }
        //Si el array devuelto por el método noLaborable no está vacío (por lo que ha tenido al menos UNA coincidencia), 
        //se instanciará un objecto de la clase IllegalAgendaStateException con mensaje y motivo
        else if(!noLaborable(cita.getFecha()).isEmpty()){
            ArrayList<String> periodos = noLaborable(cita.getFecha());
            String s = periodos.toString();
            s = s.replace("[", "").replace("]", "");
            throw new IllegalAgendaStateException  ("La cita no se puede crear porque coincide con: " + s, IllegalAgendaStateException.Motivo.DIA_NO_DISPONIBLE);
        }
        //Si la cita devuelta por el método buscarCitaCliente no es null (es decir, el cliente tiene cita), se instanciará un objecto de la clase IllegalAgendaStateException con mensaje y motivo
        else if(buscarCitaDeCliente(cita.getCliente())!=null){
            throw new IllegalAgendaStateException  ("Dicho cliente ya tiene cita", IllegalAgendaStateException.Motivo.YA_EXISTE_CITA);
        }
        //Si el número de citas activas para el día de la Cita ya super el máximo de citas, se instanciará un objecto de la clase IllegalAgendaStateException con mensaje y motivo
        else if(this.MAX_CITAS_DIA<obtenerCitasFecha(cita.getFecha(),false).size()){
            throw new IllegalAgendaStateException  ("Numero de citas máximas por día superado", IllegalAgendaStateException.Motivo.CITAS_MAXIMAS_SUPERADAS);
        }
        //Para ningún caso anterior, se añadirá la cita la lista de citas
        else{
            this.listaCitas.add(cita);
        }
    }
    
    /**
     * Método para mostrar por pantalla los atributos de la agenda
     * @return Retorna una String que contiene todos los atributos de la instancia de la clase Agenda
     */
    @Override
    public String toString(){
        
        String s;
        s = 
                "AGENDA: " + this.nombreAgenda + "\n" +
                "Citas máximas por día: " + this.MAX_CITAS_DIA + "\n" +
                "Días no hábiles: " + this.diasNoHabiles + "\n" +
                "Rango de fechas no laborables:\n..............................\n";
        
        //Para recorrer un HashMap debemos de haberlo mediante la función HashMap.keySet(), que nos devuelve el conjunto de llaves que conforman el objeto
        for (String llave:this.fechasNoLaborables.keySet()){ 
            String sTemp = llave + " : " + this.fechasNoLaborables.get(llave) + "\n";
            s = s.concat(sTemp);
        }
        return s;
    }
}