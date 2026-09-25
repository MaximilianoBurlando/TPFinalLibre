import java.util.Random;

public class VivaEnfermable extends Viva{
    
    private double probabilidad;
    private Random random;

    public VivaEnfermable(Random random, Simbolo simbolo, int vecinos, int min, int max, int exacto, int resucitar){
        super(simbolo, vecinos, min, max, exacto, resucitar);
        this.random = random;
        this.probabilidad = random.nextDouble(1);
    }

    @Override 
    public Estado sigEstado(){
        if(random.nextDouble(1) > probabilidad){
            return new Enferma(simbolo, vecinos, min, max, exacto, resucitar);
        }if(vecinos > min && vecinos < max){
            return new Muerta(simbolo, vecinos, min, max, exacto, resucitar);
        }else{
            return this;
        }
    }

}
