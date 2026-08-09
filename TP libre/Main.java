import java.io.IOException;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);//entrada de teclado

        int filas, columnas, numeroExa;//dimensiones de tablero y numero exacto para que resucite latente


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

         while(true){//ingreso de numero exacto para resucitar

            try{

                System.out.print("Ingrese numero exacto para Latentes (0-8): ");
                numeroExa = leer.nextInt();

                if(numeroExa < 0 || numeroExa > 8 ){

                    throw new Exception("Debe de ser de 0 a 8 el numero");

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
        double probabilidadLatente = 0;//probabilidad de latente

        Simbolos signos = new Simbolos(viva, muerta, enferma, latente);//instancia para signos

        while(true){//ingreso de probabilidad

            try{
                //ingreso de probabilidad
                System.out.print("Probabilidad de enfermar (0-1): ");
                probabilidad = leer.nextDouble();

                if(probabilidad < 0 || probabilidad > 1){

                    throw new Exception("Probabilidad esta entre 0.0 a 1.0");

                }

                break;

            }catch(Exception e){

                System.out.println("Entrada inválida: " + e.getMessage());
                leer.nextLine();

            }

        }

        while(true){//ingreso de probabilidad muerta a latente

            try{
                //ingreso de probabilidad de que muerta pase a latente
                System.out.print("Probabilidad de que sea Latente (0-1): ");
                probabilidadLatente = leer.nextDouble();

                if(probabilidadLatente < 0 || probabilidadLatente > 1){

                    throw new Exception("Probabilidad para latente esta entre 0.0 a 1.0");

                }

                break;

            }catch(Exception e){

                System.out.println("Entrada inválida: " + e.getMessage());
                leer.nextLine();

            }
        }

        //seleccionamos modo de carga del tablero
        System.out.println("Seleccione modo de carga:");
        System.out.println("1 - Aleatorio");//random
        System.out.println("2 - Archivo");//archivo

        int opcion = leer.nextInt();//ingresa por teclado una de las opciones


        Reglas r = new Reglas(2, 3, 1);//instancia de reglas

        //caracteristicas de tablero
        Tablero tablero = new Tablero(filas, columnas, signos, numeroExa, probabilidad, probabilidadLatente);

        String rutaGuardar;//ruta para guardar archivo

        if(opcion == 1){

            tablero.inicializarRandom();//creacion random de tablero
            
            leer.nextLine();

            while(true){

                try{

                    System.out.print("Ingrese ruta donde guardar el tablero (ejemplo: /home/maxi/Documentos/tablero.txt): ");
                    rutaGuardar = leer.nextLine();//ingreso de ruta

                    if(!rutaGuardar.toLowerCase().endsWith(".txt")){

                        throw new Exception("El archivo debe tener extensión .txt");

                    }

                    tablero.guardarArchivo(rutaGuardar);
                    break;

                }catch(IOException e){

                    System.out.println("Error al guardar archivo: " + e.getMessage());

                }catch(Exception e){

                    System.out.println("Entrada inválida: " + e.getMessage());

                }

            }

        }else if(opcion == 2){//cargamos archivo de ruta

            leer.nextLine();

            while(true){

                System.out.print("Ingrese ruta del archivo: ");
                String ruta = leer.nextLine();

                try{

                    tablero.cargarArchivo(ruta);
                    rutaGuardar = ruta;//reutilizamos el dato de la ruta para despues guardar
                    break;

                }catch(IOException | NumberFormatException e){

                    System.out.println("Error al cargar archivo: " + e.getMessage());

                }

            }

        }else{

            System.out.println("Opcion inválida");
            return;

        }
        
        Juego juego = new Juego(tablero, r, rutaGuardar);//creamos lógica del juego

        //creamos ventana usando el hilo de Swing
        SwingUtilities.invokeLater(() -> {


            Ventana ventana = new Ventana(juego);

            ventana.setVisible(true);


        });


    }

}