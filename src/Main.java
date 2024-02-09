import java.util.Scanner;

public class Main {

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Main main = new Main();
        main.init();
    }

    // arrays
    public static String[][] vehicles = new String[100][3]; // Array para almacenar los datos de los vehículos

    public static String[][] reparaciones = new String[100][3]; // Array para almacenar los datos de las reparaciones


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

            if (scanner.hasNextInt()){
                menuItem = scanner.nextInt();
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
                            matricula = scanner.nextLine();

                            // Validar el formato de la matrícula utilizando una expresión regular
                            if (!matricula.matches("\\d{4}[a-zA-Z]{3}")) {
                                System.out.println("Error: El formato de la matrícula es incorrecto.");
                            }
                            System.out.print("Introduce el modelo del vehículo: ");
                            model = scanner.nextLine();
                            System.out.print("Introduce el DNI del propietario del vehículo: ");
                            dniPropietario = scanner.nextLine();

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
                        for (int i = 0; i < clients.length; i++) {
                            if (clients[i][0] != null && dniPropietario.matches(clients[i][0])) {
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
                        matricula = scanner.nextLine();

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

                        // Encontrar el primer mecánico libre
                        String codiMecanic = "";

                        // Establecer el estado de la reparación
                        String estadoReparacion = "en curso";

                        // Guardar la información de la reparación en algún lugar (en este caso, el array de reparaciones)
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
    //insert code here

}