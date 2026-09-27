public abstract class Estado{
    
    protected final Simbolo simbolo;
    protected final int min, max, exacto, resucitar;
    protected int vecinos;

    public Estado(Simbolo simbolo, int vecinos, int min, int max, int exacto, int resucitar){
        this.simbolo = simbolo;
        this.vecinos = vecinos;
        this.min = min;
        this.max = max;
        this.exacto = exacto;
        this.resucitar = resucitar;
    }
    //getters y setters
    public void setVecinos(int vecinos) {
        this.vecinos = vecinos;
    }

    public int getVecinos() {
        return vecinos;
    }

    public abstract char getSimbolo();

    public abstract boolean estaViva();

    public abstract Estado sigEstado();

}
