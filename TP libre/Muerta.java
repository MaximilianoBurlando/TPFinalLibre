public class Muerta extends Estado{

    public Muerta(Simbolo simbolo, int vecinos, int min, int max, int exacto, int resucitar){
        super(simbolo, vecinos, min, max, exacto, resucitar);
    }

    @Override
    public char getSimbolo() {
        return this.simbolo.getMuerta();
    }

    @Override
    public boolean estaViva(){
        return false;
    }

    @Override
    public Estado sigEstado(){
        if(!(exacto == vecinos)){
            return new Muerta(simbolo, vecinos, min, max, exacto, resucitar);
        }else{
            return new Viva(simbolo, vecinos, min, max, exacto, resucitar);
        }
    }

}