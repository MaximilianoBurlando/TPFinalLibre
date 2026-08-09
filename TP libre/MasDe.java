public class MasDe extends Condicion{
    
    int max;
    
    public MasDe(int max){
        this.max = max;
    }
    
    @Override
    public boolean cumple(Celda celda){//si hay sobrepoblacion, no sobrevivira
        return celda.getVecinos() > max;
    }
    
}
