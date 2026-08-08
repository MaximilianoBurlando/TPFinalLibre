import java.util.Random;

public class Enferma extends Celda{

    Random random = new Random();

    public Enferma(char estado, int vecinos){
        super(estado, vecinos);
    }

    @Override
    public Celda sigEstado(boolean vive, Simbolos signos){

        return new Muerta(signos.getMuerta(), 0);

    }

    @Override
    public boolean estaViva(){
        return true;
    }
    
}
