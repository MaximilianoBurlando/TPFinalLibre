import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);

        int filas, columnas;

        while(true){

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
        leer.nextLine(); // limpiar buffer
        
        char viva = 0;
        char muerta = 0;
        char enferma = 0;
        char latente = 0;
        double probabilidad = 0;
        Simbolos signos = new Simbolos(viva, muerta, enferma, latente);

        try {

            System.out.print("Simbolo viva: ");
            viva = leer.nextLine().charAt(0);

            System.out.print("Simbolo muerta: ");
            muerta = leer.nextLine().charAt(0);

            System.out.print("Simbolo enferma: ");
            enferma = leer.nextLine().charAt(0);

            System.out.print("Simbolo latente: ");
            latente = leer.nextLine().charAt(0);

            signos = new Simbolos(viva, muerta, enferma, latente);

            System.out.print("Probabilidad de enfermar (0-1): ");
            probabilidad = leer.nextDouble();

        } catch (Exception e) {

            System.out.println("Entrada inválida");

        }
        
        Celda.setProbabilidad(probabilidad);

        System.out.println("Seleccione modo de carga:");
        System.out.println("1 - Aleatorio");
        System.out.println("2 - Archivo");

        int opcion = leer.nextInt();

        Tablero tablero = new Tablero(filas, columnas, signos);

        if(opcion == 1){

            tablero.inicializarRandom();

        }else if(opcion == 2){

            System.out.print("Ingrese ruta del archivo: ");
            leer.nextLine(); // limpiar buffer
            String ruta = leer.nextLine();

            //tablero.cargarArchivo(ruta);

        }else{

            System.out.println("Opcion inválida");

        }

        Juego juego = new Juego(tablero);

        juego.jugar(new IgualDe(3));

    }
}