import java.util.Random;

public class Viva extends Celda{

    Random random = new Random();

    public Viva(char simbolo, int vecinos){
        super(simbolo, vecinos);
    }

    @Override
    public Celda sigEstado(boolean vive, Simbolos signos){

        double numeroExa = random.nextDouble();

        if(vive && numeroExa < probabilidad){

            return new Enferma(signos.getEnferma(), 0);

        }else if(vive){

            return new Viva(signos.getViva(), 0);

        }else{

            return new Muerta(signos.getMuerta(), 0);

        }
    }

    @Override
    public boolean estaViva(){
        return true;
    }
}