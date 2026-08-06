public class Viva extends Celda{
    
    public Viva(char simbolo, int vecinos){
        super(simbolo, vecinos);
    }

    @Override
    public Celda sigEstado(boolean vive, Simbolos signos){

        if(vive && Math.random() < probabilidad){
            return new Enferma(signos.getEnferma(), 0);
        }else if(vive && Math.random() >= probabilidad){
            return new Viva(signos.getViva(), 0){};
        }else{
            return new Muerta(signos.getMuerta(), 0);
        }

    }

    @Override
    public boolean estaViva(){
        return true;
    }

}
