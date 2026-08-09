public abstract class Celda{//clase padre de celdas
    
    char simbolo;//simbolo que carga
    int vecinos;//cantidad de vecinos

    public Celda(char simbolo, int vecinos){
        this.simbolo = simbolo;
        this.vecinos = vecinos;
    }

    //todas las celdas tienen un estado siguiente al actual
    abstract public Celda sigEstado(boolean vivos, Simbolos signos);

    public void setVecinos(int vecinos) {//ingreso de vecinos
        this.vecinos = vecinos;
    }

    public int getVecinos(){//recuperacion de vecinos
        return vecinos;
    }

    public char getSimbolo() {//recuperacion de simbolo
        return simbolo;
    }
    //recupera si vive o muere, depende de cada hijo si es false o true (como se le considera)
    public abstract boolean estaViva();

}
