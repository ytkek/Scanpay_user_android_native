package ths.ScanPay_UserV5.GetFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import ths.ScanPay_UserV5.ApiUrl;
import ths.ScanPay_UserV5.Login;
import ths.ScanPay_UserV5.MainActivity;
import ths.ScanPay_UserV5.NetworkUtil;
import ths.ScanPay_UserV5.SplashScreen;

/**
 * Created by Windows on 20/9/2016.
 */
public class GetAndroid_IndicatorTask extends AsyncTask<Void, Integer, String> {

    public Activity context = null;

    RecyclerView list;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public GetAndroid_IndicatorTask(Activity context)
    {
        this.context = context;


    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        progDailog = new ProgressDialog(context);
        progDailog.setMessage("Loading...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);
        progDailog.show();

    }


    @Override
    protected String doInBackground(Void... params)
    {
        String response = null;
        String apiUrl = ApiUrl.Domain + "android-native-user.txt";

        if (NetworkUtil.isNetworkAvailable(context))
        {
             response = NetworkUtil.getStringFromURL(apiUrl,false);
            try
            {


            }
            catch (Exception e)
            {
                    e.printStackTrace();
            }





        }
        else
        {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showDialog();
                }
            });
        }

        return response;
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

        Log.d("wtf",result);
        //SplashScreen.versionresult = result;
        if(result.contains("0"))
        {

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms


                        if(SplashScreen.login.equals("")&&SplashScreen.password.equals(""))
                        {
                            Intent login = new Intent(context,Login.class);
                            context.startActivity(login);
                        }
                        else
                        {
                            // new PostLogin_Validate_AutoLoginID_Task(splashscreen).execute(login,password);
                            Intent mainactivity = new Intent(context,MainActivity.class);
                            mainactivity.putExtra("LoginID",SplashScreen.login);
                            mainactivity.putExtra("Password",SplashScreen.password);
                            context.startActivity(mainactivity);
                        }


                    }
                }, 3000);
            }
            else if (result.contains("1"))
            {
                new GetAndroid_VersionTask(context).execute();

            }
            else if (result.contains("2"))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Please update MyScanPay before proceed.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }




    }

    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Error #B0090 Internet Connection Failed")
                .setCancelable(false)
                .setPositiveButton("Connect to Internet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        context.finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }




}
