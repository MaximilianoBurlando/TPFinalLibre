public class And extends Condicion{

    Condicion c1, c2;

    public And(Condicion c1, Condicion c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    public boolean cumple(Celda e){//si cumple las 2 condiciones sobrevive
        return c1.cumple(e) && c2.cumple(e);
    }
    
}
