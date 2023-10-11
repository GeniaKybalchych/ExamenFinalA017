package modele;

public class Hypotheque {

    private double tia;
    private int nmbrAnnees;
    private double montant;

    public Hypotheque(double tia, int nmbrAnnees, double montant) {
        this.tia = tia;
        this.nmbrAnnees = nmbrAnnees;
        this.montant = montant;
    }

    public double getTia() {
        return tia;
    }

    public void setTia(double tia) {
        this.tia = tia;
    }

    public int getNmbrAnnees() {
        return nmbrAnnees;
    }

    public void setNmbrAnnees(int nmbrAnnees) {
        this.nmbrAnnees = nmbrAnnees;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getTim() {
        return tia / 12;
    }

    @Override
    public String toString() {
        return "Hypotheque{" +
                "tia=" + tia +
                ", nmbrAnnees=" + nmbrAnnees +
                ", montant=" + montant +
                '}';
    }
}
