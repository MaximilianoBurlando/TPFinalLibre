public class IgualDe extends Condicion{

    int igual;
    
    public IgualDe(int igual){
        this.igual = igual;
    }
    
    @Override
    public boolean cumple(Celda celda){
        return celda.getVecinos() == igual;
    }
    
}
