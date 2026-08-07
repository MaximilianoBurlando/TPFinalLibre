public class Enferma extends Celda{

    public Enferma(char estado, int vecinos){
        super(estado, vecinos);
    }

    @Override
    public Celda sigEstado(boolean revive, Simbolos signos){
        
        return new Muerta(signos.getMuerta(), 0);
        
    }

    @Override
    public boolean estaViva(){
        return true;
    }
    
}
