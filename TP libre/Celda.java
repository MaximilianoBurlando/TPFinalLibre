public abstract class Celda{
    
    char simbolo;
    int vecinos;

    public Celda(char simbolo, int vecinos){
        this.simbolo = simbolo;
        this.vecinos = vecinos;
    }

    abstract public Celda sigEstado(boolean vivos, Simbolos signos);

    public void setVecinos(int vecinos) {
        this.vecinos = vecinos;
    }

    public int getVecinos(){
        return vecinos;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public abstract boolean estaViva();

}
