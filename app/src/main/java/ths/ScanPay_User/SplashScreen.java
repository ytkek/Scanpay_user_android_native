package ths.ScanPay_User;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen  extends AppCompatActivity {
    public boolean indicator=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                indicator=false;
                if(indicator==false)
                {
                    Intent login = new Intent(getApplicationContext(),Login.class);
                    startActivity(login);
                }
                else
                {
                    Intent mainactivity = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(mainactivity);
                }


            }
        }, 3000);
    }


}
