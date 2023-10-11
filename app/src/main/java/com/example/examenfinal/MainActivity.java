package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import modele.Hypotheque;
import utile.Calcul;

public class MainActivity extends AppCompatActivity {

    private EditText txtTia, txtNmbreAnnees, txtMontant;
    private Button btnCalculate;
    private DatabaseReference eventsRef;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenir une instance de la base de données avec l'URL
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://examen-final-a2a31-default-rtdb.firebaseio.com/");

        eventsRef = database.getReference("hypotheques");

        setWidgets();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (calculateHypothecaire()) {
                    ajouterHypotheque(eventsRef);
                }

            }
        });
    }

    private void ajouterHypotheque(DatabaseReference hypothequeReference) {
        double tauxInteret = Double.parseDouble(txtTia.getText().toString());
        double montant = Double.parseDouble(txtMontant.getText().toString());
        int nbreAnnees = Integer.parseInt(txtNmbreAnnees.getText().toString());

        Hypotheque hypotheque = new Hypotheque(tauxInteret, nbreAnnees, montant);
        Calcul.calculMontantPayer(hypotheque);

        String key = hypothequeReference.push().getKey(); // Génère un identifiant unique pour la nouvelle hypotheque
        hypothequeReference.child(key).setValue(hypotheque);  //NOTE: Nous ajoutons l'objet hypotheque directement. Veuillez vous assurer que votre modèle Hypotheque est conforme à Firebase.

        Toast.makeText(MainActivity.this, "Hypotheque ajoutée avec succès!", Toast.LENGTH_SHORT).show();
    }

    private void setWidgets() {
        txtTia = findViewById(R.id.txtTia);
        txtNmbreAnnees = findViewById(R.id.txtNmbrAnnees);
        txtMontant = findViewById(R.id.txtMontant);
        btnCalculate = findViewById(R.id.btnCalculer);
    }

    private boolean calculateHypothecaire() {
        double tia, montant;
        int nmbrAnnees;

        try {
            tia = Double.parseDouble(txtTia.getText().toString());
            if (tia <= 0) {
                Toast.makeText(this, "Le taux d'intérêt doit être positif.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez entrer un taux valide.", Toast.LENGTH_SHORT).show();
            return false;
        }



        try {
            nmbrAnnees = Integer.parseInt(txtNmbreAnnees.getText().toString());
            if (nmbrAnnees != 5 && nmbrAnnees != 10 && nmbrAnnees != 15 && nmbrAnnees != 25) {
                Toast.makeText(this, "Veuillez entrer un nombre d'annees valide (5, 10, 15 ou 25).", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez entrer un nombre d'annees valide.", Toast.LENGTH_SHORT).show();
            return false;
        }


        try {
            montant = Double.parseDouble(txtMontant.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Veuillez entrer un montant valide.", Toast.LENGTH_SHORT).show();
            return false;
        }


        Hypotheque hypotheque = new Hypotheque(tia, nmbrAnnees, montant);
        double montantMensuelResultat = Calcul.calculMontantPayer(hypotheque);
        double montantTotalResultat = Calcul.calculMontantTotal(hypotheque);
        double differenceResultat = Calcul.calculDifference(hypotheque);

        Intent intent = new Intent(MainActivity.this, ResultataActivity.class);
        intent.putExtra(ResultataActivity.EXTRA_TAUX_INTERET, tia);
        intent.putExtra(ResultataActivity.EXTRA_MONTANT_HYPOTHEQUE, montant);
        intent.putExtra(ResultataActivity.EXTRA_MONTANT_MENSUEL, montantMensuelResultat);
        intent.putExtra(ResultataActivity.EXTRA_MONTANT_TOTAL, montantTotalResultat);
        intent.putExtra(ResultataActivity.EXTRA_MONTANT_DIFFERENCE, differenceResultat);

        startActivity(intent);
        return true;



    }


}