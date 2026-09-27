public class Latente extends Muerta{

    private int resucitar;//numero exacto de vecinos para resucitar

    public Latente(Simbolo simbolo, int vecinos, int min, int max, int exacto, int resucitar){
        super(simbolo, vecinos, min, max, exacto, resucitar);
        this.resucitar = resucitar;
    }

    @Override 
    public char getSimbolo() {
        return this.simbolo.getLatente();
    }

    @Override
    public Estado sigEstado(){//latente espera a que toque el valor exacto y resucita en base a el

        if(resucitar == vecinos){

            return new Viva(simbolo, vecinos, min, max, exacto, resucitar);

        }else{

            return new Latente(simbolo, vecinos, min, max, exacto, resucitar);

        }
    }

}
