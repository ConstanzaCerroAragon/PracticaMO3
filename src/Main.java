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

                                Scanner scanner = new Scanner(System.in);

                                //Llama al Metodo para pedir el dni y validarlo
                                String dni;
                                do {
                                    dni = pedirDNIAUsuario(scanner);
                                    if (!validarFormatoDNI(dni)) {
                                        System.out.println("El formato del DNI no es válido. Debe ser de 8 dígitos seguidos de 1 letra.");
                                    }
                                } while (!validarFormatoDNI(dni));

                                // Pedir al usuario que ingrese el nombre del nuevo cliente
                                String nuevoNombre = pedirNombreAUsuario(scanner);

                                // Agregar el nuevo cliente al array
                                agregarCliente(clientes, dni, nuevoNombre);

                                // Mostrar todos los clientes almacenados
                                mostrarClientes(clientes);

                            case 2:
                                System.out.println("Has dado de alta a un nuevo mecanico");
                                //insert code here
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
     * @param input Capta el numero ingresado
     *
     * @return Capta el DNI ingresado
     */
    public static String pedirDNIAUsuario(Scanner input) {
        System.out.println("Ingrese el DNI del cliente (8 dígitos seguidos de 1 letra):");
        return input.nextLine();
    }

    /**
     * Pide el nombre al usuario
     *
     * @param input Capta el nombre ingresado
     *
     * @return Capta el Nombre del usuario ingresado
     */
    public static String pedirNombreAUsuario(Scanner input) {
        System.out.println("Ingrese el nombre del nuevo cliente:");
        return input.nextLine();
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

}
