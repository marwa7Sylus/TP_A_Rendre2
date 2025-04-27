package metier;

public class Comptesimple extends Comptes implements solving {
    private float decouvert;
    public Comptesimple(int code, float solde) {
        super(code, solde);
        this.decouvert=4000;
    }
    public void display() {
        System.out.println("solde = " + solde +" decouvert=" +decouvert );
    }
    public void solver() {
        if (solde < -decouvert) {
        System.out.println("Attention: Solde dépassant le découvert autorisé!");
    } }
    public double getsolde() { return solde;}
}

//classe payment  effectuer payment appeler retirer
