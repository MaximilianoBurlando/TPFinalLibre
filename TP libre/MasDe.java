public class MasDe extends Condicion{
    
    int max;
    
    public MasDe(int max){
        this.max = max;
    }
    
    @Override
    public boolean cumple(Celda celda){
        return celda.getVecinos() > max;
    }
    
}
