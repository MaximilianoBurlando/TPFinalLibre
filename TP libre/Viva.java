public class Viva extends Celda{

    public Viva(char simbolo, int vecinos){
        super(simbolo, vecinos);
    }

    @Override
    public Celda sigEstado(boolean vive, Simbolos signos){

        if(vive){

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