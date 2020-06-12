package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity{

    Button calculer = null;
    Button effacer = null;
    Button reTourAccueil = null;

    EditText poids = null;
    EditText taille = null;
    RadioGroup group = null;

    TextView result = null;
    TextView infosMega = null;
    CheckBox mega = null;

    private String megaString = "Vous êtes PAR - FAIT.E !";
    private final String defaut = "Cliquez sur « Calculer » pour obtenir un résultat.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        calculer = (Button) findViewById(R.id.buttonCalculer);
        effacer = (Button) findViewById(R.id.buttonRetourAccueil);
        reTourAccueil = (Button) findViewById(R.id.buttonRetourAccueil);

        poids = (EditText) findViewById(R.id.editTextPoids);
        taille = (EditText) findViewById(R.id.editTextTaille);

        group = (RadioGroup) findViewById(R.id.radioGroup);

        result = (TextView) findViewById(R.id.textViewResultat);
        infosMega = (TextView) findViewById(R.id.textViewInfoMegaFonction);

        mega = (CheckBox) findViewById(R.id.checkBox);

        calculer.setOnClickListener(envoyerListener);
        effacer.setOnClickListener(razListener);
        reTourAccueil.setOnClickListener(retournerAccueilListener);

        poids.addTextChangedListener(textWatcher);
        taille.addTextChangedListener(textWatcher);

        mega.setOnClickListener(checkedListener);

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(defaut);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!mega.isChecked()) {

                String taille = ImcActivity.this.taille.getText().toString();

                String poids = ImcActivity.this.poids.getText().toString();

                float tValue = Float.valueOf(taille);
                float pValue = Float.valueOf(poids);

                // Puis on vérifie que la taille est cohérente
                if(tValue <= 0|| pValue <= 0 ) {
                    Toast.makeText(ImcActivity.this, "Sus à la Moria !",
                            Toast.LENGTH_SHORT).show();
                }
                else {


                    if(group.getCheckedRadioButtonId() == R.id.radioButtonCm)
                        tValue = tValue / 100;

                    tValue = (float)Math.pow(tValue, 2);
                    float imc = pValue / tValue;
                    result.setText(String.valueOf(imc));
                }
            } else
                result.setText(megaString);
        }
    };

    private View.OnClickListener razListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            poids.getText().clear();
            taille.getText().clear();
            result.setText(defaut);
        }
    };

    // Listener du bouton de la megafonction.
    private View.OnClickListener checkedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // On remet le texte par défaut si c'était le texte de la megafonction qui était écrit
            if(((CheckBox)v).isChecked()){
                infosMega.setVisibility(View.VISIBLE);
            }
            if(!((CheckBox)v).isChecked()){
                infosMega.setVisibility(View.INVISIBLE);
            }
            if(!((CheckBox)v).isChecked() &&
                result.getText().equals(megaString))
                result.setText(defaut);
        }
    };

    private View.OnClickListener retournerAccueilListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String resultat =result.getText().toString();
            Intent intent=new Intent();
            intent.putExtra("RESULTAT",resultat);
            setResult(2,intent);
            finish();//finishing activity
        }
    };
}
