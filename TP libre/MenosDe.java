public class MenosDe extends Condicion{
    
    int minimo;

    public MenosDe(int minimo){
        this.minimo = minimo;
    }

    @Override
    public boolean cumple(Celda celda){
        return celda.getVecinos() < minimo;
    }
    
}
