import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Tablero{

    //generacion random
    Random random = new Random();

    //es de 0 a 8 porque ese es el rango de cantidad de vecinos que puede haber
    final int numeroExa;

    //matriz de celdas (para la estructura del tablero)
    private Celda[][] celdas;//matriz de celdas

    //probabilidad de enfermar
    final double probabilidad, probabilidadLatente;

    //dimensiones
    final int filas;//filas de matriz
    final int columnas;//columnas de matriz

    //clase de simbolos
    Simbolos signos;

    //contructor
    public Tablero(int filas, int columnas, Simbolos signos, int numeroExa, double probabilidad, double probabilidadLatente){
        
        this.filas = filas;
        this.columnas = columnas;
        this.signos = signos;
        this.numeroExa = numeroExa;
        this.probabilidad = probabilidad;
        this.probabilidadLatente = probabilidadLatente;
        celdas = new Celda[filas][columnas];

    }

    //inicializar random
    public void inicializarRandom(){

        for(int i = 0; i < filas; i++){

            for(int j = 0; j < columnas; j++){

                //generamos un numero aleatorio para elegir estado
                int estado = random.nextInt(4);

                switch(estado){

                    case 0:

                        celdas[i][j] = new Viva(signos.getViva(), 0);
                        break;

                    case 1:

                        celdas[i][j] = new Muerta(signos.getMuerta(), 0);
                        break;

                    case 2:

                        celdas[i][j] = new Enferma(signos.getEnferma(), 0);
                        break;

                    case 3:

                        celdas[i][j] = new Latente(signos.getLatente(), 0, numeroExa);
                        break;

                }

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

                archivo.write(celdas[i][j].getSimbolo());

            }

            archivo.newLine();
        }

        archivo.close();
    }
    //carga de archivo
    public void cargarArchivo(String ruta) throws IOException, NumberFormatException{

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

            for(int j = 0; j < columnas; j++){

                char estado = linea.charAt(j);

                switch(estado){

                    case 'O':
                        celdas[i][j] = new Viva(signos.getViva(), 0);
                        break;

                    case 'X':
                        celdas[i][j] = new Muerta(signos.getMuerta(), 0);
                        break;

                    case 'E':
                        celdas[i][j] = new Enferma(signos.getEnferma(), 0);
                        break;

                    case 'L':
                        celdas[i][j] = new Latente(signos.getLatente(), 0, numeroExa);
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
    public int contarVecinos(int i, int j){

        int vivos = 0;
        //verificamos cuales estan vivas

        // arriba
        if(i > 0 && celdas[i-1][j].estaViva())
            vivos++;

        // abajo
        if(i < celdas.length-1 && celdas[i+1][j].estaViva())
            vivos++;

        // izquierda
        if(j > 0 && celdas[i][j-1].estaViva())
            vivos++;

        // derecha
        if(j < celdas[0].length-1 && celdas[i][j+1].estaViva())
            vivos++;

        // diagonales
        if(i > 0 && j > 0 && celdas[i-1][j-1].estaViva())
            vivos++;

        if(i > 0 && j < celdas[0].length-1 && celdas[i-1][j+1].estaViva())
            vivos++;

        if(i < celdas.length-1 && j > 0 && celdas[i+1][j-1].estaViva())
            vivos++;

        if(i < celdas.length-1 && j < celdas[0].length-1 && 
            celdas[i+1][j+1].estaViva())
            vivos++;

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
    public boolean siguienteGeneracion(Reglas r){

        //ponemos la cantidad de vecinos a cada celda
        for(int i = 0; i < filas; i++){

            for(int j = 0; j < columnas; j++){
                //ingresamos los vecinos actuales a la celda
                celdas[i][j].setVecinos(
                    contarVecinos(i, j)
                );

            }
        }

        //instancia nueva para el cambio del tablero
        Celda[][] nueva = new Celda[filas][columnas];

        //creacion de variable que vamos a retornar para indicar si cambio o no
        boolean cambio = false;
        
        //produce la siguiente generacion
        for(int i = 0; i < filas; i++){

            for(int j = 0; j < columnas; j++){

                //ver si cumple o no para vivir o revivir
                boolean vida = r.cumple(celdas[i][j]);
                
                if(celdas[i][j].estaViva() && random.nextDouble() < probabilidad){//si es menor a posibilidad
                    //se enferma
                    nueva[i][j] = new Enferma(signos.getEnferma(), 0);

                }else if(!celdas[i][j].estaViva() && celdas[i][j].getSimbolo() != signos.getLatente() &&
                    random.nextDouble() < probabilidadLatente){//si esta muerta y no es latente, puede que se transforme en latente
                    //pasa a latente
                    nueva[i][j] = new Latente(signos.getLatente(), 0, numeroExa);
                    
                }else{
                    //sigue su estado normal
                    nueva[i][j] = celdas[i][j].sigEstado(vida, signos);

                }

                //vemos si cambio o no con respecto a la anterior, con tan solo una basta
                if(nueva[i][j].getSimbolo() != celdas[i][j].getSimbolo()){

                    cambio = true;

                }

            }
        }

        //sobreescribimos la referencia
        celdas = nueva;

        //retornamos si cambio
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