import java.util.Scanner;

public class Main {

            Scanner input = new Scanner(System.in);

            public static void main(String[] args) {

                Main main = new Main();
                main.init();
            }

            public void init(){
                int menuItem = 0;
                do {
                    System.out.println("TALLER DE REPARACIONDE VEHICULOS");
                    System.out.println("[1] Dar de alta al cliente");
                    System.out.println("[2] Dar de alta a nuevo mecanico");
                    System.out.println("[3] Introducir nuevo Vehiculo");
                    System.out.println("[4] Crear fichas e nueva reparacion");
                    System.out.println("[5] Salir");
                    System.out.println("Selecciona una opcion: ");

                    if (input.hasNextInt()){
                        menuItem = input.nextInt();
                        switch (menuItem){

                            case 1:
                                // Declaración del array de clientes
                                String[][] clientes = new String[100][2];

                                Scanner Scanner = new Scanner(System.in);

                                //Llama al Metodo para pedir el dni y validarlo
                                String dni;
                                do {
                                    dni = pedirDNIAUsuario(Scanner);
                                    if (!validarFormatoDNI(dni)) {
                                        System.out.println("El formato del DNI no es válido. Debe ser de 8 dígitos seguidos de 1 letra.");
                                    }
                                } while (!validarFormatoDNI(dni));

                                // Pedir al usuario que ingrese el nombre del nuevo cliente
                                String nuevoNombre = pedirNombreAUsuario(Scanner);

                                // Agregar el nuevo cliente al array
                                agregarCliente(clientes, dni, nuevoNombre);

                                // Mostrar todos los clientes almacenados
                                mostrarClientes(clientes);

                                break;

                            case 2:

                                // Declaración del array de clientes
                                String[][] mecanicos = new String[100][3];

                                Scanner = new Scanner(System.in);

                                altaMecanico(Scanner, mecanicos);

                                break;

                            case 3:
                                System.out.println("Has dado de alta a un nuevo vehiculo");
                                //insert code here
                                break;
                            case 4:
                                System.out.println("Has creado una nueva ficha de vehiculo");
                                //insert code here
                                break;
                            case 5:
                                System.out.println("Saliedo...");
                                break;
                            default:
                                System.out.println("Opción no vàlida");
                        }
                    }else{
                        System.out.println("Opción no vàlida");
                    }
                    input.nextLine();
                    System.out.println("");

                }while(menuItem!=5);

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
     * @param dni1 Capta el numero ingresado
     *
     * @return Capta el DNI ingresado
     */
    public static String pedirDNIAUsuario(Scanner dni1) {
        System.out.println("Ingrese el DNI del cliente (8 dígitos seguidos de 1 letra):");
        return dni1.nextLine();
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
