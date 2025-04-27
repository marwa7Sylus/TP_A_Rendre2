package metier;

public class Comptes {
    private int code;
    protected float solde;
    private static int nbcomptes;
    public Comptes(int code, float solde) {
        this.code = ++nbcomptes;
        this.solde = solde;
    }

    public String toString(){
        return code + " " + solde;
    }
}
