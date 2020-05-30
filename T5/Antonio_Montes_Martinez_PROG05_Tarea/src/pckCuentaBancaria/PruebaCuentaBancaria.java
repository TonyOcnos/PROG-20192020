package pckCuentaBancaria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Programa de prueba de la clase <code>CuentaBancaria</code>.
 * <p>
 * Se crean varios objetos de la clase <code>CuentaBancaria</code> y se utilizan
 * probando sus métodos para observar su comportamiento. Cada vez que se trabaja
 * con uno de estos objetos se genera un informe para observar cómo ha afectado
 * al estado del objeto en particular (atributos de objeto) y la clase en
 * general (atributos estáticos)</p>
 *
 * @author profe
 */
public class PruebaCuentaBancaria {

    final static int LONG_MARGEN = 32;
    static String margenEstadoCuenta = relleno(' ', LONG_MARGEN);
    static String justificacionAccion = "%-" + LONG_MARGEN + "s";

    /**
     * Método principal de prueba.
     *
     * @param args Argumentos en consola del programa principal
     */
    public static void main(String[] args) {

        CuentaBancaria[] listaCuentas = {null, null, null, null};
        String[] nombreCuentas = {"C0", "C1", "C2", "C3"};

        //-------------------------------------------------------
        // CABECERA DE LAS PRUEBAS
        //-------------------------------------------------------
        System.out.println();
        System.out.println("+------------------------------------+");
        System.out.println("| PRUEBAS DE LA CLASE CuentaBancaria |");
        System.out.println("+------------------------------------+");
        System.out.println ("Fecha de realización de las pruebas: " + 
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println();
        
        // Informes iniciales
        System.out.print (generaInformeAtributosEstaticos());
        System.out.print(generaInformeGlobalCuentas());

        //-------------------------------------------------------
        // INTENTO DE CREACIÓN DE CUENTAS CON VALORES INVÁLIDOS
        //-------------------------------------------------------
        // Pruebas de creación de cuenta con valores inválidos         
        System.out.println();
        System.out.println("PRUEBAS DE CREACIÓN DE CUENTAS CON VALORES NO VÁLIDOS");
        System.out.println("-----------------------------------------------------");
        // Saldo inicial no válido
        crearCuenta3param(-0.01, LocalDate.of(2019, 9, 1), 0.0);
        crearCuenta3param(50_000_000.01, LocalDate.of(2019, 9, 1), 0.0);
        crearCuenta2param(-0.01, LocalDate.of(2019, 9, 1));
        crearCuenta2param(50_000_000.01, LocalDate.of(2019, 9, 1));
        // Fecha de creación no válida
        crearCuenta3param(0.0, null, 0.0);
        crearCuenta3param(0.0, LocalDate.of(1899, 12, 31), 0.0);
        crearCuenta3param(0.0, LocalDate.of(2030, 9, 1), 0.0);
        crearCuenta2param(0.0, null);
        crearCuenta2param(0.0, LocalDate.of(1899, 12, 31));
        crearCuenta2param(0.0, LocalDate.of(2030, 9, 1));
        // Límite de descubierto no válido
        crearCuenta3param(50_000_00.00, LocalDate.of(2019, 9, 1), -2000.01);
        crearCuenta3param(50_000_00.00, LocalDate.of(2019, 9, 1), +0.01);

        //-------------------------------------------------------
        // PRUEBAS CON LA CUENTA C0
        //-------------------------------------------------------
        System.out.println();
        System.out.println("PRUEBAS CON LA CUENTA C0");
        System.out.println("------------------------");
        listaCuentas[0] = crearCuenta3param(50_000_000, LocalDate.of(1900, 1, 1), -2000.0);

        // Pruebas de ingreso con error
        ingresarEnCuenta(listaCuentas[0], -0.01);
        ingresarEnCuenta(listaCuentas[0], +0.01);

        // Pruebas de extracción con error
        extraerDeCuenta(listaCuentas[0], -0.01);

        // Pruebas de extracción correctas
        extraerDeCuenta(listaCuentas[0], 0.00);
        extraerDeCuenta(listaCuentas[0], 0.01);

        // Pruebas de ingreso con error
        ingresarEnCuenta(listaCuentas[0], 0.02);

        // Pruebas de ingreso correctas
        ingresarEnCuenta(listaCuentas[0], 0.01);

        // Pruebas de extracción correctas
        extraerDeCuenta(listaCuentas[0], 50_000_000.00);
        extraerDeCuenta(listaCuentas[0], 1999.99); // Generamos descubierto

        // Pruebas de extracción con error
        extraerDeCuenta(listaCuentas[0],    0.02);  // Superamos descubierto

        // Pruebas de extracción correctas
        extraerDeCuenta(listaCuentas[0],    0.01);  // Alcanzamos límite descubierto

        // Informe final
        System.out.print(generaInformeCuenta(listaCuentas[0], nombreCuentas[0]));
        System.out.print(generaInformeGlobalCuentas());

        //-------------------------------------------------------
        // PRUEBAS CON LA CUENTA C1
        //-------------------------------------------------------
        System.out.println();
        System.out.println();
        System.out.println("PRUEBAS CON LA CUENTA C1");
        System.out.println("------------------------");
        listaCuentas[1] = crearCuenta2param (5000.00, LocalDate.of(2019, 9, 1));

        // Embargos
        embargarCuenta(listaCuentas[1], -0.01);
        embargarCuenta(listaCuentas[1], 0.00);
        embargarCuenta(listaCuentas[1], 100.01);
        embargarCuenta(listaCuentas[1], 25.00);
        embargarCuenta(listaCuentas[1], 50.00);
        desembargarCuenta(listaCuentas[1]);
        desembargarCuenta(listaCuentas[1]);  // Un segundo embargo debería devolver false
        embargarCuenta(listaCuentas[1], 50.00);

        // Ingresos con la cuenta embargada
        ingresarEnCuenta(listaCuentas[1], 4000.00);
        ingresarEnCuenta(listaCuentas[1], 1000.00);

        // Transferencias
        transferirEntreCuentas2param(listaCuentas[1], null, 0.50);
        transferirEntreCuentas2param(listaCuentas[1], listaCuentas[0],   -0.01);
        transferirEntreCuentas2param(listaCuentas[0], listaCuentas[1],    0.01);
        transferirEntreCuentas2param(listaCuentas[1], listaCuentas[0],    0.00);
        transferirEntreCuentas2param(listaCuentas[1], listaCuentas[0], 7000.00);
        transferirEntreCuentas2param(listaCuentas[1], listaCuentas[0],  500.01);
        transferirEntreCuentas2param(listaCuentas[1], listaCuentas[0],  500.00);

        transferirEntreCuentas2param(listaCuentas[0],  listaCuentas[1], 7500.01);
        transferirEntreCuentas2param(listaCuentas[0],  listaCuentas[1], 7500.00);
        
        
        // Informe final
        System.out.print(generaInformeCuenta(listaCuentas[0], nombreCuentas[0]));
        System.out.print(generaInformeCuenta(listaCuentas[1], nombreCuentas[1]));
        System.out.print(generaInformeGlobalCuentas());

        //-------------------------------------------------------
        // PRUEBAS CON LA CUENTA C2
        //-------------------------------------------------------
        System.out.println();
        System.out.println("PRUEBAS CON LA CUENTA C2");
        System.out.println("------------------------");
        listaCuentas[2] = crearCuenta1param (50_000_000.00);

        // Transferencias
        transferirEntreCuentas1param(listaCuentas[2], null);
        transferirEntreCuentas1param(listaCuentas[2], listaCuentas[0]);

        // Informe final
        System.out.print(generaInformeCuenta(listaCuentas[0], nombreCuentas[0]));
        System.out.print(generaInformeCuenta(listaCuentas[2], nombreCuentas[2]));
        System.out.print(generaInformeGlobalCuentas());

        //-------------------------------------------------------
        // PRUEBAS CON LA CUENTA C3
        //-------------------------------------------------------
        System.out.println();
        System.out.println("PRUEBAS CON LA CUENTA C3");
        System.out.println("------------------------");
        listaCuentas[3] = crearCuenta0param ();

        // Embargos
        embargarCuenta(listaCuentas[3], 100.00);

        // Transferencias con la cuenta embargada
        transferirEntreCuentas1param(listaCuentas[2], listaCuentas[3]);
        transferirEntreCuentas1param(listaCuentas[0], listaCuentas[3]);

        
        // Informe final
        System.out.print(generaInformeCuenta(listaCuentas[0], nombreCuentas[0]));
        System.out.print(generaInformeCuenta(listaCuentas[3], nombreCuentas[3]));
        System.out.print(generaInformeGlobalCuentas());

    }

    // Método para probar la llamada al constructor de Cuentas
    private static CuentaBancaria crearCuenta3param (double saldoInicial, 
            LocalDate fechaCreacion, double limiteDescubierto) {
        CuentaBancaria resultado = null;
        System.out.printf("Creando cuenta con valores: %.2f   %s   %.2f\n",
                saldoInicial,
                fechaCreacion == null ? null : fechaCreacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                limiteDescubierto);
        try {
            resultado = new CuentaBancaria(saldoInicial, fechaCreacion, limiteDescubierto);
            System.out.println("Cuenta creada sin error.");
        } catch (IllegalArgumentException e) {
            System.out.printf("%s.\n",
                    e.getMessage());
        }
        //System.out.println (margenEstadoCuenta + resultado);
        System.out.println("Cuenta creada: " + resultado);
        return resultado;
    }

    private static CuentaBancaria crearCuenta2param (double saldoInicial, 
            LocalDate fechaCreacion) {
        CuentaBancaria resultado = null;
        System.out.printf("Creando cuenta con valores: %.2f   %s\n",
                saldoInicial, fechaCreacion == null ? null : fechaCreacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        try {
            resultado = new CuentaBancaria(saldoInicial, fechaCreacion);
            System.out.println("Cuenta creada sin error.");
        } catch (IllegalArgumentException e) {
            System.out.printf("%s.\n",
                    e.getMessage());
        }
        //System.out.println (margenEstadoCuenta + resultado);
        System.out.println("Cuenta creada: " + resultado);
        return resultado;
    }
    
    private static CuentaBancaria crearCuenta1param(double saldoInicial) {
        CuentaBancaria resultado = null;
        System.out.printf("Creando cuenta con valores: %.2f\n",
                saldoInicial);
        try {
            resultado = new CuentaBancaria(saldoInicial);
            System.out.println("Cuenta creada sin error.");
        } catch (IllegalArgumentException e) {
            System.out.printf("%s.\n",
                    e.getMessage());
        }
        //System.out.println (margenEstadoCuenta + resultado);
        System.out.println("Cuenta creada: " + resultado);
        return resultado;
    }

    private static CuentaBancaria crearCuenta0param() {
        CuentaBancaria resultado = null;
        System.out.printf("Creando cuenta sin parámetros\n");
        try {
            resultado = new CuentaBancaria();
            System.out.println("Cuenta creada sin error.");
        } catch (IllegalArgumentException e) {
            System.out.printf("%s.\n",
                    e.getMessage());
        }
        //System.out.println (margenEstadoCuenta + resultado);
        System.out.println("Cuenta creada: " + resultado);
        return resultado;
    }
    
    // Método para probar la llamada al método de ingreso en una cuenta
    private static void ingresarEnCuenta(CuentaBancaria cuenta, double cantidad) {
        System.out.print(String.format(justificacionAccion, "Ingresar " + 
                String.format("%.2f euros: ", cantidad)));
        try {
            cuenta.ingresar(cantidad);
            System.out.println("Cantidad ingresada sin error.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(margenEstadoCuenta + cuenta.toString());
        }
    }


    // Método para probar la llamada al método de extracción de una cuenta
    private static void extraerDeCuenta(CuentaBancaria cuenta, double cantidad) {
        System.out.print(String.format(justificacionAccion, "Extraer " + 
                String.format("%.2f euros: ", cantidad)));
        try {
            cuenta.extraer(cantidad);
            System.out.println("Cantidad extraída sin error.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(margenEstadoCuenta + cuenta.toString());
        }
    }

    // Método para probar la llamada al método de transferencia entre cuentas
    private static void transferirEntreCuentas2param(CuentaBancaria origen, CuentaBancaria destino, double cantidad) {
        System.out.print(String.format(justificacionAccion, "Transferir " +
                String.format("%.2f de C%d a %s: ", cantidad,
                origen.getId(),
                destino != null ? "C" + destino.getId() : null)));
        try {
            origen.transferir(cantidad, destino);
            System.out.println("Transferencia efectuada sin error.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(margenEstadoCuenta + origen.toString());
            if (destino!=null) System.out.println(margenEstadoCuenta + destino.toString());
        }
    }

    // Método para probar la llamada al método de transferencia entre cuentas
    private static void transferirEntreCuentas1param(CuentaBancaria origen, CuentaBancaria destino) {
        System.out.print(String.format(justificacionAccion, "Transferir todo " +
                String.format("de C%d a %s: ",
                origen.getId(), 
                destino != null ? "C" + destino.getId() : null)));
        try {
            origen.transferir(destino);
            System.out.println("Transferencia efectuada sin error.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(margenEstadoCuenta + origen.toString());
            if (destino!=null) System.out.println(margenEstadoCuenta + destino.toString());
        }    
    }
    
    // Método para probar la llamada al método de embargo de una cuenta
    private static void embargarCuenta(CuentaBancaria cuenta, double porcentaje) {
        System.out.print(String.format(justificacionAccion, "Embargar " + 
                String.format("%.2f%%: ", porcentaje)));
        try {
            cuenta.embargar(porcentaje);
            System.out.println("Embargo aplicado sin error.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(margenEstadoCuenta + cuenta.toString());
        }
    }

    // Método para probar la llamada al método de desembargo de una cuenta
    private static void desembargarCuenta(CuentaBancaria cuenta) {
        System.out.print(String.format(justificacionAccion, "Desembargar "));
        if (cuenta.desembargar()) {
            System.out.println("Cuenta desembargada sin error.");
        } else {
            System.out.println("La cuenta no estaba embargada.");
        }
    }



    // Método para generar un informe sobre todo el conjunto de cuentas
    private static String generaInformeGlobalCuentas() {
        StringBuilder informe = new StringBuilder();
        String cabecera = "INFORME GLOBAL DE TODAS LAS CUENTAS";

        informe.append(cabecera).append("\n");
        informe.append(relleno('-', cabecera.length()));
        informe.append("\nSaldo total entre todas las cuentas en este instante: ");
        informe.append(String.format("%5.2f", CuentaBancaria.getSaldoGlobal()));
        informe.append("\nNúmero de cuentas embargadas en este instante: ");
        informe.append(CuentaBancaria.getNumCuentasEmbargadas());
        informe.append("\nFecha de creación de la cuenta más moderna: ");
        informe.append(CuentaBancaria.getFechaMasModerna() != null ?
                CuentaBancaria.getFechaMasModerna().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : 
                CuentaBancaria.getFechaMasModerna());
        informe.append("\n\n");

        return informe.toString();
    }

    // Método para generar un informe sobre una cuenta
    private static String generaInformeCuenta(CuentaBancaria cuenta, String nombreCuenta) {
        StringBuilder informe = new StringBuilder();
        String cabecera = "INFORME DE LA CUENTA " + nombreCuenta;

        informe.append("\n").append(cabecera).append("\n");
        informe.append(relleno('-', cabecera.length()));
        if (cuenta != null) {
            informe.append("\n");
            informe.append(informeCuenta(cuenta));
        } else {
            informe.append("\nLa cuenta ").append(nombreCuenta).append(" aún no existe.");
        }
        informe.append("\n\n");
        return informe.toString();
    }

    private static String informeCuenta(CuentaBancaria cuenta) {
        StringBuilder informe = new StringBuilder();

        informe.append("Id: ");
        informe.append(String.format("%d\n", cuenta.getId()));
        informe.append("Saldo: ");
        informe.append(String.format("%.2f euros\n", cuenta.getSaldo()));
        informe.append("Fecha de creación: ");
        informe.append(String.format("%s\n", 
                cuenta.getFechaCreacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        informe.append("Antigüedad: ");
        informe.append(String.format("%d días\n", cuenta.getDiasCuenta()));
        informe.append("Cuenta embargada: ");
        informe.append(String.format("%s\n", cuenta.isEmbargada() ? "sí" : "no"));
        informe.append("Porcentaje de embargo: ");
        informe.append(String.format("%.2f%%\n", cuenta.getPorcentajeEmbargo()) );
        informe.append("Límite de descubierto: ");
        informe.append(String.format("%.2f\n", cuenta.getLimiteDescubierto()));
        informe.append("Cuenta en descubierto: ");
        informe.append(String.format("%s\n", cuenta.isDescubierta() ? "sí" : "no"));
        informe.append("Ingresos acumulados: ");
        informe.append(String.format("%.2f euros\n", cuenta.getTotalIngresado()));
        informe.append("Saldo máximo histórico: ");
        informe.append(String.format("%.2f euros\n", cuenta.getSaldoMaximo()));

        return informe.toString();
    }

    // Método para generar una cadena rellena con un caracter
    private static String relleno(char caracter, int longitud) {
        StringBuilder resultado = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            resultado.append(caracter);
        }
        return resultado.toString();
    }

    // Método para probar los atributos estáticos públicos de la clase
    private static String generaInformeAtributosEstaticos () {
        StringBuilder informe = new StringBuilder();
        
        String cabecera = "ATRIBUTOS ESTÁTICOS PÚBLICOS DE LA CLASE";
        informe.append(cabecera).append("\n");
        informe.append(relleno('-', cabecera.length()));
        
        informe.append("\nMAX_DESCUBIERTO: ");
        informe.append(String.format("%.2f\n", CuentaBancaria.MAX_DESCUBIERTO));
        informe.append("DEFAULT_MAX_DESCUBIERTO: ");
        informe.append(String.format("%.2f\n", CuentaBancaria.DEFAULT_MAX_DESCUBIERTO));
        informe.append("MAX_SALDO: ");
        informe.append(String.format("%.2f\n", CuentaBancaria.MAX_SALDO));
        informe.append("MIN_EMBARGO: ");
        informe.append(String.format("%.2f\n", CuentaBancaria.MIN_EMBARGO));
        informe.append("MIN_EMBARGO: ");
        informe.append(String.format("%.2f\n", CuentaBancaria.MAX_EMBARGO));
        informe.append("MIN_YEAR: ");
        informe.append(String.format("%d\n", CuentaBancaria.MIN_YEAR));
        informe.append("DEFAULT_SALDO: ");
        informe.append(String.format("%.2f\n\n", CuentaBancaria.DEFAULT_SALDO));
        
        return informe.toString();
    }
    
}