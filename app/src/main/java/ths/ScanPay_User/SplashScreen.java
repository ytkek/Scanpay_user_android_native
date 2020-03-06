package ths.ScanPay_User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import ths.ScanPay_User.Generic.GenericAutologin;
import ths.ScanPay_User.PostFunction.PostLogin_Validate_AutoLoginID_Task;

public class SplashScreen  extends AppCompatActivity {
    public boolean indicator=false;
    public  String login,password;
    Activity splashscreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        splashscreen=this;
       login= GenericAutologin.getLogin(this);
        password= GenericAutologin.getPassword(this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                indicator=false;
                if(login.equals("")&&password.equals(""))
                {
                    Intent login = new Intent(getApplicationContext(),Login.class);
                    startActivity(login);
                }
                else
                {
                   // new PostLogin_Validate_AutoLoginID_Task(splashscreen).execute(login,password);
                    Intent mainactivity = new Intent(getApplicationContext(),MainActivity.class);
                    mainactivity.putExtra("LoginID",login);
                    startActivity(mainactivity);
                }


            }
        }, 3000);
    }


}
