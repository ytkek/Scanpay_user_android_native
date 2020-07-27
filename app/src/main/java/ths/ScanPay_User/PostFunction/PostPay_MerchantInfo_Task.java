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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import ths.ScanPay_User.ApiUrl;
import ths.ScanPay_User.FindMerchantlist;
import ths.ScanPay_User.MainActivity;
import ths.ScanPay_User.NetworkUtil;
import ths.ScanPay_User.PaymentScanQRActivity;
import ths.ScanPay_User.Verification_LoginPage;

/**
 * Created by Windows on 20/9/2016.
 */
public class PostPay_MerchantInfo_Task extends AsyncTask<String, Integer, String> {

    public Activity context = null;
    public static ArrayList<FindMerchantlist> listMockData;
    RecyclerView list;



    private ProgressDialog loadingDialog;
    ProgressDialog progDailog;

    public PostPay_MerchantInfo_Task(Activity context){
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
        String params1= params[0];
        String params2 = params[1];
        String params3= params[2];
        String params4 = params[3];
        String params5 = params[4];


        if (params3 != null && !params3.isEmpty())
        {

        }
        else
        {
            params3 = "";

        }
        if (params4 != null && !params4.isEmpty())
        {

        }
        else
        {
            params4 = "";
        }
        if (params5 != null && !params5.isEmpty())
        {

        }
        else
        {
            params5 = "";
        }




        String response="";
        String apiUrl = ApiUrl.Domain + ApiUrl.PostPay_MerchantInfo_Api ;
        listMockData = new ArrayList<FindMerchantlist>();
        if (NetworkUtil.isNetworkAvailable(context))
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();

            hashMap.put("type" , params1);
            hashMap.put("merchantid",params2);
            hashMap.put("amount",params3);
            hashMap.put("dynamicqrcode",params4);
            hashMap.put("qrcode", params5);



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


            if(result.equals("Invalid Merchant"))
            {

                PaymentScanQRActivity.payment_layout.setVisibility(View.GONE);
                PaymentScanQRActivity.error_message.setText("INVALID MERCHANT");
                PaymentScanQRActivity.error_message.setVisibility(View.VISIBLE);


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Error #B0034 Invalid Merchant")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new PostApp_Error_Message_Task(context).execute(MainActivity.LoginID,"unsuccessful payment Error #B0034 Invalid Merchant");
                                PaymentScanQRActivity.PaymentScanQRActivityactivity.finish();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }
            else
            {
                if(result.isEmpty())
                {

                }else {


                    String[] arrayString = result.split(",");


                    PaymentScanQRActivity.merchant_name.setText("Transaction with merchant " + arrayString[0]);
                    PaymentScanQRActivity.merchantname = arrayString[0];
                    if (arrayString[1].equals("cashier")) {
                        PaymentScanQRActivity.amount_edit.setEnabled(true);
                    } else if (arrayString[1].equals("pay")) {
                        PaymentScanQRActivity.amount_edit.setEnabled(false);
                    }


                }

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