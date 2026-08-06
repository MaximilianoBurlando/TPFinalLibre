public class Tablero{

    private Celda[][] celdas;//matriz de celdas

    final int filas;//filas de matriz
    final int columnas;//columnas de matriz

    Simbolos signos;

    public Tablero(int filas, int columnas, Simbolos signos){
        
        this.filas = filas;
        this.columnas = columnas;
        this.signos = signos;

        celdas = new Celda[filas][columnas];

    }

    public void inicializarRandom(){

    for(int i = 0; i < filas; i++){

        for(int j = 0; j < columnas; j++){

            if(Math.random() < 0.5){

                celdas[i][j] = new Viva(signos.getViva(), 0);

            }else{

                celdas[i][j] = new Muerta(signos.getMuerta(), 0);

            }

        }
    }
}

    public Celda getCelda(int fila, int columna) {
        return celdas[fila][columna];
    }
    
    public void siguienteGeneracion(Condicion c){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                int vecinosActuales = contarVecinos(i, j);
                celdas[i][j].setVecinos(vecinosActuales);
            }        
        }  
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                celdas[i][j] = celdas[i][j].sigEstado(c.cumple(celdas[i][j]), signos);
            }        
        }           
    }

    public void mostrar(){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                System.out.print(celdas[i][j].getSimbolo());
            }
            System.out.println();
        }  
    }

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

        if(i < celdas.length-1 && j < celdas[0].length-1 && celdas[i+1][j+1].estaViva())
            vivos++;

        return vivos;
    }

}