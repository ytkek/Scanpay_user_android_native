package ths.ScanPay_User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import ths.ScanPay_User.Generic.GenericAutologin;
import ths.ScanPay_User.GetFunction.GetAndroid_IndicatorTask;
import ths.ScanPay_User.PostFunction.PostLogin_Validate_AutoLoginID_Task;

public class SplashScreen  extends AppCompatActivity {
    public boolean indicator=false;
    public static String login,password;
    public static Activity splashscreen;
    URLConnection feedUrl;
    InputStream in;
    public static String versionresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        splashscreen=this;
       login= GenericAutologin.getLogin(this);
        password= GenericAutologin.getPassword(this);


        if (RootUtil.isDeviceRooted() ==true)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("ScanPay did not run on root device")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();

        }
        else
        {

            new GetAndroid_IndicatorTask(this).execute();


        }
        }



    private static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }





}
