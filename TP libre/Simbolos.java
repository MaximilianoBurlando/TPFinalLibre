public class Simbolos {
    //simbolos
    private char viva;
    private char muerta;
    private char enferma;
    private char latente;
    
    public Simbolos(char viva, char muerta, char enferma, char latente){
        this.viva = viva;
        this.muerta = muerta;
        this.enferma = enferma;
        this.latente = latente;
    }
    //recuperar parametros
    public char getViva() { return viva; }
    public char getMuerta() { return muerta; }
    public char getEnferma() { return enferma; }
    public char getLatente() { return latente; }

    // Si querés poder modificarlos desde afuera
    public void setViva(char viva) { this.viva = viva; }
    public void setMuerta(char muerta) { this.muerta = muerta; }
    public void setEnferma(char enferma) { this.enferma = enferma; }
    public void setLatente(char latente) { this.latente = latente; }
}