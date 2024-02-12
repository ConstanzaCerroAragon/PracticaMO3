/**
 * Proyecto: Practica Taller de reparacion de vehiculos
 * Integrantes del grupo:
 * -Constanza_Sofia_Cerro_Aragon
 * -Satenik_Avetisyan
 * Repositorio del proyecto: https://github.com/ConstanzaCerroAragon/PracticaMO3.git
 */

import java.util.Scanner;

public class Main {

            public static Scanner input = new Scanner(System.in);
            public static String[][] clientes = new String[100][2];
            public static String[][] mecanicos = new String[100][3];

            public static Scanner Scanner = new Scanner(System.in);
            public static String[][] vehicles = new String[100][3]; // Array para almacenar los datos de los vehículos
            public static String[][] reparaciones = new String[100][3]; // Array para almacenar los datos de las reparaciones

            public static void main(String[] args) {

                Main main = new Main();
                main.init();
            }


            public void init() {

                int menuItem = 0;
                do {

                    mostrarMenu();


                    if (input.hasNextInt()) {
                        menuItem = input.nextInt();
                        input.nextLine();
                        String dni;
                        switch (menuItem) {

                            case 1:
                                dni = pedirDNIAUsuario();
                                String nuevoNombre;
                                if (dni != null) {
                                    nuevoNombre = pedirNombreAUsuario(input);
                                    agregarCliente(clientes, dni, nuevoNombre);
                                    mostrarClientes(clientes);
                                }
                                break;

                            case 2:
                                altaMecanico(input, mecanicos);
                                break;

                            case 3:
                                nuevoVehiculo(input, vehicles,clientes);
                                break;

                            case 4:
                                Reparaciones(input,reparaciones,vehicles);
                                break;

                            case 5:
                                modificar(input,reparaciones,clientes);
                                break;

                            case 6:
                                System.out.println("Saliedo...");
                                break;
                            default:
                                System.out.println("Opción no vàlida");
                        }
                    } else {
                        System.out.println("Opción no vàlida");
                    }
                    Scanner.nextLine();
                    System.out.println("");

                } while (menuItem != 6);
            }



    //METODO PARA MENU
    /**
     * Muestra las ocpiones del menu
     */
    public void mostrarMenu() {
        System.out.println("TALLER DE REPARACIONDE VEHICULOS");
        System.out.println("[1] Dar de alta al cliente");
        System.out.println("[2] Dar de alta a nuevo mecánico");
        System.out.println("[3] Introducir nuevo Vehículo");
        System.out.println("[4] Crear fichas de nueva reparacion");
        System.out.println("[5] Modificar reparación");
        System.out.println("[6] Salir");
        System.out.println("Selecciona una opción: ");
    }


    //CASE 1: DAR DE ALTA NUEVO EMPLEADO (CONSTANZA)
    /**
     * Validar el formato del DNI
     *
     * @param dni Numero ingresado
     *
     * @return DNI valido o invalido
     */
    public static boolean validarFormatoDNI(String dni) {
        return dni.matches("[0-9]{8}[a-zA-Z]");
    }

    /**
     * Pide el DNI al usuario
     *
     * @return Capta el DNI ingresado
     */
    public static String pedirDNIAUsuario() {
        String dni;
        do {
            System.out.println("Ingrese el DNI del cliente (8 dígitos seguidos de 1 letra):");
            dni = input.nextLine();
            if (!validarFormatoDNI(dni)) {
                System.out.println("El formato del DNI no es válido. Debe ser de 8 dígitos seguidos de 1 letra.");
            }
        }while (!validarFormatoDNI(dni));
            return dni;
    }

    /**
     * Pide el nombre al usuario
     *
     * @param nombre Capta el nombre ingresado
     *
     * @return Capta el Nombre del usuario ingresado
     */
    public static String pedirNombreAUsuario(Scanner nombre) {
        System.out.println("Ingrese el nombre del nuevo cliente:");
        return nombre.nextLine();
    }

    /**
     * Completa el array con los datos del usuario: nombre y DNI
     *
     * @param clientes Matriz de clientes: nombres y Dni
     * @param dni Numero de dni captado
     * @param nombre Numero de nombre captado
     */
    public static void agregarCliente(String[][] clientes, String dni, String nombre) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i][0] == null && clientes[i][1] == null) {
                clientes[i][0] = dni;
                clientes[i][1] = nombre;
                break;
            }
        }
    }

    /**
     * Recorre la Matriz de Clientes, y los muestra
     *
     * @param clientes Matriz con todos los datos del cliente
     */
    public static void mostrarClientes(String[][] clientes) {
        System.out.println("Lista de clientes:");
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i][0] != null && clientes[i][1] != null) {
                System.out.println("Cliente " + (i + 1) + ": DNI = " + clientes[i][0] + ", Nombre = " + clientes[i][1] + "\n\nPresione enter para volver al menu, y elegir su nueva opcion!");
            } else {
                break; // Detener el for si hay una posición vacía
            }
        }
    }



    //CASE 2: DAR DE ALTA NUEVO MECANICO (CONSTANZA)
    /**
     * Metodo general que engloba cada metodo
     *
     * @param mecanico Objeto que guarda de cada metodo para generar la matriz
     * @param mecanicos matriz donde se guardan los datos
     */
    public static void altaMecanico(Scanner mecanico, String[][] mecanicos) {

        String codigoEmpleado = pedirCodigoEmpleado(mecanico, mecanicos);
        String nombreMecanico = pedirNombreMecanico(mecanico);
        String estadoMecanico = pedirEstadoMecanico(mecanico);
        guardarMecanico(mecanicos, codigoEmpleado, nombreMecanico, estadoMecanico);
    }

    /**
     * Solicita y valida el codigo del empleado para sean 6 digitos y no se repita
     *
     * @param codigo numeros identificadores
     * @param mecanicos matriz donde se guardan los datos
     *
     * @return numero identificador, guardado en la matriz
     */
    public static String pedirCodigoEmpleado(Scanner codigo, String[][] mecanicos) {
        String codigoEmpleado;
        do {
            System.out.println("Ingrese el número de empleado (6 dígitos):");
            codigoEmpleado = codigo.nextLine();

            // Validar que el formato del código de empleado sea correcto (6 dígitos)
            if (!validarFormatoCodigoEmpleado(codigoEmpleado)) {
                System.out.println("Formato de código de empleado incorrecto. Debe tener 6 dígitos.");
            }
            // Validar que el código de empleado no exista previamente
            else if (codigoEmpleadoExistente(mecanicos, codigoEmpleado)) {
                System.out.println("El código de empleado ya existe. Introduzca otro código de empleado.");
            }
        } while (!validarFormatoCodigoEmpleado(codigoEmpleado) || codigoEmpleadoExistente(mecanicos, codigoEmpleado)); // Repetir hasta que el formato y el código de empleado sean correctos
        return codigoEmpleado;
    }

    /**
     * Validar el formato del código de empleado (6 dígitos)
     *
     * @param codigoEmpleado
     *
     * @return codigo verificado
     */
    public static boolean validarFormatoCodigoEmpleado(String codigoEmpleado) {
        return codigoEmpleado.matches("[0-9]{6}");
    }

    /**
     * Validar el si el codigo del mecanico ya existe
     *
     * @param mecanicos Matriz de datos
     * @param codigoEmpleado
     *
     * @return Codigo no existente
     */
    public static boolean codigoEmpleadoExistente(String[][] mecanicos, String codigoEmpleado) {
        if (mecanicos != null) { // Verificar si mecanicos no es null
            for (String[] mecanico : mecanicos) {
                if (mecanico != null && mecanico[0] != null && mecanico[0].equals(codigoEmpleado)) { // Verificar si mecanico y mecanico[0] no son null
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Capta el nombre de mecanico
     *
     * @param nombre
     *
     * @return el nombre del mecanico
     */
    public static String pedirNombreMecanico(Scanner nombre) {
        System.out.println("Ingrese el nombre del mecánico:");
        return nombre.nextLine();
    }

    /**
     * Estado del mecanico
     *
     * @param estado
     *
     * @return si el mecanico esta ocupado o libre
     */
    public static String pedirEstadoMecanico(Scanner estado) {
        String estadoMecanico;
        do {
            System.out.println("Ingrese el estado del mecánico (ocupado o libre):");
            estadoMecanico = input.nextLine();
            if (!estadoMecanico.equalsIgnoreCase("ocupado") && !estadoMecanico.equalsIgnoreCase("libre")) {
                System.out.println("Estado del mecánico no válido. Debe ser 'ocupado' o 'libre'.");
            }
        }while (!estadoMecanico.equalsIgnoreCase("ocupado") && !estadoMecanico.equalsIgnoreCase("libre"));
        return estadoMecanico;
    }

    /**
     * Muestra la matriz con todos datos, a travez de un for
     *
     * @param mecanicos Matriz recorrida
     * @param codigoEmpleado
     * @param nombreMecanico
     * @param estadoMecanico
     */
    public static void guardarMecanico(String[][] mecanicos, String codigoEmpleado, String nombreMecanico, String estadoMecanico) {
        for (int i = 0; i < mecanicos.length; i++) {
            if (mecanicos[i][0] == null) {
                mecanicos[i][0] = codigoEmpleado;
                mecanicos[i][1] = nombreMecanico;
                mecanicos[i][2] = estadoMecanico;
                System.out.println("Nuevo mecánico dado de alta exitosamente." + "\n\nPresione enter para volver al menu, y elegir su nueva opcion!");
                break;
            }
        }
    }



    // CASE 3: INTRODUCIR NUEVO VEHICULO (SATENIK)
    public static void nuevoVehiculo(Scanner input, String[][] vehicles,String[][] clientes) {

        agregarNuevoVehiculo(input, clientes, vehicles);
    }
    public static void agregarNuevoVehiculo(Scanner scanner, String[][] clientes, String[][] vehicles) {
        System.out.println("Introduce los datos del nuevo vehículo:");
        String matricula = obtenerMatricula2(scanner, vehicles);
        String modelo = obtenerModelo(scanner);
        String dniPropietario = obtenerDniPropietario(scanner, clientes);

        // Añadir el vehículo al array de vehículos
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i][0] == null) {
                vehicles[i][0] = matricula;
                vehicles[i][1] = modelo;
                vehicles[i][2] = dniPropietario;
                System.out.println("Vehículo dado de alta correctamente.");
                break;
            }
        }
    }

    public static String obtenerMatricula(Scanner scanner, String[][] vehicles) {
        String matricula;
        do {
            System.out.print("Introduce la matrícula del vehículo (formato: 4 dígitos seguidos de 3 letras): ");
            matricula = scanner.nextLine();

            // Validar el formato de la matrícula utilizando una expresión regular
            if (!matricula.matches("\\d{4}[a-zA-Z]{3}")) {
                System.out.println("Error: El formato de la matrícula es incorrecto.");
            } else {
                // Comprobar si la matrícula ya existe
                boolean matriculaExistente = false;
                for (int i = 0; i < vehicles.length; i++) {
                    if (vehicles[i][0] != null && vehicles[i][0].equals(matricula)) {
                        matriculaExistente = true;
                        break;
                    }
                }
                if (matriculaExistente) {
                    System.out.println("La matrícula ya existe.");
                    continue;
                }
            }
        } while (!matricula.matches("\\d{4}[a-zA-Z]{3}"));
        return matricula;
    }

    public static String obtenerModelo(Scanner scanner) {
        System.out.print("Introduce el modelo del vehículo: ");
        return scanner.nextLine();
    }

    public static String obtenerDniPropietario(Scanner scanner, String[][] clientes) {
        String dniPropietario;
        boolean dniValido;
        do {
            System.out.print("Introduce el DNI del propietario del vehículo: ");
            dniPropietario = scanner.nextLine();

            dniValido = false;
            for (int i = 0; i < clientes.length; i++) {
                if (clientes[i][0] != null && dniPropietario.equals(clientes[i][0])) {
                    dniValido = true;
                    break;
                }
            }
            if (!dniValido) {
                System.out.println("Error: DNI de propietario no válido.");
            }
        } while (!dniValido);
        return dniPropietario;
    }


    // CASE 4: Reparacion
    public static void Reparaciones(Scanner input, String[][] reparaciones,String[][] vehicles) {
        String matricula = obtenerMatricula(input, vehicles);
        mostrarMatriculasVehiculos (input,reparaciones,vehicles);
        registrarReparacion(reparaciones, matricula);
    }
    public static void mostrarMatriculasVehiculos(Scanner input, String[][] vehicles,String[][] reparaciones) {
        System.out.println("Listado de matrículas de vehículos dados de alta:");
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i][0] != null) {
                System.out.println(vehicles[i][0]);
            }
        }
    }

    public static String obtenerMatricula2(Scanner input, String[][] vehicles) {
        System.out.print("Introduce la matrícula del vehículo para la reparación: ");
        String matricula = input.nextLine();

        // Validar que la matrícula introducida exista en el listado de vehículos
        boolean matriculaExistente = false;
        for (int i = 0; i < vehicles.length; i++) {
            if (vehicles[i][0] != null && vehicles[i][0].equals(matricula)) {
                matriculaExistente = true;
                break;
            }
        }

        if (!matriculaExistente) {
            System.out.println("Error: La matrícula introducida no corresponde a ningún vehículo.");
            return null;
        }

        return matricula;
    }

    public static void registrarReparacion(String[][] reparaciones, String matricula) {
        String codiMecanic = "";
        String estadoReparacion = "en curso";

        // Array de reparaciones
        for (int i = 0; i < reparaciones.length; i++) {
            if (reparaciones[i][0] == null) {
                reparaciones[i][0] = matricula;
                reparaciones[i][1] = codiMecanic;
                reparaciones[i][2] = estadoReparacion;
                System.out.println("Reparación registrada correctamente.");
                break;
            }
        }
    }



    //CASE 5: (CONSTANZA)
    public static void modificar(Scanner input,String[][] reparaciones,String[][] clientes) {
        boolean reparacionExistente = verificarReparacionEnCurso(reparaciones);
        if (!reparacionExistente) {
            System.out.println("No hay vehículos con reparacion en curso");
            return;
        } else {
            mostrarEstadosReparacion(reparaciones);
        }

        int fila = obtenerFilaAModificar(input, reparaciones);
        if (fila < 0 || fila >= reparaciones.length) {
            System.out.println("Coordenadas fuera de rango.");
            return;
        }

        System.out.print("Introduce el nuevo valor: ");
        String nuevoValor = input.nextLine();

        modificarEstadoReparacion(reparaciones, fila, nuevoValor);

        System.out.println("Dato modificado correctamente.");
    }
    public static boolean verificarReparacionEnCurso(String[][] reparaciones) {
        for (String[] reparacion : reparaciones) {
            for (String estado : reparacion) {
                if (estado != null && !estado.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void mostrarEstadosReparacion(String[][] reparaciones) {
        for (String[] reparacion : reparaciones) {
            System.out.println(reparacion[2]);
        }
        System.out.println();
    }

    public static int obtenerFilaAModificar(Scanner scanner, String[][] reparaciones) {
        System.out.println("Introduce el número de fila de la matrícula que quieres modificar el estado:");
        return scanner.nextInt();
    }

    public static void modificarEstadoReparacion(String[][] reparaciones, int fila, String nuevoValor) {
        reparaciones[fila][2] = nuevoValor;
    }

}



