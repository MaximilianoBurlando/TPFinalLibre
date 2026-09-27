public class Celda {

    private Estado estado;

    public Celda(Estado estado) {
        this.estado = estado;
    }

    public void setVecinos(int vecinos) {
        this.estado.setVecinos(vecinos);
    }

    public int getVecinos() {
        return this.estado.getVecinos();
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public Estado sigEstado() {
        this.estado = estado.sigEstado();
        return this.estado;
    }
    //usar en tablwero para detectar cambio entre generaciones
    public boolean evolucionar(){
        Estado sig = estado.sigEstado();
        if (sig.equals(this.getEstado()))
            return false;
        else {
            this.setEstado(sig);
            return true;
        }
    }
}