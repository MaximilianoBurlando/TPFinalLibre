public class Viva extends Celda{

    public Viva(char simbolo, int vecinos){
        super(simbolo, vecinos);
    }

    @Override
    public Celda sigEstado(boolean vive, Simbolos signos){

        if(vive){//si sigue viviendo

            return new Viva(signos.getViva(), 0);

        }else{//si la cantidad de vivos no era la adecuada

            return new Muerta(signos.getMuerta(), 0);

        }
    }

    @Override
    public boolean estaViva(){
        return true;
    }
}