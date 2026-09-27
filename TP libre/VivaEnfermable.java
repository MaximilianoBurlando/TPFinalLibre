import java.util.Random;

public class VivaEnfermable extends Viva{
    
    private double probabilidad;
    private Random random = new Random();

    public VivaEnfermable(Simbolo simbolo, int vecinos, int min, int max, int exacto, int resucitar){
        super(simbolo, vecinos, min, max, exacto, resucitar);
        this.probabilidad = random.nextDouble(1);
    }

    @Override 
    public char getSimbolo(){
        return simbolo.getEnfermable();
    }

    @Override 
    public Estado sigEstado(){
        if(random.nextDouble(1) > probabilidad){
            return new Enferma(simbolo, vecinos, min, max, exacto, resucitar);
        }else if(vecinos > min && vecinos < max){
            return new Muerta(simbolo, vecinos, min, max, exacto, resucitar);
        }else{
            return this;
        }
    }

}
