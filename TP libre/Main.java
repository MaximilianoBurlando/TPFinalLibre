import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.SwingUtilities;

public class Main {
    //dimensiones de tablero y numero exacto para que resucite latente
    private static int filas, columnas, min, max, exacto, resucitar;

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);//entrada de teclado
        
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
                exacto = leer.nextInt();

                System.out.print("Ingrese numero exacto para resucitar (0-8): ");
                resucitar = leer.nextInt();

                System.out.print("Ingrese numero minimo para vivir (0-8): ");
                min = leer.nextInt();

                System.out.print("Ingrese numero maximo para vivir (0-8): ");
                max = leer.nextInt();

                if(exacto < 0 && resucitar < 0 && min < 0 && max < 0
                     || exacto > 8 && resucitar > 8 && min > 8 && max > 8){

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

        Simbolo simbolo = new Simbolo(viva, muerta, enferma, latente);//instancia para signos

        //seleccionamos modo de carga del tablero
        System.out.println("Seleccione modo de carga:");
        System.out.println("1 - Aleatorio");//random
        System.out.println("2 - Archivo");//archivo

        int opcion = leer.nextInt();//ingresa por teclado una de las opciones

        //caracteristicas de tablero
        Tablero tablero = new Tablero(filas, columnas, min, max, exacto, 
            resucitar);

        tablero.setEstados(new Viva(simbolo, 0, min, max, exacto, resucitar));
        tablero.setEstados(new Muerta(simbolo, 0, min, max, exacto, resucitar));
        tablero.setEstados(new VivaEnfermable(new Random(), simbolo, 0, min, max, exacto, resucitar));
        tablero.setEstados(new Latente(simbolo, 0, min, max, exacto, resucitar));
        
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

                    tablero.cargarArchivo(ruta, simbolo);
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
        
        Juego juego = new Juego(tablero, rutaGuardar);//creamos lógica del juego

        //creamos ventana usando el hilo de Swing
        SwingUtilities.invokeLater(() -> {


            Ventana ventana = new Ventana(juego);

            ventana.setVisible(true);


        });


    }

}