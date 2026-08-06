public class Latente extends Muerta{
    
    int numExacto;//numero exacto de vecinos para reivivir

    public Latente(char estado, int vecinos, int numExacto){
        super(estado, vecinos); // Assuming 0 as the default posibilidad for Latente
        this.numExacto = numExacto;
    }

    @Override
    public Celda sigEstado(boolean revive, Simbolos signos){
        
        if(numExacto == vecinos){
            return new Viva(signos.getViva(), 0);
        }else if(revive){
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
