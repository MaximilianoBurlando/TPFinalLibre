public class MenorDe extends Condicion{
    
    int minimo;

    public MenorDe(int minimo){
        this.minimo = minimo;
    }

    @Override
    public boolean cumple(Celda celda){
        return celda.getVecinos() < minimo;
    }
    
}
