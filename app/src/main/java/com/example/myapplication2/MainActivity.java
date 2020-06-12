package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MESSAGE = "com.example.myapplication2.MESSAGE";
    Button valider = null;
    Button effacer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("msg", "Méthode onCreate (Main Activity) activée");
        setContentView(R.layout.activity_main);

        valider = (Button) findViewById(R.id.buttonConnexion);
        effacer = (Button) findViewById(R.id.buttonAnnuler);

        valider.setOnClickListener(this);
        effacer.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("msg", "Méthode onDestroy (Main Activity) activée");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("msg", "Méthode onPause (Main Activity) activée.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("msg", "Méthode onStop (Main Activity) activée");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("msg", "Méthode onResume (Main Activity) activée");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonConnexion :
                Intent intent =  new Intent(this, DisplayMessageActivity.class);
                EditText emailText = (EditText) findViewById(R.id.email);
                String message = emailText.getText().toString();

                System.out.println(message);

                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

                Log.d("msg", "Méthode sendMessage() (Main Activity) activée");

            case R.id.buttonAnnuler :
                Intent intent2 = new Intent(this, MainActivity.class);
                EditText emailText2 = (EditText) findViewById(R.id.email);
                emailText2.getText().clear();

                EditText passwordText = (EditText) findViewById(R.id.motDePasse);
                passwordText.setText("");

                Log.d("msg", "Méthode deleteFieldsConnexion() (Main Activity) activée");
        }
    }
}
