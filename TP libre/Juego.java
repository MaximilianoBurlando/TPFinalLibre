import java.io.IOException;

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

    //ruta
    private String rutaArchivo;

    //constructor
    public Juego(Tablero tablero, Reglas reglas, String rutaArchivo){

        this.tablero = tablero;
        this.reglas = reglas;
        this.rutaArchivo = rutaArchivo;

    }


    //paso o step
    public void paso(){

        sinCambio = !tablero.siguienteGeneracion(reglas);

        try{

            tablero.guardarArchivo(rutaArchivo);

        }catch(IOException e){

            System.out.println("Error al guardar tablero: " + e.getMessage());

        }

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