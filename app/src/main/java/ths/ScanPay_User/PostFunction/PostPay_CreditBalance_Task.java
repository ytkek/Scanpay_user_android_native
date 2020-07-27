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
import ths.ScanPay_User.Login;
import ths.ScanPay_User.MainActivity;
import ths.ScanPay_User.NetworkUtil;
import ths.ScanPay_User.PaymentScanQRActivity;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostPay_CreditBalance_Task extends AsyncTask<String, Integer, String> {

    public Activity context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;
    String params1,params2;

    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostPay_CreditBalance_Task(Activity context){
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
        params1= params[0];



        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostPay_CreditBalance_Api ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("LoginID", params1);


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
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        progDailog.dismiss();

        if(result.equals("Don Have Balance"))
        {
                PaymentScanQRActivity.payment_layout.setVisibility(View.GONE);
                PaymentScanQRActivity.error_message.setText("You Don Have Enough Balance To Pay");
                PaymentScanQRActivity.error_message.setVisibility(View.VISIBLE);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Error #B0033 Not Enough Balance")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            new PostApp_Error_Message_Task(context).execute(MainActivity.LoginID,"unsuccessful payment Error #B0033 Not Enough Balance");
                            PaymentScanQRActivity.PaymentScanQRActivityactivity.finish();
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }
        else
        {
            PaymentScanQRActivity.credit_balance = result;
            PaymentScanQRActivity.amount_edit.setText(PaymentScanQRActivity.qr_amount);

            new PostPay_CheckDailyExpLimit_Task(context).execute(MainActivity.LoginID);

        }












        // FindMerchantActivity.mAdapter = new FindMerchantAdapter(context, result);

       // FindMerchantActivity.recyclerView.setAdapter(FindMerchantActivity.mAdapter);

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