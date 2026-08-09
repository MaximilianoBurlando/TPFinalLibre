public class MenosDe extends Condicion{
    
    int minimo;

    public MenosDe(int minimo){//ingresamos el minimo para sobrevivir
        this.minimo = minimo;
    }

    @Override
    public boolean cumple(Celda celda){//si es menos del minimo restur false
        return celda.getVecinos() < minimo;
    }
    
}
