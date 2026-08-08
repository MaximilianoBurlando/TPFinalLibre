public class Juego {

    //tablero en que se juega
    private Tablero tablero;

    //reglas del juego
    private Reglas reglas;

    //speed
    private int velocidad = 1000;

    //detecta si el juego esta corriendo
    private boolean corriendo = false;

    //detecta si el tablero dejo de cambiar
    private boolean sinCambio = false;


    //constructor
    public Juego(Tablero tablero, Reglas reglas){

        this.tablero = tablero;
        this.reglas = reglas;

    }


    //paso o step
    public void paso(){

        //verificamos si cambio
        sinCambio = !tablero.siguienteGeneracion(reglas);

    }


    //iniciar generaciones de tablero
    public void iniciar(){

        corriendo = true;

    }


    //detener generaciones
    public void detener(){

        corriendo = false;

    }


    //verificar si esta ejecutando
    public boolean estaCorriendo(){

        return corriendo;

    }


    //verificar si el tablero se estabilizo
    public boolean sinCambio(){

        return sinCambio;

    }


    //cambiar velocidad
    public void setVelocidad(int velocidad){

        this.velocidad = velocidad;

    }


    //obtener velocidad
    public int getVelocidad(){

        return velocidad;

    }


    //obtener tablero para la GUI
    public Tablero getTablero(){

        return tablero;

    }

}