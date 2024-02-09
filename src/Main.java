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
            String codiMecanic = "";
            String estadoReparacion = "en curso";


            public void init() {

                int menuItem = 0;
                do {
                    System.out.println("TALLER DE REPARACIONDE VEHICULOS");
                    System.out.println("[1] Dar de alta al cliente");
                    System.out.println("[2] Dar de alta a nuevo mecanico");
                    System.out.println("[3] Introducir nuevo Vehiculo");
                    System.out.println("[4] Crear fichas e nueva reparacion");
                    System.out.println("[5] Salir");
                    System.out.println("Selecciona una opcion: ");

                    if (input.hasNextInt()) {
                        menuItem = input.nextInt();
                        input.nextLine();
                        String dni;
                        switch (menuItem) {

                            case 1:
                                //Llama al Metodo para pedir el dni y validarlo
                                // String dni;
                                do {
                                    //dni = input.nextLine();
                                    dni = pedirDNIAUsuario();
                                    if (!validarFormatoDNI(dni)) {
                                        System.out.println("El formato del DNI no es válido. Debe ser de 8 dígitos seguidos de 1 letra.");
                                    }
                                } while (!validarFormatoDNI(dni));


                                // Pedir al usuario que ingrese el nombre del nuevo cliente
                                String nuevoNombre = pedirNombreAUsuario(input);

                                // Agregar el nuevo cliente al array
                                agregarCliente(clientes, dni, nuevoNombre);

                                // Mostrar todos los clientes almacenados
                                mostrarClientes(clientes);
                                break;

                            case 2:
                                altaMecanico(input, mecanicos);
                                break;

                            case 3:
                                System.out.println("Introduce los datos del nuevo vehículo:");
                                String matricula;
                                String model;
                                String dniPropietario;
                                do {
                                    System.out.print("Introduce la matrícula del vehículo (formato: 4 dígitos seguidos de 3 letras): ");
                                    matricula = Scanner.nextLine();

                                    // Validar el formato de la matrícula utilizando una expresión regular
                                    if (!matricula.matches("\\d{4}[a-zA-Z]{3}")) {
                                        System.out.println("Error: El formato de la matrícula es incorrecto.");
                                    }
                                    System.out.print("Introduce el modelo del vehículo: ");
                                    model = Scanner.nextLine();
                                    System.out.print("Introduce el DNI del propietario del vehículo: ");
                                    dniPropietario = Scanner.nextLine();

                                    if (matricula.isEmpty() || model.isEmpty() || dniPropietario.isEmpty()) {
                                        System.out.println("Error: Todos los datos del vehículo son obligatorios.");
                                    }
                                } while (matricula.isEmpty() || model.isEmpty() || dniPropietario.isEmpty());

                                // Comprobación de si la matrícula ya existe
                                for (int i = 0; i < vehicles.length; i++) {
                                    if (vehicles[i][0] != null && vehicles[i][0].equals(matricula)) {
                                        System.out.println("Error: La matrícula ya existe.");
                                        return;
                                    }
                                }

                                // Comprobación de si existe el DNI del propietario
                                boolean dniValido = false;
                                for (int i = 0; i < clientes.length; i++) {
                                    if (clientes[i][0] != null && dniPropietario.matches(clientes[i][0])) {
                                        dniValido = true;
                                        break;
                                    }
                                }
                                if (!dniValido) {
                                    System.out.println("Error: DNI de propietario no válido.");
                                    return;
                                }
                                // Añadir el vehículo al array de vehículos
                                for (int i = 0; i < vehicles.length; i++) {
                                    if (vehicles[i][0] == null) {
                                        vehicles[i][0] = matricula;
                                        vehicles[i][1] = model;
                                        vehicles[i][2] = dniPropietario;
                                        System.out.println("Vehículo dado de alta correctamente.");
                                        break;
                                    }
                                }

                            case 4:
                                // Mostrar el listado de matrículas de vehículos dados de alta
                                System.out.println("Listado de matrículas de vehículos dados de alta:");
                                for (int i = 0; i < vehicles.length; i++) {

                                    if (vehicles[i][0] != null) {
                                        System.out.println(vehicles[i][0]);
                                    }
                                }

                                // Solicitar al usuario que seleccione una matrícula
                                System.out.print("Introduce la matrícula del vehículo para la reparación: ");
                                matricula = Scanner.nextLine();

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
                                    return;
                                }

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
                                break;
                            case 5:
                                boolean reparacionExistente = false;
                                for (int i = 0; i < reparaciones.length; i++) {
                                    for (int j = 0; j < reparaciones[i].length; j++) {
                                        if (reparaciones[i][j] != null && !reparaciones[i][j].isEmpty()) {
                                            matriculaExistente = true;
                                            break;
                                        }
                                    }
                                }
                                if (reparacionExistente = false) {
                                    System.out.println("No hay vehículos con reparacion en curso");
                                    return;
                                } else {
                                    for (int i = 0; i < reparaciones.length; i++) {
                                        System.out.println(reparaciones[i][2]);
                                    }
                                    System.out.println();
                                }
                                System.out.println("Introduce el número de filas de la matrícula que quieres modificar el estado");
                                int fila = Scanner.nextInt();

                                if (fila < 0 || fila >= reparaciones.length) {
                                    System.out.println("Coordenadas fuera de rango.");
                                    return;
                                }
                                Scanner.nextLine();

                                System.out.print("Introduce el nuevo valor: ");
                                String nuevoValor = Scanner.nextLine();

                                reparaciones[fila][2] = nuevoValor;

                                System.out.println("Dato modificado correctamente.");

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

    //CASE 1: DAR DE ALTA NUEVO EMPLEADO

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
        System.out.println("Ingrese el DNI del cliente (8 dígitos seguidos de 1 letra):");
        String dni = input.nextLine();
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
                System.out.println("Cliente " + (i + 1) + ": DNI = " + clientes[i][0] + ", Nombre = " + clientes[i][1]);
            } else {
                break; // Detener la iteración si se alcanza una posición vacía
            }
        }
    }



    //CASE 2: DAR DE ALTA NUEVO MECANICO

    public static void altaMecanico(Scanner scanner, String[][] mecanicos) {

        String codigoEmpleado = pedirCodigoEmpleado(scanner, mecanicos);
        String nombreMecanico = pedirNombreMecanico(scanner);
        String estadoMecanico = pedirEstadoMecanico(scanner);
        guardarMecanico(mecanicos, codigoEmpleado, nombreMecanico, estadoMecanico);
    }

    public static String pedirCodigoEmpleado(Scanner scanner, String[][] mecanicos) {
        String codigoEmpleado;
        do {
            System.out.println("Ingrese el número de empleado (6 dígitos):");
            codigoEmpleado = scanner.nextLine();

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

    // Método para validar el formato del código de empleado (6 dígitos)
    public static boolean validarFormatoCodigoEmpleado(String codigoEmpleado) {
        return codigoEmpleado.matches("[0-9]{6}");
    }

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

    public static String pedirNombreMecanico(Scanner nombre) {
        System.out.println("Ingrese el nombre del mecánico:");
        return nombre.nextLine();
    }

    public static String pedirEstadoMecanico(Scanner estado) {
        System.out.println("Ingrese el estado del mecánico (ocupado o libre):");
        return estado.nextLine();
    }

    public static void guardarMecanico(String[][] mecanicos, String codigoEmpleado, String nombreMecanico, String estadoMecanico) {
        for (int i = 0; i < mecanicos.length; i++) {
            if (mecanicos[i][0] == null) {
                mecanicos[i][0] = codigoEmpleado;
                mecanicos[i][1] = nombreMecanico;
                mecanicos[i][2] = estadoMecanico;
                System.out.println("Nuevo mecánico dado de alta exitosamente.");
                break;
            }
        }
    }

}


