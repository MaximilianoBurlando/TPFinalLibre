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

    public void sigEstado() {
        estado.sigEstado();
    }
}