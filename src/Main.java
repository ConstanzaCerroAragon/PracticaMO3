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
                    System.out.println("[4] Crear fichad e nueva reparacion");
                    System.out.println("[5] Salir");
                    System.out.println("Selecciona una opcion: ");

                    if (input.hasNextInt()){
                        menuItem = input.nextInt();
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

            //insert code here

        }









    }
}