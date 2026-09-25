import java.util.Random;

public class Enferma extends Viva{

    Random random = new Random();

    public Enferma(Simbolo simbolo, int vecinos, int min, int max, int exacto, int resucitar){
        super(simbolo, vecinos, min, max, exacto, resucitar);
    }

    @Override 
    public char getSimbolo() {
        return this.simbolo.getEnferma();
    }

    @Override
    public Estado sigEstado(){
        //enferma muere si o si en la siguiente generacion
        return new Muerta(simbolo, vecinos, min, max, exacto, resucitar);
    }    
    
}
