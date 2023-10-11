package utile;

import modele.Hypotheque;

public class Calcul {

    public static double calculMontantPayer(Hypotheque hypotheque) {
        double tim = hypotheque.getTim();
        int nbreAnnees = hypotheque.getNmbrAnnees();
        double montant = hypotheque.getMontant();

        return (tim/100 * montant) / (1 - Math.pow((1 + tim), -12 * nbreAnnees));
    }

    public static double calculMontantTotal(Hypotheque hypotheque) {
        double montantMensuel = calculMontantPayer(hypotheque);
        return montantMensuel * 12 * hypotheque.getNmbrAnnees();
    }

    public static double calculDifference(Hypotheque hypotheque) {
        double montantTotal = calculMontantTotal(hypotheque);
        return montantTotal - hypotheque.getMontant();
    }

    }
