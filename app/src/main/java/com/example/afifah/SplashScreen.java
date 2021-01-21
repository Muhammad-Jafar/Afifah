package com.example.afifah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashScreen extends Activity
{
    private int waktu_loading=1000; //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS );
        setContentView(R.layout.splashscreen);

        new Handler().postDelayed(() ->
        {
            //setelah loading maka akan langsung berpindah ke home activity
            Intent home=new Intent(SplashScreen.this, MainActivity.class);
            startActivity(home);
            finish();
        }, waktu_loading);
    }
}
