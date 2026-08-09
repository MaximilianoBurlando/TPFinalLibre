public class IgualDe extends Condicion{

    int igual;
    
    public IgualDe(int igual){
        this.igual = igual;
    }
    
    @Override
    public boolean cumple(Celda celda){//si es igual la cantidad de vecinos, se cumple la condicion
        return celda.getVecinos() == igual;
    }
    
}
