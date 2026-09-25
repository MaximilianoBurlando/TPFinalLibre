import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Tablero{

    //generacion random
    Random random = new Random();

    //es de 0 a 8 porque ese es el rango de cantidad de vecinos que puede haber
    private final int filas, columnas, min, max, exacto, resucitar;
    //matriz de celdas (para la estructura del tablero)
    private Celda[][] celdas;//matriz de celdas
    //Lista de Estados
    private ArrayList<Estado> estados;

    //contructor
    public Tablero(int filas, int columnas, int min, int max, int exacto, 
        int resucitar){
    
        this.filas = filas;
        this.columnas = columnas;
        this.min = min;
        this.max = max;
        this.exacto = exacto;
        this.resucitar = resucitar;
        celdas = new Celda[filas][columnas];
        estados = new ArrayList<>();

    }

    public void setEstados(Estado estado){
        this.estados.add(estado);
    }

    //inicializar random
    public void inicializarRandom(){

        for(int i = 0; i < filas; i++){

            for(int j = 0; j < columnas; j++){

                //generamos un numero aleatorio para elegir estado
                int estado = random.nextInt(estados.size());

                celdas[i][j] = new Celda(estados.get(estado));
               
            }
        }
    }    
    //guardado de archivo
    public void guardarArchivo(String ruta) throws IOException{

        BufferedWriter archivo = new BufferedWriter(new FileWriter(ruta));

        // Primera línea: dimensiones
        archivo.write(filas + " " + columnas);
        archivo.newLine();

        // Estados de las celdas
        for(int i = 0; i < filas; i++){

            for(int j = 0; j < columnas; j++){

                archivo.write(celdas[i][j].getEstado().getSimbolo());

            }

            archivo.newLine();
        }

        archivo.close();
    }
    //carga de archivo
    public void cargarArchivo(String ruta, Simbolo simbolo) throws IOException, NumberFormatException{

        BufferedReader archivo = new BufferedReader(new FileReader(ruta));

        //primera línea: filas y columnas
        String primeraLinea = archivo.readLine();

        if(primeraLinea == null){
            archivo.close();
            throw new IOException("El archivo está vacío");
        }

        String[] dimensiones = primeraLinea.trim().split("\\s+");

        if(dimensiones.length != 2){//leemos las diemensiones, deben ser 2 valores
            archivo.close();
            throw new IOException("La primera línea debe indicar filas y columnas");
        }

        int filasArchivo = Integer.parseInt(dimensiones[0]);
        int columnasArchivo = Integer.parseInt(dimensiones[1]);

        //verificamos que coincidan con el tablero
        if(filasArchivo != filas || columnasArchivo != columnas){
            archivo.close();
            throw new IOException("Las dimensiones del archivo no coinciden con el tablero");
        }

        //leemos cada fila
        for(int i = 0; i < filas; i++){

            String linea = archivo.readLine();

            if(linea == null || linea.length() != columnas){
                archivo.close();
                throw new IOException("Cantidad de columnas inválida en la fila " + (i + 1));
            }
            //leemos cada columna
            for(int j = 0; j < columnas; j++){

                char estado = linea.charAt(j);

                switch(estado){

                    case 'O':
                        celdas[i][j] = new Celda(new Viva(simbolo, 0, min, max, exacto, resucitar));
                        break;

                    case 'X':
                        celdas[i][j] = new Celda(new Muerta(simbolo, 0, min, max, exacto, resucitar));
                        break;

                    case 'E':
                        celdas[i][j] = new Celda(new Enferma(simbolo, 0, min, max, exacto, resucitar));
                        break;

                    case 'L':
                        celdas[i][j] = new Celda(new Latente(simbolo, 0, min, max, exacto, resucitar));
                        break;

                    default:
                        archivo.close();
                        throw new IOException(
                            "Estado inválido '" + estado +
                            "' en fila " + (i + 1) +
                            ", columna " + (j + 1)
                        );
                }
            }
        }

        //ahora, afuera del for
        if(archivo.readLine() != null){

            archivo.close();
            throw new IOException("El archivo contiene filas de más");

        }

        archivo.close();
    }

    //contabilizar vecinos de celda
    public int contarVecinos(int f, int c){

        int vivos = 0;
        //verificamos cuales estan vivas

        for(int i=-1; i<2; i++){

            for(int j=-1; j<2; j++){

                //nos saltamos la celda actual
                if(!(i == 0 && j == 0)){
                

                    //verificamos que no se salga de los limites del tablero
                    int nuevaFila = f + i;
                    int nuevaColumna = c + j;

                    if(nuevaFila >= 0 && nuevaFila < filas && 
                        nuevaColumna >= 0 && nuevaColumna < columnas){

                        if(celdas[nuevaFila][nuevaColumna].getEstado().estaViva()){
                            vivos++;
                        }
                    }
                }
            }
        }

        return vivos;
    }

    //imprimir tablero por consola, lo dejo por si se testea a futuro
    /*public void mostrar(){

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < celdas[i].length; j++) {
                //imprimimos celda
                System.out.print(celdas[i][j].getSimbolo());

            }

            System.out.println();
        }
    }*/


    //siguiente paso
    public boolean siguienteGeneracion(){

        // Ponemos la cantidad de vecinos a cada celda
        for(int i = 0; i < filas; i++){

            for(int j = 0; j < columnas; j++){

                celdas[i][j].getEstado().setVecinos(
                    contarVecinos(i, j)
                );
            }
        }

        // Guardamos solamente los nuevos estados
        Estado[][] nuevosEstados = new Estado[filas][columnas];

        boolean cambio = false;

        // Calculamos la siguiente generación
        for(int i = 0; i < filas; i++){

            for(int j = 0; j < columnas; j++){

                nuevosEstados[i][j] =
                    celdas[i][j].getEstado().sigEstado();

                if(nuevosEstados[i][j].getClass() !=
                celdas[i][j].getEstado().getClass()){

                    cambio = true;
                }
            }
        }

        // Ahora modificamos las mismas celdas
        for(int i = 0; i < filas; i++){

            for(int j = 0; j < columnas; j++){

                celdas[i][j].setEstado(nuevosEstados[i][j]);
            }
        }

        return cambio;
    }

    //para que la GUI pueda obtener una celda sin acceder directamente a la matriz
    public Celda getCelda(int fila, int columna){

        return celdas[fila][columna];

    }

    //recuperar filas
    public int getFilas(){

        return filas;

    }

    //recuperar columnas
    public int getColumnas(){

        return columnas;

    }

}