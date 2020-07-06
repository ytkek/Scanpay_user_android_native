package ths.ScanPay_User.PostFunction;

/**
 * Created by Windows on 1/10/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.FindMerchantlist;
import ths.ScanPay_User.Generic.Generic;
import ths.ScanPay_User.NetworkUtil;
import ths.ScanPay_User.PaymentScanQRActivity;
import ths.ScanPay_User.TopUpScanQRActivity;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostTopup_Save_OTP_Task extends AsyncTask<String, Integer, String> {

    public Activity context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;
    String param1,param2;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostTopup_Save_OTP_Task(Activity context){
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
    protected String doInBackground(String... params)
    {
         param1= params[0];
         param2=params[1];
        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostPay_Save_OTP_Api ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("LoginID", param1);
            hashMap.put("OTP", param2);
            response = NetworkUtil.sendPost(apiUrl,hashMap);
            try{

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
    protected void onPostExecute(final String result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

        if(result.equals("SAVE OTP BACKEND SYSTEM SUCCESS"))
        {
            Generic.SaveOtp(param2,context);
           // Toast.makeText(context,"You Have Save New Otp",Toast.LENGTH_SHORT).show();
            TopUpScanQRActivity.set_new_Otp_layout.setVisibility(View.GONE);
            TopUpScanQRActivity.topup_layout.setVisibility(View.VISIBLE);
        }
        else if(result.equals("SAVE OTP BACKEND SYSTEM FAIL"))
        {
            //Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Error SAVE OTP BACKEND SYSTEM FAIL")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                           dialog.dismiss();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }


    }
    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Error #B0090 Internet Connection Failed ")
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
