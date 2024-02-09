import java.util.Scanner;

public class Main {
    public static Scanner Scanner = new Scanner(System.in);
    public static String[][] vehicles = new String[100][3]; // Array para almacenar los datos de los vehículos
    public static String[][] reparaciones = new String[100][3]; // Array para almacenar los datos de las reparaciones

    public static void main(String[] args) {

        Main main = new Main();
        main.init();
    }

    //Variables
    // Encontrar el primer mecánico libre
    String codiMecanic = "";

    // Establecer el estado de la reparación
    String estadoReparacion = "en curso";

    public void init(){
        int menuItem = 0;
        do {
            System.out.println("TALLER DE REPARACIONDE VEHICULOS");
            System.out.println("[1] Dar de alta al cliente");
            System.out.println("[2] Dar de alta a nuevo mecanico");
            System.out.println("[3] Introducir nuevo Vehiculo");
            System.out.println("[4] Crear fichas e nueva reparacion");
            System.out.println("[5] Modificar la reparacion");
            System.out.println("[6] Salir");
            System.out.println("Selecciona una opcion: ");

            if (Scanner.hasNextInt()){
                menuItem = Scanner.nextInt();
                switch (menuItem){
                    case 1:
                        System.out.println("Has dado de alta a un nuevo cliente");
                        //insert code here
                        break;
                    case 2:
                        System.out.println("Has dado de alta a un nuevo mecanico");
                        //insert code here
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
                        boolean reparacionExistente=false;
                        for (int i = 0; i < reparaciones.length; i++) {
                            for (int j = 0; j < reparaciones[i].length; j++) {
                                if (reparaciones[i][j] != null && !reparaciones[i][j].isEmpty()) {
                                    matriculaExistente = true;
                                    break;
                                }
                            }
                        }
                        if (reparacionExistente=false) {
                            System.out.println("No hay vehículos con reparacion en curso");
                            return;
                        } else {
                            for (int i = 0; i < reparaciones.length; i++) {
                                System.out.println (reparaciones[i][2]);
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
            }else{
                System.out.println("Opción no vàlida");
            }
            Scanner.nextLine();
            System.out.println("");

        }while(menuItem!=6);

    }
    //insert code here

}