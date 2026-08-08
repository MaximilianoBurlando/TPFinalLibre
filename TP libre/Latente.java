public class Latente extends Celda{
    
    int numExacto;//numero exacto de vecinos para reivivir

    public Latente(char estado, int vecinos, int numExacto){
        super(estado, vecinos); // Assuming 0 as the default posibilidad for Latente
        this.numExacto = numExacto;
    }

    @Override
    public Celda sigEstado(boolean revive, Simbolos signos){

        if(numExacto == vecinos){

            return new Viva(signos.getViva(), 0);

        }else{

            return new Latente(signos.getLatente(), 0, numExacto);

        }
    }

    @Override
    public boolean estaViva(){
        return false;
    }

}
