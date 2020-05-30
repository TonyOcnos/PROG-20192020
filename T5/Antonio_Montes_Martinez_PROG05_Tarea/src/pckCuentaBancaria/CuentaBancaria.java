package pckCuentaBancaria;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * <p>Clase que representa una <b>cuenta bancaria.</b><br>
 * <p>Cada objeto creado tendrá los siguientes atributos:
 * <ul>
 * <li><b>ID</b> de cada cuenta creada. Es un valor constante que no podrá ser cambiado
 * <li><b>Fecha de creación</b> de cada cuenta creada. Es un valor constante que no podrá ser cambiado
 * <li><b>Porcentaje de embargo</b> de la cuenta
 * <li><b>Saldo actual</b> de la cuenta
 * <li><b>Saldo máximo</b> alcanzado durante toda la vida de la cuenta
 * <li><b>Ingresos totales</b> que ha tenido la cuenta durante toda su vida
 * </ul>
 * <p>La clase, a nivel general, también dispone de atributos que podrán ser consultados en cualquier momento:
 * <ul>
 * <li><b>Saldo global</b> entre todas las cuentas en el momento de la consulta
 * <li><b>Número de cuentas embargadas</b> total en el momento de la consulta
 * <li><b>Fecha de creación</b> más moderna, que no la fecha en el momento en que se creó la última cuenta
 * </ul>
 * <p>Se permitirán diferentes métodos como: ingresar, extraer dinero, transferir entre cuentas, embargar...
 * <p>Se tomará en cuenta el valor de embargo para los cálculos así como el máximo de descubierto (cuenta en negativo)
 * @author Antonio Manuel Montes Martínez
 * @version 1.0
 * 
 */
public class CuentaBancaria {
    /*Variable para formatear algunas salidas del saldo actual con dos decimales*/
    DecimalFormat df = new DecimalFormat("0.00");
    /**Establece el máximo permitido como valor negativo en la cuenta*/
    public final static double MAX_DESCUBIERTO = -2000.00;
    /**Establece el máximo saldo posible en una cuenta*/
    public final static double MAX_SALDO = 50000000.00;
    /**Establece un año mínimo como referencia para la fecha más moderna*/
    public final static int MIN_YEAR = 1900;
    /**Establece en máximo de embargo permitido por defecto*/
    public final static double MAX_EMBARGO = 100.0;
    /**Establece el mínimo de embargo permitido por defecto*/
    public final static double MIN_EMBARGO = 0.0;
    /**Establece en saldo por defecto en caso de constructor sin parámetros*/
    public final static double DEFAULT_SALDO = 0.00;
    /**Establece el máximo de descubierto para constructores que no admitan dicho parámetro*/
    public final static double DEFAULT_MAX_DESCUBIERTO = 0.00;
    
    private static int embargosTotales = 0;
    private static LocalDate fechaMaxReciente = LocalDate.parse("1900-01-01");
    private static double saldoTotalCuentas;
    private static int numCuentas;
    
    private final int ID_CUENTA;
    private final LocalDate FECHA_CREACION;
    private double limDescubierto;
    
    private double saldoCuenta = 0;
    private double porcEmbargo;
    private double ingresosTotales = 0;
    private double saldoMax;
    
    /**
     * Constructor sin parámetros. Se usarán valores por defecto
     */
    public CuentaBancaria(){
        this(DEFAULT_SALDO, LocalDate.now(), DEFAULT_MAX_DESCUBIERTO);
    }
    /**
     * Constructor de 1 parámetro
     * @param saldoCuenta Saldo que queremos introducir en la cuenta de manera inicial
     */
    public CuentaBancaria(double saldoCuenta){
        this(saldoCuenta, LocalDate.now(), DEFAULT_MAX_DESCUBIERTO);
    }
    /**
     * Constructor de 2 parámetros
     * @param saldoCuenta Saldo que queremos introducir en la cuenta de manera inicial
     * @param fechaCreacion Fecha que queremos que aparezca reflejada como fecha de creación
     */
    public CuentaBancaria(double saldoCuenta, LocalDate fechaCreacion){
        this(saldoCuenta, fechaCreacion, DEFAULT_MAX_DESCUBIERTO);
    }
    /**
     * Constructor de 3 parámetros
     * @param saldoCuenta Saldo que queremos introducir en la cuenta de manera inicial
     * @param fechaCreacion Fecha que queremos que aparezca reflejada como fecha de creación
     * @param limDescubierto Valor límite para poder tener la cuenta en negativo
     * @throws IllegalArgumentException Excepción en caso de que la inicialización del objeto tipo CuentaBancaria falle a través de uno de sus parámetros
     */
    public CuentaBancaria(double saldoCuenta, LocalDate fechaCreacion, double limDescubierto) throws IllegalArgumentException {
        /*If en caso de que el saldo inicial o bien sea menor a 0 (se admite 0) o bien supere el saldo máximo estipulado por la variable global MAX_SALDO*/
        if(saldoCuenta<0 || saldoCuenta>MAX_SALDO){
            throw new IllegalArgumentException ("ERROR: Parámetros de creación de la cuenta inválidos. Saldo inicial: " + df.format(saldoCuenta) + "€");
        }
        /*If en caso de que la fecha de creación sea null, supere la fecha actual de ejecución o su año sea anterior a 1900*/
        else if(fechaCreacion == null || fechaCreacion.isAfter(LocalDate.now()) || fechaCreacion.getYear()<1900){
            throw new IllegalArgumentException ("ERROR: Parámetros de creación de la cuenta inválidos. Fecha de creación: " + fechaCreacion);
        }
        /*If en caso de que el límite estipulado supere al máximo establecido por la variable global MAX_DESCUBIERTO o bien sea inferior a 0*/
        else if(limDescubierto<MAX_DESCUBIERTO || limDescubierto>0){
            throw new IllegalArgumentException ("ERROR: Parámetros de creación de la cuenta inválidos. Máximo descubierto: " + limDescubierto);
        }
        else {
            /*En caso de que la inicialización sea correcta, se pasará a ir incrementando el número ID asociado a cada cuenta*/
            this.ID_CUENTA = numCuentas++;
            /*Se asignan los atributos inicializados*/
            this.FECHA_CREACION = fechaCreacion;
            this.limDescubierto = limDescubierto;
            this.saldoCuenta += saldoCuenta;
            /*Se va actualizando el cúmulo total de saldo de todas las cuentas*/
            CuentaBancaria.saldoTotalCuentas += this.saldoCuenta;
            /*Se establece el saldo inicial como saldo máximo alcanzado*/
            this.saldoMax = this.saldoCuenta;
            /*Se establece el saldo inicial como ingreso en el seguimiento y acumulación de todos ellos*/
            this.ingresosTotales = this.saldoCuenta;
            /*En caso de que la fecha creada sea superior a la última fecha de creación, la variable global fechaMaxRecien+te se irá actualizando*/
            if(fechaCreacion.isAfter(fechaMaxReciente)){
                CuentaBancaria.fechaMaxReciente = fechaCreacion;
            }
        }
    }
    /**
     * Obtiene el ID de la cuenta actual
     * @return ID de la cuenta consultada
     */
    public int getId() {
        return this.ID_CUENTA;
    }
    /**
     * Obtiene el saldo de la cuenta actual
     * @return saldo de la cuenta consultada
     */
    public double getSaldo(){
        return this.saldoCuenta;
    }
    /**
     * Obtiene la fecha de creación de la cuenta actual
     * @return fecha de creación de la cuenta consultada
     */
    public LocalDate getFechaCreacion(){
        return this.FECHA_CREACION;
    }
    /**
     * Obtiene el límite de descubierto de la cuenta actual
     * @return límite de descubierto de la cuenta consultada
     */
    public double getLimiteDescubierto(){
        return this.limDescubierto;
    }
    /**
     * Obtiene el porcentaje de embargo de la cuenta actual
     * @return porcentaje de embargo de la cuenta consultada
     */
    public double getPorcentajeEmbargo(){
        return this.porcEmbargo;
    }
    /**
     * Comprueba si la cuenta está o no al descubierto (negativo)
     * @return true en caso de estar descubierto y false en caso de no estarlo
     */
    public boolean isDescubierta(){
        return this.saldoCuenta<0;
    }
    /**
     * Comprueba si la cuenta está o no embargada
     * @return true en caso de estar embargada y false en caso de no estarlo
     */
    public boolean isEmbargada(){
        return this.porcEmbargo>0;
    }
    /**
     * Obtiene el número de días que tiene la cuenta desde su creación a la fecha actual de consulta
     * @return número de días desde que se creo la cuenta
     */
    public long getDiasCuenta(){
        return ChronoUnit.DAYS.between(FECHA_CREACION, LocalDate.now());
    }
    /**
     * Obtiene el saldo máximo que ha llegado a tener la cuenta contando con ingresos-extracciones
     * @return el saldo máximo registrado en la cuenta
     */
    public double getSaldoMaximo(){
        return this.saldoMax;
    }
    /**
     * Obtiene el total de ingresos registrados en la cuenta
     * @return cantidad total del dinero ingresado en cuenta
     */
    public double getTotalIngresado(){
        return this.ingresosTotales;
    }
    /**
     * Obtiene el saldo disponible total de todas las cuentas creadas
     * @return saldo total de todas las cuentas en el momento de la consulta
     */
    public static double getSaldoGlobal(){
        return CuentaBancaria.saldoTotalCuentas;
    }
    /**
     * Obtiene la fecha de la cuenta más moderla creada (que no la fecha de la última cuenta que se creó)
     * @return fecha más moderna de creación
     */
    public static LocalDate getFechaMasModerna(){
        return CuentaBancaria.fechaMaxReciente;
    }
    /**
     * Obtiene el número total de cuentas embargadas
     * @return número total de cuentas embargadas en el momento de la consulta
     */
    public static int getNumCuentasEmbargadas(){
        return CuentaBancaria.embargosTotales;
    }
    /**
     * <p>Devuelve una cadena que representa el estado actual de la cuenta. Esa cadena proporcionará la siguiente información:
     * <ul>
     * <li><b>Identificador</b> de la cuenta.
     * <li><b>Saldo actual</b> de la cuenta.
     * <li>Si la cuenta <b>está o no embargada.</b>
     * </ul>
     * <p>El formato de salida será del siguiente tipo:
     * <p>Id: XXX - Saldo: YYYYYYYY.YY - Embargada: sí|no [ZZZ.Z%]
     * <p>Id: 0 - Saldo: 50000000,00 - Embargada: no
     * <p>Id: 0 - Saldo:        0,00 - Embargada: no
     * <p>Id: 0 - Saldo:    -2000,00 - Embargada: no
     * <p>Id: 1 - Saldo:     5000,00 - Embargada: sí  25,0%
     * <p>Id: 1 - Saldo:     3750,00 - Embargada: sí  50,0%
     * <p>Id: 3 - Saldo:        0,00 - Embargada: sí 100,0%
     * @return cadena con formato específico para la muestra de todos los datos en cuenta
     */
    @Override
    public String toString(){
        StringBuilder cadenaFinal = new StringBuilder("");
        
        cadenaFinal = cadenaFinal.append("ID: ")
                .append(this.ID_CUENTA)
                .append(" - Saldo: ")
                .append(df.format(this.saldoCuenta))
                .append(" - Embargada: ");
        
        if (isEmbargada()){
            cadenaFinal = cadenaFinal.append("SÍ ")
                    .append(this.porcEmbargo)
                    .append("%");
        }
        else {cadenaFinal = cadenaFinal.append("NO");}
        
        return cadenaFinal.toString();
    }
    /**
     * Método para embargar una cuenta.
     * @param porciento Valor por el que embargamos la cuenta
     * @throws IllegalArgumentException en caso de que la cuenta ya esté embargada
     * @throws IllegalStateException en caso de un porcentaje de error no válido
     */
    public void embargar(double porciento) throws IllegalArgumentException , IllegalStateException {
        if (isEmbargada()) {
            throw new IllegalArgumentException("ERROR: La cuenta ya está embargada");
        }
        if (porciento<=0 || porciento>100){
            throw new IllegalStateException("ERROR: Porcentaje de embargo no válido: " + porciento + "%");
        }
        else {
            /*En caso de poder embargar, se asignará a la variable correspondiente el valor de parámetro del método*/
            this.porcEmbargo = porciento;
            /*Se aumentará en 1 el valor de cuentas embargadas*/
            embargosTotales++;
        }
    }
    /**
     * Método para desembargar una cuenta.
     * @return booleano de control para el programa de ejucición de prueba. True si estaba embargada y por tanto se ha desembargado. En caso contrario, retorna false
     */
    public boolean desembargar(){
        if (isEmbargada()) {
            /*En caso de estar embargada, ponemos el porcentaje de embargo a 0*/
            this.porcEmbargo = 0;
            /*También descontamos 1 en el contador de embargos totales*/
            embargosTotales--;
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Método para ingresar dinero en cuenta
     * @param cantidad Valor que queremos ingresar en cuenta
     * @throws IllegalArgumentException en caso de que la cantidad sea negativa
     * @throws IllegalStateException en caso de que la cantidad que queremos ingresar mas el saldo disponible contando con el embargo supere al máximo establecido por
     * la variable global MAX_SALDO
     */
    public void ingresar(double cantidad) throws IllegalArgumentException, IllegalStateException{
        
        if(cantidad<0){
            throw new IllegalArgumentException("Error: cantidad de ingreso no válida: " + cantidad + "€");
        }
        else if(cantidad+(this.saldoCuenta*(1-(this.porcEmbargo/100)))>MAX_SALDO){
            throw new IllegalStateException("Error: saldo máximo superado con este ingreso: " + cantidad + "€");
        }
        else {
            /*En caso de que pueda ingresarse, sumamos al saldo disponible la cantidad del parámetro contando con el embargo actual (de existir)*/
            this.saldoCuenta += cantidad*(1-(this.porcEmbargo/100));
            /*Se actulizará la variable global del saldo disponible total de todas las cuentas*/
            CuentaBancaria.saldoTotalCuentas += cantidad*(1-(this.porcEmbargo/100));
            /*Se sumará también la cantidad con o sin embargo al seguimiento de ingresos totales en cuenta*/
            this.ingresosTotales += cantidad*(1-(this.porcEmbargo/100));
            /*Si en los momentos del ingreso, éste supera el saldoMax anterior, se solapa dicha cantidad en la variable saldoMax*/
            if (this.saldoCuenta > this.saldoMax){
                this.saldoMax = this.saldoCuenta;
            }
        }
    }
    /**
     * Método para extraer dinero de una cuenta
     * @param cantidad Valor que queremos extraer de cuenta
     * @throws IllegalArgumentException en caso de que la cantidad sea negativa
     * @throws IllegalStateException en caso de que con la extracción superemos el límite inferior estipulado por la variable limDescubierto de la instancia que
     * llama al método
     */
    public void extraer(double cantidad) throws IllegalArgumentException, IllegalStateException{

        if (cantidad < 0){
            throw new IllegalArgumentException ("ERROR: Cantidad de extracción no válida: " + cantidad + "€");
        }

        else if (this.saldoCuenta - cantidad<this.limDescubierto) {
            throw new IllegalStateException ("ERROR: Descubierto máximo superado con esta extracción: " + df.format(cantidad) + "€");
        }
        else {
            /*En caso de que pueda extraerse, se descontará la cantidad del saldo actual*/
            this.saldoCuenta -= cantidad;
            /*Se restará la cantidad de la variable global del saldo disponible total de todas las cuentas*/
            CuentaBancaria.saldoTotalCuentas -= cantidad;
        }
    }
    /**
     * Método para trasferir dinero de una cuenta a otra con dos parámetros
     * @param cantidad Valor que queremos transferir de una cuenta a una cuenta de destino
     * @param cbDestino Identificador de la cuenta de destino
     * @throws IllegalArgumentException en caso de que la cuenta de destino no exista o la cantidad sea negativa
     * @throws IllegalStateException en caso de que al extraer el dinero de la cuenta de origen estemos por debajo de límite a descubierto o bien,
     * en caso de que en la cuenta de destino superemos el valor máximo de la variable global constante MAX_SALDO
     */
    public void transferir (double cantidad, CuentaBancaria cbDestino) throws IllegalArgumentException, IllegalStateException{

        if (cbDestino == null){
            throw new IllegalArgumentException ("ERROR: cuenta de destino no válida");
        }
        else if (cantidad < 0){
            throw new IllegalArgumentException("ERROR: cantidad a transferir no válida: " + cantidad + "€");
        }
        else if (this.saldoCuenta-cantidad<this.limDescubierto){
            throw new IllegalStateException("ERROR: cantidad no disponible en cuenta origen: " + cantidad + "€");
        }
        else if ((cbDestino.saldoCuenta*(cbDestino.porcEmbargo/100)) + cantidad>MAX_SALDO){
            throw new IllegalStateException("ERROR: saldo máximo de cuenta destino superado con esta transferencia: " + cantidad + "€");
        }
        else {
            /*Se realizarán las operaciones permitentes llamando a los métodos de objeto ingresar() y extraer()*/
            cbDestino.ingresar(cantidad);
            this.extraer(cantidad);
        }
    }
    /**
     * Método para transferir dinero de una cuenta a otra de un sólo parámetro
     * @param cbDestino Identificador de la cuenta de destino
     */
    public void transferir(CuentaBancaria cbDestino){
        if (cbDestino == null){
                throw new IllegalArgumentException ("ERROR: cuenta de destino no válida");
        }
        else {
            /*En caso de poderse realizar dicho traspaso, pasaremos a la cuenta de destino todo el dinero disponible en la cuenta de origen. Llamaremos al método de objeto ingresar()*/
            cbDestino.ingresar(this.saldoCuenta);
            /*Y se extraerá de la cuenta de origen todo el dinero disponible. Llamaremos al método de objeto extraer()*/
            this.extraer(this.saldoCuenta);
        }
    }
}