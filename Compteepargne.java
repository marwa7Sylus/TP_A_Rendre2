package metier;

public class Compteepargne extends Comptes {
    private float tauxInteret;
    public Compteepargne(int code1, float solde1,float tauxInteret1) {
        super(code1, solde1);
        this.tauxInteret = tauxInteret1;
    }
    public void calculInteret() {
        solde += solde * tauxInteret / 100;
    }
    public void display() {
        System.out.println("le taux interet: " + tauxInteret );
    }
}

