public class Viva extends Estado{

    public Viva(Simbolo simbolo, int vecinos, int min, int max, int exacto, int resucitar){
        super(simbolo, vecinos, min, max, exacto, resucitar);
    }

    @Override
    public char getSimbolo() {
        return this.simbolo.getViva();
    }

    @Override
    public boolean estaViva(){
        return true;
    }

    @Override
    public Estado sigEstado(){
        if(vecinos > min && vecinos < max){
            return new Muerta(simbolo, vecinos, min, max, exacto, resucitar);
        }else{
            return new Viva(simbolo, vecinos, min, max, exacto, resucitar);
        }
    }

}