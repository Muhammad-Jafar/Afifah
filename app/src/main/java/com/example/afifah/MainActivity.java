package com.example.afifah;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{

    public EditText ketikdisini;
    public TextView hasil_terjemahan;
    public Button btn_audio;

    private final int REQ_CODE = 100;

    private SQLiteDatabase ambildata;
    private Cursor kursor;
    private DataKamus datakamus;
    private KelolaCRUD keloladata;

    public static final String INGGRIS = "ENG";
    public static final String SUMBAWA = "SBW";
    public static final String INDONESIA = "IND";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keloladata = new KelolaCRUD(this);
        ambildata = keloladata.getWritableDatabase();
        keloladata.createTable(ambildata);
        keloladata.generateData(ambildata);

        ketikdisini = findViewById(R.id.ketikdisini);
        hasil_terjemahan = findViewById(R.id.hasil_terjemahan);
        btn_audio = findViewById(R.id.btn_audio);
    }

    //tampilkan terjemahan teks
    public void getTerjemahan(View view)
    {
        String result = "";
        String englishword = ketikdisini.getText().toString();
        kursor = ambildata.rawQuery("SELECT SBW " + "FROM data where ENG = '" + englishword + "' ORDER BY ENG", null);

        if (kursor.moveToFirst())
        {
            result = kursor.getString(0);

            for (;!kursor.isAfterLast(); kursor.moveToNext())
            {
                result = kursor.getString(0);
            }
        }

        if (result.equals(""))
        {
            Toast.makeText(this,"Translate not found",Toast.LENGTH_SHORT).show();
        }
        hasil_terjemahan.setText(result);
    }

    //buat suara jadi teks
    public void getsuara(View view)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
        try {
            startActivityForResult(intent, REQ_CODE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Sorry your device not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //tampilkan terjemahan suara
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQ_CODE:
                {
                if (resultCode == RESULT_OK && null != data)
                {
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    ketikdisini.setText((CharSequence) result.get(0));
                }
                break;
            }
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        kursor.close();
        keloladata.close();
    }

}
