package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class ResultataActivity extends AppCompatActivity {

    private TextView lblResultat;
    public static final String EXTRA_TAUX_INTERET = "com.example.examenfinal.EXTRA_TAUX_INTERET";
    public static final String EXTRA_MONTANT_HYPOTHEQUE = "com.example.examenfinal.EXTRA_MONTANT_HYPOTHEQUE";
    public static final String EXTRA_MONTANT_MENSUEL = "com.example.examenfinal.EXTRA_MONTANT_MENSUEL";
    public static final String EXTRA_MONTANT_TOTAL = "com.example.examenfinal.EXTRA_MONTANT_TOTAL";
    public static final String EXTRA_MONTANT_DIFFERENCE = "com.example.examenfinal.EXTRA_MONTANT_DIFFERENCE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultata);

        initializeWidgets();
        Intent intent = getIntent();
        double tauxInteretResultat = intent.getDoubleExtra(ResultataActivity.EXTRA_TAUX_INTERET, 0);
        double montantHypothequeResultat = intent.getDoubleExtra(ResultataActivity.EXTRA_MONTANT_HYPOTHEQUE, 0);
        double montantMensuelResultat = intent.getDoubleExtra(ResultataActivity.EXTRA_MONTANT_MENSUEL, 0);
        double montantTotalResultat = intent.getDoubleExtra(ResultataActivity.EXTRA_MONTANT_TOTAL, 0);
        double montantDifferenceResultat = intent.getDoubleExtra(ResultataActivity.EXTRA_MONTANT_DIFFERENCE, 0);

        String resultat = String.format(Locale.getDefault(),
                "Taux d'intérêt: %.2f%%\nMontant: $%.2f\nMontant mensuel: $%.2f\nMontant total: $%.2f\nDifférence: $%.2f",
                tauxInteretResultat, montantHypothequeResultat, montantMensuelResultat, montantTotalResultat, montantDifferenceResultat);

        lblResultat.setText(resultat);

    }

    private void initializeWidgets() {
        lblResultat = findViewById(R.id.lblResultat);

    }

    // Méthode liée au bouton Retour
    public void onRetour(View view) {
        finish();
    }
}