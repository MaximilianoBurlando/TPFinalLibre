public class Simbolo {
    //simbolos
    private char viva;
    private char muerta;
    private char enferma;
    private char latente;
    private char enfermable;
    
    public Simbolo(char viva, char muerta, char enferma, char latente, char enfermable){
        this.viva = viva;
        this.muerta = muerta;
        this.enferma = enferma;
        this.latente = latente;
        this.enfermable = enfermable;
    }
    //recuperar parametros
    public char getViva() { return viva; }
    public char getMuerta() { return muerta; }
    public char getEnferma() { return enferma; }
    public char getLatente() { return latente; }
    public char getEnfermable() { return enfermable; }

}
