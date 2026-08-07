import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);//entrada de teclado

        int filas, columnas;//dimensiones de tablero


        while(true){//ingreso de datos de tablero

            try{

                System.out.print("Ingrese Filas: ");
                filas = leer.nextInt();

                System.out.print("Ingrese Columnas: ");
                columnas = leer.nextInt();


                if(filas <= 0 || columnas <= 0){

                    throw new Exception("Las filas y columnas deben ser mayores a 0");

                }

                break;


            }catch(Exception e){

                System.out.println("Entrada inválida: " + e.getMessage());
                leer.nextLine(); // limpia lo que quedó mal ingresado

            }

        }


        leer.nextLine();//limpiamos buffer


        //caracteres que usaremos para las celdas
        char viva = 'O';
        char muerta = 'X';
        char enferma = 'E';
        char latente = 'L';


        double probabilidad = 0;//probabilidad de enfermar


        Simbolos signos = new Simbolos(viva, muerta, enferma, latente);//instancia para signos



        while(true){//ingreso de probabilidad

            try{

                System.out.print("Probabilidad de enfermar (0-1): ");//ingreso de probabilidad
                probabilidad = leer.nextDouble();


                if(probabilidad < 0 || probabilidad > 1){

                    throw new Exception("Debe estar entre 0 y 1");

                }


                break;


            }catch(Exception e){

                System.out.println("Entrada inválida: " + e.getMessage());
                leer.nextLine();

            }

        }


        Celda.setProbabilidad(probabilidad);//ingresamos la variable probabilidad en la clase celda



        //seleccionamos modo de carga del tablero
        System.out.println("Seleccione modo de carga:");
        System.out.println("1 - Aleatorio");//random
        System.out.println("2 - Archivo");//archivo


        int opcion = leer.nextInt();//ingresa por teclado una de las opciones


        Reglas r = new Reglas(2, 3, 1);//instancia de reglas


        Tablero tablero = new Tablero(filas, columnas, signos);//caracteristicas de tablero



        if(opcion == 1){//crear random


            tablero.inicializarRandom();


        }else if(opcion == 2){//lee archivo


            System.out.print("Ingrese ruta del archivo: ");

            leer.nextLine(); //limpiamos buffer

            String ruta = leer.nextLine();


            //cuando se implemente:
            //tablero.cargarArchivo(ruta);


            System.out.println("Carga por archivo todavía no implementada");
            return;


        }else{


            System.out.println("Opcion inválida");
            return;


        }



        Juego juego = new Juego(tablero,r);//creamos lógica del juego



        //creamos ventana usando el hilo de Swing
        SwingUtilities.invokeLater(() -> {


            Ventana ventana = new Ventana(juego);

            ventana.setVisible(true);


        });


    }

}