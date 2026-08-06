public class Juego {

    private Tablero tablero;

    public Juego(Tablero tablero){
        this.tablero = tablero;
    }

    public void jugar(Condicion c){

        while(true){

            tablero.siguienteGeneracion(c);

            tablero.mostrar();

        }

    }

}