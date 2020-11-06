package ths.ScanPay_UserV5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;

import ths.ScanPay_UserV5.Generic.GenericAutologin;
import ths.ScanPay_UserV5.GetFunction.GetAndroid_IndicatorTask;

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
            builder.setMessage("Error #B0061 MyScanPay,will not run on jailbreak/root device")
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
