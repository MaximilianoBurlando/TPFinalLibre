public class Muerta extends Celda{

    public Muerta(char estado, int vecinos){
        super(estado, vecinos);
    }

    @Override
    public Celda sigEstado(boolean revive, Simbolos signos){
        
        if(revive){
            return new Viva(signos.getViva(), 0);
        }else{
            return new Muerta(signos.getMuerta(), 0);
        }

    }

    @Override
    public boolean estaViva(){
        return false;
    }

}