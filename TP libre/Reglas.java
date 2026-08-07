public class Reglas extends Condicion {

    private Condicion vive;//si sigue viviendo o no
    private Condicion revive;//si se dan las condiciones para revivir

    public Reglas(int menor, int mayor, int igual){
        vive = new And(new MasDe(menor-1), new MenosDe(mayor+1));
        revive = new IgualDe(igual);
    }

    @Override
    public boolean cumple(Celda celda) {
        if (celda.estaViva()) {
            return vive.cumple(celda);
        }

        return revive.cumple(celda);
    }
}