package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    private Button calculerIMC = null;
    private EditText monIMC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Log.d("msg", "Méthode onCreate (Display Message Activity) activée");

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.emailUtilisateur);
        textView.setText(message);

        calculerIMC = (Button) findViewById(R.id.buttonCalculerIMC);
        monIMC =  (EditText) findViewById(R.id.editTextIMC);

        calculerIMC.setOnClickListener(envoyerListener);

        System.out.println(message+" connecté");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("msg", "Méthode onDestroy (Display Message Activity) activée");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("msg", "Méthode onPause (Display Message Activity) activée.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("msg", "Méthode onStop (Display Message Activity) activée");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("msg", "Méthode onResume (Display Message Activity) activée");
    }



    private View.OnClickListener envoyerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculerImc();
        }
    };

    private void calculerImc(){
        Intent intent = new Intent(this, ImcActivity.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String resultat =data.getStringExtra("RESULTAT");
            monIMC.setText(resultat);
        }
    }
}
