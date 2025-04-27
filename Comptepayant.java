package metier;

public class Comptepayant extends Comptes {
    private static final float FRAIS = 0.05f; // 5 % de frais

    public Comptepayant(int code2, float solde2) {
        super(code2, solde2);
    }

    public void verser(float montant) {
        float frais = montant * FRAIS;
        solde += (montant - frais);
    }

    public void retirer(float montant) {
        float frais = montant * FRAIS;
        float total = montant + frais;
        if (solde >= total) {
            solde -= total;
        } else {
            System.out.println("Fonds insuffisants pour effectuer le retrait avec frais.");
        }
    }

    public void display() {
        System.out.println("Compte payant : solde = " + solde);
    }
}
