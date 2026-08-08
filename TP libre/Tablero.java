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

    //imprimir tablero
    public void mostrar(){

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < celdas[i].length; j++) {

                System.out.print(celdas[i][j].getSimbolo());

            }

            System.out.println();
        }
    }


    //siguiente paso
    public boolean siguienteGeneracion(Reglas r){

        //ponemos la cantidad de vecinos a cada celda
        for(int i = 0; i < filas; i++){

            for(int j = 0; j < columnas; j++){

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

                if(celdas[i][j].estaViva() && random.nextDouble() < probabilidad){

                    nueva[i][j] = new Enferma(signos.getEnferma(), 0);

                }else if(!celdas[i][j].estaViva() && 
                        random.nextDouble() < probabilidadLatente){

                    nueva[i][j] = new Latente(signos.getLatente(), 0, numeroExa);

                }else{

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


    public int getFilas(){

        return filas;

    }


    public int getColumnas(){

        return columnas;

    }

}